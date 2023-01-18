package org.xi.studentmanagesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xi.studentmanagesystem.entity.SC;
import org.xi.studentmanagesystem.entity.pk.StudentCoursePK;

public interface SCRepository extends JpaRepository<SC, StudentCoursePK> {
}
