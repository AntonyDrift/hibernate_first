package by.it.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;

public class HibernateUtil {
    private static final EntityManagerFactory emFactory;

    /*
        EntityManager initialization
     */
    static {
        emFactory = Persistence.createEntityManagerFactory("by.it");
    }

    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public static void close() {
        if (emFactory != null) {
            emFactory.close();
        }
    }
}
