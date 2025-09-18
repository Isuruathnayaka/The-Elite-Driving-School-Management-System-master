package com.example.the_elite_driving_school_management_system.Controller;

import com.example.the_elite_driving_school_management_system.Bo.BOFactory;
import com.example.the_elite_driving_school_management_system.Bo.Custom.CourseBo;
import com.example.the_elite_driving_school_management_system.DTO.CourseDTO;
import com.example.the_elite_driving_school_management_system.TM.CourseTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CourseManageController implements Initializable {
    @FXML
    public Button btnsaveACourse;
    public AnchorPane addCourseANC;
    public Label txtCourseID;
    public TableView table;
    public TableColumn colCourseID;
    public TableColumn colCourseName;
    public TableColumn colCourseDuration;
    public TableColumn colFee;
    public TableColumn colDescription;
    public TextField txtName;
    public TextField txtDuration;
    public TextField txtFee;
    public TextField txtDescription;
    private final String namePattern = "^[A-Za-z ]{2,50}$";
    public Button btnEditeCourse;
    public Button btnSaveCourse;
    public Button btnDelete;
    public AnchorPane ANCCourse;
    private String durationPattern = "^[0-9]+\\s+(Week|Weeks|Month|Months|Day|Days)$";

    private String feePattern = "^[0-9]+(\\.[0-9]{1,2})?$";
    private String descriptionPattern = "^[A-Za-z0-9 .,()-]{10,200}$";



    private final CourseBo courseBo = (CourseBo) BOFactory.getInstance().getBO(BOFactory.BOType.COURSE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTableColumns();
        loadTableData();
        txtCourseID.setText(generateNewId());


             btnSaveCourse.setVisible(true);
             btnEditeCourse.setVisible(false);
             btnDelete.setVisible(false);

        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setDataToFields((CourseTM) newValue);

                btnSaveCourse.setVisible(false); // hide Save
                btnEditeCourse.setVisible(true);  // show Edit
                btnDelete.setVisible(true);
            } else {
                // If no row selected, reset
              btnSaveCourse.setVisible(true);
                btnEditeCourse.setVisible(false);
                btnDelete.setVisible(false);
            }
        });
        ANCCourse.setOnMouseClicked(event -> {
            addCourseANC.setVisible(false);
            clear();
        });
    }

    private void setDataToFields(CourseTM course) {
        txtCourseID.setText(course.getId());
        txtName.setText(course.getName());
        txtDuration.setText(course.getDuration());
        txtFee.setText(String.valueOf(course.getFee()));
        txtDescription.setText(course.getDescription());
 }
    private String generateNewId() {
        try {
            String id = courseBo.generateNewCourseId();
            if (id != null) return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "C1001";
    }
    private void loadTableData(){
        ArrayList<CourseDTO> allCourses = (ArrayList<CourseDTO>) courseBo.getAllCourses();
        ObservableList<CourseTM>  tableList = FXCollections.observableArrayList();

        if (allCourses != null) {
            for (CourseDTO courseDTO : allCourses) {
                tableList.add(new CourseTM(
                        courseDTO.getId(),
                        courseDTO.getName(),
                        courseDTO.getDuration(),
                        courseDTO.getFee(),
                        courseDTO.getDescription()
                ));
            }
        }
        table.setItems(tableList);
    }
private void setupTableColumns(){
    colCourseID.setCellValueFactory(new PropertyValueFactory<>("id"));
    colCourseName.setCellValueFactory(new PropertyValueFactory<>("name"));
    colCourseDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
    colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
    colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

}

    public void btnsaveACourse(ActionEvent actionEvent) {
        addCourseANC.setVisible(true);

    }

    public void btnsave(ActionEvent actionEvent) {
        CourseDTO courseDTO = checkMatch();
        if (courseDTO != null) {
            boolean isSaved = courseBo.save(courseDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Saved", ButtonType.OK).show();
                addCourseANC.setVisible(false);
                txtCourseID.setText(generateNewId());
                loadTableData();
                clear();


            } else {
                new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK).show();
            }


        } else {
            new Alert(Alert.AlertType.ERROR,"Invalid", ButtonType.OK).show();
        }
    }
    private void clear(){
        txtCourseID.setText(generateNewId());
        txtName.clear();
        txtDuration.clear();
        txtFee.clear();
        txtDescription.clear();
    }

    private CourseDTO checkMatch() {
        String id = txtCourseID.getText();
        String name = txtName.getText();
        String duration = txtDuration.getText();

        // clean commas before parsing to Double
        String feeText = txtFee.getText().replaceAll(",", "");
        Double fee = null;
        try {
            fee = Double.valueOf(feeText);
        } catch (NumberFormatException e) {
            return null;
        }

        String description = txtDescription.getText();

        boolean isValidName = name.matches(namePattern);
        boolean isValidDuration = duration.matches(durationPattern);
        boolean isValidFee = feeText.matches(feePattern);
        boolean isValidDescription = description.matches(descriptionPattern);

        System.out.println("Name: " + name + " | " + isValidName);
        System.out.println("Duration: " + duration + " | " + isValidDuration);
        System.out.println("Fee: " + feeText + " | " + isValidFee);
        System.out.println("Description: " + description + " | " + isValidDescription);

        if (isValidName && isValidDuration && isValidFee && isValidDescription) {
            return new CourseDTO(id, name, duration, fee, description);
        }
        return null;
    }

    public void btnEditCourse(ActionEvent actionEvent) {
        addCourseANC.setVisible(true);

    }

    public void btnDelete(ActionEvent actionEvent) {
     CourseTM selectedCourse= (CourseTM) table.getSelectionModel().getSelectedItem();

     if (selectedCourse != null) {
         boolean isDeleted=courseBo.delete(selectedCourse.getId());
         if (isDeleted) {
             new Alert(Alert.AlertType.INFORMATION, "Deleted", ButtonType.OK).show();
             clear();
             loadTableData();
         }else {
             new Alert(Alert.AlertType.ERROR, "Failed to delete Course", ButtonType.OK).show();
         }
     }
    }

    public void btnUpdate(ActionEvent actionEvent) {
        CourseDTO courseDTO = checkMatch();
        if (courseDTO != null) {
            boolean isUpdated = courseBo.update(courseDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Updated", ButtonType.OK).show();
                loadTableData();
                txtCourseID.setText(generateNewId());
                addCourseANC.setVisible(false);
                clear();
            } else {
                new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK).show();
            }
        }
    }
}

