package com.example.the_elite_driving_school_management_system.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LessonScheduleController implements Initializable {
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
    public TextField txtStudentID;
    public TextField txtCourseID;
    public DatePicker txtDate;
    public TextField txtTime;
    public TextField txtStudentIName;
    public TextField txtInstructorID;
    public TextField txtInstructorName;
    public ComboBox txtStatus;
    public Button btnEditeLesson;
    public Button btnDelete;

    public void btnSheduleBtn(ActionEvent actionEvent) {
        addCourseANC.setVisible(true);

    }

    public void btnsave(ActionEvent actionEvent) {

    }

    public void btnUpdate(ActionEvent actionEvent) {
    }

    public void btnEditLesson(ActionEvent actionEvent) {
    }

    public void btnDelete(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
