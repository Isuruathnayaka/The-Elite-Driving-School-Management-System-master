package com.example.the_elite_driving_school_management_system.Bo.Custom;

import com.example.the_elite_driving_school_management_system.Bo.CrudBo;
import com.example.the_elite_driving_school_management_system.DTO.SettingsDTO;

public interface SettingsBo extends CrudBo<SettingsDTO> {
    String validateLoginDetails(String username, String password);
}
