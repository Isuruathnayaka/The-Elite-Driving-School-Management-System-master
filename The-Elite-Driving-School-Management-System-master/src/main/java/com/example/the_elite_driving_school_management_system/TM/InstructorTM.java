package com.example.the_elite_driving_school_management_system.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor

public class InstructorTM implements  Comparable<InstructorTM> {
    private String instructorId;   // must match PropertyValueFactory in controller
    private String name;
    private int age;
    private String address;
    private String contact;
    private String email;
    private LocalDate date;     // must match PropertyValueFactory
    private String courses;     // must match PropertyValueFactory
   // private String courseID;


    @Override
    public int compareTo(InstructorTM o) {
       return this.instructorId.compareTo(o.instructorId);
    }
}

