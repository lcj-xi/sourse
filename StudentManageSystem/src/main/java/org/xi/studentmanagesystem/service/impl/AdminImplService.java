package org.xi.studentmanagesystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xi.studentmanagesystem.entity.Admin;
import org.xi.studentmanagesystem.exception.REnum;
import org.xi.studentmanagesystem.exception.RException;
import org.xi.studentmanagesystem.repository.AdminRepository;
import org.xi.studentmanagesystem.service.AdminService;

import java.util.Optional;

@Service
public class AdminImplService implements AdminService {

    @Autowired
    private AdminRepository adminInfoRepository;

    @Override
    public Admin getByAidAndApassword(Integer aname,String apassword){

        Admin admin = adminInfoRepository.findByAidAndApassword(aname, apassword);
        if(admin!=null){
            return admin;
        }
        else{
            throw new RException(REnum.LOGIN_ERROR);
        }

    }

    @Override
    public Admin getById(Integer aid) {
        Admin admin = adminInfoRepository.findById(aid).orElse(null);
        if(admin!=null){
            return admin;
        }
        else{
            throw new RException(REnum.SELECT_ERROR);
        }

    }

    @Override
    public Admin insert(Admin admin) {
        Admin admin1 = adminInfoRepository.save(admin);
        if(admin1!=null){
            return admin1;
        }
        else{
            throw new RException(REnum.INSERT_ERROR);
        }

    }

    @Override
    public Admin update(Admin admin) {
        Admin admin1 = adminInfoRepository.findById(admin.getAid()).orElse(null);
        if(admin1!=null){
            return adminInfoRepository.save(admin);
        }
        else{
            throw new RException(REnum.UPDATE_ERROR);
        }
    }

    @Override
    public Admin delete(Admin admin) {
        Admin admin1 = adminInfoRepository.findById(admin.getAid()).orElse(null);
        if(admin1!=null){
           adminInfoRepository.delete(admin);
           return admin;
        }
        else {
            throw new RException(REnum.DELETE_ERROR);
        }

    }

}
