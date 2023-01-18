package org.xi.studentmanagesystem.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xi.studentmanagesystem.entity.*;

import java.util.List;

@Service
public interface AdminService {
    Admin getByAidAndApassword(Integer aid,String apassword);

    Admin getById(Integer aid);

    Admin insert(Admin admin);

    Admin update(Admin admin);

    Admin delete(Admin admin);



}
