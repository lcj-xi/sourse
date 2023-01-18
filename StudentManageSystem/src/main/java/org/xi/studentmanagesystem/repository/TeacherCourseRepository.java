package org.xi.studentmanagesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xi.studentmanagesystem.entity.TeacherCourseInfo;
import org.xi.studentmanagesystem.entity.pk.TeacherCoursePK;

@Repository
public interface TeacherCourseRepository extends JpaRepository<TeacherCourseInfo, TeacherCoursePK> {


}
