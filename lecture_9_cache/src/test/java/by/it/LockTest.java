package by.it;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.junit.Before;
import org.junit.Test;

import by.it.entity.Department;
import by.it.entity.Employee;
import by.it.util.HibernateUtil;

/**
 * Class LockTest
 *
 * Created by yslabko on 10/06/2017.
 */
public class LockTest {
    @Before
    public void init() {
        Department developer = new Department("Developer");
        Department hr = new Department("HR");
        Department qa = new Department("QA");
        developer.getEmployees().add(new Employee(null, "Yuli", "", new Date(), null, developer, null));
        developer.getEmployees().add(new Employee(null, "Max", "", new Date(), null, developer, null));
        developer.getEmployees().add(new Employee(null, "Paul", "", new Date(), null, developer, null));
        qa.getEmployees().add(new Employee(null, "Gleb", "", new Date(), null, qa, null));
        qa.getEmployees().add(new Employee(null, "Li", "", new Date(), null, qa, null));
        hr.getEmployees().add(new Employee(null, "Alex", "", new Date(), null, hr, null));
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(developer);
        em.persist(qa);
        em.persist(hr);
        em.getTransaction().commit();
        em.clear();
        em.close();
    }

    @Test
    public void noLockModeTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, 3L);
        employee.setLastname("Knuth");
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void optimisticLockModeTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, 3L, LockModeType.OPTIMISTIC);
        employee.setLastname("Knuth");
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void optimisticIncrementLockModeTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, 3L, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        employee.setLastname("Knuth");
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void lockModeReadTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, 3L, LockModeType.PESSIMISTIC_WRITE);
        employee.setLastname("Knuth");
        em.getTransaction().commit();
        em.close();
    }
}
