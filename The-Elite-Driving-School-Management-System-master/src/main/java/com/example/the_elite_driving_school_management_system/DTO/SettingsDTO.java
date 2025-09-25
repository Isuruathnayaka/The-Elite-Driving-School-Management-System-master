package com.example.the_elite_driving_school_management_system.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SettingsDTO {
    private String Name;
    private String userName;
    private String password;
    private String role;

    public SettingsDTO(String fullName, String userName, String role) {
        Name = fullName;
        this.userName = userName;
        this.role = role;
    }
}
