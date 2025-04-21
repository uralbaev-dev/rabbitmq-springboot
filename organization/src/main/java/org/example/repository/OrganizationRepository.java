package org.example.repository;

import org.example.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className: PersonalRepository
 * @date: 18.04.2025
 * @author: Uralbaev Diyorbek
 */

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
