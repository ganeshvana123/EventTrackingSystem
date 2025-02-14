package com.example.EventTrackingSystem.Model;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "events")
@CompoundIndex(def = "{'userId':1,'eventType':1,'timeStamp':-1}")
public class Event {
    @Id
    private String id;

    @Indexed
    private String eventType;

    @Indexed
    private String userId;

    @Indexed
    private LocalDateTime timeStamp;

    private Map<String, Object> metadata;


}
