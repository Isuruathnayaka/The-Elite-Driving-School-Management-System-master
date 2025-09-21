package com.example.the_elite_driving_school_management_system.DAO.Custom;

import com.example.the_elite_driving_school_management_system.DAO.CrudDAO;
import com.example.the_elite_driving_school_management_system.Entity.Payment;

import java.util.ArrayList;
import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {
    String generateNewId();


    ArrayList<Payment> getAll();

    boolean delete(String paymentId);

    List<String> getUnpaidCoursesByStudent(String studentId);
}
