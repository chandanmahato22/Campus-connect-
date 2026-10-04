package in.chandan.CampusConnect.service;

import in.chandan.CampusConnect.dto.ClubRespDto;
import in.chandan.CampusConnect.dto.EventDto;
import in.chandan.CampusConnect.entity.Club;
import in.chandan.CampusConnect.entity.Event;
import in.chandan.CampusConnect.exceptions.BadRequestException;
import in.chandan.CampusConnect.exceptions.ResourceNotFoundException;
import in.chandan.CampusConnect.repository.EventRepository;
import in.chandan.CampusConnect.repository.RegistrationRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    private EventRepository eventRepository;
    private RegistrationRepository registrationRepository;
    public EventService(EventRepository eventRepository,
                        RegistrationRepository registrationRepository){
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
    }

    @Transactional
    public String createEvent(EventDto eventDto){
        LocalDate start = LocalDate.parse(eventDto.getStart());
        LocalDate end = LocalDate.parse(eventDto.getEnd());

        if(end.isBefore(start)){
            throw new BadRequestException("end date must not be before start date");
        }

        Event event = new Event();
        event.setStart(start);
        event.setEnd(end);
        event.setSeats(eventDto.getSeats());
        event.setName(eventDto.getName());
        Event eventResp = eventRepository.save(event);

        return eventResp.getName() + "Event created successfully with " +
                eventResp.getSeats() + " number of seats";

    }


    public List<Event> getEvents(){
        return eventRepository.findAll();
    }

    @Cacheable(value = "events", key = "#id")
    public Event getEvent(long id) {
        Event event =   eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("event not found with evend id: " + id)
        );

        int number = registrationRepository.countByEvent_id(id);
         event.setSeats(event.getSeats() - number);
        return event;
    }
}
