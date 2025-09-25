package com.example.the_elite_driving_school_management_system.DAO.Custom;

import com.example.the_elite_driving_school_management_system.DAO.CrudDAO;
import com.example.the_elite_driving_school_management_system.DTO.InstructorDTO;
import com.example.the_elite_driving_school_management_system.Entity.Instructor;
import com.example.the_elite_driving_school_management_system.Entity.Student;

import java.util.ArrayList;

public interface InstructorDAO extends CrudDAO<Instructor> {


    String generateNewId();

    ArrayList<Instructor> getAll();
    boolean delete(String id);

    int getInstructorCount();
}
