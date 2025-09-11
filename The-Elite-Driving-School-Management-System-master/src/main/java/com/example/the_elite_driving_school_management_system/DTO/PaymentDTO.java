package com.example.the_elite_driving_school_management_system.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private String paymentID;
    private String StudentID;
    private String CourseID;
    private Long Payment;
    private String Status;


}
