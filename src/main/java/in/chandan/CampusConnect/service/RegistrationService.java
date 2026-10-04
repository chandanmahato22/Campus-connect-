package in.chandan.CampusConnect.service;

import in.chandan.CampusConnect.dto.RegistrationReqDto;
import in.chandan.CampusConnect.entity.Event;
import in.chandan.CampusConnect.entity.Registration;
import in.chandan.CampusConnect.exceptions.BadRequestException;
import in.chandan.CampusConnect.exceptions.ResourceNotFoundException;
import in.chandan.CampusConnect.repository.EventRepository;
import in.chandan.CampusConnect.repository.RegistrationRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private EventRepository eventRepository;

    public RegistrationService(RegistrationRepository registrationRepository,
                               EventRepository eventRepository){
        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
    }


    public String register(RegistrationReqDto req,
                           Authentication authentication){

        long uid = Long.parseLong(authentication.getName());
        Registration reg = new Registration();

        Event event = eventRepository.findByName(req.getEvent_name()).orElseThrow(
                () ->  new
                ResourceNotFoundException("event not found with event name :" + req.getEvent_name()));
        int remaining_seat = event.getSeats() - registrationRepository.countByEvent_id(event.getId());
        if(remaining_seat <= 0 ){
            throw new BadRequestException("you can't register for the event its already full !");
        }
        LocalDate endDate = event.getEnd();
        LocalDate today = LocalDate.now();
        if(endDate.isBefore(today)){
            throw new BadRequestException("You can't register for this " +
                    "event the you are past the registration date");
        }
        reg.setEvent(event);
        reg.setUid(uid);
        registrationRepository.save(reg);
        return "Registration completed for event" + reg.getEvent().getName();
    }

    @CacheEvict(value = "registrations",key = "#id")
    public void deleteRegistration(Authentication authentication) {
        long uid = Long.parseLong(authentication.getName());
        Registration registration = registrationRepository.findByUid(uid).orElseThrow(
                () -> new ResourceNotFoundException("registration not found ")
        );
        registrationRepository.delete(registration);
    }

    @Cacheable(value = "registrations",key = "#id")
    public Registration getRegistration(Long id) {
        Registration reg = registrationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Registration not found with id: " + id)
        );
            return reg;
    }

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }
}
