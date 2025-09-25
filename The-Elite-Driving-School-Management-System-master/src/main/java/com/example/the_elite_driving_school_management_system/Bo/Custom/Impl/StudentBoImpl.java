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

            // 1️⃣ Create Student entity without using the constructor that doesn't set courses
            Student student = new Student();
            student.setId(studentDTO.getStudentID());
            student.setName(studentDTO.getName());
            student.setAge(studentDTO.getAge());
            student.setAddress(studentDTO.getAddress());
            student.setContact(studentDTO.getContact());
            student.setEmail(studentDTO.getEmail());
            student.setRegistrationDate(studentDTO.getRegistrationDate());
            student.setCourseType(studentDTO.getCourse());

            // 2️⃣ Attach selected courses properly
            List<Course> courseEntities = new ArrayList<>();
            for (String courseId : studentDTO.getCourseIdList()) {
                Course course = session.get(Course.class, courseId); // find course by ID
                if (course != null) {
                    courseEntities.add(course);
                }
            }
            student.setCourses(courseEntities); // set the courses list

            // 3️⃣ Save Student (Hibernate will handle join table automatically)
            session.persist(student); // safer than save() for managed entity
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

    @Override
    public int getStudentCount() {
        return studentDAO.getStudentCount();
    }
}