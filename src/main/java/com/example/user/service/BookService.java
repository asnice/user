package com.example.user.service;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class BookService {

    private final static Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    @RabbitListener(queues = "queueMessage")
    public void receive(@Payload String message,
                        @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                        Channel channel) throws IOException {
        System.out.println("Message:"+message);
        channel.basicAck(deliveryTag, false);

    }

    @RabbitListener(queues = "dlxQueue")
    public void dlxQueue(@Payload String message,
                        @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                        Channel channel) throws IOException {
        LOGGER.info("Message:{}", message);
        channel.basicAck(deliveryTag, false);

    }

}
