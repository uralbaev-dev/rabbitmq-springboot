package org.example.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: RabbitConfig
 * @date: 18.04.2025
 * @author: Uralbaev Diyorbek
 */

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE = "personal.to.organization";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    public Queue personalResponseQueue() {
        return new Queue("personal.response.queue");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
