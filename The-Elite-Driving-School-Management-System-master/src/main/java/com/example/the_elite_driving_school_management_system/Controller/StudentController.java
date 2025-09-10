package com.example.the_elite_driving_school_management_system.Controller;

import com.example.the_elite_driving_school_management_system.Bo.BOFactory;
import com.example.the_elite_driving_school_management_system.Bo.Custom.CourseBo;
import com.example.the_elite_driving_school_management_system.Bo.Custom.StudentBo;
import com.example.the_elite_driving_school_management_system.DTO.CourseDTO;
import com.example.the_elite_driving_school_management_system.DTO.StudentDTO;
import com.example.the_elite_driving_school_management_system.TM.StudentTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class StudentController implements Initializable {

    @FXML
    private TableView<StudentTM> table;
    @FXML
    private TableColumn<StudentTM, String> colStudentId;
    @FXML
    private TableColumn<StudentTM, String> colName;
    @FXML
    private TableColumn<StudentTM, Integer> colAge;
    @FXML
    private TableColumn<StudentTM, String> colAddress;
    @FXML
    private TableColumn<StudentTM, String> colContact;
    @FXML
    private TableColumn<StudentTM, String> colEmail;
    @FXML
    private TableColumn<StudentTM, LocalDate> colDate;
    @FXML
    private TableColumn<StudentTM, String> colCourses;
    @FXML
    private TableColumn<StudentTM, String> colCourseId;

    @FXML
    private TextField txtName, txtAge, txtAddress, txtContact, txtEmail, txtCourseID;
    @FXML
    private TextArea courseList;
    @FXML
    private DatePicker datePiker;
    @FXML
    private Label txtStudentId;
    @FXML
    private ListView<String> courseListView;

    private final String namePattern = "^[A-Za-z ]+$";
    private final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final String phonePattern = "^(\\d+)$";

    private final StudentBo studentBo = (StudentBo) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
    private final CourseBo courseBo = (CourseBo) BOFactory.getInstance().getBO(BOFactory.BOType.COURSE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCoursesFromDB();
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                setDataToFields(newSelection);
            }
        });
        setupTableColumns();

        loadTableData();
        txtStudentId.setText(generateNewId());
    }

    private void setupTableColumns() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCourses.setCellValueFactory(new PropertyValueFactory<>("courseType")); // show names
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courses"));   // show IDs




    }
    private void setDataToFields(StudentTM student) {
        txtStudentId.setText(student.getId());
        txtName.setText(student.getName());
        txtAge.setText(String.valueOf(student.getAge()));
        txtAddress.setText(student.getAddress());
        txtContact.setText(student.getContact());
        txtEmail.setText(student.getEmail());
        datePiker.setValue(student.getDate());
        courseList.setText(student.getCourse());
        txtCourseID.setText(student.getCourseId());
    }

    private void loadTableData() {
        ArrayList<StudentDTO> allStudents = studentBo.getAllStudents();
        ObservableList<StudentTM> tableList = FXCollections.observableArrayList();

        if (allStudents != null) {
            for (StudentDTO student : allStudents) {

                // Build comma-separated course IDs (or names)
                List<String> courseIds = student.getCourseIdList(); // List<String>
                String courseIdsStr = "";
                if (courseIds != null && !courseIds.isEmpty()) {
                    courseIdsStr = String.join(", ", courseIds);
                }

                // Optional: To show course names instead of IDs, use courseBo.findById()
            /*
            List<String> courseNames = new ArrayList<>();
            for (String id : courseIds) {
                CourseDTO c = courseBo.findById(id);
                if (c != null) courseNames.add(c.getName());
            }
            String courseNamesStr = String.join(", ", courseNames);
            */

                tableList.add(new StudentTM(
                        student.getStudentID(),
                        student.getName(),
                        student.getAge(),
                        student.getAddress(),
                        student.getContact(),
                        student.getEmail(),
                        student.getRegistrationDate(),
                        student.getCourseType(), // human-readable courseType
                        courseIdsStr             // or courseNamesStr if you prefer names
                ));
            }
        }

        table.setItems(tableList);
    }


    public void btnAdd(ActionEvent actionEvent) {
        StudentDTO studentDTO = checkMatch();
        if (studentDTO != null) {
            boolean isSaved = studentBo.save(studentDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Student Added Successfully").show();
                clearFields();
                loadCoursesFromDB();
                loadTableData();

            } else {
                new Alert(Alert.AlertType.ERROR, "Student Already Exists").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Input").show();
        }
    }

    public StudentDTO checkMatch() {
        try {
            String studentId = txtStudentId.getText();
            String name = txtName.getText();
            int age = Integer.parseInt(txtAge.getText());
            String address = txtAddress.getText();
            String email = txtEmail.getText();
            String contact = txtContact.getText();
            LocalDate date = datePiker.getValue();
            String courses= courseList.getText();

            boolean isValidName = name.matches(namePattern);
            boolean isValidEmail = email.matches(emailPattern);
            boolean isValidPhone = contact.matches(phonePattern);

            if (isValidName && isValidEmail && isValidPhone) {
                // Get selected courses from ListView
                ObservableList<String> selectedCourseNames = courseListView.getSelectionModel().getSelectedItems();

                // Convert to list of course IDs via DAO
                List<String> selectedCourseIds = new ArrayList<>();
                for (String courseName : selectedCourseNames) {
                    CourseDTO course = courseBo.findByName(courseName);
                    if (course != null) {
                        selectedCourseIds.add(course.getId());
                    }
                }

                return new StudentDTO(studentId, name, age, address, contact, email, date,courses, "", selectedCourseIds);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private String generateNewId() {
        try {
            String id = studentBo.generateNewStudentId();
            if (id != null) return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "S001";
    }

    private void loadCoursesFromDB() {
        courseListView.getItems().clear();

        try {
            List<CourseDTO> coursesFromDB = courseBo.getAllCourses(); // renamed
            ObservableList<String> courseNames = FXCollections.observableArrayList();

            for (CourseDTO course : coursesFromDB) {
                courseNames.add(course.getName());
            }
            courseListView.setItems(courseNames);

            // Allow multiple selection
            courseListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            // Show selected courses in courseList TextArea
            courseListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
                if (newSel != null) {
                    ObservableList<String> selected = courseListView.getSelectionModel().getSelectedItems();
                    courseList.clear();
                    for (String c : selected) {
                        courseList.appendText(c + "\n");
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    public void btnUpdate(ActionEvent actionEvent) {
    StudentDTO studentDTO = checkMatch();
    if (studentDTO != null) {
        boolean isUpdated=studentBo.update(studentDTO);
        if (isUpdated) {
            loadTableData();
            new Alert(Alert.AlertType.INFORMATION, "Student Updated Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Student Not Update ").show();
        }
    }
    }

    public void btnDelete(ActionEvent actionEvent) {
        StudentTM selectedStudent = table.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            boolean isDeleted = studentBo.delete(selectedStudent.getId()); // Use your BO delete method

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Student Deleted Successfully").show();
                loadTableData();
               // clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Delete Student").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a student to delete").show();
        }
    }

    public void btnView(ActionEvent actionEvent) {
    }

    public void btnReset(ActionEvent actionEvent) {
        txtName.clear();
        txtAge.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtContact.clear();
        txtCourseID.clear();
        datePiker.setValue(null);
        courseList.clear();
    }
    private void clearFields() {
        txtName.clear();
        txtAge.clear();
        txtAddress.clear();
        txtContact.clear();
        txtCourseID.clear();
        txtEmail.clear();
        courseList.clear();
        txtStudentId.setText(generateNewId());

    }
}
