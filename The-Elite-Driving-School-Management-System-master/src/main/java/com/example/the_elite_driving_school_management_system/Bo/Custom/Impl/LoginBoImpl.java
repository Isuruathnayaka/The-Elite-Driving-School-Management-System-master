//package com.example.the_elite_driving_school_management_system.Bo.Custom.Impl;
//
//import com.example.the_elite_driving_school_management_system.Bo.Custom.LoginBo;
//import com.example.the_elite_driving_school_management_system.Bo.MapUtil;
//import com.example.the_elite_driving_school_management_system.DAO.Custom.LoginDAO;
//import com.example.the_elite_driving_school_management_system.DTO.LoginDTO;
//import com.example.the_elite_driving_school_management_system.DTO.StudentDTO;
//import com.example.the_elite_driving_school_management_system.Entity.Login;
//import com.example.the_elite_driving_school_management_system.Util.PasswordUtil;
//
//public class LoginBoImpl implements LoginBo {
//
//    private final LoginDAO loginDAO; // mark as final
//
//    // Constructor injection keeps loose coupling
//    public LoginBoImpl(LoginDAO loginDAO) {
//        this.loginDAO = loginDAO;
//    }
//
////    @Override
////    public boolean saveLogin(LoginDTO dto) {
////        // Convert DTO → Entity
////        String encryptedPassword = PasswordUtil.hashPassword(dto.getPassword());
////        dto.setPassword(encryptedPassword);
////        return loginDAO.save(MapUtil.toEntity(dto));
////    }
//    @Override
//    public boolean validateLogin(String loginEmail, String loginPassword) {
//        // 1️⃣ Get the login entity from DAO
//        Login login = loginDAO.findByEmail(loginEmail);
//
//        if (login != null) {
//            // 2️ Compare entered password with hashed password
//            return PasswordUtil.checkPassword(loginPassword, login.getPassword());
//        }
//
//        return false; // user not found or password mismatch
//    }
//
//    @Override
//    public boolean validateLoginDetails(String email, String password) {
//        Login login = loginDAO.findByEmail(email);
//        if (login != null) {
//            return PasswordUtil.checkPassword(password, login.getPassword());
//        }
//        return false;
//    }
//
//
//
//    @Override
//    public boolean save(LoginDTO dto) {
//        // Convert DTO → Entity
//        String encryptedPassword = PasswordUtil.hashPassword(dto.getPassword());
//        dto.setPassword(encryptedPassword);
//        return loginDAO.save(MapUtil.toEntity(dto));
//    }
//
//
//
//    @Override
//    public boolean update(LoginDTO dto) {
//        return false;
//    }
//}
