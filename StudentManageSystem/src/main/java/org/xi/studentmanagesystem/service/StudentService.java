package org.xi.studentmanagesystem.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xi.studentmanagesystem.entity.Student;

import java.util.List;

@Service
public interface StudentService {
    Student getById(Integer sid);
    Student getBySidAndSpassword(Integer sid, String spassword);

    Page<Student>  getAll(Example<Student> example, Pageable pageable);

    Student insert(Student student);

    Student update(Student student);

    Student delete(Student student);




}
