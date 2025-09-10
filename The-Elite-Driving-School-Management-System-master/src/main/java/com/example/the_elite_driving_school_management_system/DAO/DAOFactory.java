package com.example.the_elite_driving_school_management_system.DAO;

import com.example.the_elite_driving_school_management_system.DAO.Custom.impl.CourseDAOImpl;
import com.example.the_elite_driving_school_management_system.DAO.Custom.impl.InstructorDAOImpl;
import com.example.the_elite_driving_school_management_system.DAO.Custom.impl.LoginDAOimpl;
import com.example.the_elite_driving_school_management_system.DAO.Custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {}
    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    public enum DAOType {
        LOGIN,
        STUDENT,
        INSTRUCTOR,
        COURSE,
    }
    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case LOGIN:
                return new LoginDAOimpl();
                case STUDENT:
                    return new StudentDAOImpl();
                    case INSTRUCTOR:
                        return new InstructorDAOImpl();
                        case COURSE:
                            return new CourseDAOImpl();
        }
        return null;
    }
}
