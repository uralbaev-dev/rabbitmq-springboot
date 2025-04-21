package org.example.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @className: OrganizationResponse
 * @date: 21.04.2025
 * @author: Uralbaev Diyorbek
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationResponse implements Serializable {

    private Long personalId;

    private Long organizationId;
}
