package com.example.the_elite_driving_school_management_system.Controller;

import com.example.the_elite_driving_school_management_system.Util.AnimationUtil;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public AnchorPane ANCMain;


    public ImageView btnStudentManage;
    public ImageView btnCourcesManage;
    public ImageView btnDashboard;
    public ImageView btnSettings;
    public ImageView btnInstructorManage;
    public ImageView btnPayment;
    public ImageView btnScheduleManagement;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnimationUtil.addHoverAnimation(btnDashboard);
        AnimationUtil.addHoverAnimation(btnInstructorManage);
        AnimationUtil.addHoverAnimation(btnStudentManage);
        AnimationUtil.addHoverAnimation(btnScheduleManagement);
        AnimationUtil.addHoverAnimation(btnCourcesManage);
        AnimationUtil.addHoverAnimation(btnPayment);
        AnimationUtil.addHoverAnimation(btnSettings);

         buttons();


    }

    public void buttons(){
        btnStudentManage.setOnMouseClicked(mouseEvent -> {
            navigateTo("/com/example/the_elite_driving_school_management_system/view/Student.fxml",ANCMain);
        });
        btnInstructorManage.setOnMouseClicked(mouseEvent -> {
            navigateTo("/com/example/the_elite_driving_school_management_system/view/Instructor.fxml",ANCMain);
        });
        btnCourcesManage.setOnMouseClicked(mouseEvent -> {
            navigateTo("/com/example/the_elite_driving_school_management_system/view/CourseManage.fxml",ANCMain);
        });
        btnScheduleManagement.setOnMouseClicked(mouseEvent -> {
            navigateTo("/com/example/the_elite_driving_school_management_system/view/LessonSchedule.fxml",ANCMain);
        });
        btnPayment.setOnMouseClicked(mouseEvent -> {
            navigateTo("/com/example/the_elite_driving_school_management_system/view/Payment.fxml",ANCMain);
        });
    }
    public static void navigateTo(String path, AnchorPane ANCMain) {
        try {
            ANCMain.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(DashboardController.class.getResource(path));

            anchorPane.prefWidthProperty().bind( ANCMain.widthProperty());
            anchorPane.prefHeightProperty().bind( ANCMain.heightProperty());

            ANCMain.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }

}
