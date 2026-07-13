package com.example.demo.infrastructure.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "subscription.billing.queue";

    @Bean
    public TopicExchange saasExchange(){
        return new TopicExchange(RabbitMQEventAdapter.EXCHANGE_NAME);
    }

    @Bean
    public Queue billingQueue(){
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Binding binding(Queue billingQueue, TopicExchange saasExchange){
        return BindingBuilder
                .bind(billingQueue)
                .to(saasExchange)
                .with(RabbitMQEventAdapter.ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
