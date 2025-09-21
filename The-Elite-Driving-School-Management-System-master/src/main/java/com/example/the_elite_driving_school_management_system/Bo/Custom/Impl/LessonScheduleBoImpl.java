package com.example.the_elite_driving_school_management_system.Bo.Custom.Impl;

import com.example.the_elite_driving_school_management_system.Bo.Custom.LessonScheduleBo;
import com.example.the_elite_driving_school_management_system.DAO.Custom.LessonScheduleDAO;
import com.example.the_elite_driving_school_management_system.DTO.LessonScheduleDTO;
import com.example.the_elite_driving_school_management_system.DTO.PaymentDTO;

public class LessonScheduleBoImpl implements LessonScheduleBo {
    private final LessonScheduleDAO lessonScheduleDAO;

    public LessonScheduleBoImpl(LessonScheduleDAO lessonScheduleDAO) {
        this.lessonScheduleDAO = lessonScheduleDAO;
    }

    @Override
    public boolean save(LessonScheduleDTO dto) {
        return false;
    }

    @Override
    public boolean update(LessonScheduleDTO dto) {
        return false;
    }


    @Override
    public PaymentDTO findById(String paymentId) {
        return lessonScheduleDAO.findById(paymentId);
    }
}
