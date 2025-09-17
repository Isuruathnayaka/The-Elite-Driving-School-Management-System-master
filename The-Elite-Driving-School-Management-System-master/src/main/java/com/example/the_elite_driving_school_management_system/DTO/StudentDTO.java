package com.example.the_elite_driving_school_management_system.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String studentID;
    private String name;
    private int age;
    private String address;
    private String contact;
    private String email;
    private LocalDate registrationDate;
    private String course;   // for course type or description
    private List<String> courseIdList;  // multiple course IDs

    public StudentDTO(String studentId, String name, int age, String address, String contact, String email, LocalDate date, String courses, String s, List<String> selectedCourseIds) {
        this.studentID = studentId;
        this.name = name;
        this.age = age;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.registrationDate = date;
        this.course = courses;
        this.courseIdList = selectedCourseIds;

    }

    // --- Getter for multiple course IDs ---


    public LocalDate getDate() {
        return registrationDate;
    }

    public String getCourseType() {
        return course;
    }

    public String[] getCourseId() {
        return courseIdList.toArray(new String[0]);
    }

    // other getters/setters...
}