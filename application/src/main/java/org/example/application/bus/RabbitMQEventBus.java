package org.example.application.bus;

import org.example.application.repo.GsonEventSerializer;
import co.com.sofka.generic.DomainEvent;

import co.com.sofka.generic.EventBus;
import org.example.application.ApplicationConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQEventBus implements EventBus {

    private final RabbitTemplate rabbitTemplate;
    private final GsonEventSerializer serializer;

    public RabbitMQEventBus(RabbitTemplate rabbitTemplate,  GsonEventSerializer serializer) {
        this.serializer = serializer;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(DomainEvent event) {
        var notification = new Notification(
                event.getClass().getTypeName(),
                serializer.serialize(event)
        );
        rabbitTemplate.convertAndSend(ApplicationConfig.EXCHANGE, event.getType(), notification.serialize().getBytes());
    }

    @Override
    public void publishError(Throwable errorEvent) {

    }


}