package co.com.sofka.application.bus;

import co.com.sofka.application.repo.GsonEventSerializer;
import co.com.sofka.generic.DomainEvent;

import co.com.sofka.generic.EventBus;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import static co.com.sofka.application.ApplicationConfig.EXCHANGE;


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
        rabbitTemplate.convertAndSend(EXCHANGE, event.getType(), notification.serialize().getBytes());
    }

    @Override
    public void publishError(Throwable errorEvent) {

    }


}