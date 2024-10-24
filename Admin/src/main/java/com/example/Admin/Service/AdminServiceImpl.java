package com.example.Admin.Service;

import com.example.Admin.Model.Admin;
import com.example.Admin.Repository.AdminRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements  AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> getAllStudents() {
        return adminRepository.findAll();
    }

    @Override
    public void saveEmployee(Admin admin) {
        this.adminRepository.save(admin);
    }

    @Override
    public Admin getStudentById(long id) {
        Optional<Admin> optional= adminRepository.findById(id);
        Admin admin= null;
        if(optional.isPresent())
        {
            admin=optional.get();
        }
        else
        {
            throw  new RuntimeException("Employee Not Found for id::"+id);
        }
        return admin;
    }

    @Override
    public void deleteStudentById(long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public String getPass(Admin admin) {
        return admin.getPassword();
    }

    @Override
    public Admin getStudentByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public String generateCommonTextPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        String pwd = RandomStringUtils.random( 15, characters );
        return pwd;
    }

    @Override
    public Admin updateStudent(Admin admin) {
        Admin existingStudent= adminRepository.findById(admin.getId()).orElse(null);
        existingStudent.setFirstName(admin.getFirstName());
        existingStudent.setLastName(admin.getLastName());
        existingStudent.setEmail(admin.getEmail());
        existingStudent.setRole(admin.getRole());
        existingStudent.setPassword(admin.getPassword());
        return  adminRepository.save(existingStudent);
    }

}
