package by.it.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMUtil {
    private static EntityManagerFactory emFactory=null;

    public static EntityManager getEntityManager() {
        emFactory  = Persistence.createEntityManagerFactory("by.it");
        return emFactory.createEntityManager();
    }

    public static EntityManager getEntityManager(String unit) {
        if (emFactory == null) {
            emFactory  = Persistence.createEntityManagerFactory(unit);
        }
        return emFactory.createEntityManager();
    }

    public static void closeEMFactory() {
        emFactory.close();
    }
}