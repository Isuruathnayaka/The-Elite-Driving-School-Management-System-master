package com.example.the_elite_driving_school_management_system.DAO.Custom;

import com.example.the_elite_driving_school_management_system.DAO.CrudDAO;
import com.example.the_elite_driving_school_management_system.DTO.CourseDTO;
import com.example.the_elite_driving_school_management_system.Entity.Course;

import java.util.List;

public interface CourseDAO extends CrudDAO<Course> {
    boolean save(Course dto);

    boolean update(Course dto);

    String generateNewId();

    boolean delete(String id);

    CourseDTO findByName(String courseName);

    List<String> getCourseFeeByCourseId(String courseId);
}
