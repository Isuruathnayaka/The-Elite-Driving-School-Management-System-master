package com.example.the_elite_driving_school_management_system.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @Column(name = "InstructorId", nullable = false, unique = true)

    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "contact", nullable = false)
    private String contact;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "registrationDate", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "courseType", nullable = false)
    private String courseType;
    @Column(name = "courseId", nullable = false)
    private String courseId;


    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new ArrayList<>();

    public Instructor() {}

    public Instructor(String instructorID, String name, int age, String address, String contact, String email, LocalDate date, String course, String courseId) {
        this.id = instructorID;
        this.name = name;
        this.age = age;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.registrationDate = date;
        this.courseType = course;
        this.courseId = courseId;
    }
    public String getCourse() {
        return courseType;
    }

    public LocalDate getDate() {
        return registrationDate;
    }

    public String getInstructorID() {
        return id;
    }

    public String getCourseId() {
        return courseId;
    }
}
