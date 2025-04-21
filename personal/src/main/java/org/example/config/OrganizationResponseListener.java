package org.example.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.domain.response.OrganizationResponse;
import org.example.entity.Personal;
import org.example.exceptions.PersonalNotFoundException;
import org.example.repository.PersonalRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @className: OrganizationResponseListener
 * @date: 21.04.2025
 * @author: Uralbaev Diyorbek
 */

@Component
@Log4j2
@RequiredArgsConstructor
public class OrganizationResponseListener {

    private final PersonalRepository repository;

    @RabbitListener(queues = RabbitConfig.PERSONAL_RESPONSE_QUEUE)
    public void receiveOrganizationId(OrganizationResponse dto) {
        log.info("Received organizationId for personalId: {}", dto.getPersonalId());
        Personal personal = repository.findById(dto.getPersonalId()).orElseThrow(() -> new PersonalNotFoundException("Personal not found with ID: " + dto.getPersonalId()));
        personal.setOrganizationId(dto.getOrganizationId());
        log.info("Updating personal organizationId: {}", personal.getOrganizationId());
        repository.save(personal);
    }
}
