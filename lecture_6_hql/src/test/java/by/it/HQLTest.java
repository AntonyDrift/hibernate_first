package by.it;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import by.it.entity.Author;
import by.it.entity.Book;
import by.it.entity.Employee;
import by.it.util.EMUtil;

/**
 * Created by yslabko on 09/30/2017.
 */
public class HQLTest {
    @Before
    public void init() {
        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        Author author = new Author(null, "Tolstoy", new ArrayList<>());
        author.getBooks().add(new Book(null, "Alice", 1872, author));
        author.getBooks().add(new Book(null, "War & Piece", 1869, author));
        author.getBooks().add(new Book(null, "Philipok", 1865, author));
        Author pikul = new Author(null, "Pikul", new ArrayList<>());
        pikul.getBooks().add(new Book(null, "Barbarossa", 2012, pikul));
        pikul.getBooks().add(new Book(null, "Favorit", 1978, pikul));
        pikul.getBooks().add(new Book(null, "By pen & sword", 1992, pikul));
        em.persist(author);
        em.persist(pikul);

        em.persist(new Employee(null, "Yulij", 30, 8500));
        em.persist(new Employee(null, "Alex", 28, 5500));
        em.persist(new Employee(null, "Sergey", 40, 7500));
        em.persist(new Employee(null, "Yulij", 40, 9500));
        em.persist(new Employee(null, "Maria", 28, 3500));
        em.getTransaction().commit();
        em.clear();
    }

    @Test
    public void hql() {
        EntityManager em = EMUtil.getEntityManager();
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("from Employee");
        // timeout - в milliseconds
        query.setTimeout(1000)
                // включить в кеш запросов
                .setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);

        System.out.println(query.list());
    }

    @Test
    public void selectTest() {
        EntityManager em = EMUtil.getEntityManager();
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("from Employee");
        query.list().forEach(System.out::println);
    }

    @Test
    public void orderByTest() {
        EntityManager em = EMUtil.getEntityManager();
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("from Employee order by salary desc");
        query.list().forEach(System.out::println);
    }

    @Test
    public void parameterTest() {
        EntityManager em = EMUtil.getEntityManager();
        String name = "Yulij";
        javax.persistence.Query badQuery = em.createQuery("from Employee e where e.name=" + name);
        javax.persistence.Query query = em.createQuery("from Employee e where e.name= :name");
        query.setParameter("name", name)
                .getResultList().forEach(System.out::println);
    }

    @Test
    public void parameterOrderTest() {
        EntityManager em = EMUtil.getEntityManager();
        javax.persistence.Query query = em.createQuery(
                "from Employee e where e.name=? and e.salary > :salary");
        query.setParameter(0, "Yulij")
                .setParameter("salary", 5000)
                .getResultList().forEach(System.out::println);
    }

    @Test
    public void parameterListTest() {
        EntityManager em = EMUtil.getEntityManager();
        javax.persistence.Query query = em.createQuery("from Employee e where e.id in(:ids)");
        query.setParameter("ids", Stream.of(1L, 4L).collect(Collectors.toList()))
                .getResultList().forEach(System.out::println);
    }

    @Test
    public void groupByTest() {
        EntityManager em = EMUtil.getEntityManager();
        javax.persistence.Query query = em.createQuery("select count(e.name), e.name from Employee e group by e.name");
        query.getResultList().forEach(employees -> {
            Object[] employee = (Object[]) employees;
            System.out.println("Имя: " + employee[1] + " количество:" + employee[0]);
        });
    }

    @Test
    public void groupByWithoutLambdaTest() {
        EntityManager em = EMUtil.getEntityManager();
        javax.persistence.Query query = em.createQuery("select count(e.name), e.name from Employee e group by e.name");
        List<Object[]> list = query.getResultList();
        for (Object[] res : list) {
            System.out.println("Имя: " + res[1] + " количество:" + res[0]);
        }
    }

    @Test
    public void countDistinctTest() {
        EntityManager em = EMUtil.getEntityManager();
        javax.persistence.Query query = em.createQuery("select count(distinct e.name), e.name from Employee e group by e.name");
        query.getResultList().forEach(employees -> {
            Object[] emp = (Object[]) employees;
            System.out.println("Имя: " + emp[1] + " количество:" + emp[0]);
        });
    }

    @Test
    public void hqlEmployee() {
        EntityManager em = EMUtil.getEntityManager();
        Session session = em.unwrap(Session.class);
        String order = "DESC";
        Query query = session
                .createQuery("from Employee e where e.name like 'Yuli' order by e.name");
        System.out.println(query.list());
    }

    @Test
    public void deleteTest() {
        EntityManager em = EMUtil.getEntityManager();
        Employee employee = new Employee(null, "Tuk", 100, 99);
        em.getTransaction().begin();
        em.persist(employee);
        javax.persistence.Query query = em.createQuery("delete from Employee e where e.id=:id");
        System.out.println(
                query.setParameter("id", employee.getId())
                        .executeUpdate());
        em.getTransaction().commit();
    }

    @Test
    public void leftJoinTest() {
        EntityManager em = EMUtil.getEntityManager();
        List<Author> authors = em.createQuery(
                "select distinct a " +
                        "from Author a " +
                        "left join a.books b " +
                        "where b.title = 'War & Piece'", Author.class)
                .getResultList();
        authors.forEach(System.out::println);
    }

    @Test
    public void withJoinTest() {
        EntityManager em = EMUtil.getEntityManager();
        List<Author> authors = em.createQuery(
                "select distinct a " +
                        "from Author a " +
                        "inner join a.books b on b.title = 'War & Piece'")
                .getResultList();
        authors.forEach(System.out::println);
    }

    @AfterClass
    public static void cleanUp() {
        EMUtil.closeEMFactory();
    }
}
