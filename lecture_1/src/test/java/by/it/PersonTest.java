package by.it;

import javax.persistence.EntityManager;
import org.hibernate.HibernateException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import by.it.pojos.Person;
import by.it.util.HibernateUtil;
public class PersonTest {
    @Test
    public void saveTest() {
        Person person = new Person(null, 25, "Yuli", "Slabko");
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } catch (HibernateException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

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
