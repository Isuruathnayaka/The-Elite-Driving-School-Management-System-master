package com.example.the_elite_driving_school_management_system.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonTM {
    private String lesson_id;
    private String lesson_name;
    private String duration;
    private String course_id;
    private Time lesson_time;
    private LocalDate lesson_date;
    private String student_id;
    private String instructor_id;
    private String status;

    public LessonTM(String lesson_id, String lessonTitle, String duration ,LocalDate date, Time time, String courseId, String instructorId, String studentId, String status) {
        this.lesson_id = lesson_id;
        this.lesson_name = lessonTitle;
        this.duration =duration;
        this.lesson_time=time;
        this.lesson_date=date;
        this.student_id=studentId;
        this.instructor_id=instructorId;
        this.course_id=courseId;
        this.status=status;
    }
}
