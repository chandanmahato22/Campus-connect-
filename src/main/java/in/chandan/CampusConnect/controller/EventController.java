package in.chandan.CampusConnect.controller;

import in.chandan.CampusConnect.dto.ClubRespDto;
import in.chandan.CampusConnect.dto.EventDto;
import in.chandan.CampusConnect.entity.Club;
import in.chandan.CampusConnect.entity.Event;
import in.chandan.CampusConnect.service.EventService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    //create a new event
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createEvent(@RequestBody EventDto eventReq){
        System.out.println("entered evnetController");
        String msg = eventService.createEvent(eventReq);
        return ResponseEntity.ok(msg);
    }

    //get all the events
    @GetMapping
    public ResponseEntity<List<Event>> getEvents(){
        List<Event> events = eventService.getEvents();
        return ResponseEntity.ok(events);
    }

    //get particulat event

    @GetMapping("/{id}")
    public ResponseEntity<Event> getClub(@PathVariable long id){
        Event resp = eventService.getEvent(id);
        return ResponseEntity.ok(resp);
    }

}
