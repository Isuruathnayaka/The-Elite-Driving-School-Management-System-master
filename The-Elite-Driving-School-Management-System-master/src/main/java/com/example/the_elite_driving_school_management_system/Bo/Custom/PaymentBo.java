package com.example.the_elite_driving_school_management_system.Bo.Custom;

import com.example.the_elite_driving_school_management_system.DAO.CrudDAO;
import com.example.the_elite_driving_school_management_system.DTO.PaymentDTO;
import com.example.the_elite_driving_school_management_system.Entity.Student;

import java.util.List;

public interface PaymentBo extends CrudDAO<PaymentDTO> {
   String generateNewPaymentId();

   String generateNewStudentId();

   Student findById(String studentId);

   List<String> getCourseIdsByStudent(String studentId);
}
