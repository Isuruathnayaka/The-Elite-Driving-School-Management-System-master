package com.example.the_elite_driving_school_management_system.Bo.Custom;

import com.example.the_elite_driving_school_management_system.Bo.CrudBo;
import com.example.the_elite_driving_school_management_system.DTO.InstructorDTO;
import com.example.the_elite_driving_school_management_system.Entity.Instructor;

import java.util.ArrayList;

public interface InstructorBo extends CrudBo<InstructorDTO> {
   // boolean save(InstructorDTO dto);


   // boolean save(InstructorDTO dto);



    String  generateNewInstructorId();

    ArrayList<InstructorDTO> getAllInstructors();
    boolean delete(String id);

    InstructorDTO findByName(String courseName);

    int getInstructorCount();
}
