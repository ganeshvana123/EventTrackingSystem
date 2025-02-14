package com.example.EventTrackingSystem.Service;

import com.example.EventTrackingSystem.Model.Event;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    Event saveEvent(Event event);

    List<Event> getAllEvents();

    List<Event> getEventsByUserId(String userId);

    List<Event> getEventsByType(String eventType);

    List<Event> getEventsByTimeRange(LocalDateTime start, LocalDateTime end);

    List<Event> saveEvents(List<Event> events);
}
