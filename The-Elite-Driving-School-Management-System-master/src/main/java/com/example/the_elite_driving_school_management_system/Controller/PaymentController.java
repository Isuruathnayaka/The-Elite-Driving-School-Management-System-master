package com.example.the_elite_driving_school_management_system.Controller;

import com.example.the_elite_driving_school_management_system.Bo.BOFactory;
import com.example.the_elite_driving_school_management_system.Bo.Custom.CourseBo;
import com.example.the_elite_driving_school_management_system.Bo.Custom.PaymentBo;
import com.example.the_elite_driving_school_management_system.Bo.Custom.StudentBo;
import com.example.the_elite_driving_school_management_system.DTO.PaymentDTO;
import com.example.the_elite_driving_school_management_system.TM.PaymentTM;
import jakarta.transaction.SystemException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
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
    public Button btnSave;
    public Button btnUpdateDetails;

    public void btnAddPayment(ActionEvent actionEvent) {
        ANCAddPaymentPart.setVisible(true);
    }

    public void btnUpdatePayment(ActionEvent actionEvent) {
        ANCAddPaymentPart.setVisible(true);

    }

    public void btnDeletePayment(ActionEvent actionEvent) {
        ANCAddPaymentPart.setVisible(false);
        PaymentTM selectedPayment=(PaymentTM) table.getSelectionModel().getSelectedItem();
        if (selectedPayment != null) {
            boolean isDeleted=paymentBo.deletePayment(selectedPayment.getPaymentId());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Payment deleted successfully").show();
                clear();
                loadTableData();
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Failed").show();
            }

        }



    }
    private void loadTableData() {
        ArrayList<PaymentDTO> allPayments = paymentBo.getAllPayments();
        ObservableList<PaymentTM> tableList = FXCollections.observableArrayList();

        if (allPayments != null) {
            for (PaymentDTO paymentDTO : allPayments) {
                PaymentTM tm = new PaymentTM(
                        paymentDTO.getPaymentId(),
                        paymentDTO.getStudentId(),
                        paymentDTO.getCourseId(),
                        paymentDTO.getPayment(),
                        paymentDTO.getStatus()
                );
                tableList.add(tm);
            }
        }

        table.setItems(tableList);
    }


    public void btnSave(ActionEvent actionEvent) {
       PaymentDTO paymentDTO= checkMatch();
       if (paymentDTO != null) {
           boolean isSaved=paymentBo.save(paymentDTO);
           if (isSaved) {
               new Alert(Alert.AlertType.INFORMATION, "Payment Saved").show();
               clear();
               loadTableData();

           }
           else {
               new Alert(Alert.AlertType.ERROR, "Payment Not Saved").show();
           }

       }else {
           new Alert(Alert.AlertType.INFORMATION, "Invalid").show();
       }

    }


    public void btnCancle(ActionEvent actionEvent) {
        ANCAddPaymentPart.setVisible(false);
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
        ANCAddPaymentPart.setVisible(false);
        setupColumns();
        loadTableData();
        statusComBox.getItems().addAll("Advance Paid", "Full Paid");
        txtPaymentId.setText(generateNewId());
        txtStudentId.setOnAction(actionEvent -> {
            try {
                loadCourse();
            } catch (SystemException e) {
                throw new RuntimeException(e);
            }
        });
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           if (newValue != null) {
               btnUpdatePayment.setVisible(true);
               btnDeletePayment.setVisible(true);
               setDataToFields((PaymentTM) newValue);

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

        //  Use the regex constants
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

        if (studentId.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter a Student ID").show();
            return;
        }

        PaymentBo paymentBo = (PaymentBo) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);

        // 1. Get unpaid courses for this student
        List<String> unpaidCourses = paymentBo.getUnpaidCoursesByStudent(studentId);

        // 2. Clear the ComboBox and add only unpaid courses
        cmbCourses.getItems().clear();
        cmbCourses.getItems().addAll(unpaidCourses);

        if (!unpaidCourses.isEmpty()) {
            cmbCourses.getSelectionModel().selectFirst();

            cmbCourses.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    System.out.println("Selected course: " + newVal);
                }
            });
        } else {
            new Alert(Alert.AlertType.INFORMATION, "All courses for this student are already paid!").show();
        }
    }



    private void setupColumns(){
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    private void setDataToFields(PaymentTM payment){
        txtPaymentId.setText(payment.getPaymentId());
        txtStudentId.setText(payment.getStudentId());
        cmbCourses.setValue(payment.getCourseId());
        txtPayment.setText(payment.getPayment().toString());
        statusComBox.setValue(payment.getStatus());
    }


    public void btnUpdateDetails(ActionEvent actionEvent) {
        PaymentDTO paymentDTO = checkMatch();
        if (paymentDTO != null) {
            boolean isUpdated= paymentBo.update(paymentDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Updated").show();
                loadTableData();
                clear();
                ANCAddPaymentPart.setVisible(false);
            }else {
                new Alert(Alert.AlertType.ERROR, "Invalid Payment").show();
            }
        }
    }
}
