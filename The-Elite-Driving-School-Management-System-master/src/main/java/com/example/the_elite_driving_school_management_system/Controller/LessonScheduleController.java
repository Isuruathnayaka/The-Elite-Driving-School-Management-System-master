package com.example.the_elite_driving_school_management_system.Controller;


import com.example.the_elite_driving_school_management_system.Bo.BOFactory;
import com.example.the_elite_driving_school_management_system.Bo.Custom.LessonScheduleBo;
import com.example.the_elite_driving_school_management_system.Bo.Custom.PaymentBo;
import com.example.the_elite_driving_school_management_system.DTO.CourseDTO;
import com.example.the_elite_driving_school_management_system.DTO.LessonDTO;
import com.example.the_elite_driving_school_management_system.TM.CourseTM;
import com.example.the_elite_driving_school_management_system.TM.LessonTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LessonScheduleController implements Initializable {
    private final LessonScheduleBo lessonBO=(LessonScheduleBo) BOFactory.getInstance().getBO(BOFactory.BOType.LESSON);
    private final String namePattern = "^[A-Za-z ]+$";

    public AnchorPane ANCCourse;
    public TableView table;
    public TableColumn colLessonID;
    public TableColumn colLessonTitle;
    public TableColumn colDuration;
    public TableColumn colCourseId;
    public TableColumn colTime;
    public TableColumn colDate;
    public TableColumn colStudentID;
    public TableColumn colStudentName;
    public TableColumn colInstructorId;
    public TableColumn colInstructorName;
    public TableColumn colStatus;
    public Button btnSheduleBtn;
    public AnchorPane addCourseANC;
    public Label txtLessonID;
    public TextField txtName;
    public TextField txtDuration;

    public TextField txtCourseID;
    public DatePicker txtDate;
    public TextField txtTime;

    public ComboBox txtStatus;
    public Button btnEditeLesson;
    public Button btnDelete;
    public AnchorPane ANCValidationView;
    public TextField txtStudentIdValidate;
    public Button btnCheckValidation;
    public ComboBox txtStudentID;
    public ComboBox txtInstructorID;
    public AnchorPane ANCLesson;
    public AnchorPane addLessonANC;

    public void btnSheduleBtn(ActionEvent actionEvent) {
        addLessonANC.setVisible(true);

    }

    public void btnsave(ActionEvent actionEvent) {
        LessonDTO lessonDTO=checkMatch();
        if (lessonDTO!=null) {
            boolean isSaved=lessonBO.save(lessonDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Lesson saved successfully").show();
                refresh();
                addLessonANC.setVisible(false);
                loadTableData();
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Lesson could not be saved").show();
            }
        }


    }

    public void btnUpdate(ActionEvent actionEvent) {
        LessonDTO lessonDTO=checkMatch();
        if (lessonDTO!=null) {
            boolean isUpdated=lessonBO.update(lessonDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Lesson updated successfully").show();
                refresh();
                loadTableData();
                addLessonANC.setVisible(false);
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Lesson could not be updated").show();
            }
        }
    }

    public void btnEditLesson(ActionEvent actionEvent) {
        addLessonANC.setVisible(true);
    }

    public void btnDelete(ActionEvent actionEvent) {
        LessonTM selectedLesson= (LessonTM) table.getSelectionModel().getSelectedItem();

        if (selectedLesson != null) {
            boolean isDeleted=lessonBO.delete(selectedLesson.getLesson_id());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully", ButtonType.OK).show();
                refresh();
                loadTableData();
            }else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete Scheduled Lesson", ButtonType.OK).show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //ANCValidationView.setVisible(true);

        refresh();
        ANCLesson.setOnMouseClicked(mouseEvent -> {
            ANCLesson.setVisible(true);
            addLessonANC.setVisible(false);
            btnSheduleBtn.setVisible(true); // hide Save
            btnEditeLesson.setVisible(false);  // show Edit
            btnDelete.setVisible(false);
        });
        txtStatus.getItems().addAll("Scheduled","Completed","Cancelled");
        txtCourseID.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                loadStudentsAndInstructor();
            }
        });
        setupTableColumns();
        loadTableData();
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setDataToFields((LessonTM) newValue);

                btnSheduleBtn.setVisible(false); // hide Save
                btnEditeLesson.setVisible(true);  // show Edit
                btnDelete.setVisible(true);
            } else {
                // If no row selected, reset
                btnSheduleBtn.setVisible(true); // hide Save
                btnEditeLesson.setVisible(false);  // show Edit
                btnDelete.setVisible(false);
            }
        });



    }
    public LessonDTO checkMatch(){
        String lessonID = txtLessonID.getText();
        String lessonName = txtName.getText();
        String duration = txtDuration.getText();
        String courseID = txtCourseID.getText();
        LocalDate date = txtDate.getValue();
        String time = txtTime.getText();
        String status = txtStatus.getSelectionModel().getSelectedItem().toString();
        String studentID = txtStudentID.getSelectionModel().getSelectedItem().toString();
        String instructorID = txtInstructorID.getSelectionModel().getSelectedItem().toString();
        boolean isValidName = lessonName.matches(namePattern);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime startTime = LocalTime.parse(time.trim(), formatter);
        if (isValidName){
            return new LessonDTO(lessonID,lessonName,duration,courseID,date, startTime,status,studentID,instructorID);
        }
        return null;

    }

    public void btnCheckValidation(ActionEvent actionEvent) {

        String studentValidateID = txtStudentIdValidate.getText();
        if(studentValidateID.matches("^S\\d{3}$")) {

            ANCValidationView.setVisible(false);

        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Input OR Student Not Pay for the Course").show();
        }
    }
    private void loadStudentsAndInstructor() {
        String courseId = txtCourseID.getText().trim();

        if (courseId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter a Course ID").show();
            return;
        }

        try {
            // Load student IDs
            List<String> studentIds = lessonBO.getStudentIdsByCourse(courseId);
            txtStudentID.getItems().clear();
            txtStudentID.getItems().addAll(studentIds);

            // Load instructor ID
            String instructorId = lessonBO.getInstructorIdByCourse(courseId);
            txtInstructorID.getItems().clear();
            if (instructorId != null && !instructorId.isEmpty()) {
                txtInstructorID.getItems().add(instructorId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load students or instructor").show();
        }
    }
    private void loadTableData(){
        ArrayList<LessonDTO> allLessons = (ArrayList<LessonDTO>) lessonBO.getAllLessons();
        ObservableList<LessonTM> tableList = FXCollections.observableArrayList();

        if (allLessons != null) {
            for (LessonDTO lessonDTO : allLessons) {
                tableList.add(new LessonTM(
                        lessonDTO.getLessonId(),
                        lessonDTO.getLessonTitle(),
                        lessonDTO.getDuration(),
                        lessonDTO.getDate(),
                        lessonDTO.getTime(),
                        lessonDTO.getCourseId(),
                        lessonDTO.getInstructorId(),
                        lessonDTO.getStudentId(),
                        lessonDTO.getStatus()
                ));
            }
        }
        table.setItems(tableList);
    }
    private void setDataToFields(LessonTM lesson) {
        txtLessonID.setText(lesson.getLesson_id());
        txtName.setText(lesson.getLesson_name());
        txtDuration.setText(lesson.getDuration());
        txtCourseID.setText(lesson.getCourse_id());
        txtStudentID.getSelectionModel().select(lesson.getStudent_id());
        txtInstructorID.getSelectionModel().select(lesson.getInstructor_id());
        txtStatus.getSelectionModel().select(lesson.getStatus());
        txtDate.setValue(
                lesson.getLesson_date()
        );
        txtTime.setText(String.valueOf(lesson.getLesson_time()));
    }
    private String generateNewId() {
        try {
            String id = String.valueOf(lessonBO.generateNewLessonId());
            if (id != null) return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "L001";
    }
    private void refresh(){
        txtLessonID.setText(generateNewId());
        txtName.clear();
        txtDuration.clear();
        txtCourseID.clear();
        txtTime.clear();
        txtStatus.getItems().clear();
        txtStudentID.getItems().clear();
        txtInstructorID.getItems().clear();
        txtStatus.setValue(null);
        txtStudentID.setValue(null);
    }
    private void setupTableColumns(){
        colLessonID.setCellValueFactory(new PropertyValueFactory<>("lesson_id"));
        colLessonTitle.setCellValueFactory(new PropertyValueFactory<>("lesson_name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("lesson_time"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("lesson_date"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colInstructorId.setCellValueFactory(new PropertyValueFactory<>("instructor_id"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));




    }
}
