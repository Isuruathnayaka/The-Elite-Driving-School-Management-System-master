package com.example.the_elite_driving_school_management_system.Bo;

import com.example.the_elite_driving_school_management_system.Bo.Custom.Impl.*;
import com.example.the_elite_driving_school_management_system.DAO.Custom.*;
import com.example.the_elite_driving_school_management_system.DAO.DAOFactory;


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

        STUDENT,
        INSTRUCTOR,
        COURSE,
        PAYMENT,
        LESSON,
        SETTINGS
    }

    public SuperBO getBO(BOType boType) {
        switch (boType) {
//            case LOGIN:
//                LoginDAO loginDAO = (LoginDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.LOGIN);
//                return new LoginBoImpl(loginDAO);

            case STUDENT:
                StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);
                return new StudentBoImpl(studentDAO);

                case INSTRUCTOR:
                    InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.INSTRUCTOR);
                    return new InstructorBoImpl(instructorDAO);

                    case COURSE:
                        CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.COURSE);
                        return new CourseBoImpl(courseDAO);

                        case PAYMENT:
                            PaymentDAO paymentDAO=(PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
                            return new PaymentBoImpl(paymentDAO);
                           case LESSON:
                                LessonScheduleDAO lessonScheduleDAO=(LessonScheduleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.LESSON);
                                return new LessonScheduleBoImpl(lessonScheduleDAO);
                                case SETTINGS:
                                    SettingsDAO settingsDAO=(SettingsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SETTINGS);
                                    return new SettingsBoImpl(settingsDAO);



            default:
                throw new IllegalArgumentException("Invalid BO Type: " + boType);
        }
    }
}
