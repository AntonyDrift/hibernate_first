import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import by.it.entity.Employee;
import by.it.util.EMUtil;

/**
 * Class PersonEntityManagerTest
 *
 * Created by yslabko on 08/30/2017.
 */
public class MappedSuperclassTest {
    @Test
    public void saveTest() throws InterruptedException {
        Employee employee = new Employee("Yulij", "Exadel", 15200.0);

        EntityManager em = EMUtil.getEntityManager("by.it.test");
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.clear();
        Employee employeeFromDb = em.find(Employee.class, 1L);
        System.out.println(employeeFromDb);
        em.getTransaction().begin();
        employeeFromDb.setSalary(20000.0);
        Thread.sleep(10);
        em.merge(employeeFromDb);
        em.getTransaction().commit();
        System.out.println(employeeFromDb);
    }

    @AfterClass
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}
