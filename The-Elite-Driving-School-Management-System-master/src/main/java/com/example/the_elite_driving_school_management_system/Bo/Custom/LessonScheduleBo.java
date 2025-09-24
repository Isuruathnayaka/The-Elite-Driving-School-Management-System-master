package com.example.the_elite_driving_school_management_system.Bo.Custom;

import com.example.the_elite_driving_school_management_system.Bo.CrudBo;
import com.example.the_elite_driving_school_management_system.DTO.LessonDTO;
import com.example.the_elite_driving_school_management_system.DTO.PaymentDTO;
import com.example.the_elite_driving_school_management_system.Entity.Course;
import com.example.the_elite_driving_school_management_system.Entity.Instructor;
import com.example.the_elite_driving_school_management_system.Entity.Student;

import java.util.List;

public interface LessonScheduleBo extends CrudBo<LessonDTO> {
  PaymentDTO findById(String paymentId);

  boolean update(LessonDTO dto);
  String generateNewLessonId();

  List<String> getStudentIdsByCourse(String courseId);

  String getInstructorIdByCourse(String courseId);
}
