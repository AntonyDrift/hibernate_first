import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OneToMany;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import by.it.entity.Department;
import by.it.entity.Employee;
import by.it.entity.EmployeeDetail;
import by.it.util.EMUtil;

/**
 * Class PersonEntityManagerTest
 *
 * Created by yslabko on 08/30/2017.
 */
public class OneToManyTest {
    private static final Logger logger = LogManager.getLogger(OneToManyTest.class);

    @Before
    public void init() {
        EntityManager em = EMUtil.getEntityManager("by.it.test");
        em.getTransaction().begin();
        Department qa = new Department("QA");
        Department ba = new Department("BA");
        Department hr = new Department("HR");
        Department devOps = new Department("DevOps");
        Department pm = new Department("PM");
        Department al = new Department("AL");
        em.persist(qa);
        em.persist(ba);
        em.persist(hr);
        em.persist(devOps);
        em.persist(pm);
        em.persist(al);
        em.persist(new Employee(null, "Mohammad", "Salek", null, null, qa));
        em.persist(new Employee(null, "Lilia", "Worjec", null, null, qa));
        em.persist(new Employee(null, "Yana", "Kozhedub", null, null, hr));
        em.persist(new Employee(null, "Yuliya", "Samohina", null, null, hr));
        em.persist(new Employee(null, "Gena", "RGB", null, null, devOps));
        em.persist(new Employee(null, "Sasha", "Shi", null, null, pm));
        em.persist(new Employee(null, "Alex", "Trump", null, null, al));
        em.getTransaction().commit();
    }

    @Test
    public void saveTest() {
        EntityManager em = EMUtil.getEntityManager("by.it.test");
        em.getTransaction().begin();
        Department department = new Department("SD");
        em.persist(department);
        Employee employee = new Employee(null, "Yulij", "Slabko", null, null, department);
        EmployeeDetail employeeDetail = new EmployeeDetail(null, "Sadovaya", "Minsk", "", "Belarus", employee);
        em.persist(employee);
        em.persist(employeeDetail);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void batchTest() {
        EntityManager em = EMUtil.getEntityManager("by.it.test");
        em.getTransaction().begin();
        Department sd = new Department("SD");
        em.persist(sd);
        em.persist(new Employee(null, "Yulij", "Slabko", null, null, sd));
        em.persist(new Employee(null, "Paul", "Knuth", null, null, sd));
        em.getTransaction().commit();
        em.clear();
        List<Department> departments = em.createQuery(
                "select d from Department d " +
                        "inner join d.employees e " +
                        "where e.firstName like '%'", Department.class)
                .getResultList();

        for (Department department : departments) {
            logger.error("Department {} contains {} employees",
                    department.getDepartmentId(),
                    department.getEmployees().size()
            );
        }
        em.close();
    }

    @AfterClass
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}
