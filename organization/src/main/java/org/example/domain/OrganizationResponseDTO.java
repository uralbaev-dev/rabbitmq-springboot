package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @className: OrganizationResponseDTO
 * @date: 21.04.2025
 * @author: Uralbaev Diyorbek
 */

@Getter
@AllArgsConstructor
public class OrganizationResponseDTO {
    private Long personalId;
    private Long organizationId;
}
