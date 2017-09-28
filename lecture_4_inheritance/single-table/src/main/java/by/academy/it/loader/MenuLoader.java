package by.academy.it.loader;
import by.academy.it.db.PersonDao;
import by.academy.it.db.exceptions.DaoException;
import by.academy.it.pojos.Person;
import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * User: yslabko
 * Date: 14.04.14
 * Time: 12:28
 */
public class MenuLoader {
    private static Logger log = Logger.getLogger(MenuLoader.class);
    public static Boolean needMenu = true;
    private static PersonDao personDao = null;

    public static void menu() throws DaoException {
        Person person = null;
        while (needMenu) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Perform "original number" case.
                    break;
                case 2:
                    person = findPerson();
                    break;
                case 3:
                    person = createPerson(person);
                    getPersonDao().saveOrUpdate(person);
                    break;
                case 4:
                    person = loadPerson();
                    break;
                case 5:
                    flushSession();
                    break;
                case 6:
                    System.exit(0); break;
                default:
                    needMenu = true;
                    break;
            }
            needMenu = true;
        }

    }

    private static void printMenu() {
        System.out.println(" Options:");
        System.out.println("        1. Delete Person");
        System.out.println("        2. Get Person");
        System.out.println("        3. Save or Update Person");
        System.out.println("        4. Load Person");
        System.out.println("        5. Flush example");
        System.out.println("        6. Exit");
    }

    public static Person createPerson(Person person) {
        System.out.println("Please enter person description:");
        System.out.print("Name - ");

        if(person == null) {person = new Person();}
        Scanner scanner = new Scanner(System.in);
        String parameter = scanner.nextLine();
        person.setName(parameter);
        System.out.print("Surname - ");
        parameter = scanner.nextLine();
        person.setSurname(parameter);
        System.out.print("Age - ");
        return person;
    }

    public static Person findPerson() {
        System.out.println("Get by Id. Please enter person id:");
        System.out.print("Id - ");

        Scanner scanner = new Scanner(System.in);
        Person person = null;
        Integer id = scanner.nextInt();
        try {
            person = getPersonDao().get(id);
        } catch (DaoException e) {
            log.error(e, e);
        } catch (NullPointerException e) {
            log.error("Unable find person:", e);
        }
        System.out.print(person);
        return person;
    }

    public static Person loadPerson() {
        System.out.println("Loag by Id. Please enter person id:");
        System.out.print("Id - ");

        Scanner scanner = new Scanner(System.in);
        Person person = null;
        Integer id = scanner.nextInt();
        try {
            person = getPersonDao().get(id);
        } catch (DaoException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NullPointerException e) {
            log.error("Unable find person:", e);
        }
        System.out.print(person);
        return person;
    }

    public static void flushSession() {
        System.out.println("Please enter person id:");
        System.out.print("Id - ");
        Scanner scanner = new Scanner(System.in);
        Person person = null;
        Integer id = scanner.nextInt();
        System.out.println("Please enter new Name:");
        System.out.print("New Name - ");
        scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        try {
            getPersonDao().flush(id, name);
        } catch (DaoException e) {
            log.error("Get person" + e.getMessage(), e);
        }
    }

    public static PersonDao getPersonDao() {
        if (personDao == null) {
            personDao = new PersonDao();
        }

        return personDao;
    }

}
