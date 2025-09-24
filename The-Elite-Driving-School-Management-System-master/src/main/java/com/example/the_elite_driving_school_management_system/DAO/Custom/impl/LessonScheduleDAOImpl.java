package com.example.the_elite_driving_school_management_system.DAO.Custom.impl;

import com.example.the_elite_driving_school_management_system.Bo.MapUtil;
import com.example.the_elite_driving_school_management_system.Config.FactoryConfiguration;
import com.example.the_elite_driving_school_management_system.DAO.Custom.LessonScheduleDAO;
import com.example.the_elite_driving_school_management_system.DTO.PaymentDTO;
import com.example.the_elite_driving_school_management_system.Entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class LessonScheduleDAOImpl implements LessonScheduleDAO {
    private final FactoryConfiguration factoryConfiguration =
            FactoryConfiguration.getInstance();

    @Override
    public boolean save(Lesson dto) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(dto);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Lesson dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(dto);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
    @Override
    public boolean delete(String lessonId) {
        try (Session session = factoryConfiguration.getSession()) {
            Transaction tx = session.beginTransaction();

            Lesson lesson = session.get(Lesson.class, lessonId); // find by ID
            if (lesson != null) {
                session.delete(lesson);
                tx.commit();
                return true;
            }

            tx.rollback();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PaymentDTO findById(String paymentId) {
        Session session =factoryConfiguration.getSession();
        Query<Payment> query = session.createQuery(
                "FROM Payment p WHERE p.paymentId = :paymentId", Payment.class);

        query.setParameter("paymentId", paymentId);
        Payment payment = query.uniqueResult();
        if (payment != null) {
            return MapUtil.toDTO(payment);
        }

        return  null;
    }
    @Override
    public List<String> getStudentIdsByCourseId(String courseId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            // Use native SQL because you don’t have a StudentCourse entity
            String sql = "SELECT student_id FROM student_course WHERE course_id = :courseId";
            return session.createNativeQuery(sql)
                    .setParameter("courseId", courseId)
                    .getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public String getInstructorIdByCourseId(String courseId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            String sql = "SELECT InstructorId FROM instructor WHERE courseId = :courseId";
            return (String) session.createNativeQuery(sql)
                    .setParameter("courseId", courseId)
                    .uniqueResult();
        } finally {
            session.close();
        }
    }

    @Override
    public Student findStudentById(String studentId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            return session.get(Student.class, studentId);
        } finally {
            session.close();
        }
    }

    @Override
    public Instructor findInstructorById(String instructorId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            return session.get(Instructor.class, instructorId);
        } finally {
            session.close();
        }
    }

    @Override
    public Course findCourseById(String courseId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            return session.get(Course.class, courseId);
        } finally {
            session.close();
        }
    }
    @Override
    public String generateNewId() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Get the last lesson ID ordered descending
            String lastId = session.createQuery(
                            "SELECT l.id FROM Lesson l ORDER BY l.id DESC", String.class
                    )
                    .setMaxResults(1)
                    .uniqueResult();

            if (lastId != null) {

                String numericPart = lastId.replaceAll("\\D", "");
                int newId = Integer.parseInt(numericPart) + 1;

                return String.format("L%03d", newId);
            } else {
                return "L001";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "L001";
        }
    }

}
