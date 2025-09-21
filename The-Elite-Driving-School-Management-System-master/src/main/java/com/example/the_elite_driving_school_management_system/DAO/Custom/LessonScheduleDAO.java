package com.example.the_elite_driving_school_management_system.DAO.Custom;

import com.example.the_elite_driving_school_management_system.DAO.CrudDAO;
import com.example.the_elite_driving_school_management_system.DTO.PaymentDTO;
import com.example.the_elite_driving_school_management_system.Entity.LessonSchedule;

public interface LessonScheduleDAO extends CrudDAO<LessonSchedule> {
    PaymentDTO findById(String paymentId);
}
