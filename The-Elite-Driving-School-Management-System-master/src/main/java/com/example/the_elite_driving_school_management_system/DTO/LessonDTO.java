package com.example.the_elite_driving_school_management_system.DTO;

import com.example.the_elite_driving_school_management_system.Entity.Course;
import com.example.the_elite_driving_school_management_system.Entity.Instructor;
import com.example.the_elite_driving_school_management_system.Entity.Student;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class LessonDTO {

    private String lessonId;
    private String lessonTitle;
    private String lessonDuration;
    private String courseId;
    private LocalTime startTime;   // safer than java.sql.Time
    private LocalDate date;
    private String studentId;
    private String instructorId;
    private String status;

    // ✅ Full constructor
    public LessonDTO(String lessonId, String lessonTitle, String lessonDuration,
                     String courseId, LocalDate date, LocalTime startTime,
                     String status, String studentId, String instructorId) {
        this.lessonId = lessonId;
        this.lessonTitle = lessonTitle;
        this.lessonDuration = lessonDuration;
        this.courseId = courseId;
        this.date = date;
        this.startTime = startTime;
        this.status = status;
        this.studentId = studentId;
        this.instructorId = instructorId;
    }






    public LessonDTO(String id, String name, LocalDate date, Course course, Instructor instructor, String duration, String status, Student student, LocalTime time) {
        this.lessonId = id;
        this.lessonTitle = name;
        this.lessonDuration = duration;
        this.courseId = course.getId();
        this.studentId = student.getId();
        this.instructorId = instructor.getId();
        this.status = status;
        this.date = date;
        this.startTime=time;

    }

    public LessonDTO(String id, String name, String duration, LocalDate date, LocalTime time, String status, String student, String instrucot, String course) {
        this.lessonId = id;
        this.lessonTitle = name;
        this.lessonDuration = duration;
        this.date = date;
        this.startTime=time;
        this.status=status;
        this.studentId = student;
        this.instructorId = instrucot;
        this.courseId = course;


    }

    private static LocalTime parseTimeSafe(String time) {
        if (time == null || time.isBlank()) return null;
        try {
            // If input is HH:mm
            if (time.length() == 5) return LocalTime.parse(time + ":00");
            return LocalTime.parse(time); // expects HH:mm:ss
        } catch (Exception e) {
            System.err.println("Invalid time format: " + time);
            return null;
        }
    }

    // --- Getters & Setters ---
    public String getLessonId() { return lessonId; }
    public void setLessonId(String lessonId) { this.lessonId = lessonId; }

    public String getLessonTitle() { return lessonTitle; }
    public void setLessonTitle(String lessonTitle) { this.lessonTitle = lessonTitle; }

    public String getLessonDuration() { return lessonDuration; }
    public void setLessonDuration(String lessonDuration) { this.lessonDuration = lessonDuration; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getInstructorId() { return instructorId; }
    public void setInstructorId(String instructorId) { this.instructorId = instructorId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Time getTime() {
        return new Time(startTime.getHour(), startTime.getMinute(), startTime.getSecond());
    }

    public String getDuration() {
        return lessonDuration;
    }

    public String getId() {
        return lessonId;
    }

    public String getName() {
        return lessonTitle;
    }
}
