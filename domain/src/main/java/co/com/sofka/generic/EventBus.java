package co.com.sofka.generic;

public interface EventBus {

     void publish(DomainEvent event) ;

     void publishError(Throwable errorEvent);
}
