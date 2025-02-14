package com.example.EventTrackingSystem.Controller;

import com.example.EventTrackingSystem.Model.Event;
import com.example.EventTrackingSystem.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    
    //  Save an event
     @PostMapping("/save")
    public ResponseEntity<Event> saveEvent(@RequestBody Event event) {
        Event savedEvent = eventService.saveEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }
    @PostMapping("/saveAll")
    public ResponseEntity<List<Event>> saveEvents(@RequestBody List<Event> events) {
        List<Event> savedEvents = eventService.saveEvents(events);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvents);
    }

    //  Get all events
    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    //  Get events by user ID
    @GetMapping("/user/{userId}")
    public List<Event> getEventsByUserId(@PathVariable String userId) {
        return eventService.getEventsByUserId(userId);
    }

    //  Get events by event type
    @GetMapping("/type/{eventType}")
    public List<Event> getEventsByType(@PathVariable String eventType) {
        return eventService.getEventsByType(eventType);
    }

    //  Get events within a time range
    @GetMapping("/byTimeRange")
    public ResponseEntity<List<Event>> getEventsByTimeRange(
        @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
        @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
    
        List<Event> events = eventService.getEventsByTimeRange(startTime, endTime);
        return ResponseEntity.ok(events);
    }
}
