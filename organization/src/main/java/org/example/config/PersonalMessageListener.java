package org.example.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.domain.OrganizationResponseDTO;
import org.example.domain.PersonalDTO;
import org.example.entity.Organization;
import org.example.repository.OrganizationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @className: PersonalMessageListener
 * @date: 19.04.2025
 * @author: Uralbaev Diyorbek
 */

@Component
@Log4j2
@RequiredArgsConstructor
public class PersonalMessageListener {

    private final OrganizationRepository organizationRepository;

    private final OrganizationMessageSender messageSender;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receivePersonal(PersonalDTO dto) {
        log.info("Message received: ");

        Organization organization = new Organization();
        organization.setPersonalId(dto.getPersonalId());

        // Save to DB
        Organization saved = organizationRepository.save(organization);

        // send personalId and organizationId
        OrganizationResponseDTO responseDTO = new OrganizationResponseDTO(dto.getPersonalId(), saved.getId());
        log.info("Success sending personal message");
        messageSender.sendOrganizationCreated(responseDTO);
    }
}
