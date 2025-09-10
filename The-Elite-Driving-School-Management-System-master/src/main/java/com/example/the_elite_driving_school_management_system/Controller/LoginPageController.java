package com.example.the_elite_driving_school_management_system.Controller;

import com.example.the_elite_driving_school_management_system.Bo.BOFactory;
import com.example.the_elite_driving_school_management_system.Bo.Custom.LoginBo;
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

import java.net.URL;
import java.util.ResourceBundle;



public class LoginPageController implements Initializable {
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

    LoginBo loginBo= (LoginBo) BOFactory.getInstance().getBO(BOFactory.BOType.LOGIN);
    public void btnSignIn(ActionEvent actionEvent) {
        String email = signInEmail.getText().trim();
        String password = signInPassword.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter username and password").show();
            return;
        }

        try {

            boolean isValid = loginBo.validateLoginDetails(email, password);

            if (isValid) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/the_elite_driving_school_management_system/view/Dashboard.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Dashboard");
                stage.show();
                // Close current window
                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                currentStage.close();

            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid username or password").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error during login").show();
        }

        // Clear input fields
        signInUserName.clear();
        signInPassword.clear();
    }

    public void btnSignUp(ActionEvent actionEvent) {
       LoginDTO loginDTO = checkMach();
        boolean isSaved = loginBo.save(loginDTO);
        clearAllText();
        ANCSignUp.setVisible(false);
        ANCSignIn.setVisible(true);

       if (isSaved) {
           new Alert(Alert.AlertType.INFORMATION,"Sign Up Successful").show();
       }else {
           new Alert(Alert.AlertType.ERROR,"Sign Up Failed").show();
           clearAllText();
           signUpSectionUserName.requestFocus();
       }

    }
    public LoginDTO checkMach(){
        String name= signUpSectionUserName.getText();
        String password= signUpSecssionPassword.getText();
        String email= signUpsecssionEmail.getText();

        boolean isValid = name.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);

        signUpSectionUserName.setStyle("-fx-border-color: #2c3e50; -fx-border-width: 2px;");
        signUpSecssionPassword.setStyle("-fx-border-color: #2c3e50; -fx-border-width: 2px;");
        signUpsecssionEmail.setStyle("-fx-border-color: #2c3e50; -fx-border-width: 2px;");

        if (!isValid) signUpSectionUserName.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        if (isValidEmail) signUpsecssionEmail.setStyle("-fx-border-color:red; -fx-border-width: 2px;");
        if (isValid && isValidEmail){
            return new LoginDTO(name, password, email);
        }
        return null;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        signInAnimation();
        SignUptxtButton.setOnMouseClicked(event -> {
            clearAllText();
            ANCSignIn.setVisible(false);
            ANCSignUp.setVisible(true);
            //SignUp Animation
            double width=445;
           ANCSignUp.setTranslateX(width);
           TranslateTransition slide2 = new TranslateTransition();
           slide2.setDuration(Duration.seconds(0.7));
           slide2.setNode(ANCSignUp);
           slide2.setFromX(width);
           slide2.setToX(0);
           slide2.play();
        });

        signInMessageButton.setOnMouseClicked(event -> {
            clearAllText();
            ANCSignUp.setVisible(false);
            ANCSignIn.setVisible(true);
            signInAnimation();
        });
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
    public void clearAllText(){
        signInUserName.clear();
        signInEmail.clear();
        signInPassword.clear();
        signUpsecssionEmail.clear();
        signUpSecssionPassword.clear();
        signUpSectionUserName.clear();

    }

}
