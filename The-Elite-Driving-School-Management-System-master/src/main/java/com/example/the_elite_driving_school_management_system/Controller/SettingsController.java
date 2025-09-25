package com.example.the_elite_driving_school_management_system.Controller;

import com.example.the_elite_driving_school_management_system.Bo.BOFactory;
import com.example.the_elite_driving_school_management_system.Bo.Custom.SettingsBo;
import com.example.the_elite_driving_school_management_system.DTO.CourseDTO;
import com.example.the_elite_driving_school_management_system.DTO.SettingsDTO;
import com.example.the_elite_driving_school_management_system.TM.CourseTM;
import com.example.the_elite_driving_school_management_system.TM.SettingsTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
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
       addUserANC.setVisible(true);
    }

    public void btnsave(ActionEvent actionEvent) {
        SettingsDTO settingsDTO = checkMatch();
        if (settingsDTO != null) {
            boolean isSaved =settingsBo.save(settingsDTO);
            if (isSaved) {
                loadTableData();
                addUserANC.setVisible(false);
                new Alert(Alert.AlertType.INFORMATION, " saved successfully").show();

            }
            else {
                new Alert(Alert.AlertType.ERROR, " save failed").show();
            }
        }
    }

    public void btnUpdate(ActionEvent actionEvent) {
        SettingsDTO settingsDTO = checkMatch();
        if (settingsDTO != null) {
            boolean isUpdated =settingsBo.update(settingsDTO);
            if (isUpdated) {
                loadTableData();
                addUserANC.setVisible(false);
                new Alert(Alert.AlertType.INFORMATION, " Updated successfully").show();

            }
            else {
                new Alert(Alert.AlertType.ERROR, " update failed").show();
            }
        }
    }

    public void btnUpdateUsernameorPassword(ActionEvent actionEvent) {
        addUserANC.setVisible(true);
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
        setupTableColumns();
        loadTableData();
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setDataToFields((SettingsTM) newValue);

                btnSaveCourse.setVisible(false);
                btnUpdateUsernameorPassword.setVisible(true);  // show Edit
                btnDelete.setVisible(true);
            } else {
                btnSaveCourse.setVisible(true);
                btnUpdateUsernameorPassword.setVisible(false);  // show Edit
                btnDelete.setVisible(false);

            }
        });



    }
    private void setDataToFields(SettingsTM settingsTM) {
        txtName.setText(settingsTM.getName());
        txtUserName.setText(settingsTM.getUserName());
        txtPassword.setText(settingsTM.getPassword());
        txtRole.setValue(settingsTM.getRole());}
    private void loadTableData(){
        ArrayList<SettingsDTO> allUsers = (ArrayList<SettingsDTO>) settingsBo.getAllCourses();
        ObservableList<SettingsTM> tableList = FXCollections.observableArrayList();

        if (allUsers != null) {
            for (SettingsDTO settingsDTO : allUsers) {
                tableList.add(new SettingsTM(
                        settingsDTO.getName(),
                        settingsDTO.getUserName(),
                        settingsDTO.getRole()
                ));
            }
        }
        table.setItems(tableList);
    }
    private void setupTableColumns(){
        colFullName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colRoleType.setCellValueFactory(new PropertyValueFactory<>("role"));

    }

}
