package com.example.user.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue queueMessage() {
        return new Queue("queueMessage");
    }

    /**
     * 声明一个Topic类型的交换机
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("mybootexchange");
    }

    /**
     * 绑定Q到交换机,并且指定routingKey
     * @return
     */
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(queueMessage()).to(exchange()).with("topic.message");
    }



    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }


    private final static String dlxExchange = "dlxExchange";

    private final static String dlxQueue = "dlxQueue";

    private final static String dlxRoutingKey = "dlxRoutingKey";

    private final static String orderExchange = "orderExchange";

    private final static String orderQueue = "orderQueue";

    private final static String orderRoutingKey = "orderRoutingKey";
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(dlxExchange);
    }
    /**
     * 声明死信队列
     */
    @Bean
    public Queue dlxQueue() {
        return new Queue(dlxQueue);
    }
    /**
     * 声明订单业务交换机
     */
    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(orderExchange);
    }
    /**
     * 声明订单队列 核心操作一
     */
    @Bean
    public Queue orderQueue() {
        Map<String, Object> arguments = new HashMap<>(2);
        // 绑定我们的死信交换机
        arguments.put("x-dead-letter-exchange", dlxExchange);
        // 绑定我们的路由key
        arguments.put("x-dead-letter-routing-key", dlxRoutingKey);
        return new Queue(orderQueue, true, false, false, arguments);
    }
    /**
     * 绑定订单队列到订单交换机
     */
    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with(orderRoutingKey);
    }
    /**
     * 绑定死信队列到死信交换机
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange()).with(dlxRoutingKey);
    }



}