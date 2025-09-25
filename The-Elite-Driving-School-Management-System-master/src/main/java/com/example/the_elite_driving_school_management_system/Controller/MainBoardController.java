package com.example.the_elite_driving_school_management_system.Controller;

import com.example.the_elite_driving_school_management_system.Bo.BOFactory;
import com.example.the_elite_driving_school_management_system.Bo.Custom.StudentBo;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainBoardController implements Initializable {
  private final StudentBo studentBo=(StudentBo) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);

    public Label txtInstrucorCount;
    public Label txtCourseCount;
    public Label txtStudentCount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int studentcount = studentBo.getStudentCount(); // Example: fetch count from DB

// Format to always show 2 digits (e.g., 01, 02, 04, 12, etc.)
        String formattedCount = String.format("%02d", studentcount);

// Set to label
        txtStudentCount.setText(formattedCount);

    }
}
