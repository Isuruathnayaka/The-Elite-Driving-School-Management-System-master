package com.example.the_elite_driving_school_management_system.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "studentId", nullable = false, unique = true)

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();


    public Student(String studentID, String name, int age, String address, String contact, String email, LocalDate date, String courseType) {
        this.id = studentID;
        this.name = name;
        this.age = age;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.registrationDate = date;
        this.courseType = courseType;

    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new ArrayList<>();

    // Default constructor (required by JPA)
    public Student() {}

    // Full constructor
    public Student(String id, String name, int age, String address, String contact,
                   String email, LocalDate registrationDate, String courseType, String courseId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.registrationDate = registrationDate;
        this.courseType = courseType;

    }





    // Getters and Setters
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    // Essential methods for entity classes
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                ", courseType='" + courseType + '\'' +
                '}';
    }

    public String getStudentID() {
        return id;
    }

    public LocalDate getDate() {
        return registrationDate;
    }

    public String getCourse() {
        return courseType;
    }



}