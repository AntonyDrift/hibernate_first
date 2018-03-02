import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import by.it.entity.Cat;
import by.it.entity.VetVisit;
import by.it.util.EMUtil;

/**
 * Class CatTest
 *
 * Created by yslabko on 10/20/2017.
 */
public class CatTest {

    @Test
    public void saveTest() {
        Cat cat = new Cat(null, 2, "Cat", new VetVisit(null, null));
        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(cat);
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
//        Assert.assertEquals(person, personFromDb);
        em.getTransaction().commit();
    }

    @AfterClass
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}
