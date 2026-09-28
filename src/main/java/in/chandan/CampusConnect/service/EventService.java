package in.chandan.CampusConnect.service;

import in.chandan.CampusConnect.dto.EventDto;
import in.chandan.CampusConnect.entity.Event;
import in.chandan.CampusConnect.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EventService {

    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public String createEvent(EventDto eventDto){
        LocalDate start = LocalDate.parse(eventDto.getStart());
        LocalDate end = LocalDate.parse(eventDto.getEnd());

        Event event = new Event();
        event.setStart(start);
        event.setEnd(end);
        event.setSeats(eventDto.getSeats());
        event.setName(eventDto.getName());
        Event eventResp = eventRepository.save(event);
        String msg = eventResp.getName() + "Event created successfully with " +
                eventResp.getSeats() + " number of seats";
        return msg;
    }
}
