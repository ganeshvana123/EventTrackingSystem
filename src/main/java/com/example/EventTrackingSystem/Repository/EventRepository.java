package com.example.EventTrackingSystem.Repository;

import com.example.EventTrackingSystem.Model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
    
    // Find events by event type
    List<Event> findByEventType(String eventType);

    // Find events by user ID
    List<Event> findByUserId(String userId);

    // Find events within a specific time range
    List<Event> findByTimeStampBetween(LocalDateTime start, LocalDateTime end);
}
