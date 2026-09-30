package in.chandan.CampusConnect.service;

import in.chandan.CampusConnect.dto.RegistrationReqDto;
import in.chandan.CampusConnect.entity.Event;
import in.chandan.CampusConnect.entity.Registration;
import in.chandan.CampusConnect.exceptions.BadRequestException;
import in.chandan.CampusConnect.exceptions.ResourceNotFoundException;
import in.chandan.CampusConnect.repository.EventRepository;
import in.chandan.CampusConnect.repository.RegistrationRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

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

    public void deleteRegistration(Authentication authentication) {
        long uid = Long.parseLong(authentication.getName());
        Registration registration = registrationRepository.findByUid(uid).orElseThrow(
                () -> new ResourceNotFoundException("registration not found ")
        );
        registrationRepository.delete(registration);
    }
}
