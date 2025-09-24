package com.example.the_elite_driving_school_management_system.Bo.Custom.Impl;

import com.example.the_elite_driving_school_management_system.Bo.Custom.LessonScheduleBo;
import com.example.the_elite_driving_school_management_system.Bo.MapUtil;
import com.example.the_elite_driving_school_management_system.Config.FactoryConfiguration;
import com.example.the_elite_driving_school_management_system.DAO.Custom.LessonScheduleDAO;
import com.example.the_elite_driving_school_management_system.DTO.LessonDTO;
import com.example.the_elite_driving_school_management_system.DTO.PaymentDTO;
import com.example.the_elite_driving_school_management_system.Entity.Course;
import com.example.the_elite_driving_school_management_system.Entity.Instructor;
import com.example.the_elite_driving_school_management_system.Entity.Lesson;
import com.example.the_elite_driving_school_management_system.Entity.Student;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;



public class LessonScheduleBoImpl implements LessonScheduleBo {
    private final LessonScheduleDAO lessonScheduleDAO;
    private final FactoryConfiguration factoryConfiguration=FactoryConfiguration.getInstance();

    public LessonScheduleBoImpl(LessonScheduleDAO lessonScheduleDAO) {
        this.lessonScheduleDAO = lessonScheduleDAO;
    }



    @Override
    public boolean save(LessonDTO dto) {
        try {
            // Fetch related entities from their DAOs using IDs in DTO
            Student student = lessonScheduleDAO.findStudentById(dto.getStudentId());
            Instructor instructor = lessonScheduleDAO.findInstructorById(dto.getInstructorId());
            Course course = lessonScheduleDAO.findCourseById(dto.getCourseId());

            // Map DTO -> Entity
            Lesson lesson = MapUtil.toEntity(dto, student, instructor, course);

            // Save entity
            return lessonScheduleDAO.save(lesson);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(LessonDTO dto) {
        return false;
    }

    @Override
    public String generateNewLessonId() {
        return lessonScheduleDAO.generateNewId();
    }

    @Override
    public List<String> getStudentIdsByCourse(String courseId) {
        try {
            return lessonScheduleDAO.getStudentIdsByCourseId(courseId);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public String getInstructorIdByCourse(String courseId) {
        try {
            return lessonScheduleDAO.getInstructorIdByCourseId(courseId);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public List<LessonDTO> getAllLessons() {
        List<LessonDTO> lessonDTOList = new ArrayList<>();
        try (Session session = factoryConfiguration.getSession()) {
            List<Lesson> lessons = session.createQuery("FROM Lesson", Lesson.class).list();
            for (Lesson l : lessons) {
                lessonDTOList.add(new LessonDTO(
                        l.getId(),
                        l.getName(),
                        l.getDate(),
                        l.getCourse(),
                        l.getInstructor(),
                        l.getDuration(),
                        l.getStatus(),
                        l.getStudent(),
                        l.getTime().toLocalTime()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lessonDTOList;
    }


    @Override
    public PaymentDTO findById(String paymentId) {
        return lessonScheduleDAO.findById(paymentId);
    }
}
