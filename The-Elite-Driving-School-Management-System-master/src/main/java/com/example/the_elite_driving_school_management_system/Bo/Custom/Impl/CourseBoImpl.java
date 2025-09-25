package com.example.the_elite_driving_school_management_system.Bo.Custom.Impl;

import com.example.the_elite_driving_school_management_system.Bo.Custom.CourseBo;
import com.example.the_elite_driving_school_management_system.Bo.MapUtil;
import com.example.the_elite_driving_school_management_system.Config.FactoryConfiguration;
import com.example.the_elite_driving_school_management_system.DAO.Custom.CourseDAO;
import com.example.the_elite_driving_school_management_system.DTO.CourseDTO;
import com.example.the_elite_driving_school_management_system.Entity.Course;

import jakarta.transaction.SystemException;
import jakarta.transaction.Transaction;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CourseBoImpl implements CourseBo {
    private final FactoryConfiguration factoryConfiguration =
            FactoryConfiguration.getInstance();
    private final CourseDAO courseDAO;

    public CourseBoImpl(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public boolean save(CourseDTO dto) {
        return courseDAO.save(MapUtil.toEntity(dto));
    }

    @Override
    public boolean update(CourseDTO dto) {
        return courseDAO.update(MapUtil.toEntity(dto));
    }

    @Override
    public String generateNewCourseId() {
        return courseDAO.generateNewId();
    }


    @Override
    public List<CourseDTO> getAllCourses() {
        List<CourseDTO> courseDTOList = new ArrayList<>();
        try (Session session = factoryConfiguration.getSession()) {
            List<Course> courses = session.createQuery("FROM Course", Course.class).list();
            for (Course c : courses) {
                courseDTOList.add(new CourseDTO(
                        c.getId(),
                        c.getName(),
                        c.getDuration(),
                        c.getFee(),
                        c.getDescription()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseDTOList;
    }

    @Override
    public boolean delete(String id) {
        return courseDAO.delete(id);
    }

    @Override
    public CourseDTO findByName(String courseName) {
        return courseDAO.findByName(courseName);
    }

    @Override
    public String getCourseFeeByCourseId(String courseId) throws SystemException {
        Session session = factoryConfiguration.getSession();
        Transaction tx = null;
        String feeStr = null;

        try {
            tx = (Transaction) session.beginTransaction();
            Course course = session.get(Course.class, courseId);
            if (course != null && course.getFee() != null) {
                feeStr = String.valueOf(course.getFee()); // Convert Double → String
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return feeStr;
    }

    @Override
    public CourseDTO findById(String id) {
        return courseDAO.findById(id);
    }

    @Override
    public int getCourseCount() {
        return courseDAO.getCourseCount();
    }


}
