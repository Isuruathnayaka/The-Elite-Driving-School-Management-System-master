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

import java.util.ArrayList;
import java.util.List;

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
        String encriptedPasword= PasswordUtil.hashPassword(dto.getPassword());
        dto.setPassword(encriptedPasword);
        return settingsDAO.update(MapUtil.toEntity(dto));
    }


    @Override
    public String validateLoginDetails(String username, String password) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Login login = session.createQuery("FROM Login WHERE username = :username", Login.class)
                    .setParameter("username", username)
                    .uniqueResult();

            if (login == null) {
                return null; // user not found
            }

            String hashedPassword = login.getPassword();

            if (!PasswordUtil.checkPassword(password, hashedPassword)) {
                return null; // wrong password
            }

            transaction.commit();
            return login.getRole(); // return "ADMIN" or "USER"

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<SettingsDTO> getAllCourses() {
        List<SettingsDTO> usersDTOList = new ArrayList<>();
        try (Session session = factoryConfiguration.getSession()) {
            List<Login> users = session.createQuery("FROM Login ", Login.class).list();
            for (Login l : users) {
                usersDTOList.add(new SettingsDTO(
                        l.getFullName(),
                        l.getUserName(),
                        l.getRole()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersDTOList;
    }

    }



