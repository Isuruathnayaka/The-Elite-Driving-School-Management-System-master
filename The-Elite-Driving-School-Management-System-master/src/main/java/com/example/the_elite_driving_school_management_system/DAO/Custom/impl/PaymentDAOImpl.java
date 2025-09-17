package com.example.the_elite_driving_school_management_system.DAO.Custom.impl;

import com.example.the_elite_driving_school_management_system.Config.FactoryConfiguration;
import com.example.the_elite_driving_school_management_system.DAO.Custom.PaymentDAO;
import com.example.the_elite_driving_school_management_system.Entity.Payment;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class PaymentDAOImpl implements PaymentDAO {
    private static FactoryConfiguration factoryConfiguration=FactoryConfiguration.getInstance();

    @Override
    public boolean save(Payment payment) {
        Transaction transaction = null;
        try (Session session = factoryConfiguration.getSession()) {
            transaction = session.beginTransaction();
            session.persist(payment);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Payment dto) {
        return false;
    }

    @Override
    public String generateNewId() {
        try (Session session = factoryConfiguration.getSession()) {
            String lastId = (String) session.createQuery("SELECT p.paymentId FROM Payment p ORDER BY p.paymentId DESC")
                    .setMaxResults(1)
                    .uniqueResult();
            if (lastId != null) {
                int num = Integer.parseInt(lastId.substring(1)) + 1;
                return String.format("P%03d", num);
            } else {
                return "P001";
            }
        }
    }
}
