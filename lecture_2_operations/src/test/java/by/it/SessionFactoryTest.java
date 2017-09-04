package by.it;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import by.it.entity.Person;
import by.it.util.HibernateUtil;

/**
 * Class SessionFactoryTest
 *
 * Created by yslabko on 08/30/2017.
 */
public class SessionFactoryTest {

    private Person p;

    @Before
    public void init() {
        Session session = HibernateUtil.getSession();
        p = new Person(null, 50, "Test", "Test");
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void saveTest() {
        Session session = HibernateUtil.getSession();
        Assert.assertNotNull(session);
        Person person = new Person(null, 25, "Yuli", "Slabko");
        session.beginTransaction();
        session.save(person);
        session.getTransaction().commit();
        session.clear();

        Person personFromDb = session.get(Person.class, person.getId());
        Assert.assertEquals(person, personFromDb);

        session.close();
    }

    @Test
    public void loadTest() {
        Session session = HibernateUtil.getSession();
        Person loadedPerson = session.load(Person.class, 2);
        loadedPerson.getAge();
        session.close();
    }

    @Test
    public void lazyLoadTest() {
        Session session = HibernateUtil.getSession();
        Person loadedPerson = session.load(Person.class, 2);
        session.clear();
        loadedPerson.getAge();
        session.close();
    }

    @Test
    public void getTest() {
        Session session = HibernateUtil.getSession();
        Person loadedPerson = session.get(Person.class, 2);
        System.out.println(loadedPerson);
        session.close();
    }

    @Test
    public void isDirtyTest() {
        Session session = HibernateUtil.getSession();
        Person loadedPerson = session.get(Person.class, p.getId());
        loadedPerson.setAge(555);
        System.out.println(session.isDirty());
        session.close();
    }

    @Test
    public void deleteTest() {
        Session session = HibernateUtil.getSession();
        Person personForDelete = new Person(null, 100, "Deleted", "Man");
        System.out.println("Count before save " + session.createQuery("Select count(*) from Person").getSingleResult());
        session.beginTransaction();
        session.save(personForDelete);
        System.out.println("Count after save " + session.createQuery("Select count(*) from Person").getSingleResult());
        session.delete(personForDelete);
        session.getTransaction().commit();
        System.out.println("Count after delete " + session.createQuery("Select count(*) from Person").getSingleResult());

        session.close();
    }

    @Test
    public void deleteExistedTest() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Person personForDelete = session.get(Person.class, 1L);
        session.delete(personForDelete);
        session.getTransaction().commit();

        session.close();
    }

    @After
    public void closeSessionFactory() {
        HibernateUtil.getSession().delete(p);
        HibernateUtil.closeSessionFactory();
    }
}
