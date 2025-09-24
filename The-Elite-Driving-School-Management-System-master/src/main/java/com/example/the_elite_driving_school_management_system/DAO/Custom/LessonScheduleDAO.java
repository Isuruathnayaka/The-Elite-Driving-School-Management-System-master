package com.example.the_elite_driving_school_management_system.DAO.Custom;

import com.example.the_elite_driving_school_management_system.DAO.CrudDAO;
import com.example.the_elite_driving_school_management_system.DTO.PaymentDTO;
import com.example.the_elite_driving_school_management_system.Entity.Course;
import com.example.the_elite_driving_school_management_system.Entity.Instructor;
import com.example.the_elite_driving_school_management_system.Entity.Lesson;
import com.example.the_elite_driving_school_management_system.Entity.Student;

import java.util.List;

public interface LessonScheduleDAO extends CrudDAO<Lesson> {
    PaymentDTO findById(String paymentId);
    List<String> getStudentIdsByCourseId(String courseId);
    String getInstructorIdByCourseId(String courseId);
    String generateNewId();
    Student findStudentById(String studentId);

    Instructor findInstructorById(String instructorId);

    Course findCourseById(String courseId);
}
