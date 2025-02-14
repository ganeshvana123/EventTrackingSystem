package com.example.EventTrackingSystem.Service;

import com.example.EventTrackingSystem.Repository.EventAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EventAnalysisServiceImpl implements EventAnalysisService {

    @Autowired
    private EventAnalysisRepository eventAnalysisRepository;

    @Override
    public List<Map<String, Object>> findMostFrequentEvents(int limit) {
        return eventAnalysisRepository.findMostFrequentEvents(limit);
    }

    @Override
    public List<Map<String, Object>> findMostActiveUsers(int limit) {
        return eventAnalysisRepository.findMostActiveUsers(limit);
    }

    @Override
    public List<Map<String, Object>> findPeakActivityHours() {
        return eventAnalysisRepository.findPeakActivityHours();
    }

    @Override
    public List<Map<String, Object>> findEventTrendsOverTime() {
        return eventAnalysisRepository.findEventTrendsOverTime();
    }

    @Override
    public List<Map<String, Object>> findMostEngagedUsers() {
        return eventAnalysisRepository.findMostEngagedUsers();
    }
}
