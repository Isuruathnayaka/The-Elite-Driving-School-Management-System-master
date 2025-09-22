package com.example.the_elite_driving_school_management_system.Bo.Custom;

import com.example.the_elite_driving_school_management_system.Bo.CrudBo;
import com.example.the_elite_driving_school_management_system.DTO.CourseDTO;
import jakarta.transaction.SystemException;

import java.util.List;

public interface CourseBo extends CrudBo<CourseDTO> {
    String generateNewCourseId();

    List<CourseDTO> getAllCourses();

    boolean delete(String id);

    CourseDTO findByName(String courseName);

    String getCourseFeeByCourseId(String courseId) throws SystemException;

    CourseDTO findById(String id);
}
