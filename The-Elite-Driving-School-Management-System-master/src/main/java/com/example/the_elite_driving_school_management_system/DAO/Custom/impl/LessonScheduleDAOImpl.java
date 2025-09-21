package com.example.the_elite_driving_school_management_system.DAO.Custom.impl;

import com.example.the_elite_driving_school_management_system.Bo.MapUtil;
import com.example.the_elite_driving_school_management_system.Config.FactoryConfiguration;
import com.example.the_elite_driving_school_management_system.DAO.Custom.LessonScheduleDAO;
import com.example.the_elite_driving_school_management_system.DTO.PaymentDTO;
import com.example.the_elite_driving_school_management_system.Entity.LessonSchedule;
import com.example.the_elite_driving_school_management_system.Entity.Payment;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class LessonScheduleDAOImpl implements LessonScheduleDAO {
    private final FactoryConfiguration factoryConfiguration =
            FactoryConfiguration.getInstance();

    @Override
    public boolean save(LessonSchedule dto) {
        return false;
    }

    @Override
    public boolean update(LessonSchedule dto) {
        return false;
    }

    @Override
    public PaymentDTO findById(String paymentId) {
        Session session =factoryConfiguration.getSession();
        Query<Payment> query = session.createQuery(
                "FROM Payment p WHERE p.paymentId = :paymentId", Payment.class);

        query.setParameter("paymentId", paymentId);
        Payment payment = query.uniqueResult();
        if (payment != null) {
            return MapUtil.toDTO(payment);
        }

        return  null;
    }
}
