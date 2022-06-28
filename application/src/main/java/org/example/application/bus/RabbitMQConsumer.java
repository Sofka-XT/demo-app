package org.example.application.bus;

import org.example.application.repo.GsonEventSerializer;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQConsumer  {
    private final GsonEventSerializer serializer;
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    public RabbitMQConsumer(GsonEventSerializer serializer) {
        this.serializer = serializer;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "program.handles", durable = "true"),
            exchange = @Exchange(value = "scoreextraction", type = "topic"),
            key = "sofkau.program.#"
    ))
    public void recievedMessage(Message<String> message) {
        var notification = Notification.from(message.getPayload());
        try {
            var event = serializer.deserialize(notification.getBody(), Class.forName(notification.getType()));
            publisher.publishEvent(event);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }





}