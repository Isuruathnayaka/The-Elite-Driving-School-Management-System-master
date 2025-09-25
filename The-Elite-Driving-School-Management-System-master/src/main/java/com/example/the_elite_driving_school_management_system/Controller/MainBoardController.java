package com.example.the_elite_driving_school_management_system.Controller;

import com.example.the_elite_driving_school_management_system.Bo.BOFactory;
import com.example.the_elite_driving_school_management_system.Bo.Custom.CourseBo;
import com.example.the_elite_driving_school_management_system.Bo.Custom.InstructorBo;
import com.example.the_elite_driving_school_management_system.Bo.Custom.StudentBo;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainBoardController implements Initializable {
  private final StudentBo studentBo=(StudentBo) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
    private final InstructorBo instructorBo=(InstructorBo) BOFactory.getInstance().getBO(BOFactory.BOType.INSTRUCTOR);
    private final CourseBo courseBo=(CourseBo) BOFactory.getInstance().getBO(BOFactory.BOType.COURSE);

    public Label txtInstrucorCount;
    public Label txtCourseCount;
    public Label txtStudentCount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int studentcount = studentBo.getStudentCount();
        String formattedCount = String.format("%02d", studentcount);
        txtStudentCount.setText(formattedCount);

       int InstrucorCount=instructorBo.getInstructorCount();
         String formattedInstructorCount = String.format("%02d", InstrucorCount);
         txtInstrucorCount.setText(formattedInstructorCount);
       int CourseCount=courseBo.getCourseCount();
       String formattedCourseCount = String.format("%02d", CourseCount);
       txtCourseCount.setText(formattedCourseCount);


    }
}
