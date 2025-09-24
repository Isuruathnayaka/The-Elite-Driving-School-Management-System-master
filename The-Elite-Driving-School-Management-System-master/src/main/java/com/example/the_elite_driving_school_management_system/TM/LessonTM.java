package com.example.the_elite_driving_school_management_system.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonTM {
    private String lessonId;
    private String lessonTitle;
    private String lessonDescription;
    private String CourseId;
    private Time startTime;
    private Date date;
    private String studentId;
    private String studentName;
}
