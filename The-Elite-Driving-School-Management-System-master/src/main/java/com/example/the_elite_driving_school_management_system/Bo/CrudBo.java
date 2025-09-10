package com.example.the_elite_driving_school_management_system.Bo;

import com.example.the_elite_driving_school_management_system.DTO.StudentDTO;

public interface CrudBo<T> extends SuperBO {
 boolean save(T dto);

 boolean update(T dto);

}
