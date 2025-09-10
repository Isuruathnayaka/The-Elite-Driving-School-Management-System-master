package com.example.the_elite_driving_school_management_system.DAO.Custom.impl;


import com.example.the_elite_driving_school_management_system.Config.FactoryConfiguration;
import com.example.the_elite_driving_school_management_system.DAO.Custom.InstructorDAO;
import com.example.the_elite_driving_school_management_system.Entity.Instructor;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class InstructorDAOImpl implements InstructorDAO {
    private final FactoryConfiguration factoryConfiguration =
            FactoryConfiguration.getInstance();


    @Override
    public boolean save(Instructor dto) {
        try (Session session = factoryConfiguration.getSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(dto);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Instructor dto) {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        session.update(dto);
        tx.commit();
        return true;
    }



    @Override
    public String generateNewId() {
        try (Session session = factoryConfiguration.getSession()) {
            Transaction transaction = session.beginTransaction();

            // Use the entity class name "Instructor" in HQL, not the table name
            Query<String> query = session.createQuery(
                    "SELECT i.id FROM Instructor i ORDER BY i.id DESC",
                    String.class
            );
            query.setMaxResults(1);

            String lastId = query.uniqueResult();
            transaction.commit();

            if (lastId != null) {
                int newId = Integer.parseInt(lastId.replace("I001", "")) + 1;
                return "I001" + newId;
            } else {
                return "I001";
            }
        }
    }

    @Override
    public ArrayList<Instructor> getAll() {
        try (Session session = factoryConfiguration.getSession()) {
            List<Instructor> entityList = session.createQuery("from Instructor ", Instructor.class).list();
            return new ArrayList<>(entityList); // return entities
        }
    }

}
