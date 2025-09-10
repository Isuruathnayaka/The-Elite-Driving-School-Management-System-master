package com.example.the_elite_driving_school_management_system.Bo;

import com.example.the_elite_driving_school_management_system.DTO.CourseDTO;
import com.example.the_elite_driving_school_management_system.DTO.InstructorDTO;
import com.example.the_elite_driving_school_management_system.DTO.LoginDTO;
import com.example.the_elite_driving_school_management_system.DTO.StudentDTO;
import com.example.the_elite_driving_school_management_system.Entity.Course;
import com.example.the_elite_driving_school_management_system.Entity.Instructor;
import com.example.the_elite_driving_school_management_system.Entity.Login;
import com.example.the_elite_driving_school_management_system.Entity.Student;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class MapUtil {

    // ==================== Login ====================
    public static Login toEntity(LoginDTO dto) {
        return new Login(
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail()
        );
    }

    // ==================== Student ====================
    public static Student toEntity(StudentDTO dto, Session session) {
        // Create Student entity
        Student student = new Student(
                dto.getStudentID(),
                dto.getName(),
                dto.getAge(),
                dto.getAddress(),
                dto.getContact(),
                dto.getEmail(),
                dto.getDate(),
                dto.getCourseType()
        );

        // Convert course IDs to Course entities
        List<Course> courses = new ArrayList<>();
        if (dto.getCourseId() != null) {
            for (String courseId : dto.getCourseId()) {
                Course course = session.get(Course.class, courseId); // fetch managed entity from DB
                if (course != null) {
                    courses.add(course);
                }
            }
        }

        student.setCourses(courses); // ✅ set the courses list

        return student;
    }


    public static StudentDTO toDTO(Student entity) {
        List<String> courseId = new ArrayList<>();
        if (entity.getCourses() != null) {
            for (Course c : entity.getCourses()) {
                courseId.add(c.getId());
            }
        }

        return new StudentDTO(
                entity.getId(),
                entity.getName(),
                entity.getAge(),
                entity.getAddress(),
                entity.getContact(),
                entity.getEmail(),
                entity.getRegistrationDate(),
                entity.getCourseType(),
                courseId    // pass the list of course IDs
        );
    }


    // ==================== Instructor ====================
    public static Instructor toEntity(InstructorDTO dto) {
        return new Instructor(
                dto.getInstructorID(),
                dto.getName(),
                dto.getAge(),
                dto.getAddress(),
                dto.getContact(),
                dto.getEmail(),
                dto.getDate(),        // LocalDate (or Date if your entity uses Date)
                dto.getCourse()
               // dto.getCourseId()
        );
    }

    public static InstructorDTO toDTO(Instructor entity) {
        return new InstructorDTO(
                entity.getId(),
                entity.getName(),
                entity.getAge(),
                entity.getAddress(),
                entity.getContact(),
                entity.getEmail(),
                entity.getRegistrationDate(),  // LocalDate
                entity.getCourse()
//                entity.getCourseId()
        );
    }
    public static Course toEntity(CourseDTO dto) {
        return new Course(
                dto.getId(),
                dto.getName(),
                dto.getDuration(),
                dto.getFee(),
                dto.getDescription()
        );
    }
    public static CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getDuration(),
                course.getFee(),
                course.getDescription()
        );
    }

}
