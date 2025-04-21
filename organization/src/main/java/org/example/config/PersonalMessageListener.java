package org.example.config;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class PersonalMessageListener {

    private final OrganizationRepository organizationRepository;

    private final OrganizationMessageSender messageSender;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receivePersonal(PersonalDTO dto) {
        System.out.println("Message received: ");

        // Save to DB
        Organization organization = new Organization();
        organization.setPersonalId(dto.getPersonalId());

        Organization saved = organizationRepository.save(organization);

        OrganizationResponseDTO responseDTO = new OrganizationResponseDTO(dto.getPersonalId(), saved.getId());
        messageSender.sendOrganizationCreated(responseDTO);
    }
}
