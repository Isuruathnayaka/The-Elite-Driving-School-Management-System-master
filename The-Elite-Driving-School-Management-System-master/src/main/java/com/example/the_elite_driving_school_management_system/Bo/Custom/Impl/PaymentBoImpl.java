package com.example.the_elite_driving_school_management_system.Bo.Custom.Impl;

import com.example.the_elite_driving_school_management_system.Bo.Custom.PaymentBo;
import com.example.the_elite_driving_school_management_system.Bo.SuperBO;
import com.example.the_elite_driving_school_management_system.Config.FactoryConfiguration;
import com.example.the_elite_driving_school_management_system.DAO.Custom.PaymentDAO;
import com.example.the_elite_driving_school_management_system.DTO.PaymentDTO;

public class PaymentBoImpl implements PaymentBo, SuperBO {
    private static FactoryConfiguration factoryConfiguration=FactoryConfiguration.getInstance();
    private final PaymentDAO paymentDAO;

    public PaymentBoImpl(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @Override
    public boolean save(PaymentDTO dto) {
        return false;
    }

    @Override
    public boolean update(PaymentDTO dto) {
        return false;
    }

    @Override
    public String generateNewStudentId() {
        return paymentDAO.generateNewId();
    }
}
