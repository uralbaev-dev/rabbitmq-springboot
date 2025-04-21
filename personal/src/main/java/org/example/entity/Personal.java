package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.domain.response.PersonalResponse;

import java.time.LocalDate;

/**
 * @className: Personal
 * @date: 18.04.2025
 * @author: Uralbaev Diyorbek
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personals")
public class Personal {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @SequenceGenerator(
            name = "personalIdGenerator",
            sequenceName = "personal_id_seq",
            schema = "public",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personalIdGenerator")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "position")
    private String position;

    @Column(name = "organization_id")
    private Long organizationId;

    public PersonalResponse map2Response() {
        return PersonalResponse.builder()
                .personalId(this.getId())
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .phone(this.getPhone())
                .position(this.getPosition())
                .organizationId(this.getOrganizationId() != null ? this.getOrganizationId() : null)
                .build();
    }
}
