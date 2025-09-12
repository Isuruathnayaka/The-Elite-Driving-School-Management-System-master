package com.example.the_elite_driving_school_management_system.Bo.Custom;

import com.example.the_elite_driving_school_management_system.Bo.CrudBo;
import com.example.the_elite_driving_school_management_system.DTO.InstructorDTO;
import com.example.the_elite_driving_school_management_system.DTO.StudentDTO;
import com.example.the_elite_driving_school_management_system.Entity.Student;

import java.util.ArrayList;
import java.util.List;

public interface StudentBo extends CrudBo<StudentDTO> {




    String generateNewStudentId();

    ArrayList<StudentDTO> getAllStudents();


    boolean delete(String studentId);
    List<String> getCourseIdsByStudent(String studentId);
}
