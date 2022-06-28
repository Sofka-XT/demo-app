package org.example.generic;


import java.util.List;


public interface DomainEventRepository {

    List<DomainEvent> getEventsBy(String aggregateRootId);

    List<DomainEvent> getEventsBy(String aggregate, String aggregateRootId);
}