package com.example.the_elite_driving_school_management_system.Bo.Custom;

import com.example.the_elite_driving_school_management_system.Bo.CrudBo;
import com.example.the_elite_driving_school_management_system.DTO.LoginDTO;
import com.example.the_elite_driving_school_management_system.Entity.Login;

public interface LoginBo extends CrudBo<LoginDTO> {
    boolean validateLogin(String loginEmail, String loginPassword);
    boolean validateLoginDetails(String email,String password);
}
