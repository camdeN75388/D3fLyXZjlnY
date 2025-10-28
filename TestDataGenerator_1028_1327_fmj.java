// 代码生成时间: 2025-10-28 13:27:23
package com.example.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.selector.SimpleStrategyRegistrationImpl;
import java.util.Properties;

/**
 * TestDataGenerator is a utility class used to generate test data using Hibernate.
 * It provides a simple way to populate the database with sample data.
 */
public class TestDataGenerator {

    private static SessionFactory sessionFactory;

    /**
     * Initialize the Hibernate SessionFactory.
     * This method is called once to set up the database connection.
     */
    private static void initializeSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + "Exception: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Generate test data and save it to the database.
     * This method is responsible for creating and persisting test data.
     */
    public static void generateTestData() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                // Code to generate and persist test data goes here.
                // For example, let's create a simple Entity:
                // Entity entity = new Entity();
                // entity.setData("Sample Data");
                // session.save(entity);

                // Commit the transaction
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the SessionFactory.
     */
    public static void shutdown() {
        getSessionFactory().close();
    }

    /**
     * Get the SessionFactory.
     * @return the SessionFactory instance.
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            initializeSessionFactory();
        }
        return sessionFactory;
    }

    public static void main(String[] args) {
        generateTestData();
        shutdown();
    }
}
