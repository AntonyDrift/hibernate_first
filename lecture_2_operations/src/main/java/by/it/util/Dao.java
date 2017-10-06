package by.it.util;

import by.it.entity.Person;

import javax.persistence.EntityManager;

/**
 * Created by yslabko on 10/04/2017.
 */
public class Dao {

    public void save(Person person) {
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(person);
        entityManager.getTransaction().commit();
    }
}
