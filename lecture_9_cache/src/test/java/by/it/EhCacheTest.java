package by.it;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Test;

import by.it.entity.Employee;
import by.it.util.HibernateUtil;

/**
 * Class EhCacheTest
 *
 * Created by yslabko on 08/30/2017.
 */
public class EhCacheTest {
    @Test
    public void namedQueryTest() {
        Employee employee = new Employee(null, "Yuli", 25, 10000.99);
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.clear();
    }

    @AfterClass
    public static void cleanUp() {
        HibernateUtil.close();
    }
}
