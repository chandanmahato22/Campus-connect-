package in.chandan.CampusConnect.controller;

import in.chandan.CampusConnect.dto.EventDto;
import in.chandan.CampusConnect.entity.Club;
import in.chandan.CampusConnect.entity.Event;
import in.chandan.CampusConnect.service.EventService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }
    @PostMapping("/createEvent")
    public ResponseEntity<String> createEvent(@RequestBody EventDto eventReq){
        System.out.println("entered evnetController");
        String msg = eventService.createEvent(eventReq);
        return ResponseEntity.ok(msg);
    }
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getEvents(){
        List<Event> events = eventService.getEvents();
        return ResponseEntity.ok(events);
    }
}
