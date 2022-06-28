package org.example.application.handles;

import co.com.sofka.generic.DomainEvent;
import co.com.sofka.generic.EventBus;
import co.com.sofka.generic.EventStoreRepository;
import co.com.sofka.generic.StoredEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HandleBase {
    @Autowired
    private EventStoreRepository repository;
    @Autowired
    private StoredEvent.EventSerializer eventSerializer;
    @Autowired
    private EventBus eventBus;

    public void emit(List<DomainEvent> events){
        events.forEach(domainEvent -> {
            var stored = StoredEvent.wrapEvent(domainEvent, eventSerializer);
            repository.saveEvent("program", domainEvent.getAggregateId(), stored);
            eventBus.publish(domainEvent);
        });
    }
}
