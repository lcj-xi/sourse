package org.xi.studentmanagesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xi.studentmanagesystem.entity.StudentCourseInfo;
import org.xi.studentmanagesystem.entity.pk.StudentCoursePK;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourseInfo, StudentCoursePK> {


}
