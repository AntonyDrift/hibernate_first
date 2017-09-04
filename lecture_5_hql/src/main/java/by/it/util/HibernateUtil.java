package by.it.util;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import by.it.entity.Person;

public class HibernateUtil {
    private static final EntityManagerFactory emFactory;
    private static final SessionFactory sessionFactory;

    /*
        EntityManager initialization
     */
    static {
        emFactory = Persistence.createEntityManagerFactory("by.it");
    }

    /*
        SessionFactory initialization
     */
    static {
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

        // Hibernate settings equivalent to hibernate.cfg.xml's properties
        Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/jpadb");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "yuli");
        settings.put(Environment.HBM2DDL_AUTO, "validate");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.STORAGE_ENGINE, "innodb");

        // Apply settings
        serviceRegistryBuilder.applySettings(settings);

        // Create registry
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();

        // Create MetadataSources
        MetadataSources sources = new MetadataSources(serviceRegistry);
        sources.addAnnotatedClass(Person.class);

        // Create Metadata
        Metadata metadata = sources.getMetadataBuilder().build();

        // Create SessionFactory
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public static void close() {
        if (emFactory != null) {
            emFactory.close();
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
