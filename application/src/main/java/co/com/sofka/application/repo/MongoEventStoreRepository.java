package co.com.sofka.application.repo;


import co.com.sofka.generic.DomainEvent;
import co.com.sofka.generic.EventStoreRepository;
import co.com.sofka.generic.StoredEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongoEventStoreRepository implements EventStoreRepository {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Autowired
    private StoredEvent.EventSerializer eventSerializer;

    @Override
    public List<DomainEvent> getEventsBy(String aggregateName, String aggregateRootId) {
        var query = new Query(Criteria.where("aggregateRootId").is(aggregateRootId));
        return mongoTemplate.find(query, DocumentEventStored.class, aggregateName)
                .map((documentEventStored -> documentEventStored.getStoredEvent().deserializeEvent(eventSerializer)))
                .collectList()
                .block();
    }

    @Override
    public void saveEvent(String aggregateName, String aggregateRootId, StoredEvent storedEvent) {
        var eventStored = new DocumentEventStored();
        eventStored.setAggregateRootId(aggregateRootId);
        eventStored.setStoredEvent(storedEvent);
        mongoTemplate.save(eventStored, aggregateName);
    }
}
