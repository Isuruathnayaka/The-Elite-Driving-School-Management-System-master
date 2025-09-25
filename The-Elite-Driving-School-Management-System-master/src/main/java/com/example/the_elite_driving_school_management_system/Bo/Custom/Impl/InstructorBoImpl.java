package com.example.the_elite_driving_school_management_system.Bo.Custom.Impl;

import com.example.the_elite_driving_school_management_system.Bo.Custom.InstructorBo;
import com.example.the_elite_driving_school_management_system.Bo.MapUtil;
import com.example.the_elite_driving_school_management_system.DAO.Custom.InstructorDAO;
import com.example.the_elite_driving_school_management_system.DTO.InstructorDTO;
import com.example.the_elite_driving_school_management_system.Entity.Instructor;
import com.example.the_elite_driving_school_management_system.Entity.Student;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class InstructorBoImpl implements InstructorBo {
    private final InstructorDAO instructorDAO;

    public InstructorBoImpl(InstructorDAO instructorDAO) {
        this.instructorDAO = instructorDAO;
    }







    @Override
    public boolean save(InstructorDTO dto) {
        Instructor instructor = MapUtil.toEntity(dto); // convert DTO → Entity
        return instructorDAO.save(instructor);
    }


    @Override
    public String generateNewInstructorId() {
        return instructorDAO.generateNewId();
    }

    @Override
    public ArrayList<InstructorDTO> getAllInstructors() {
        ArrayList<Instructor> instructors = instructorDAO.getAll();
        return instructors
                .stream()
                .map(MapUtil::toDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    @Override
    public boolean delete(String id){
        return instructorDAO.delete(id);
    }

    @Override
    public InstructorDTO findByName(String courseName) {
        return null;
    }

    @Override
    public int getInstructorCount() {
        return instructorDAO.getInstructorCount();
    }


    @Override
    public boolean update(InstructorDTO dto) {

        return instructorDAO.update(MapUtil.toEntity(dto));
    }
}
