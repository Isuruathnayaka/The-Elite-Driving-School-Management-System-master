package com.example.the_elite_driving_school_management_system.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lessonShedule")
public class LessonSchedule {
    @Id
    @Column(name = "lessonId", nullable = false, unique = true)
    private String lessonId;

}
