package org.xi.studentmanagesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xi.studentmanagesystem.entity.Course;

public interface CourseRepository extends JpaRepository<Course,Integer> {

}
