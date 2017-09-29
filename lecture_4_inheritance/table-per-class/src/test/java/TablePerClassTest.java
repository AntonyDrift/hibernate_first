import by.it.entity.Employee;
import by.it.entity.Person;
import by.it.entity.Student;
import by.it.util.EMUtil;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;

/**
 * Class PersonEntityManagerTest
 *
 * Created by yslabko on 08/30/2017.
 */
public class TablePerClassTest {
    @Test
    public void saveTest() {
        Person person = new Person(null, 25, "Yuli", "Slabko");
        Employee employee = new Employee(null, 25, "Tom", "Soyer", "RedAndBlack", 1200.0);
        Student student = new Student(null, 25, "Tom", "Soyer", "Chemistry", 8.0);

        EntityManager em = EMUtil.getEntityManager("by.it.test");
        em.getTransaction().begin();
        em.persist(person);
        em.persist(employee);
        em.persist(student);
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        Person personFromDb = em.find(Person.class, person.getId());
        em.getTransaction().commit();
        Assert.assertEquals(person, personFromDb);
    }

    @AfterClass
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}
