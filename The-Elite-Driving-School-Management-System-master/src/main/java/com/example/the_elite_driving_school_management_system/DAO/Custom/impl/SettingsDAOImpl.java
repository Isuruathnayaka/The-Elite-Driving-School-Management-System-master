package com.example.the_elite_driving_school_management_system.DAO.Custom.impl;

import com.example.the_elite_driving_school_management_system.Config.FactoryConfiguration;
import com.example.the_elite_driving_school_management_system.DAO.Custom.SettingsDAO;
import com.example.the_elite_driving_school_management_system.Entity.Login;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SettingsDAOImpl implements SettingsDAO {
    private final FactoryConfiguration factoryConfiguration=FactoryConfiguration.getInstance();
    @Override
    public boolean save(Login dto) {
        Transaction transaction = null;
        try (Session session = factoryConfiguration.getSession() ) {
            transaction = session.beginTransaction();

            session.save(dto); // save entity
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Login dto) {
        Transaction transaction = null;

        try (Session session = factoryConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.merge(dto);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }




    @Override
    public Login findBy(String password) {
        Session session = factoryConfiguration.getSession();
        return session.createQuery("from Login where password=:password", Login.class).setParameter("password", password).uniqueResult();
    }
}
