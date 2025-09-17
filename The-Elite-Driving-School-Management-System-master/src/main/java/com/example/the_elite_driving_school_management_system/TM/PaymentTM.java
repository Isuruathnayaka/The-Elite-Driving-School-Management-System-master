package com.example.the_elite_driving_school_management_system.TM;

public class PaymentTM {
    private String paymentId;
    private String studentId;
    private String courseId;
    private Long payment;
    private String status;

    public PaymentTM(String paymentId, String studentId, String courseID, Long payment, String status) {

    this.paymentId = paymentId;
    this.studentId = studentId;
    this.courseId = courseID;
    this.payment = payment;
    this.status = status;}


    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Long getPayment() {
        return payment;
    }

    public void setPayment(Long payment) {
        this.payment = payment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
