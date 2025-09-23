package com.example.the_elite_driving_school_management_system.Bo;

import com.example.the_elite_driving_school_management_system.DTO.*;
import com.example.the_elite_driving_school_management_system.Entity.*;
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

        Student student = new Student(
                dto.getStudentID(),
                dto.getName(),
                dto.getAge(),
                dto.getAddress(),
                dto.getContact(),
                dto.getEmail(),
                dto.getDate(),
                dto.getCourseType(),
                dto.getCourse()
        );


        List<Course> courses = new ArrayList<>();
        if (dto.getCourseId() != null) {
            for (String courseId : dto.getCourseId()) {
                Course course = session.get(Course.class, courseId);
                if (course != null) {
                    courses.add(course);
                }
            }
        }

        student.setCourses(courses);

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
                courseId
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
                dto.getDate(),
                dto.getCourse(),
               dto.getCourseId()
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
                entity.getCourse(),
                 entity.getCourseId()
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

    public static Payment toEntity(PaymentDTO dto, Student student) {
        if (dto == null) return null;

        return new Payment(
                dto.getPaymentID(),
                student,                 // needs Student entity
                dto.getCourseID(),
                dto.getPayment(),
                dto.getStatus()
        );
    }

    public static PaymentDTO toDTO(Payment entity) {
        if (entity == null) return null;

        return new PaymentDTO(
                entity.getPaymentID(),
                entity.getStudent(),  // convert Student object → studentId
                entity.getCourseID(),
                entity.getPayment(),
                entity.getStatus()
        );
    }


    public static Payment toEntity(PaymentDTO dto) {
        if (dto == null) return null;

        return new Payment(
                dto.getPaymentID(),
                dto.getStudentId(),                 // needs Student entity
                dto.getCourseID(),
                dto.getPayment(),
                dto.getStatus()
        );
    }
}
