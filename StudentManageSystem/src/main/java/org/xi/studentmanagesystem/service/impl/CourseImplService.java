package org.xi.studentmanagesystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xi.studentmanagesystem.entity.*;
import org.xi.studentmanagesystem.entity.pk.StudentCoursePK;
import org.xi.studentmanagesystem.exception.REnum;
import org.xi.studentmanagesystem.exception.RException;
import org.xi.studentmanagesystem.repository.*;
import org.xi.studentmanagesystem.service.CourseService;

import java.util.Optional;

@Service
public class CourseImplService implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    TeacherCourseRepository teacherCourseRepository;

    @Autowired
    StudentCourseRepository studentCourseRepository;
    @Autowired
    private TCRepository tCRepository;
    @Autowired
    private SCRepository sCRepository;


    @Override
    public Course getByCid(Integer cid) {
        Course course = courseRepository.findById(cid).orElse(null);
        if(course!=null){
            return course;
        }
        else{
            throw new RException(REnum.SELECT_ERROR);
        }
    }

    @Override
    public Page<Course> getAllCourse(Example<Course> example, Pageable pageable) {
        Page<Course> courses= courseRepository.findAll(example, pageable);
        if(courses!=null){
            return courses;
        }
        else{
            throw new RException(REnum.SELECT_ERROR);
        }
    }

    @Override
    public Course insert(Course course) {
        Course course1 = courseRepository.save(course);
        if(course1!=null){
            return course;
        }
        else{
            throw new RException(REnum.INSERT_ERROR);
        }
    }

    @Override
    public Course update(Course course) {
        Course course1 = courseRepository.findById(course.getCid()).orElse(null);
        if(course1!=null){
            return courseRepository.save(course1);
        }
        else{
            throw new RException(REnum.UPDATE_ERROR);
        }
    }

    @Override
    public Course delete(Course course) {

        Course course1 = courseRepository.findById(course.getCid()).orElse(null);
        if(course1!=null){
            courseRepository.delete(course);
            return  course1;
        }
        else{
            throw new RException(REnum.DELETE_ERROR);
        }
    }

    @Override
    public Page<TeacherCourseInfo> getAllTC(Example<TeacherCourseInfo> example, Pageable pageable) {
        Page<TeacherCourseInfo> teacherCourseInfos= teacherCourseRepository.findAll(example, pageable);
        if(teacherCourseInfos!=null){
            return teacherCourseInfos;
        }
        else{
            throw new RException(REnum.SELECT_ERROR);
        }
    }

    @Override
    public TC insert(TC tc) {
        TC tc1 = tCRepository.save(tc);
        if(tc1!=null){
            return tc1;
        }
        else{
            throw new RException(REnum.INSERT_ERROR);
        }
    }

    @Override
    public TC update(TC tc) {
        TC tc1 = tCRepository.findById(tc.getCid()).orElse(null);
        if(tc1!=null){
            return tCRepository.save(tc);
        }
        else{
            throw new RException(REnum.UPDATE_ERROR);
        }
    }

    @Override
    public TC delete(TC tc) {


        TC tc1 = tCRepository.findById(tc.getTcid()).orElse(null);
        if(tc1!=null){
            tCRepository.delete(tc);
            return  tc1;
        }
        else{
            throw new RException(REnum.DELETE_ERROR);
        }
    }

    @Override
    public TC getByTcid(Integer tcid) {
        TC tc = tCRepository.findById(tcid).orElse(null);
        if(tc!=null){
            return tc;
        }
        else{
            throw new RException(REnum.SELECT_ERROR);
        }
    }


    @Override
    public Page<StudentCourseInfo> getAllSC(Example<StudentCourseInfo> example, Pageable pageable) {
        Page<StudentCourseInfo> studentCourseInfos= studentCourseRepository.findAll(example, pageable);
        if(studentCourseInfos!=null){
            return studentCourseInfos;
        }
        else{
            throw new RException(REnum.SELECT_ERROR);
        }
    }

    @Override
    public SC insert(SC sc) {
        SC sc1 = sCRepository.findById(new StudentCoursePK(sc.getSid(), sc.getTcid())).orElse(null);
        if(sc1==null){
            return sCRepository.save(sc);
        }
        else{
            throw new RException(REnum.INSERT_ERROR);
        }
    }

    @Override
    public SC update(SC sc) {

        SC sc1 = sCRepository.save(sc);
        if(sc1!=null){
            return sc1;
        }
        else{
            throw new RException(REnum.UPDATE_ERROR);
        }
    }

    @Override
    public SC delete(SC sc) {
        SC sc1 = sCRepository.findById(new StudentCoursePK(sc.getSid(), sc.getTcid())).orElse(null);
        if(sc1!=null){
            sCRepository.delete(sc);
            return  sc1;
        }
        else{
            throw new RException(REnum.DELETE_ERROR);
        }
    }


}
