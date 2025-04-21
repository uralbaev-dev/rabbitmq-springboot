package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.config.RabbitProducer;
import org.example.domain.request.PersonalCreateRequest;
import org.example.domain.response.ApiResponse;
import org.example.domain.response.PersonalResponse;
import org.example.entity.Personal;
import org.example.exceptions.PersonalNotFoundException;
import org.example.repository.PersonalRepository;
import org.example.service.PersonalService;
import org.springframework.stereotype.Service;

/**
 * @className: PersonalServiceImpl
 * @date: 19.04.2025
 * @author: Uralbaev Diyorbek
 */

@Service
@Log4j2
@RequiredArgsConstructor
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository repository;
    private final RabbitProducer rabbitProducer;

    @Override
    public ApiResponse<PersonalResponse> createPersonal(PersonalCreateRequest request) {
        log.info("create personal");
        try {
            Personal personal = new Personal();
            personal.setFirstName(request.getFirstName());
            personal.setLastName(request.getLastName());
            personal.setPhone(request.getPhone());
            personal.setPosition(request.getPosition());
            repository.save(personal);

            PersonalResponse personalResponse = personal.map2Response();
            rabbitProducer.sendPersonalCreated(personalResponse);

            log.info("create personal success");
            return ApiResponse.success(0, "Successfully saved", personalResponse);
        } catch (Exception e) {
            log.error("Error create personal: {} ", e.getMessage());
            return ApiResponse.error(400, "Error creating personal: " + e.getMessage());
        }
    }

    @Override
    public ApiResponse<PersonalResponse> getPersonal(Long id) {
        log.info("get personal");
        Personal personal = repository.findById(id).orElseThrow(() -> new PersonalNotFoundException("Personal not found with ID: " + id));
        log.info("get personal success");
        return ApiResponse.success(0, "OK", personal.map2Response());
    }
}
