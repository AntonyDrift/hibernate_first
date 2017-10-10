import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import by.it.entity.Employee;
import by.it.entity.EmployeeDetail;
import by.it.util.EMUtil;

/**
 * Class PersonEntityManagerTest
 *
 * Created by yslabko on 08/30/2017.
 */
public class OneToOneTest {
    @Test
    public void saveTest() {
        EntityManager em = EMUtil.getEntityManager("by.it.test");
        em.getTransaction().begin();
        Employee employee = new Employee(null, "Yulij", "Slabko", null, null);
        EmployeeDetail employeeDetail = new EmployeeDetail(null, "Sadovaya", "Minsk", "",
                "Belarus", employee);
        em.persist(employee);
        employee.setEmployeeDetail(employeeDetail);
        em.getTransaction().commit();
        em.clear();
        Employee employeeFromDb = em.find(Employee.class, 1L);
        Assert.assertEquals(employee.getFirstName(), employeeFromDb.getFirstName());
    }

    @Test
    public void persistCascadeTest() {
        EntityManager em = EMUtil.getEntityManager("by.it.test");
        em.getTransaction().begin();
        Employee employee = new Employee(null, "Yulij", "Slabko", null, null);
        EmployeeDetail employeeDetail = new EmployeeDetail(null, "Sadovaya", "Minsk", "", "Belarus", employee);
        employee.setEmployeeDetail(employeeDetail);
        em.persist(employee);
        em.getTransaction().commit();
        em.clear();
        Employee employeeFromDb = em.find(Employee.class, 1L);
        Assert.assertEquals(employee.getFirstName(), employeeFromDb.getFirstName());
        em.getTransaction().begin();
        em.remove(employeeFromDb);
        em.getTransaction().commit();
    }

    @Test
    public void mergeCascadeTest() {
        EntityManager em = EMUtil.getEntityManager("by.it.test");
        em.getTransaction().begin();
        Employee employee = new Employee(null, "Yulij", "Slabko", null, null);
        EmployeeDetail employeeDetail = new EmployeeDetail(null, "Sadovaya", "Minsk", "", "Belarus", employee);
        employee.setEmployeeDetail(employeeDetail);
        em.persist(employee);
        em.getTransaction().commit();
        em.clear();
        Employee employeeFromDb = em.find(Employee.class, 1L);
        employeeFromDb.getEmployeeDetail().setCity("Kiev");
        em.getTransaction().begin();
        em.merge(employeeFromDb);
        em.getTransaction().commit();
        Assert.assertEquals(employee.getFirstName(), employeeFromDb.getFirstName());
    }

    @Test
    public void removeCascadeTest() {
        EntityManager em = EMUtil.getEntityManager("by.it.test");
        em.getTransaction().begin();
        Employee employee = new Employee(null, "Yulij", "Slabko", null, null);
        EmployeeDetail employeeDetail = new EmployeeDetail(null, "Sadovaya", "Minsk", "", "Belarus", employee);
        employee.setEmployeeDetail(employeeDetail);
        em.persist(employee);
        em.getTransaction().commit();
        em.getTransaction().begin();
        em.remove(employee);
        em.getTransaction().commit();
    }
    @AfterClass
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}
