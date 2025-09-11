package com.example.the_elite_driving_school_management_system.Bo.Custom;

import com.example.the_elite_driving_school_management_system.DAO.CrudDAO;
import com.example.the_elite_driving_school_management_system.DTO.PaymentDTO;

public interface PaymentBo extends CrudDAO<PaymentDTO> {
   String generateNewStudentId();
}
