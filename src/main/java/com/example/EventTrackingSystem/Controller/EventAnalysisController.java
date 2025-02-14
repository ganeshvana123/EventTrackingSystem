package com.example.EventTrackingSystem.Controller;

import com.example.EventTrackingSystem.Service.EventAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class EventAnalysisController {

    @Autowired
    private EventAnalysisService eventAnalysisService;

    // 🔹 Find the most common event types
    @GetMapping("/most-frequent-events")
    public List<Map<String, Object>> getMostFrequentEvents(@RequestParam(defaultValue = "10") int limit) {
        return eventAnalysisService.findMostFrequentEvents(limit);
    }

    // 🔹 Identify users with the most interactions
    @GetMapping("/most-active-users")
    public List<Map<String, Object>> getMostActiveUsers(@RequestParam(defaultValue = "10") int limit) {
        return eventAnalysisService.findMostActiveUsers(limit);
    }

    // 🔹 Show busiest hours of the day
    @GetMapping("/peak-activity-hours")
    public List<Map<String, Object>> getPeakActivityHours() {
        return eventAnalysisService.findPeakActivityHours();
    }

    // 🔹 Show event trends over time
    @GetMapping("/event-trends")
    public List<Map<String, Object>> getEventTrendsOverTime() {
        return eventAnalysisService.findEventTrendsOverTime();
    }

    // 🔹 Find most engaged users (Users with 10+ interactions)
    @GetMapping("/most-engaged-users")
    public List<Map<String, Object>> getMostEngagedUsers() {
        return eventAnalysisService.findMostEngagedUsers();
    }
}
