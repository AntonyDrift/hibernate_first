/*
 * Copyright (c) 2012 by VeriFone, Inc.
 * All Rights Reserved.
 *
 * THIS FILE CONTAINS PROPRIETARY AND CONFIDENTIAL INFORMATION
 * AND REMAINS THE UNPUBLISHED PROPERTY OF VERIFONE, INC.
 *
 * Use, disclosure, or reproduction is prohibited
 * without prior written approval from VeriFone, Inc.
 */
package by.academy.it.db;

import by.academy.it.db.exceptions.DaoException;
import by.academy.it.pojos.Employee;
import by.academy.it.pojos.Person;
import by.academy.it.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import java.util.List;


/**
 * User: yslabko
 * Date: 14.04.14
 * Time: 13:05
 */
public class PersonDao extends BaseDao<Person> {

    private static Logger log = Logger.getLogger(PersonDao.class);

    public void flush(Integer id, String newName) throws DaoException {
        try {
            Session session = HibernateUtil.getHibernateUtil().getSession();
            Person p = (Person)session.get(Person.class, id);
            System.out.println(p);
            p.setName(newName);
            System.out.println(p);
            session.flush();
            System.out.println(p);


            List<EmployeeDTO> list =  session.createSQLQuery("Select e.ID as id, e.FIRST_NAME as name from EMPLOYEE e" +
                " LEFT JOIN PHONE p ON e.ID = p.ID WHERE p.STATUS = 'CONFIRMED'")
                .addScalar("id", StandardBasicTypes.INTEGER)
                .addScalar("firstName", StandardBasicTypes.STRING)
                .addScalar("password", StandardBasicTypes.STRING)
                .setResultTransformer(new ResultTransformer() {
                    private static final long serialVersionUID = -5815434828170704822L;
                    public Object transformTuple(Object[] arg0, String[] arg1) {
                        EmployeeDTO e = new EmployeeDTO();
                        e.setId((Long) arg0[0]);
                        e.setFirstName((String) arg0[1]);
                        e.setPhone((String) arg0[2]);
                        return e;
                    }

                    @SuppressWarnings("unchecked")
                    public List transformList(List arg0) {
                        return arg0;
                    }
                })
                .list();


            System.out.println(list);
            session
                .createSQLQuery("select e.id as id, e.first_name as firstName,e.password as password from Employee_History")
                .addScalar("id", StandardBasicTypes.INTEGER )
                .addScalar("firstName",StandardBasicTypes.STRING )
                .addScalar("password",StandardBasicTypes.STRING )
                .setResultTransformer(Transformers.aliasToBean(Employee.class))
                .list();




        } catch (HibernateException e) {
            log.error("Error Flush person" + e);
            throw new DaoException(e);
        }

    }

    class EmployeeDTO {
        private long id;
        private String firstName;
        private String phone;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

}
