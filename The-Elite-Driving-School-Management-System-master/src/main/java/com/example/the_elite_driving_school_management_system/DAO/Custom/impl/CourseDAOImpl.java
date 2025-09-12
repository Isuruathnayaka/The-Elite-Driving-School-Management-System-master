package com.example.the_elite_driving_school_management_system.DAO.Custom.impl;

import com.example.the_elite_driving_school_management_system.Bo.MapUtil;
import com.example.the_elite_driving_school_management_system.Config.FactoryConfiguration;
import com.example.the_elite_driving_school_management_system.DAO.Custom.CourseDAO;
import com.example.the_elite_driving_school_management_system.DTO.CourseDTO;
import com.example.the_elite_driving_school_management_system.Entity.Course;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    private final FactoryConfiguration factoryConfiguration =
            FactoryConfiguration.getInstance();

    @Override
    public boolean save(Course dto) {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        try {

            session.persist(dto); //persist=save
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
    public boolean update(Course dto) {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.merge(dto); // merge=update
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
        try (Session session = factoryConfiguration.getSession();) {
            // Get the last courseId (ordered descending)
            String lastId = session.createQuery(
                            "SELECT c.id FROM Course c ORDER BY c.id DESC", String.class
                    )
                    .setMaxResults(1)   // only take top 1
                    .uniqueResult();

            if (lastId != null) {
                // Extract digits only
                String numericPart = lastId.replaceAll("\\D", "");
                int newId = Integer.parseInt(numericPart) + 1;

                // Format as C1001, C1002, etc.
                return String.format("C%d", newId);
            } else {
                return "C1001"; // first ID
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "C1001"; // fallback to first ID if query fails
        }

    }

    @Override
    public boolean delete(String id) {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        try {
            Course course = session.get(Course.class, id);
            if (course != null) {
                session.remove(course);
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
    public CourseDTO findByName(String courseName) {
        try (Session session = factoryConfiguration.getSession()) {
            Query<Course> query = session.createQuery(
                    "FROM Course c WHERE c.name = :name", Course.class);
            query.setParameter("name", courseName);

            Course course = query.uniqueResult();
            if (course != null) {
                return MapUtil.toDTO(course);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> getCourseFeeByCourseId(String courseId) {
        Session session = factoryConfiguration.getSession();
        Transaction tx = null;
        List<String> courseFeeList = new ArrayList<>();

        try {
            tx = session.beginTransaction();
            Course course = session.get(Course.class, courseId);

            if (course != null && course.getFee() != null) {
                courseFeeList.add(String.valueOf(course.getFee())); // Convert Double -> String
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return courseFeeList;
    }

}
