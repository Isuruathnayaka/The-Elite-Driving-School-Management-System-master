package com.example.the_elite_driving_school_management_system.Bo.Custom.Impl;

import com.example.the_elite_driving_school_management_system.Bo.Custom.SettingsBo;
import com.example.the_elite_driving_school_management_system.Bo.MapUtil;
import com.example.the_elite_driving_school_management_system.Config.FactoryConfiguration;
import com.example.the_elite_driving_school_management_system.DAO.Custom.SettingsDAO;
import com.example.the_elite_driving_school_management_system.DTO.SettingsDTO;
import com.example.the_elite_driving_school_management_system.Entity.Login;
import com.example.the_elite_driving_school_management_system.Util.PasswordUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SettingsBoImpl implements SettingsBo {
    private final FactoryConfiguration factoryConfiguration=FactoryConfiguration.getInstance();
    private final SettingsDAO settingsDAO;
    public SettingsBoImpl(SettingsDAO settingsDAO) {
        this.settingsDAO=settingsDAO;
    }

    @Override
    public boolean save(SettingsDTO dto) {
        String encriptedPasword= PasswordUtil.hashPassword(dto.getPassword());
        dto.setPassword(encriptedPasword);
        return settingsDAO.save(MapUtil.toEntity(dto));
    }

    @Override
    public boolean update(SettingsDTO dto) {
        return false;
    }

    @Override
    public boolean validateLoginDetails(String username, String password) {
        Session session = null;
        Transaction transaction = null;
        boolean isValid = false;

        try {
            session = factoryConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Use HQL instead of get()
            String hql = "FROM Login l WHERE l.username = :username";
            Login login = session.createQuery(hql, Login.class)
                    .setParameter("username", username)
                    .uniqueResult();

            if (login != null) {
                String hashedPassword = login.getPassword();

                // Hash old plain password if needed
                if (hashedPassword == null || !hashedPassword.startsWith("$2")) {
                    hashedPassword = PasswordUtil.hashPassword(hashedPassword);
                    login.setPassword(hashedPassword);
                    session.update(login);
                }

                // Check password while session is open
                isValid = PasswordUtil.checkPassword(password, hashedPassword);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }

        return isValid;
    }




}
