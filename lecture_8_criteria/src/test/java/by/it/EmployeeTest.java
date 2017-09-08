package by.it;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import by.it.entity.Department;
import by.it.entity.Employee;
import by.it.util.HibernateUtil;

/**
 * Class EmployeeTest
 *
 * Created by yslabko on 08/30/2017.
 */
public class EmployeeTest {
    @Before
    public void init() {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        Department d = new Department("D");
        em.persist(new Employee(null, "Yuli", 27, 16000.99, d));
        em.persist(new Employee(null, "Max", 38, 10000, d));
        em.persist(new Employee(null, "Paul", 43, 15000, d));
        em.persist(new Employee(null, "Gleb", 37, 15000, d));
        em.persist(new Employee(null, "Li", 62, 13099, d));
        em.persist(new Employee(null, "Alex", 22, 4500, d));
        em.getTransaction().commit();
        em.clear();
    }

    @Test
    public void getAllEmployee() {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteria.from(Employee.class);
        criteria.select(employeeRoot);
        List<Employee> employees = em.createQuery(criteria).getResultList();
        employees.forEach(System.out::println);
    }

    @Test
    public void getEmployeeByNameTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> emp = criteria.from(Employee.class);
        criteria.select(emp)
                .where(cb.equal(emp.get("name"), "Yuli"));
        List<Employee> employees = em.createQuery(criteria).getResultList();
        employees.forEach(System.out::println);
    }

    @Test
    public void greaterTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> emp = criteria.from(Employee.class);
        criteria.select(emp)
                .where(cb.gt(emp.get("salary"), 10000));
        List<Employee> employees = em.createQuery(criteria).getResultList();
        employees.forEach(System.out::println);
    }

    @Test
    public void lessTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> emp = criteria.from(Employee.class);
        criteria.select(emp)
                .where(cb.le(emp.get("age"), 43));
        List<Employee> employees = em.createQuery(criteria).getResultList();
        employees.forEach(System.out::println);
    }

    @Test
    public void likeTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> emp = criteria.from(Employee.class);
        criteria.select(emp)
                .where(cb.like(emp.get("name"), "%ul%"));
        List<Employee> employees = em.createQuery(criteria).getResultList();
        employees.forEach(System.out::println);
    }

    @Test
    public void betweenTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> emp = criteria.from(Employee.class);
        criteria.select(emp)
                .where(cb.between(emp.get("age"), 20, 50));
        List<Employee> employees = em.createQuery(criteria).getResultList();
        employees.forEach(System.out::println);
    }

    @Test
    public void isNullTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> emp = criteria.from(Employee.class);
        criteria.select(emp)
                .where(cb.isNotNull(emp.get("name")));
//              .where(cb.isNull(emp.get("name")));
        List<Employee> employees = em.createQuery(criteria).getResultList();
        employees.forEach(System.out::println);
    }

    @Test
    public void logicalPredicateTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> emp = criteria.from(Employee.class);
        Predicate predicate = cb.and(
                cb.like(emp.get("name"), "%ul%"),
                cb.gt(emp.get("age"), 30)
        );
        criteria.select(emp).where(predicate);

        List<Employee> employees = em.createQuery(criteria).getResultList();
        employees.forEach(System.out::println);
    }

    @Test
    public void orderTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> emp = criteria.from(Employee.class);
        criteria.select(emp).orderBy(
                cb.desc(emp.get("salary")),
                cb.asc(emp.get("name"))
        );

        List<Employee> employees = em.createQuery(criteria).getResultList();
        employees.forEach(System.out::println);
    }

    @Test
    public void pagingTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        int pageNumber = 1;
        int pageSize = 2;
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> emp = criteria.from(Employee.class);
        criteria.select(emp);
        TypedQuery<Employee> typedQuery = em.createQuery(criteria);
        typedQuery.setFirstResult(pageSize * (pageNumber-1));
        typedQuery.setMaxResults(pageSize);
        List<Employee> employees = typedQuery.getResultList();

        employees.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        criteria.select(cb.count(criteria.from(Employee.class)));
        long count = em.createQuery(criteria).getSingleResult();
        System.out.println(count);
    }

    @Test
    public void countDistinctTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        criteria.select(cb.countDistinct(criteria.from(Employee.class)));
        long count = em.createQuery(criteria).getSingleResult();
        System.out.println(count);
    }

    @Test
    public void avgTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Double> criteria = cb.createQuery(Double.class);
        criteria.select(cb.avg(criteria.from(Employee.class).get("salary")));
        double count =  em.createQuery(criteria).getSingleResult();
        System.out.println(count);
    }

    @Test
    public void maxTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Double> criteria = cb.createQuery(Double.class);
        criteria.select(cb.max(criteria.from(Employee.class).get("salary")));
        double count =  em.createQuery(criteria).getSingleResult();
        System.out.println(count);
    }
    @Test
    public void minTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Double> criteria = cb.createQuery(Double.class);
        criteria.select(cb.min(criteria.from(Employee.class).get("age")));
        double count =  em.createQuery(criteria).getSingleResult();
        System.out.println(count);
    }
    @Test
    public void sumTest() {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Double> criteria = cb.createQuery(Double.class);
        criteria.select(cb.sum(criteria.from(Employee.class).get("salary")));
        double count =  em.createQuery(criteria).getSingleResult();
        System.out.println(count);
    }


    @AfterClass
    public static void cleanUp() {
        HibernateUtil.close();
    }
}
