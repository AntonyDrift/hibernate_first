package by.it.loader;
import by.it.pojos.Person;
import by.it.util.HibernateUtil;

import javax.persistence.EntityManager;

public class PersonLoader {
    public static void main(String[] args) throws Exception {
        Person person = new Person(null, 35, "Yuli", "Slabko");
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        HibernateUtil.close();
    }
}


