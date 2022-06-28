package org.example.generic;

public interface EventBus {

     void publish(DomainEvent event) ;

     void publishError(Throwable errorEvent);
}
