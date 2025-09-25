package com.example.the_elite_driving_school_management_system.DAO.Custom;

import com.example.the_elite_driving_school_management_system.DAO.CrudDAO;
import com.example.the_elite_driving_school_management_system.Entity.Login;

public interface SettingsDAO extends CrudDAO<Login> {
    Login findBy(String password);
}
