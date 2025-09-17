package com.example.the_elite_driving_school_management_system.Controller;

import com.example.the_elite_driving_school_management_system.Bo.BOFactory;
import com.example.the_elite_driving_school_management_system.Bo.Custom.CourseBo;
import com.example.the_elite_driving_school_management_system.Bo.Custom.PaymentBo;
import com.example.the_elite_driving_school_management_system.Bo.Custom.StudentBo;
import com.example.the_elite_driving_school_management_system.DTO.PaymentDTO;
import jakarta.transaction.SystemException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class PaymentController implements Initializable {
    public TableColumn colPaymentId;
    public TableColumn colStudentID;
    public TableView table;
    public TableColumn colCourseId;
    public TableColumn colPayment;
    public TableColumn colStatus;
    public Button addpayment;
    public Button btnUpdatePayment;
    public Button btnDeletePayment;
    public AnchorPane ANCAddPaymentPart;
    public TextField txtPaymentId;
    public TextField txtStudentId;


    public TextField txtPayment;
    public ComboBox statusComBox;
    private final PaymentBo paymentBo=(PaymentBo) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);
    private final String studentId="^S\\d{3}$\n";
    private final String courseId="^C\\d{3}$\n";
    // Remove the \n !!!
    private final String studentIdPattern = "^S\\d{3}$";      // S001, S123
    private final String courseIdPattern = "^C\\d{3,4}$";     // C001, C123, C1001


    public ComboBox cmbCourses;

    public void btnAddPayment(ActionEvent actionEvent) {
        ANCAddPaymentPart.setVisible(true);
    }

    public void btnUpdatePayment(ActionEvent actionEvent) {
    }

    public void btnDeletePayment(ActionEvent actionEvent) {
    }

    public void btnSave(ActionEvent actionEvent) {
       PaymentDTO paymentDTO= checkMatch();
       if (paymentDTO != null) {
           boolean isSaved=paymentBo.save(paymentDTO);
           if (isSaved) {
               new Alert(Alert.AlertType.INFORMATION, "Payment Saved").show();
               clear();

           }
           else {
               new Alert(Alert.AlertType.ERROR, "Payment Not Saved").show();
           }

       }else {
           new Alert(Alert.AlertType.INFORMATION, "Invalid").show();
       }

    }


    public void btnCancle(ActionEvent actionEvent) {
    }
    public void clear(){
        txtPaymentId.setText(generateNewId());
        txtStudentId.clear();
        cmbCourses.setValue(null);
        txtPayment.clear();
        statusComBox.setValue(null);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ANCAddPaymentPart.setVisible(true);
        statusComBox.getItems().addAll("Advance Paid", "Full Paid");
        txtPaymentId.setText(generateNewId());
        txtStudentId.setOnAction(actionEvent -> {
            try {
                loadCourse();
            } catch (SystemException e) {
                throw new RuntimeException(e);
            }
        });

    }
    private String generateNewId() {
        try {
            String id = String.valueOf(paymentBo.generateNewPaymentId());
            if (id != null) return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "P001";
    }
    public PaymentDTO checkMatch() {
        String id = txtPaymentId.getText();
        String studentIdText = txtStudentId.getText();
        String courseIdText = (String) cmbCourses.getValue();
        String status = (String) statusComBox.getValue();

        // Validate payment amount
        Long payment;
        try {
            payment = Long.valueOf(txtPayment.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid payment amount").show();
            return null;
        }

        // ✅ Use the regex constants
        boolean isValidStudentId = studentIdText.matches(studentIdPattern);
        boolean isValidCourseId = courseIdText != null && courseIdText.matches(courseIdPattern);

        if (isValidStudentId && isValidCourseId) {
            return new PaymentDTO(id, studentIdText, courseIdText, payment, status);
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Student ID or Course ID").show();
            return null;
        }
    }


    private void loadCourse() throws SystemException {
        String studentId = txtStudentId.getText().trim();

        StudentBo studentBo = (StudentBo) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);

        List<String> courseIds = studentBo.getCourseIdsByStudent(studentId);

        cmbCourses.getItems().clear();
        cmbCourses.getItems().addAll(courseIds);

        if (!courseIds.isEmpty()) {

            cmbCourses.getSelectionModel().selectFirst();


            cmbCourses.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    System.out.println("Selected course: " + newVal);

                }
            });
        } else {
            new Alert(Alert.AlertType.ERROR, "No courses found for student: " + studentId).show();
        }
    }





}
