package com.example.the_elite_driving_school_management_system.Controller;

import com.example.the_elite_driving_school_management_system.Bo.BOFactory;
import com.example.the_elite_driving_school_management_system.Bo.Custom.PaymentBo;
import com.example.the_elite_driving_school_management_system.Bo.Custom.StudentBo;
import com.example.the_elite_driving_school_management_system.DTO.PaymentDTO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
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
    public TextField txtCourseId;
    public TextField txtPayment;
    public ComboBox statusComBox;
    private final PaymentBo paymentBo=(PaymentBo) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);
    private final String studentId="^S\\d{3}$\n";
    private final String courseId="^C\\d{3}$\n";

    public void btnAddPayment(ActionEvent actionEvent) {
        ANCAddPaymentPart.setVisible(true);
    }

    public void btnUpdatePayment(ActionEvent actionEvent) {
    }

    public void btnDeletePayment(ActionEvent actionEvent) {
    }

    public void btnSave(ActionEvent actionEvent) {
    }

    public void btnCancle(ActionEvent actionEvent) {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ANCAddPaymentPart.setVisible(true);
        statusComBox.getItems().addAll("Advance Paid", "Full Paid");
        txtPaymentId.setText(generateNewId());

    }
    private String generateNewId() {
        try {
            String id = String.valueOf(paymentBo.generateNewStudentId());
            if (id != null) return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "P001";
    }
    public PaymentDTO checkMatch(){
        String id = txtPaymentId.getText();
        String studentId = txtStudentId.getText();
        String courseId = txtCourseId.getText();
        Long payment = Long.valueOf(txtPayment.getText());
        String status= (String) statusComBox.getValue();

        boolean isValidStudentId = studentId.matches(studentId);
        boolean isValidCourseId = courseId.matches(courseId);
        if (isValidStudentId && isValidCourseId){
            return new PaymentDTO(id, studentId, courseId, payment, status);
        }

        return new PaymentDTO(id,studentId,courseId,payment,status);
    }
}
