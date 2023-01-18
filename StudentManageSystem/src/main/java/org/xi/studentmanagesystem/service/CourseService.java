package org.xi.studentmanagesystem.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xi.studentmanagesystem.entity.*;
import org.xi.studentmanagesystem.entity.pk.StudentCoursePK;

@Service
public interface CourseService {
    Course getByCid(Integer cid);

    Page<Course> getAllCourse(Example<Course> example, Pageable pageable);

    Course insert(Course course);

    Course update(Course course);

    Course delete(Course course);


    Page<TeacherCourseInfo> getAllTC(Example<TeacherCourseInfo> example, Pageable pageable);

    TC insert(TC tc);

    TC update(TC tc);

    TC delete(TC tc);


    TC getByTcid(Integer tcid);




    Page<StudentCourseInfo> getAllSC(Example<StudentCourseInfo> example, Pageable pageable);



    SC insert(SC sc);

    SC update(SC sc);

    SC delete(SC sc);







}
