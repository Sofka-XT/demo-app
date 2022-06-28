package org.example.application.repo;


import co.com.sofka.generic.DomainEvent;
import co.com.sofka.generic.EventStoreRepository;
import co.com.sofka.generic.StoredEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MongoEventStoreRepository implements EventStoreRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private StoredEvent.EventSerializer eventSerializer;

    @Override
    public List<DomainEvent> getEventsBy(String aggregateName, String aggregateRootId) {
        var query = new Query(Criteria.where("aggregateRootId").is(aggregateRootId));
        return mongoTemplate.find(query, DocumentEventStored.class, aggregateName)
                .stream().map((documentEventStored -> documentEventStored.getStoredEvent().deserializeEvent(eventSerializer)))
                .collect(Collectors.toList());
    }

    @Override
    public void saveEvent(String aggregateName, String aggregateRootId, StoredEvent storedEvent) {
        var eventStored = new DocumentEventStored();
        eventStored.setAggregateRootId(aggregateRootId);
        eventStored.setStoredEvent(storedEvent);
        mongoTemplate.save(eventStored, aggregateName);
    }
}
