package com.example.EventTrackingSystem.Repository;

import java.util.List;
import java.util.Map;

public interface EventAnalysisRepository {
     List<Map<String, Object>> findMostFrequentEvents(int limit);

    // Identifies users with the most interactions
    List<Map<String, Object>> findMostActiveUsers(int limit);

    // Shows the busiest hours of the day
    List<Map<String, Object>> findPeakActivityHours();

    // Displays daily event trends
    List<Map<String, Object>> findEventTrendsOverTime();

    // Identifies users with 10+ interactions
    List<Map<String, Object>> findMostEngagedUsers();
}
