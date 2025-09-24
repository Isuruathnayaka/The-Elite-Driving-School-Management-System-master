package com.example.the_elite_driving_school_management_system.Controller;


import com.example.the_elite_driving_school_management_system.Bo.BOFactory;
import com.example.the_elite_driving_school_management_system.Bo.Custom.LessonScheduleBo;
import com.example.the_elite_driving_school_management_system.Bo.Custom.PaymentBo;
import com.example.the_elite_driving_school_management_system.DTO.LessonDTO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class LessonScheduleController implements Initializable {
    private final LessonScheduleBo lessonBO=(LessonScheduleBo) BOFactory.getInstance().getBO(BOFactory.BOType.LESSON);
    private final String namePattern = "^[A-Za-z ]+$";

    public AnchorPane ANCCourse;
    public TableView table;
    public TableColumn colLessonID;
    public TableColumn colLessonTitle;
    public TableColumn colDuration;
    public TableColumn colCourseId;
    public TableColumn colTime;
    public TableColumn colDate;
    public TableColumn colStudentID;
    public TableColumn colStudentName;
    public TableColumn colInstructorId;
    public TableColumn colInstructorName;
    public TableColumn colStatus;
    public Button btnSheduleBtn;
    public AnchorPane addCourseANC;
    public Label txtLessonID;
    public TextField txtName;
    public TextField txtDuration;

    public TextField txtCourseID;
    public DatePicker txtDate;
    public TextField txtTime;

    public ComboBox txtStatus;
    public Button btnEditeLesson;
    public Button btnDelete;
    public AnchorPane ANCValidationView;
    public TextField txtStudentIdValidate;
    public Button btnCheckValidation;
    public ComboBox txtStudentID;
    public ComboBox txtInstructorID;

    public void btnSheduleBtn(ActionEvent actionEvent) {
        addCourseANC.setVisible(true);

    }

    public void btnsave(ActionEvent actionEvent) {
        LessonDTO lessonDTO=checkMatch();
        if (lessonDTO!=null) {
            boolean isSaved=lessonBO.save(lessonDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Lesson saved successfully").show();
                refresh();
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Lesson could not be saved").show();
            }
        }


    }

    public void btnUpdate(ActionEvent actionEvent) {
    }

    public void btnEditLesson(ActionEvent actionEvent) {
    }

    public void btnDelete(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //ANCValidationView.setVisible(true);
        refresh();
        txtStatus.getItems().addAll("Scheduled","Completed","Cancelled");
        txtCourseID.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                loadStudentsAndInstructor();
            }
        });


    }
    public LessonDTO checkMatch(){
        String lessonID = txtLessonID.getText();
        String lessonName = txtName.getText();
        String duration = txtDuration.getText();
        String courseID = txtCourseID.getText();
        LocalDate date = txtDate.getValue();
        String time = txtTime.getText();
        String status = txtStatus.getSelectionModel().getSelectedItem().toString();
        String studentID = txtStudentID.getSelectionModel().getSelectedItem().toString();
        String instructorID = txtInstructorID.getSelectionModel().getSelectedItem().toString();
        boolean isValidName = lessonName.matches(namePattern);
        if (isValidName){
            return new LessonDTO(lessonID,lessonName,duration,courseID,date,time,status,studentID,instructorID);
        }
        return null;

    }

    public void btnCheckValidation(ActionEvent actionEvent) {

        String studentValidateID = txtStudentIdValidate.getText();
        if(studentValidateID.matches("^S\\d{3}$")) {

            ANCValidationView.setVisible(false);

        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Input OR Student Not Pay for the Course").show();
        }
    }
    private void loadStudentsAndInstructor() {
        String courseId = txtCourseID.getText().trim();

        if (courseId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter a Course ID").show();
            return;
        }

        try {
            // Load student IDs
            List<String> studentIds = lessonBO.getStudentIdsByCourse(courseId);
            txtStudentID.getItems().clear();
            txtStudentID.getItems().addAll(studentIds);

            // Load instructor ID
            String instructorId = lessonBO.getInstructorIdByCourse(courseId);
            txtInstructorID.getItems().clear();
            if (instructorId != null && !instructorId.isEmpty()) {
                txtInstructorID.getItems().add(instructorId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load students or instructor").show();
        }
    }
    private String generateNewId() {
        try {
            String id = String.valueOf(lessonBO.generateNewLessonId());
            if (id != null) return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "L001";
    }
    private void refresh(){
        txtLessonID.setText(generateNewId());
        txtName.clear();
        txtDuration.clear();
        txtCourseID.clear();
        txtStatus.getItems().clear();
        txtStudentID.getItems().clear();
        txtInstructorID.getItems().clear();
        txtStatus.getSelectionModel().clearSelection();
        txtStudentID.getSelectionModel().clearSelection();
    }
}
