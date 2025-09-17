package com.example.the_elite_driving_school_management_system.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "courseId", nullable = false, unique = true)
    private String id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "duration", nullable = false)
    private String duration;
    @Column(name = "fee", nullable = false)
    private Double fee;
    @Column (name = "description", nullable = false)
    private String description;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();


    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new ArrayList<>();


    public Course(String id, String name, String duration, Double fee, String description) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.fee = fee;
        this.description = description;
    }

    public Course() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getCourseId() {
        return id;
    }
}
