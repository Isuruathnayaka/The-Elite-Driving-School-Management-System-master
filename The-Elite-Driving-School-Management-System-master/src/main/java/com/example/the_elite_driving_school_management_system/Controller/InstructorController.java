package com.example.the_elite_driving_school_management_system.Controller;

import com.example.the_elite_driving_school_management_system.Bo.BOFactory;
import com.example.the_elite_driving_school_management_system.Bo.Custom.InstructorBo;
import com.example.the_elite_driving_school_management_system.DTO.InstructorDTO;
import com.example.the_elite_driving_school_management_system.TM.InstructorTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class InstructorController implements Initializable {
    public TextField txtName;
    public TextField txtAge;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtEmail;
    public DatePicker datePiker;
    public TextArea courseList;
    public TableView table;
    public TableColumn colInstructorId;
    public TableColumn colName;
    public TableColumn colAge;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colEmail;
    public TableColumn colDate;
    public TableColumn colCourses;
    public TableColumn colCourseId;
    private final String namePattern = "^[A-Za-z ]+$";
    private final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final String phonePattern = "^(\\d+)$";

    public ListView courseListView;
    public TextField txtCourseID;

    private final InstructorBo instructorBo=(InstructorBo) BOFactory.getInstance().getBO(BOFactory.BOType.INSTRUCTOR);
    public Label txtInstructorID;
    public RadioButton availableInstuctor;

    public void btnReset(ActionEvent actionEvent) {
    }

    public void btnAdd(ActionEvent actionEvent) {
        InstructorDTO dto = checkMatch(); // get DTO from form
        if (dto != null) {
            // Pass DTO directly to BO
            boolean isSaved = instructorBo.save(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Instructor Added Successfully").show();
                txtInstructorID.setText(generateNewId());
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Instructor Already Exists").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Input").show();
        }
    }


    private void loadTableData() {
        ArrayList<InstructorDTO> allInstructors = instructorBo.getAllInstructors();
        ObservableList<InstructorTM> tableList = FXCollections.observableArrayList();

        if (allInstructors != null) {
            for (InstructorDTO instructor : allInstructors) {
                tableList.add(new InstructorTM(
                        instructor.getInstructorID(),
                        instructor.getName(),
                        instructor.getAge(),
                        instructor.getAddress(),
                        instructor.getContact(),
                        instructor.getEmail(),
                        instructor.getRegistrationDate(),
                        instructor.getCourse()
//                        instructor.getCourseId()
                ));
            }
        }
        table.setItems(tableList);

    }
    private void courseSelection() {
        courseListView.getItems().addAll(
                "Basic Learner Program",
                "Advanced Defensive Driving",
                "Motorcycle License Training",
                "Heavy Vehicle Training",
                "Refresher Driving Course"
        );

        Map<String, String> courseMapping = new HashMap<>();
        courseMapping.put("Basic Learner Program", "Basic Learner Program");
        courseMapping.put("Advanced Defensive Driving", "Advanced Defensive Driving");
        courseMapping.put("Motorcycle License Training", "Motorcycle License Training");
        courseMapping.put("Heavy Vehicle Training", "Heavy Vehicle Training");
        courseMapping.put("Refresher Driving Course", "Refresher Driving Course");

        Map<String, String> courseIdMapping = new HashMap<>();
        courseIdMapping.put("Basic Learner Program", "C1001");
        courseIdMapping.put("Advanced Defensive Driving", "C1002");
        courseIdMapping.put("Motorcycle License Training", "C1003");
        courseIdMapping.put("Heavy Vehicle Training", "C1004");
        courseIdMapping.put("Refresher Driving Course", "C1005");

        courseListView.setOnMouseClicked(event -> {
            String selectedCourse = (String) courseListView.getSelectionModel().getSelectedItem();
            if (selectedCourse != null) {
                courseList.appendText(courseMapping.get(selectedCourse) + "\n");
                txtCourseID.appendText(courseIdMapping.get(selectedCourse) + "\n");
                courseListView.getItems().remove(selectedCourse);
            }
        });
    }
    private void setupTableColumns() {
        colInstructorId.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCourses.setCellValueFactory(new PropertyValueFactory<>("courses"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseID"));


    }

    private String generateNewId() {
        try {
            String id = instructorBo. generateNewInstructorId();
            if (id != null) return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "I001";
    }

    public InstructorDTO checkMatch() {
        try {
            String instructorId = txtInstructorID.getText();
            String name = txtName.getText();
            int age = Integer.parseInt(txtAge.getText());
            String address = txtAddress.getText();
            String contact = txtContact.getText();
            String email = txtEmail.getText();
            LocalDate date = datePiker.getValue();
            String courses = courseList.getText();
            String courseID = txtCourseID.getText();

            boolean isValidName = name.matches(namePattern);
            boolean isValidEmail = email.matches(emailPattern);
            boolean isValidPhone = contact.matches(phonePattern);

            if (isValidName && isValidEmail && isValidPhone) {
                return new InstructorDTO(
                        instructorId,
                        name,
                        age,
                        address,
                        contact,
                        email,
                        date,
                        courses
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void btnUpdate(ActionEvent actionEvent) {
    }

    public void btnDelete(ActionEvent actionEvent) {
    }

    public void btnView(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtInstructorID.setText(generateNewId());
        courseSelection();
        loadTableData();
        setupTableColumns();


    }
}
