package com.example.the_elite_driving_school_management_system.Bo;

import com.example.the_elite_driving_school_management_system.Bo.Custom.Impl.CourseBoImpl;
import com.example.the_elite_driving_school_management_system.Bo.Custom.Impl.InstructorBoImpl;
import com.example.the_elite_driving_school_management_system.Bo.Custom.Impl.LoginBoImpl;
import com.example.the_elite_driving_school_management_system.Bo.Custom.Impl.StudentBoImpl;
import com.example.the_elite_driving_school_management_system.DAO.Custom.CourseDAO;
import com.example.the_elite_driving_school_management_system.DAO.Custom.InstructorDAO;
import com.example.the_elite_driving_school_management_system.DAO.Custom.LoginDAO;
import com.example.the_elite_driving_school_management_system.DAO.Custom.StudentDAO;
import com.example.the_elite_driving_school_management_system.DAO.DAOFactory;
import com.example.the_elite_driving_school_management_system.DTO.CourseDTO;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOType {
        LOGIN,
        STUDENT,
        INSTRUCTOR,
        COURSE
    }

    public SuperBO getBO(BOType boType) {
        switch (boType) {
            case LOGIN:
                LoginDAO loginDAO = (LoginDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.LOGIN);
                return new LoginBoImpl(loginDAO);

            case STUDENT:
                StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);
                return new StudentBoImpl(studentDAO);

                case INSTRUCTOR:
                    InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.INSTRUCTOR);
                    return new InstructorBoImpl(instructorDAO);

                    case COURSE:
                        CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.COURSE);
                        return new CourseBoImpl(courseDAO);

            default:
                throw new IllegalArgumentException("Invalid BO Type: " + boType);
        }
    }
}
