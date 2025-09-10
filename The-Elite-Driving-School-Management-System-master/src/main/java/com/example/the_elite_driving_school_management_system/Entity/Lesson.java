package com.example.the_elite_driving_school_management_system.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @Column(name = "lesson_id", nullable = false)
    private String id;

    @Column(name = "lesson_name", nullable = false)
    private String name;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "lesson_date", nullable = false)
    private Date date;

    @Column(name = "lesson_time", nullable = false)
    private Time time;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;



    public Lesson() {}


}
