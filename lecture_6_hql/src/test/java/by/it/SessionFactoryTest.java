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

//    private Person p;
//
//    @Before
//    public void init() {
//        Session session = HibernateUtil.getSession();
//        p = new Person(null, 50, "Test", "Test");
//        session.beginTransaction();
//        session.delete(p);
//        session.getTransaction().commit();
//        session.close();
//    }
//
//
//
//    @After
//    public void closeSessionFactory() {
//        HibernateUtil.getSession().delete(p);
//        HibernateUtil.closeSessionFactory();
//    }
}
