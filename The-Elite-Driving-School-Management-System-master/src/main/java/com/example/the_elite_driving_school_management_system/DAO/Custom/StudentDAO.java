package com.example.the_elite_driving_school_management_system.DAO.Custom;

import com.example.the_elite_driving_school_management_system.DAO.CrudDAO;
import com.example.the_elite_driving_school_management_system.DTO.InstructorDTO;
import com.example.the_elite_driving_school_management_system.DTO.StudentDTO;
import com.example.the_elite_driving_school_management_system.Entity.Student;

import java.util.ArrayList;
import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {


    String generateNewId();

    ArrayList<Student> getAll();

    boolean delete(String studentId);
    List<String> getCourseIdsByStudentId(String studentId);
}
