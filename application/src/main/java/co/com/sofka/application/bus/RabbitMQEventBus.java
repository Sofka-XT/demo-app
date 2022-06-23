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

import java.net.URI;


@Service
public class RabbitMQEventBus implements EventBus {
    private static final String EXCHANGE = "core-demo";

    private final RabbitTemplate rabbitTemplate;
    private final GsonEventSerializer serializer;
    private final RabbitAdmin rabbitAdmin;


    public RabbitMQEventBus(@Value("${spring.bus.uri}") String uri, GsonEventSerializer serializer) {
        this.rabbitTemplate = new RabbitTemplate(new CachingConnectionFactory(URI.create(uri)));
        this.serializer = serializer;
        this.rabbitAdmin = new RabbitAdmin(this.rabbitTemplate);
    }

    @Override
    public void publish(DomainEvent event) {
        var notificationSerialization =serializer.serialize(event);
        rabbitAdmin.declareExchange(new TopicExchange(EXCHANGE));
        rabbitTemplate.convertAndSend(EXCHANGE, event.getType(), notificationSerialization.getBytes());
    }

    @Override
    public void publishError(Throwable errorEvent) {

    }


}