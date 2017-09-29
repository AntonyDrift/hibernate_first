import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

import by.it.entity.Personable;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import by.it.entity.Employee;
import by.it.entity.Person;
import by.it.entity.Student;
import by.it.util.EMUtil;

import java.util.List;

/**
 * Class PersonEntityManagerTest
 *
 * Created by yslabko on 08/30/2017.
 */
public class TablePerSubclassTest {
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
        Assert.assertEquals(person, personFromDb);
        em.getTransaction().commit();
    }

    @AfterClass
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}
