package org.example.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.domain.request.PersonalCreateRequest;
import org.example.domain.response.ApiResponse;
import org.example.domain.response.PersonalResponse;
import org.example.service.PersonalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @className: PersonalController
 * @date: 18.04.2025
 * @author: Uralbaev Diyorbek
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/personal")
public class PersonalController {

    private final PersonalService personalService;

    @PostMapping
    @RateLimiter(name = "personal-limiter")
    public ResponseEntity<ApiResponse<PersonalResponse>> createPersonal(@RequestBody @Valid PersonalCreateRequest request) {
        ApiResponse<PersonalResponse> response = personalService.createPersonal(request);
        return ResponseEntity.status(response.getCode() == 0 ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping
    @RateLimiter(name = "personal-limiter")
    public ResponseEntity<ApiResponse<PersonalResponse>> getPersonal(@RequestParam(value = "id") Long id) {
        ApiResponse<PersonalResponse> response = personalService.getPersonal(id);
        return ResponseEntity.status(response.getCode() == 0 ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(response);
    }
}
