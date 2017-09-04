package by.it;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import by.it.component.Address;
import by.it.entity.Person;
import by.it.util.HibernateUtil;

/**
 * Class PersonTest
 *
 * Created by yslabko on 08/30/2017.
 */
public class PersonTest {
    @Test
    public void saveTest() {
        Person person = new Person(null, 25, "Yuli", "Slabko", new Address("Sadovaya", "Minsk", "220000"));
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.clear();

        em.createQuery("from Employee")
                .getResultList();

        em.getTransaction().begin();
        Person personFromDb = em.find(Person.class, person.getId());
        Assert.assertEquals(person, personFromDb);
        em.getTransaction().commit();
    }

    @AfterClass
    public static void cleanUp() {
        HibernateUtil.close();
    }
}
