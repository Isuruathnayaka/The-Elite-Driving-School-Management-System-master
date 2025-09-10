package com.example.the_elite_driving_school_management_system.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class InstructorDTO {
    private String instructorID;
    private String name;
    private int age;
    private String address;
    private String contact;
    private String email;
    private LocalDate date;
    private String course;
  //  private String courseId;

    public LocalDate getRegistrationDate() {
        return date;
    }
}

