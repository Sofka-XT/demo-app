package co.com.sofka.application.bus;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQConsumer  {

    @Autowired
    public RabbitMQConsumer() {

    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "program.handles", durable = "true"),
            exchange = @Exchange(value = "scoreextraction", type = "topic"),
            key = "sofkau.program.#"
    ))
    public void recievedMessage(Message<String> message) {
        //message
    }



}