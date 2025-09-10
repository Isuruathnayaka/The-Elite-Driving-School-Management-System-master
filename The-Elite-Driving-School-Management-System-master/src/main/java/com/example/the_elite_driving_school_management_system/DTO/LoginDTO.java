package com.example.the_elite_driving_school_management_system.DTO;

public class LoginDTO {
    private String username;
    private String password;
    private String email;


    private String loginPassword;
    private String loginEmail;

    public LoginDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public LoginDTO(String loginEmail, String loginPassword) {
        this.loginEmail = loginEmail;
        this.loginPassword = loginPassword;

    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


}
