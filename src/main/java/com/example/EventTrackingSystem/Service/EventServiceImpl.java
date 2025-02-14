package com.example.EventTrackingSystem.Service;

import com.example.EventTrackingSystem.Model.Event;
import com.example.EventTrackingSystem.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event saveEvent(Event event) {
        // Automatically set the timestamp to the current time
        event.setTimeStamp(LocalDateTime.now()); 
        return eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getEventsByUserId(String userId) {
        return eventRepository.findByUserId(userId);
    }

    @Override
    public List<Event> getEventsByType(String eventType) {
        return eventRepository.findByEventType(eventType);
    }

    @Override
    public List<Event> getEventsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return eventRepository.findByTimeStampBetween(start, end);
    }
    @Override
    public List<Event> saveEvents(List<Event> events) {
        return eventRepository.saveAll(events);
    }
    
}
