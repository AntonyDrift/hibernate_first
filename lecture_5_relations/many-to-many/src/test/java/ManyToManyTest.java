import by.it.entity.Department;
import by.it.entity.Employee;
import by.it.entity.Meeting;
import by.it.util.EMUtil;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;

/**
 * Class PersonEntityManagerTest
 *
 * Created by yslabko on 08/30/2017.
 */
public class ManyToManyTest {
    @Before
    public void init() {
        EntityManager em = EMUtil.getEntityManager("by.it");
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
        Meeting meeting1 = new Meeting();
        Meeting meeting2 = new Meeting();
        meeting1.setSubject("Subject1");
        meeting2.setSubject("Subject2");
        Employee e1 = new Employee(null, "Mohammad", "Salek", null, null, qa, new ArrayList());
        Employee e2 = new Employee(null, "Lilia", "Worjec", null, null, qa, new ArrayList());
        Employee e3 = new Employee(null, "Yana", "Kozhedub", null, null, hr, new ArrayList());
        Employee e4 = new Employee(null, "Yuliya", "Samohina", null, null, hr, new ArrayList());
        Employee e5 = new Employee(null, "Gena", "RGB", null, null, devOps, new ArrayList());
        Employee e6 = new Employee(null, "Sasha", "Shi", null, null, pm, new ArrayList());
        em.persist(e6);
        Employee e7 = new Employee(null, "Alex", "Trump", null, null, al, new ArrayList());
        meeting1.getEmployees().add(e1);
        meeting1.getEmployees().add(e2);
        meeting1.getEmployees().add(e3);
        meeting1.getEmployees().add(e5);
        meeting2.getEmployees().add(e4);
        meeting2.getEmployees().add(e5);
        meeting2.getEmployees().add(e6);
        meeting2.getEmployees().add(e7);
        meeting2.getEmployees().add(e1);
        e1.getMeetings().add(meeting1);
        e2.getMeetings().add(meeting1);
        e3.getMeetings().add(meeting1);
        e4.getMeetings().add(meeting2);
        e5.getMeetings().add(meeting1);
        e5.getMeetings().add(meeting2);
        e7.getMeetings().add(meeting2);
        em.persist(meeting1);
        em.persist(meeting2);
        em.getTransaction().commit();
    }



    @Test
    public void saveTest() {
        Employee employee = new Employee(null, "Yulij", "Slabko", null, null, null, null);

        EntityManager em = EMUtil.getEntityManager("by.it");
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.clear();
        Employee employeeFromDb = em.find(Employee.class, 1L);
    }

    @Test
    public void deleteTest() {
        EntityManager em = EMUtil.getEntityManager("by.it");
        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, 11L);
        System.out.println(employee);
        for (Meeting meeting : employee.getMeetings()) {
            em.remove(meeting);
        }

        em.remove(employee);
        em.getTransaction().commit();
        em.clear();
    }

    @AfterClass
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}
