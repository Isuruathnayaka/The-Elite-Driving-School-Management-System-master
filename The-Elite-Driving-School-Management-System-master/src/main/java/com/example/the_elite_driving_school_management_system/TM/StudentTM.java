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
    private String courses;     // must match PropertyValueFactory
    private String courseID;    // must match PropertyValueFactory

    public StudentTM(String studentId, String name, int age, String address, String contact,
                     String email, LocalDate date, String courses, String courseID) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.date = date;
        this.courses = courses;
        this.courseID = courseID;
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getAddress() { return address; }
    public String getContact() { return contact; }
    public String getEmail() { return email; }
    public LocalDate getDate() { return date; }
    public String getCourses() { return courses; }
    public String getCourseID() { return courseID; }

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
        return courseID;
    }
}
