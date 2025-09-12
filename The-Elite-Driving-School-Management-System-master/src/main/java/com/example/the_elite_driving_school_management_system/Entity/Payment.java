package com.example.the_elite_driving_school_management_system.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @Column(name = "payment_id", nullable = false, unique = true)
    private String id;

    @Column(name = "course_id", nullable = false)
    private String courseId;

    @Column(name = "payment", nullable = false)
    private Long paymentAmount;

    @Column(name = "status", nullable = false)
    private String status;

    // Each payment belongs to ONE student
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false) // foreign key column
    private Student student;

    // ---- Constructors ----
    public Payment() {
    }

    public Payment(String id, String courseId, Long paymentAmount, String status, Student student) {
        this.id = id;
        this.courseId = courseId;
        this.paymentAmount = paymentAmount;
        this.status = status;
        this.student = student;
    }

    // ---- Getters & Setters ----
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Long getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Long paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
