package com.example.the_elite_driving_school_management_system.DAO.Custom.impl;


import com.example.the_elite_driving_school_management_system.Config.FactoryConfiguration;
import com.example.the_elite_driving_school_management_system.DAO.Custom.InstructorDAO;
import com.example.the_elite_driving_school_management_system.Entity.Course;
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
        session.merge(dto);
        tx.commit();
        return true;
    }
    @Override
    public boolean delete(String id) {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        try {
            Instructor instructor = session.get(Instructor.class, id);
            if (instructor != null) {
                session.remove(instructor);
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
    public String generateNewId() {
        try (Session session = factoryConfiguration.getSession()) {
            String lastId=session.createQuery(
                    "SELECT i.id FROM Instructor i ORDER BY i.id DESC",String.class
            )
                    .setMaxResults(1)
                    .uniqueResult();
            if (lastId != null) {
            String numbericPart=lastId.replaceAll("\\D","");
            int newId=Integer.parseInt(numbericPart)+1;
            return String.format("I%03d",newId);
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
