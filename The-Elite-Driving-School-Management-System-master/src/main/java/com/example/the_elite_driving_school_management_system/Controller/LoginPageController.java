package com.example.the_elite_driving_school_management_system.Controller;

import com.example.the_elite_driving_school_management_system.Bo.BOFactory;
import com.example.the_elite_driving_school_management_system.Bo.Custom.LoginBo;
import com.example.the_elite_driving_school_management_system.Bo.Custom.SettingsBo;
import com.example.the_elite_driving_school_management_system.DTO.LoginDTO;
import com.mysql.cj.result.BooleanValueFactory;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class LoginPageController implements Initializable {
    private final SettingsBo settingsBo=(SettingsBo) BOFactory.getInstance().getBO(BOFactory.BOType.SETTINGS);
    public TextField signInUserName;
    public TextField signInEmail;
    public PasswordField signInPassword;
    public Label SignUptxtButton;
    public TextField signUpSectionUserName;
    public TextField signUpsecssionEmail;
    public PasswordField signUpSecssionPassword;
    public Label signInMessageButton;
    public AnchorPane ANCSignIn;
    public AnchorPane ANCSignUp;

    String namePattern = "^[A-Za-z ]{2,}$";
    String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

   // LoginBo loginBo= (LoginBo) BOFactory.getInstance().getBO(BOFactory.BOType.LOGIN);
   public void btnSignIn(ActionEvent actionEvent) throws IOException {
       String username = signInUserName.getText().trim();
       String password = signInPassword.getText().trim();

       if (username.isEmpty() || password.isEmpty()) {
           new Alert(Alert.AlertType.WARNING, "Please enter username and password").show();
           return;
       }

       try {
           String role = settingsBo.validateLoginDetails(username, password);

           if (role == null) {
               new Alert(Alert.AlertType.ERROR, "Invalid username or password").show();
               return;
           }

           switch (role.toUpperCase()) {
               case "ADMIN":
                   uiLoad("/com/example/the_elite_driving_school_management_system/view/Dashboard.fxml", actionEvent);
                   break;
               case "USER":
                   uiLoad("/com/example/the_elite_driving_school_management_system/view/UserDashboard.fxml", actionEvent);
                   break;
               default:
                   new Alert(Alert.AlertType.ERROR, "Unknown role").show();
           }

       } finally {
           // nothing needed here
       }
   }

    private void uiLoad(String path, ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Dashboard");
        stage.show();

        // Close current login window
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        signInAnimation();



    }
    public void signInAnimation(){
        ANCSignIn.setTranslateX(-400);

        //signIn Animation
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(ANCSignIn);
        slide.setFromX(-400);
        slide.setToX(0);
        slide.play();
    }


}
