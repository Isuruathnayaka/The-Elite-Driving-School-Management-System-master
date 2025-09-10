package com.example.the_elite_driving_school_management_system.DAO;

import com.example.the_elite_driving_school_management_system.DTO.InstructorDTO;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T dto);

    boolean update(T dto);
}
