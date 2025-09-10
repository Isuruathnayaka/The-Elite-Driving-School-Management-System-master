package com.example.the_elite_driving_school_management_system.DAO.Custom;

import com.example.the_elite_driving_school_management_system.DAO.CrudDAO;
import com.example.the_elite_driving_school_management_system.DTO.LoginDTO;
import com.example.the_elite_driving_school_management_system.Entity.Login;

public interface LoginDAO extends CrudDAO<Login> {




    Login findByEmail(String email);
}
