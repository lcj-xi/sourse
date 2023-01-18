package org.xi.studentmanagesystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xi.studentmanagesystem.entity.Admin;
import org.xi.studentmanagesystem.entity.Student;
import org.xi.studentmanagesystem.exception.REnum;
import org.xi.studentmanagesystem.exception.RException;
import org.xi.studentmanagesystem.repository.StudentRepository;
import org.xi.studentmanagesystem.service.StudentService;

@Service
public class StudentImplService implements StudentService {

    @Autowired
    private StudentRepository studentInfoRepository;

    @Override
    public Student getById(Integer sid) {
        Student student = studentInfoRepository.findById(sid).orElse(null);
        if(student!=null){
            return student;
        }
        else{
            throw new RException(REnum.SELECT_ERROR);
        }
    }

    @Override
    public Student getBySidAndSpassword(Integer sid, String spassword) {
        Student student = studentInfoRepository.findBySidAndSpassword(sid, spassword);
        if(student!=null){
            return student;
        }
        else{
            throw new RException(REnum.LOGIN_ERROR);
        }
    }

    @Override
    public Page<Student> getAll(Example<Student> example, Pageable pageable) {
        Page<Student> students= studentInfoRepository.findAll(example, pageable);
        if(students!=null){
            return students;
        }
        else{
            throw new RException(REnum.SELECT_ERROR);
        }
    }

    @Override
    public Student insert(Student student) {
        Student student1 = studentInfoRepository.save(student);
        if(student1!=null){
             return student1;
        }
        else{
            throw new RException(REnum.INSERT_ERROR);
        }
    }

    @Override
    public Student update(Student student) {
        Student student1 = studentInfoRepository.findById(student.getSid()).orElse(null);
        if(student1!=null){
            return studentInfoRepository.save(student);
        }
        else{
            throw new RException(REnum.UPDATE_ERROR);
        }
    }

    @Override
    public Student delete(Student student) {
        Student student1 = studentInfoRepository.findById(student.getSid()).orElse(null);
        if(student1!=null){
            studentInfoRepository.delete(student);
            return  student1;
        }
        else{
            throw new RException(REnum.DELETE_ERROR);
        }
    }
}
