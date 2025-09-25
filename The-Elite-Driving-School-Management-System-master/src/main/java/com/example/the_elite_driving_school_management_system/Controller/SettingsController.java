package com.example.the_elite_driving_school_management_system.Controller;

import com.example.the_elite_driving_school_management_system.Bo.BOFactory;
import com.example.the_elite_driving_school_management_system.Bo.Custom.SettingsBo;
import com.example.the_elite_driving_school_management_system.DTO.SettingsDTO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    private final SettingsBo settingsBo=(SettingsBo) BOFactory.getInstance().getBO(BOFactory.BOType.SETTINGS);
    public AnchorPane ANCSettings;
    public TableView table;
    public TableColumn colFullName;
    public TableColumn colUserName;
    public TableColumn colRoleType;
    public Button btnSaveCourse;
    public AnchorPane addUserANC;
    public Label txtCourseID;
    public TextField txtName;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public Button btnUpdateUsernameorPassword;
    public Button btnDelete;
    public ComboBox txtRole;

    public void btnsaveACourse(ActionEvent actionEvent) {
    }

    public void btnsave(ActionEvent actionEvent) {
        SettingsDTO settingsDTO = checkMatch();
        if (settingsDTO != null) {
            boolean isSaved =settingsBo.save(settingsDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, " saved successfully").show();

            }
            else {
                new Alert(Alert.AlertType.ERROR, " save failed").show();
            }
        }
    }

    public void btnUpdate(ActionEvent actionEvent) {
    }

    public void btnUpdateUsernameorPassword(ActionEvent actionEvent) {
    }

    public void btnDelete(ActionEvent actionEvent) {
    }
    private SettingsDTO checkMatch() {
        String fullName = txtName.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        String role = (String) txtRole.getValue();


        boolean isValidName=fullName.matches("[a-zA-Z]+");
        boolean isValidUserName=userName.matches("[a-zA-Z]+");

        if (isValidName && isValidUserName){
            return new SettingsDTO(fullName, userName, password, role);
        }
        return null;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtRole.getItems().addAll("USER","ADMIN");

    }
}
