package com.example.EventTrackingSystem.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
@Repository
public class EventAnalysisRepositoryImpl implements EventAnalysisRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String COLLECTION_NAME = "events";

    // 🔹 Find the most common event types
    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> findMostFrequentEvents(int limit) {
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.project("eventType"),  // ✅ Reduce document size before grouping
            Aggregation.group("eventType").count().as("count"),
            Aggregation.sort(Sort.by(Sort.Direction.DESC, "count")),
            Aggregation.limit(limit)
        );
    
        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, COLLECTION_NAME, Map.class);
        return (List<Map<String, Object>>) (List<?>) results.getMappedResults();
    }
    

    // 🔹 Identify users with the most interactions
    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> findMostActiveUsers(int limit) {
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.project("userId"),
            Aggregation.group("userId").count().as("eventCount"),
            Aggregation.sort(Sort.by(Sort.Direction.DESC, "eventCount")),
            Aggregation.limit(limit)
        );
    
        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, COLLECTION_NAME, Map.class);
        return (List<Map<String, Object>>) (List<?>) results.getMappedResults();
    }
    

    // 🔹 Show busiest hours of the day
    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> findPeakActivityHours() {
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.project().andExpression("hour($timeStamp)").as("hour"),
            Aggregation.group("hour").count().as("count"),
            Aggregation.sort(Sort.by(Sort.Direction.DESC, "count")),
            Aggregation.project()
                .and("_id").as("hour")
                .andInclude("count")
                .andExclude("_id")
            );


        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, COLLECTION_NAME, Map.class);
        return (List<Map<String, Object>>) (List<?>) results.getMappedResults(); // ✅ Explicitly cast
    }

    // 🔹 Show event trends over time (daily trends)
    @SuppressWarnings("unchecked")
    @Override
   public List<Map<String, Object>> findEventTrendsOverTime() {
    Aggregation aggregation = Aggregation.newAggregation(
        Aggregation.project().andExpression("{ $dateTrunc: { date: '$timeStamp', unit: 'day' } }").as("date"), // ✅ Faster Date Aggregation
        Aggregation.group("date").count().as("eventCount"),
        Aggregation.sort(Sort.by(Sort.Direction.ASC, "_id"))
    );

    AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, COLLECTION_NAME, Map.class);
    return (List<Map<String, Object>>) (List<?>) results.getMappedResults();
}


    // 🔹 Find most engaged users (Users with 10+ interactions)
    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> findMostEngagedUsers() {
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.project("userId"),
            Aggregation.group("userId").count().as("eventCount"),
            Aggregation.match(Criteria.where("eventCount").gte(10)),  // ✅ Faster filtering
            Aggregation.sort(Sort.by(Sort.Direction.DESC, "eventCount"))
        );
    
        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, COLLECTION_NAME, Map.class);
        return (List<Map<String, Object>>) (List<?>) results.getMappedResults();
    }
    
}
