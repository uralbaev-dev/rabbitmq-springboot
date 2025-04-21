package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

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
@Table(name = "organization")
public class Organization {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @SequenceGenerator(
            name = "OrganizationIdGenerator",
            sequenceName = "organization_id_seq",
            schema = "public",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OrganizationIdGenerator")
    private Long id;

    private Long personalId;
}
