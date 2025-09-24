package com.example.the_elite_driving_school_management_system.DTO;

import java.sql.Time;
import java.time.LocalDate;

public class LessonDTO {

    private String lessonId;
    private String lessonTitle;
    private String lessonDuration;
    private String CourseId;
    private Time startTime;
    private LocalDate date;
    private String studentId;
    private String instructorId;
    private String status;

    public LessonDTO(String lessonID, String lessonName, String duration, String courseID, LocalDate date, String time, String status, String studentID, String instructorID) {
        this.lessonId = lessonID;
        this.lessonTitle = lessonName;
        this.lessonDuration = duration;
        this.CourseId = courseID;
        this.date=date;
        this.startTime=Time.valueOf(time);
        this.status=status;
        this.studentId=studentID;
        this.instructorId=instructorID;

    }

    public LessonDTO(String id, String name, String duration, LocalDate date, Time time, String status, String status1, String studentID, String instructorID) {
        this.lessonId=id;
        this.lessonTitle=name;
        this.lessonDuration=duration;
        this.CourseId=id;
        this.date=date;
        this.startTime=time;
        this.status=status;
        this.studentId=studentID;
        this.instructorId=instructorID;

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getLessonDescription() {
        return lessonDuration;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDuration = lessonDescription;
    }

    public String getCourseId() {
        return CourseId;
    }

    public void setCourseId(String courseId) {
        CourseId = courseId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }



    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getId() {
        return lessonId;
    }

    public String getName() {
        return lessonTitle;
    }

    public String getDuration() {
        return lessonDuration;
    }

    public Time getTime() {
        return startTime;
    }
}
