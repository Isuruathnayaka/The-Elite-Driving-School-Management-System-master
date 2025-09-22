package com.example.the_elite_driving_school_management_system.DTO;

import com.example.the_elite_driving_school_management_system.Entity.Student;

public class PaymentDTO {
    private String paymentId;
    private String studentId;
    private String courseId;
    private Long payment;
    private String status;

    public PaymentDTO(String paymentID, Student student, String courseID, Long payment, String status) {
        this.paymentId = paymentID;
        this.studentId = student.getId();
        this.courseId = courseID;
        this.payment = payment;
        this.status = status;

    }

    public PaymentDTO(String paymentId, String studentId, String courseId, Long payment, String status) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.payment = payment;
        this.status = status;
    }



    // Getters and Setters
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

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
