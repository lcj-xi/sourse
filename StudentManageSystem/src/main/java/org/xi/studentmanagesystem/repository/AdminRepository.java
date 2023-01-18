package org.xi.studentmanagesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xi.studentmanagesystem.entity.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Admin findByAidAndApassword(Integer aid,String apassword);


}
