package com.example.the_elite_driving_school_management_system.Entity;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO-INCREMENT
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column (name = "name", nullable = false)
    private String fullName;
    @Column(name = "username",updatable = false,nullable = false)
    private String username;
    @Column(name = "password",nullable =false,unique = true)
    private String password;
    @Column(name = "role",nullable = false)
    private String role;


    public Login() {

    }
    public Login(String username, String password, String name) {
        this();
        this.fullName = name;
        this.username = username;
        this.password = password;

    }

    public Login(String name, String userName, String password, String role) {
        this.fullName = name;
        this.username = userName;
        this.password = password;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public String getName() {
        return fullName;
    }

    public String getUserName() {
        return username;
    }
}
