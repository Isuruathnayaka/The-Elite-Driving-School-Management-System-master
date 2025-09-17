package com.example.the_elite_driving_school_management_system.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @Column(name = "payment_id")
    private String paymentId;

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private Student student;

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "payment")
    private Long payment;

    @Column(name = "status")
    private String status;

    public Payment() {
    }

    public Payment(String paymentId, Student student, String courseId, Long payment, String status) {
        this.paymentId = paymentId;
        this.student = student;
        this.courseId = courseId;
        this.payment = payment;
        this.status = status;
    }

    // Getters and Setters
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public Long getPayment() { return payment; }
    public void setPayment(Long payment) { this.payment = payment; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPaymentID() {
        return paymentId;
    }

    public String getCourseID() {
        return courseId;
    }
}
