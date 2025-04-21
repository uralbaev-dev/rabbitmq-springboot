package org.example.config;

import lombok.RequiredArgsConstructor;
import org.example.domain.response.PersonalResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @className: RabbitProducer
 * @date: 18.04.2025
 * @author: Uralbaev Diyorbek
 */

@Component
@RequiredArgsConstructor
public class RabbitProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendPersonalCreated(PersonalResponse message) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.ROUTING_KEY,
                message
        );
    }
}
