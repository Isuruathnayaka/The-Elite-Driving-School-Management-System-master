package com.example.the_elite_driving_school_management_system.DAO.Custom.impl;

import com.example.the_elite_driving_school_management_system.Config.FactoryConfiguration;
import com.example.the_elite_driving_school_management_system.DAO.Custom.StudentDAO;
import com.example.the_elite_driving_school_management_system.DTO.InstructorDTO;
import com.example.the_elite_driving_school_management_system.DTO.StudentDTO;
import com.example.the_elite_driving_school_management_system.Entity.Course;
import com.example.the_elite_driving_school_management_system.Entity.Student;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private static FactoryConfiguration factoryConfiguration=FactoryConfiguration.getInstance();;

    @Override
    public boolean save(Student dto) {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        try {
            // Use persist() instead of save() for Hibernate 6.0+
            session.persist(dto);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public String generateNewId() {
        try (Session session = factoryConfiguration.getSession()) {
            // Get the last studentId (ordered descending)
            String lastId = session.createQuery(
                            "SELECT s.id FROM Student s ORDER BY s.id DESC", String.class
                    )
                    .setMaxResults(1)   // only take top 1
                    .uniqueResult();

            if (lastId != null) {
                // Extract digits only
                String numericPart = lastId.replaceAll("\\D", "");
                int newId = Integer.parseInt(numericPart) + 1;

                // Format as S001, S002, etc.
                return String.format("S%03d", newId);
            } else {
                return "S001"; // first ID
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "S001"; // fallback to first ID if query fails
        }
    }

    @Override
    public ArrayList<Student> getAll() {
        try (Session session =factoryConfiguration.getSession()) {
            List<Student> entityList = session.createQuery("from Student", Student.class).list();
            return new ArrayList<>(entityList); // return entities
        }
    }

    // Additional CRUD methods you might need

    public Student findById(String id) {
        try (Session session = factoryConfiguration.getSession()) {
            return session.get(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public boolean update(Student student) {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.merge(student); // Use merge() for updates
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean delete(String studentId) {
        Session session =factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        try {
            Student student = session.get(Student.class, studentId);
            if (student != null) {
                session.remove(student);
                tx.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<String> getCourseIdsByStudentId(String studentId) {
        Session session = factoryConfiguration.getSession();
        Transaction tx = null;
        List<String> courseIds = new ArrayList<>();

        try{
            tx = session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            if (student != null) {
                courseIds=student.getCourses()
                        .stream()
                        .map(Course::getId)
                        .toList();
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
        return courseIds;
    }

}