package org.xi.studentmanagesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xi.studentmanagesystem.entity.Student;

//@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findBySidAndSpassword(Integer sid,String spassword);


}
