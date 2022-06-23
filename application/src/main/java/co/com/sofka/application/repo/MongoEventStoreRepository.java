package co.com.sofka.application.repo;

import co.com.sofka.application.repo.DocumentStoredEvent;
import co.com.sofka.application.repo.StoreEventRepository;
import co.com.sofka.generic.DomainEvent;
import co.com.sofka.generic.EventStoreRepository;
import co.com.sofka.generic.StoredEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MongoEventStoreRepository implements EventStoreRepository {

    @Autowired
    private StoreEventRepository repository;

    @Autowired
    private StoredEvent.EventSerializer eventSerializer;

    @Override
    public List<DomainEvent> getEventsBy(String aggregateName, String aggregateRootId) {
        return repository.findByAggregateRootId(aggregateRootId)
                .stream()
                .map(storeEvent -> storeEvent.deserializeEvent(eventSerializer))
                .collect(Collectors.toList());
    }

    @Override
    public void saveEvent(String aggregateName, String aggregateRootId, StoredEvent storedEvent) {
        var document = new DocumentStoredEvent();
        document.setEventBody(storedEvent.getEventBody());
        document.setOccurredOn(storedEvent.getOccurredOn());
        document.setTypeName(storedEvent.getTypeName());
        repository.save(document);
    }
}
