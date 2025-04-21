package org.example.config;

import lombok.RequiredArgsConstructor;
import org.example.domain.OrganizationResponseDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

/**
 * @className: OrganizationMessageSender
 * @date: 21.04.2025
 * @author: Uralbaev Diyorbek
 */

@Component
@RequiredArgsConstructor
public class OrganizationMessageSender {

    private final AmqpTemplate amqpTemplate;

    public void sendOrganizationCreated(OrganizationResponseDTO dto) {
        amqpTemplate.convertAndSend("personal.response.queue", dto);
    }
}
