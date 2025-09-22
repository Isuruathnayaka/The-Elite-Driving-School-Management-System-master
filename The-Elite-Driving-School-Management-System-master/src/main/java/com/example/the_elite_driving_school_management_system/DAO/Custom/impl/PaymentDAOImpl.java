package com.example.the_elite_driving_school_management_system.DAO.Custom.impl;

import com.example.the_elite_driving_school_management_system.Config.FactoryConfiguration;
import com.example.the_elite_driving_school_management_system.DAO.Custom.PaymentDAO;
import com.example.the_elite_driving_school_management_system.Entity.Payment;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

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
        Session session = factoryConfiguration.getSession();
        Transaction tx=session.beginTransaction();
        try {
            session.merge(dto); // Use merge() for updates
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public String generateNewId() {
        try (Session session = factoryConfiguration.getSession()) {
            // Get the last studentId (ordered descending)
            String lastId = session.createQuery(
                            "SELECT p.id FROM Payment p ORDER BY p.id DESC", String.class
                    )
                    .setMaxResults(1)   // only take top 1
                    .uniqueResult();

            if (lastId != null) {
                // Extract digits only
                String numericPart = lastId.replaceAll("\\D", "");
                int newId = Integer.parseInt(numericPart) + 1;

                // Format as S001, S002, etc.
                return String.format("P%03d", newId);
            } else {
                return "P001"; // first ID
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "P001"; // fallback to first ID if query fails
        }
    }

    @Override
    public ArrayList<Payment> getAll() {
       try (Session session = factoryConfiguration.getSession()) {
            List<Payment> payments = session.createQuery("FROM Payment", Payment.class).list();
            return new ArrayList<>(payments);

        }
    }

    @Override
    public boolean delete(String paymentId) {
        Session session = factoryConfiguration.getSession();
        Transaction tx=session.beginTransaction();
        try {
            Payment payment = session.get(Payment.class, paymentId);
            if (payment!= null) {
                session.remove(payment);
                tx.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }

    }

    @Override
    public List<String> getUnpaidCoursesByStudent(String studentId) {
        String sql = """
            SELECT sc.course_id
            FROM student_course sc
            WHERE sc.student_id = :studentId
              AND sc.course_id NOT IN (
                  SELECT p.course_id
                  FROM payment p
                  WHERE p.studentId = :studentId
              )
        """;

        try (Session session = factoryConfiguration.getSession()) {
            NativeQuery<String> query = session.createNativeQuery(sql);
            query.setParameter("studentId", studentId);
            return query.getResultList();
        }
    }


}
