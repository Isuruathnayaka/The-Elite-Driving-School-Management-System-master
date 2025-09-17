package com.example.the_elite_driving_school_management_system.DAO.Custom;

import com.example.the_elite_driving_school_management_system.DAO.CrudDAO;
import com.example.the_elite_driving_school_management_system.Entity.Payment;

import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<Payment> {
    String generateNewId();


    ArrayList<Payment> getAll();
}
