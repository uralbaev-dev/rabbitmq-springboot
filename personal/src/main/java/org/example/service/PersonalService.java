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

    ApiResponse<PersonalResponse> createPersonal(PersonalCreateRequest request);

    ApiResponse<PersonalResponse> getPersonal(Long id);
}
