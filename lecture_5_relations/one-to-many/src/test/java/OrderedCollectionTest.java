import by.it.entity.Author;
import by.it.entity.Book;
import by.it.entity.User;
import by.it.util.EMUtil;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yslabko on 09/30/2017.
 */
public class OrderedCollectionTest {
    @Before
    public void init() {
        EntityManager em = EMUtil.getEntityManager("by.it");
        em.getTransaction().begin();
        Author author = new Author(1L, "Tolstoy", new ArrayList<>());
        Book alice = new Book(null, "Alice", author);
        author.getBooks().add(alice);
        author.getBooks().add(new Book(null, "War&Piece", author));
        author.getBooks().add(new Book(null, "Philipok", author));
        em.persist(author);
        em.getTransaction().commit();
        em.clear();
    }

    @Test
    public void saveTest() {
        EntityManager em = EMUtil.getEntityManager("by.it");
        Author author = em.find(Author.class, 1l);
        System.out.println(author);
//        System.out.println(author.getBooks().get(0).getTitle());
        em.close();
    }

    @AfterClass
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}
