package com.example.EventTrackingSystem.Service;

import java.util.List;
import java.util.Map;

public interface EventAnalysisService {

    List<Map<String, Object>> findMostFrequentEvents(int limit);

    List<Map<String, Object>> findMostActiveUsers(int limit);

    List<Map<String, Object>> findPeakActivityHours();

    List<Map<String, Object>> findEventTrendsOverTime();

    List<Map<String, Object>> findMostEngagedUsers();
}
