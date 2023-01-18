package org.xi.studentmanagesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xi.studentmanagesystem.entity.Professional;

public interface ProfessionalRepository extends JpaRepository<Professional,Integer> {
}
