package com.example.the_elite_driving_school_management_system.Util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    // Encrypt password
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    // Verify password (for login)
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
