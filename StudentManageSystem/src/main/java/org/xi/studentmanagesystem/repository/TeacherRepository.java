package org.xi.studentmanagesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xi.studentmanagesystem.entity.TeacherCourseInfo;
import org.xi.studentmanagesystem.entity.Teacher;

import java.util.List;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Teacher findByTidAndTpassword(Integer tid ,String tpassword);
}

