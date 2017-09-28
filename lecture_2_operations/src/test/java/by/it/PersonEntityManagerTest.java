package by.it;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import by.it.entity.Person;
import by.it.util.EMUtil;

import static org.junit.Assert.assertTrue;

/**
 * Class PersonEntityManagerTest
 *
 * Created by yslabko on 08/30/2017.
 */
public class PersonEntityManagerTest {
    @Test
    public void saveTest() {
        Person person = new Person(null, 25, "Yuli", "Slabko");
        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        Person personFromDb = em.find(Person.class, person.getId());
        Assert.assertEquals(person, personFromDb);
        em.getTransaction().commit();
    }
    @Test
    public void flushAutoJPQLTest() {
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new Person(null, 25, "Yuli", "Slabko"));
        entityManager.createQuery( "select d from Department d" ).getResultList();
        entityManager.createQuery( "select p from Person p" ).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    @Test
    public void flushAutoNativeSqlTest() {
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery( "select count(*) from Person" ).getSingleResult();
        entityManager.persist(new Person(null, 25, "Yuli", "Slabko"));
        entityManager.createNativeQuery( "select count(*) from Person" ).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    @Test
    public void flushCommitJPQLTest() {
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new Person(null, 25, "Yuli", "Slabko"));
        entityManager.createQuery( "select d from Department d" )
                .setFlushMode(FlushModeType.COMMIT)
                .getResultList();
        entityManager.createQuery( "select p from Person p" )
                .setFlushMode(FlushModeType.COMMIT)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    @Test
    public void flushManualTest() {
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new Person(null, 25, "Yuli", "Slabko"));
        entityManager.flush();
        Session session = entityManager.unwrap( Session.class);
        session.setHibernateFlushMode( FlushMode.MANUAL );
        assertTrue(((Number) entityManager
                .createQuery("select count(id) from Person")
                .getSingleResult()).intValue() == 0);
        assertTrue(((Number) session
                .createNativeQuery("select count(*) from Person")
                .uniqueResult()).intValue() == 0);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    @Test
    public void flushOperationOrderTest() {
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new Person(null, 25, "Yuli", "Slabko"));
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        Person person = entityManager.find( Person.class, 1);
        entityManager.remove(person);

        entityManager.persist( new Person(null, 22, "Yulij", "Slabko") );
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @AfterClass
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}
