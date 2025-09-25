package com.example.the_elite_driving_school_management_system.Controller;

import com.example.the_elite_driving_school_management_system.Util.AnimationUtil;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserDashboardController implements Initializable {
    public AnchorPane ANCMain;
    public ImageView btnScheduleManagement;
    public ImageView btnPayment;
    public ImageView btnStudentManage;
    public ImageView btnDashboard;
    public AnchorPane ANCMainSize;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnimationUtil.addHoverAnimation(btnDashboard);
        AnimationUtil.addHoverAnimation(btnStudentManage);
        AnimationUtil.addHoverAnimation(btnScheduleManagement);
        AnimationUtil.addHoverAnimation(btnPayment);

            navigateTo("/com/example/the_elite_driving_school_management_system/view/MainBoard.fxml",ANCMain);



        buttons();


    }

    public void buttons(){
        btnDashboard.setOnMouseClicked(mouseEvent -> {
            navigateTo("/com/example/the_elite_driving_school_management_system/view/MainBoard.fxml",ANCMain);
        });
        btnStudentManage.setOnMouseClicked(mouseEvent -> {
            navigateTo("/com/example/the_elite_driving_school_management_system/view/Student.fxml",ANCMain);
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

