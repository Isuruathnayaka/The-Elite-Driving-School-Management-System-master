package com.example.the_elite_driving_school_management_system.TM;

import java.time.LocalDate;

public class StudentTM implements Comparable<StudentTM> {
    private String studentId;   // must match PropertyValueFactory in controller
    private String name;
    private int age;
    private String address;
    private String contact;
    private String email;
    private LocalDate date;     // must match PropertyValueFactory
    private String courseType;
    private String courses;    // must match PropertyValueFactory

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public StudentTM(String studentId, String name, int age, String address, String contact,
                     String email, LocalDate date, String courses, String courseID) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.date = date;
        this.courseType = courses;
        this.courses = courseID;

    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getAddress() { return address; }
    public String getContact() { return contact; }
    public String getEmail() { return email; }
    public LocalDate getDate() { return date; }
    public String getCourses() { return courses; }

    @Override
    public int compareTo(StudentTM o) {
        return this.studentId.compareTo(o.studentId);
    }

    public String getId() {
        return studentId;
    }

    public String getCourse() {
        return courses;
    }


    public String getCourseId() {
        return courses;
    }
}
