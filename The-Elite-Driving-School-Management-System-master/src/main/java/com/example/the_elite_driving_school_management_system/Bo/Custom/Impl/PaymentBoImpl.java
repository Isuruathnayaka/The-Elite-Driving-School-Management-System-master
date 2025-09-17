package com.example.the_elite_driving_school_management_system.Bo.Custom.Impl;

import com.example.the_elite_driving_school_management_system.Bo.Custom.PaymentBo;
import com.example.the_elite_driving_school_management_system.Bo.MapUtil;
import com.example.the_elite_driving_school_management_system.Bo.SuperBO;
import com.example.the_elite_driving_school_management_system.DAO.Custom.PaymentDAO;
import com.example.the_elite_driving_school_management_system.DAO.Custom.StudentDAO;
import com.example.the_elite_driving_school_management_system.DAO.DAOFactory;
import com.example.the_elite_driving_school_management_system.DTO.PaymentDTO;
import com.example.the_elite_driving_school_management_system.Entity.Student;
import com.example.the_elite_driving_school_management_system.Entity.Payment;
import com.example.the_elite_driving_school_management_system.Bo.Custom.StudentBo;

import java.util.List;

public class PaymentBoImpl implements PaymentBo, SuperBO {

    // DAO instances
    private final PaymentDAO paymentDAO;
    private final StudentDAO studentDAO;
    private final StudentBo studentBo;

    // Constructor
    public PaymentBoImpl(PaymentDAO paymentDAO) {
        this.paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
        this.studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);
        this.studentBo = (StudentBo) com.example.the_elite_driving_school_management_system.Bo.BOFactory.getInstance()
                .getBO(com.example.the_elite_driving_school_management_system.Bo.BOFactory.BOType.STUDENT);
    }

    @Override
    public boolean save(PaymentDTO paymentDTO) {
        // Fetch student entity
        Student student = studentDAO.findById(paymentDTO.getStudentId());
        if (student == null) return false;

        // Map DTO to Entity
        Payment payment = MapUtil.toEntity(paymentDTO, student);

        // Save using DAO
        return paymentDAO.save(payment);
    }

    @Override
    public boolean update(PaymentDTO dto) {
        // Implement update logic if needed
        return false;
    }

    @Override
    public String generateNewPaymentId() {
        return paymentDAO.generateNewId(); // Use DAO to generate new ID like P001
    }


    @Override
    public String generateNewStudentId() {
        return "";
    }

    @Override
    public Student findById(String studentId) {
        return studentDAO.findById(studentId);
    }

    @Override
    public List<String> getCourseIdsByStudent(String studentId) {
        // Fetch student entity and get their courses
        Student student = findById(studentId);
        if (student != null && student.getCourses() != null) {
            return student.getCourses().stream()
                    .map(course -> course.getCourseId().toString()) // Assuming courseId is String
                    .toList();
        }
        return List.of();
    }
}
