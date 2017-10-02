package by.it.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMUtil {
    private static final EntityManagerFactory emFactory;
    /*
        EntityManager initialization
     */
    static {
        emFactory = Persistence.createEntityManagerFactory("by.it.test");
    }

    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public static void closeEMFactory() {
        emFactory.close();
    }
}
