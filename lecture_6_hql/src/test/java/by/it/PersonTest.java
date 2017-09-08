package by.it;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.AfterClass;
import org.junit.Test;

import by.it.entity.Person;
import by.it.util.HibernateUtil;

/**
 * Class PersonTest
 *
 * Created by yslabko on 08/30/2017.
 */
public class PersonTest {
    @Test
    public void namedQueryTest() {
        Person person = new Person(null, 25, "Yuli", "Slabko");
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.clear();

        Query personByName = em.createNamedQuery("getPersonByName");
        List personList = personByName.setParameter("name", "Yuli")
                .getResultList();
        personList.forEach(System.out::println);
    }

    @AfterClass
    public static void cleanUp() {
        HibernateUtil.close();
    }
}
