package com.example.the_elite_driving_school_management_system.DAO.Custom.impl;

import com.example.the_elite_driving_school_management_system.Config.FactoryConfiguration;
import com.example.the_elite_driving_school_management_system.DAO.Custom.PaymentDAO;
import com.example.the_elite_driving_school_management_system.Entity.Payment;
import org.hibernate.Session;



public class PaymentDAOImpl implements PaymentDAO {
    private static FactoryConfiguration factoryConfiguration=FactoryConfiguration.getInstance();
    @Override
    public boolean save(Payment dto) {
        return false;
    }

    @Override
    public boolean update(Payment dto) {
        return false;
    }


    @Override
    public String generateNewId() {
        try (Session session = factoryConfiguration.getSession()) {
            // Get the last payment ID (ordered descending)
            String lastId = session.createQuery(
                            "SELECT p.id FROM Payment p ORDER BY p.id DESC", String.class
                    )
                    .setMaxResults(1)   // only take top 1
                    .uniqueResult();

            if (lastId != null) {
                // Extract digits (e.g., "P005" -> "005")
                String numericPart = lastId.replaceAll("\\D", "");
                int newId = Integer.parseInt(numericPart) + 1;

                // Format with prefix "P" and 3 digits (P006, P007, etc.)
                return String.format("P%03d", newId);
            } else {
                return "P001"; // If no record exists
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "P001"; // fallback
        }
    }



}

