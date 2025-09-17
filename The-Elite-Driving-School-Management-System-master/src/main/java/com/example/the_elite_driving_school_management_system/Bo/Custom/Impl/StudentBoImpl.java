package com.example.the_elite_driving_school_management_system.Bo.Custom.Impl;

import com.example.the_elite_driving_school_management_system.Bo.Custom.StudentBo;
import com.example.the_elite_driving_school_management_system.Bo.MapUtil;
import com.example.the_elite_driving_school_management_system.Config.FactoryConfiguration;
import com.example.the_elite_driving_school_management_system.DAO.Custom.StudentDAO;
import com.example.the_elite_driving_school_management_system.DTO.StudentDTO;
import com.example.the_elite_driving_school_management_system.Entity.Course;
import com.example.the_elite_driving_school_management_system.Entity.Student;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentBoImpl implements StudentBo {
    private static FactoryConfiguration factoryConfiguration=FactoryConfiguration.getInstance();

    private final StudentDAO studentDAO;

    public StudentBoImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public boolean save(StudentDTO studentDTO) {
        try (Session session = factoryConfiguration.getSession()) {
            Transaction tx = session.beginTransaction();

            // Convert DTO → Entity
            Student student = new Student(
                    studentDTO.getStudentID(),
                    studentDTO.getName(),
                    studentDTO.getAge(),
                    studentDTO.getAddress(),
                    studentDTO.getContact(),
                    studentDTO.getEmail(),
                    studentDTO.getRegistrationDate(),
                    studentDTO.getCourse()
            );

            // Attach selected courses
            for (String courseId : studentDTO.getCourseIdList()) {
                Course course = session.get(Course.class, courseId); // find course by ID
                if (course != null) {
                    student.getCourses().add(course);
                }
            }

            session.save(student); // Hibernate auto-saves into student + student_course join table
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean update(StudentDTO studentDTO) {
        Transaction tx = null;
        try (Session session = factoryConfiguration.getSession()) {
            tx = session.beginTransaction();

            Student student = MapUtil.toEntity(studentDTO, session);

            // Merge updates (handles courses in student_course join table)
            session.merge(student);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String generateNewStudentId() {
        return studentDAO.generateNewId();
    }

    @Override
    public ArrayList<StudentDTO> getAllStudents() {
        ArrayList<Student> students = studentDAO.getAll();
        return students.stream()
                .map(MapUtil::toDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public boolean delete(String studentId) {
        return studentDAO.delete(studentId);
    }

    @Override
    public List<String> getCourseIdsByStudent(String studentId) {
        return studentDAO.getCourseIdsByStudentId(studentId);
    }
}