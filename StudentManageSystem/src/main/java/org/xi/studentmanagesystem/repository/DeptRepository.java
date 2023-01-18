package org.xi.studentmanagesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xi.studentmanagesystem.entity.Dept;

public interface DeptRepository extends JpaRepository<Dept,Integer> {
}
