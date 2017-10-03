import by.it.entity.Employee;
import by.it.util.EMUtil;
import org.junit.AfterClass;
import org.junit.Test;

import javax.persistence.EntityManager;

/**
 * Class PersonEntityManagerTest
 *
 * Created by yslabko on 08/30/2017.
 */
public class ManyToManyTest {
    @Test
    public void saveTest() {
        Employee employee = new Employee(null, "Yulij", "Slabko", null, null, null, null);

        EntityManager em = EMUtil.getEntityManager("by.it.test");
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.clear();
        Employee employeeFromDb = em.find(Employee.class, 1L);
    }

    @AfterClass
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}
