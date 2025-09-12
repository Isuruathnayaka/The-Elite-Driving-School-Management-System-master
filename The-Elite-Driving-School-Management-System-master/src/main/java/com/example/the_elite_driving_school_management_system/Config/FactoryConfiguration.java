package com.example.the_elite_driving_school_management_system.Config;

import com.example.the_elite_driving_school_management_system.Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        // 1. Create Configuration object
        Configuration configuration = new Configuration();

        // 2. Add all entity classes
        configuration.addAnnotatedClass(Login.class);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Instructor.class);
        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(Lesson.class);
        configuration.addAnnotatedClass(Payment.class);

        // 3. Build SessionFactory using hibernate.properties
        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    // Singleton instance
    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
