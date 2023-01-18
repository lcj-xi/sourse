package org.xi.studentmanagesystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xi.studentmanagesystem.entity.Student;
import org.xi.studentmanagesystem.entity.Teacher;
import org.xi.studentmanagesystem.exception.REnum;
import org.xi.studentmanagesystem.exception.RException;
import org.xi.studentmanagesystem.repository.TeacherRepository;
import org.xi.studentmanagesystem.service.TeacherService;

import java.util.List;

@Service
public class TeacherImplService implements TeacherService {
    @Autowired
    private TeacherRepository teacherInfoRepository;


    @Override
    public Teacher getById(Integer tid) {
        Teacher teacher = teacherInfoRepository.findById(tid).orElse(null);
        if(teacher!=null){
            return teacher;
        }
        else{
            throw new RException(REnum.SELECT_ERROR);
        }
    }

    @Override
    public Teacher getByTidAndTpassword(Integer tid, String tpassword) {

        Teacher teacher = teacherInfoRepository.findByTidAndTpassword(tid, tpassword);
        if(teacher!=null){
            return teacher;
        }
        else{
            throw new RException(REnum.LOGIN_ERROR);
        }
    }

    @Override
    public Page<Teacher> getAll(Example<Teacher> example, Pageable pageable) {
        Page<Teacher> teachers = teacherInfoRepository.findAll(example, pageable);
        if(teachers!=null){
            return teachers;
        }
        else{
            throw new RException(REnum.SELECT_ERROR);
        }
    }

    @Override
    public Teacher insert(Teacher teacher) {
        Teacher teacher1 = teacherInfoRepository.save(teacher);
        if(teacher1!=null){
            return teacher1;
        }
        else{
            throw new RException(REnum.INSERT_ERROR);
        }
    }

    @Override
    public Teacher update(Teacher teacher) {
        Teacher teacher1 = teacherInfoRepository.findById(teacher.getTid()).orElse(null);
        if(teacher1!=null){
            return teacherInfoRepository.save(teacher);
        }
        else{
            throw new RException(REnum.UPDATE_ERROR);
        }
    }

    @Override
    public Teacher delete(Teacher teacher) {
        Teacher teacher1 = teacherInfoRepository.findById(teacher.getTid()).orElse(null);
        if(teacher1!=null){
            teacherInfoRepository.delete(teacher);
            return  teacher1;
        }
        else{
            throw new RException(REnum.DELETE_ERROR);
        }
    }
}
