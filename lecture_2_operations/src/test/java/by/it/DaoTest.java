package by.it;

import by.it.entity.Person;
import by.it.util.Dao;
import by.it.util.EMUtil;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

/**
 * Created by yslabko on 10/04/2017.
 */
public class DaoTest {
    @Before
    public void init() {
        EntityManager entityManager = EMUtil.getEntityManager("by.it.test");
    }

    @Test
    public void testSave() {
        Person test = new Person(null, 30, "Test", "");
        Dao dao = new Dao();
        dao.save(test);
    }
}
