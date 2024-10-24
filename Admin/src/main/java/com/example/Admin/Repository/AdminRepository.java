package com.example.Admin.Repository;

import com.example.Admin.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<Admin,Long> {

    @Query("select a.password from Admin a where a.password = :id and a.email= :email")
    String findByPassword(@Param("id") String  id, @Param("email") String  email);
    @Query("select a from Admin a where a.email = :email")
    Admin findByEmail(@Param("email") String email);


    //@Query("select a from Admin a where a.role = ?1")
    //Admin findByRole(String email);
}
