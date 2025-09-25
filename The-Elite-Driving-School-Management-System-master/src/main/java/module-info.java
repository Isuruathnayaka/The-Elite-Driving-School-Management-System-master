module com.example.the_elite_driving_school_management_system {
    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    // Hibernate + JPA
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    // Database Driver
    requires mysql.connector.j;

    // JNDI (fixes javax.naming.Referenceable error)
    requires java.naming;
    requires jbcrypt;
    requires static lombok;
    requires jakarta.transaction;
    requires java.management;

    // Open entity packages for Hibernate reflection
    opens com.example.the_elite_driving_school_management_system.Entity to org.hibernate.orm.core;


    // Open controller package for FXML
    opens com.example.the_elite_driving_school_management_system.Controller to javafx.fxml;
    opens com.example.the_elite_driving_school_management_system.TM to javafx.base;

    // Export packages if needed outside
    exports com.example.the_elite_driving_school_management_system;
    exports com.example.the_elite_driving_school_management_system.Controller;
}
