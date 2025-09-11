package com.example.the_elite_driving_school_management_system.DAO;

import com.example.the_elite_driving_school_management_system.DAO.Custom.impl.*;

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
        PAYMENT
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
                            case PAYMENT:
                                return new PaymentDAOImpl();
        }
        return null;
    }
}
