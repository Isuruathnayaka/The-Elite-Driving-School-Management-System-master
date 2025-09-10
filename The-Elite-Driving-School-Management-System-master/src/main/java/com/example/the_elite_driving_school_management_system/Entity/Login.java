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
    @Column(name = "username",updatable = false,nullable = false)
    private String username;
    @Column(name = "password",nullable =false)
    private String password;
    @Column(name = "email" ,updatable = false,nullable = false,unique = true)
    private String email;

    public Login() {

    }
    public Login(String username, String password, String email) {
        this();
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
