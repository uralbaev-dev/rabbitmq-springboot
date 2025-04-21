package org.example.domain.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @className: PersonalDto
 * @date: 18.04.2025
 * @author: Uralbaev Diyorbek
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalCreateRequest {

    @NotNull(message = "firstName can not be null")
    private String firstName;

    @NotNull(message = "lastName can not be null")
    private String lastName;

    @NotNull(message = "phoneNumber can not be null")
    @Pattern(regexp = "^998[0-9]{9}$", message = "Phone number must be in format 998XXXXXXXXX")
    private String phone;

    @NotNull(message = "position can not be null")
    private String position;
}

