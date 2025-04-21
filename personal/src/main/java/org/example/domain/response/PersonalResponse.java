package org.example.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @className: PersonalResponse
 * @date: 19.04.2025
 * @author: Uralbaev Diyorbek
 */

@Builder
@Getter
@Setter
public class PersonalResponse {

    private Long personalId;
    private String firstName;
    private String lastName;
    private String phone;
    private String position;
    private Long organizationId;
}

