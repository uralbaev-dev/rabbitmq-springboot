package org.example.service;

import org.example.domain.request.PersonalCreateRequest;
import org.example.domain.response.ApiResponse;
import org.example.domain.response.PersonalResponse;

/**
 * @className: PersonalService
 * @date: 18.04.2025
 * @author: Uralbaev Diyorbek
 */

public interface PersonalService {

    /**
     * Create personal
     * @param request firstName, lastName, phone, position
     * @return personal data
     */
    ApiResponse<PersonalResponse> createPersonal(PersonalCreateRequest request);

    /**
     * Get Personal
     * @param id personalId
     * @return personal data
     */
    ApiResponse<PersonalResponse> getPersonal(Long id);
}
