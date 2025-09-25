package com.example.the_elite_driving_school_management_system.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SettingsTM {
    private String Name;
    private String userName;
    private String password;
    private String role;

    public SettingsTM(String name, String userName, String role) {
        Name = name;
        this.userName = userName;
        this.role = role;
    }
}
