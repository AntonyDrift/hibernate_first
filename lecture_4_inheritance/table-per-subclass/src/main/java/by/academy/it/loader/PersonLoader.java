package by.academy.it.loader;

import by.academy.it.pojos.Employee;
import by.academy.it.pojos.Student;
import by.academy.it.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.Locale;

import static by.academy.it.loader.MenuLoader.menu;

public class PersonLoader {
    private static Logger log = Logger.getLogger(PersonLoader.class);
    public static HibernateUtil util = null;

    public static void main(String[] args) throws Exception {

        Locale.setDefault(Locale.US);
        util = HibernateUtil.getHibernateUtil();
        Session s = util.getSession();
        Employee e = new Employee();
        Student student = new Student();
        e.setName("Tim");
        e.setCompany("Exadel");
        s.beginTransaction();
        s.saveOrUpdate(e);
        s.getTransaction().commit();
        System.out.println("Start Menu");
        menu();
    }

}


