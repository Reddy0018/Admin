package com.example.Admin.Service;

import com.example.Admin.Controller.AdminController;
import com.example.Admin.Model.Admin;
import com.example.Admin.Repository.AdminRepository;
import com.example.Admin.Repository.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository repo;
    @Autowired
    AdminController adminController;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Admin admin= repo.findByEmail(s);
        AdminController.id=admin.getId();
        if (admin==null)
        {
            throw new UsernameNotFoundException("User Does Not Exist");
        }
        return new UserPrinciple(admin);
    }
}
