package by.it;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;

import by.it.entity.Department;
import by.it.entity.Employee;
import by.it.util.HibernateUtil;

/**
 * Class JoinTest
 *
 * Created by yslabko on 09/08/2017.
 */
public class JoinTest {

    @Before
    public void init() {
        Department developer = new Department("Developer");
        Department hr = new Department("HR");
        Department qa = new Department("QA");
        developer.getEmployees().add(new Employee(null, "Yuli", 27, 16000.99, developer));
        developer.getEmployees().add(new Employee(null, "Max", 38, 10000, developer));
        developer.getEmployees().add(new Employee(null, "Paul", 43, 15000, developer));
        qa.getEmployees().add(new Employee(null, "Gleb", 37, 15000, qa));
        qa.getEmployees().add(new Employee(null, "Li", 62, 13099, qa));
        hr.getEmployees().add(new Employee(null, "Alex", 22, 4500, hr));
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
    public void joinTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Department> criteria = cb.createQuery(Department.class);
        Root<Department> department = criteria.from(Department.class);
        Join<Department, Employee> employeeJoin = department.join("employees", JoinType.INNER);
        criteria.where(cb.equal(employeeJoin.get("name"), "Yuli"));
        List<Department> departments = em.createQuery(criteria).getResultList();
        departments.forEach(System.out::println);
    }
}
