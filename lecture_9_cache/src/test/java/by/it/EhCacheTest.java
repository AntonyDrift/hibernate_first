package by.it;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import by.it.entity.Department;
import by.it.entity.Employee;
import by.it.util.HibernateUtil;

/**
 * Class EhCacheTest
 *
 * Created by yslabko on 08/30/2017.
 */
public class EhCacheTest {
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
    public void getEmployeeTest() {
        EntityManager em = HibernateUtil.getEntityManager();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
//        Root<Employee> employeeRoot = criteria.from(Employee.class);
//        criteria.select(employeeRoot);
//        List<Employee> employees = em.createQuery(criteria).getResultList();
//        employees.forEach(System.out::println);
        em.clear();
        System.out.println(em.find(Employee.class, 3L));
        System.out.println(em.find(Employee.class, 1L));
        em.close();
    }

    @AfterClass
    public static void cleanUp() {
        HibernateUtil.close();
    }
}
