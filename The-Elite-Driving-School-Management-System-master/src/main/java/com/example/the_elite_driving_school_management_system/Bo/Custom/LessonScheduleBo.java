package com.example.the_elite_driving_school_management_system.Bo.Custom;

import com.example.the_elite_driving_school_management_system.Bo.CrudBo;
import com.example.the_elite_driving_school_management_system.DTO.LessonScheduleDTO;
import com.example.the_elite_driving_school_management_system.DTO.PaymentDTO;

public interface LessonScheduleBo extends CrudBo<LessonScheduleDTO> {
  PaymentDTO findById(String paymentId);
}
