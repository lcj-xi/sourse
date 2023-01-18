package org.xi.studentmanagesystem.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xi.studentmanagesystem.entity.Teacher;
import org.xi.studentmanagesystem.entity.TeacherCourseInfo;

import java.util.List;

@Service
public interface TeacherService {

    Teacher getById(Integer tid);

    Teacher getByTidAndTpassword(Integer tid, String tpassword);

    Page<Teacher> getAll(Example<Teacher> example, Pageable pageable);

    Teacher insert(Teacher teacher);

    Teacher update(Teacher teacher);

    Teacher delete(Teacher teacher);


}
