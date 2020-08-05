package com.example.user.service;

import com.example.user.util.LongIdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class HelloSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final static String orderExchange = "orderExchange";

    private final static String orderQueue = "orderQueue";

    private final static String orderRoutingKey = "orderRoutingKey";

    private final static Logger LOGGER = LoggerFactory.getLogger(HelloSender.class);

    public void send() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //24小时制
        String orderId = LongIdUtil.getLongId();
        LOGGER.info("orderId : {}", orderId);
        //简单对列的情况下routingKey即为Q名
        rabbitTemplate.convertAndSend(orderExchange, orderRoutingKey, orderId,messagePostProcessor());

        //this.rabbitTemplate.convertAndSend("queueMessage", context);
    }

    private MessagePostProcessor messagePostProcessor(){
        return  new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //设置有效期30分钟
                //message.getMessageProperties().setExpiration("1800000");
                message.getMessageProperties().setExpiration("60000");
                return message;
            }
        };
    }


}
