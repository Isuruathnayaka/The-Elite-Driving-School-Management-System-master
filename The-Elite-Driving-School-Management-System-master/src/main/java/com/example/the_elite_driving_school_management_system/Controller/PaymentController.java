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
    @FXML
    public TextField txtCourseId;
    public TextField txtPayment;
    public ComboBox statusComBox;
    private final PaymentBo paymentBo=(PaymentBo) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);
    private final String studentId="^S\\d{3}$\n";
    private final String courseId="^C\\d{3}$\n";
    public ComboBox cmbCourses;

    public void btnAddPayment(ActionEvent actionEvent) {
        ANCAddPaymentPart.setVisible(true);
    }

    public void btnUpdatePayment(ActionEvent actionEvent) {
    }

    public void btnDeletePayment(ActionEvent actionEvent) {
    }

    public void btnSave(ActionEvent actionEvent) {
        PaymentDTO paymentDTO=checkMatch();
        if(paymentDTO!=null){
            boolean isSaved=paymentBo.save(paymentDTO);
            clear();
            if(isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Payment Saved").show();

            }
            else{
                new Alert(Alert.AlertType.ERROR,"Payment Not Saved").show();
            }
        }
    }

    public void btnCancle(ActionEvent actionEvent) {
    }
    public void clear(){
        txtPaymentId.setText(generateNewId());
        txtStudentId.clear();
        txtCourseId.clear();
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

        return null;
    }
    private void loadCourse() throws SystemException {
        String studentId = txtStudentId.getText().trim();

        StudentBo studentBo = (StudentBo) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
        CourseBo courseBo = (CourseBo) BOFactory.getInstance().getBO(BOFactory.BOType.COURSE);

        List<String> courseIds = studentBo.getCourseIdsByStudent(studentId);

        cmbCourses.getItems().clear();
        cmbCourses.getItems().addAll(courseIds);

        if (!courseIds.isEmpty()) {
            // Select first course
            String firstCourseId = courseIds.get(0);
            cmbCourses.getSelectionModel().select(firstCourseId);

            // Show in CourseId field
            txtCourseId.setText(firstCourseId);

            // 🔑 Get fee from Course table
            String fee = courseBo.getCourseFeeByCourseId(firstCourseId);
            if (fee != null) {
                txtPayment.setText(fee);
            } else {
                txtPayment.clear();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "No courses found for student: " + studentId).show();
        }
    }


}
