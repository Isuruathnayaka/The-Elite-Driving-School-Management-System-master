package com.example.the_elite_driving_school_management_system.DAO.Custom.impl;

import com.example.the_elite_driving_school_management_system.Config.FactoryConfiguration;
import com.example.the_elite_driving_school_management_system.DAO.Custom.LoginDAO;
import com.example.the_elite_driving_school_management_system.DTO.InstructorDTO;
import com.example.the_elite_driving_school_management_system.DTO.LoginDTO;
import com.example.the_elite_driving_school_management_system.Entity.Login;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginDAOimpl implements LoginDAO {
    private final FactoryConfiguration factoryConfiguration =
            FactoryConfiguration.getInstance();
    @Override
    public boolean save(Login login) {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();

        try {
            session.save(login);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }



    @Override
    public Login findByEmail(String email) {
       try(Session session = factoryConfiguration.getSession()){
           return session.createQuery("from Login where email = :email", Login.class).setParameter("email", email).uniqueResult();

        }
    }




    @Override
    public boolean update(Login dto) {
        return false;
    }
}


