import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import by.it.entity.Department;
import by.it.entity.Employee;
import by.it.entity.EmployeeDetail;
import by.it.entity.User;
import by.it.util.EMUtil;

/**
 * Class ElementCollectionTest
 *
 * Created by yslabko on 09/29/2017.
 */
public class ElementCollectionTest {
    @Before
    public void init() {
    }

    @Test
    public void saveTest() {
        EntityManager em = EMUtil.getEntityManager("by.it");
        em.getTransaction().begin();
        User user = new User(null, "Tim", Stream.of("Cat", "Dog").collect(Collectors.toList()));
        em.persist(user);
        em.getTransaction().commit();
        em.clear();
        user = em.find(User.class, 1l);
//        ArrayList<String> pets = (ArrayList<String>) user.getPets();
        System.out.println(user);
        em.close();
    }

    @AfterClass
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}
