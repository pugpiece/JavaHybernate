package DAO.Implementations;

import DAO.Interfaces.ICustomerDAO;
import DAO.SessionFactory.SessionFactorySingletone;
import Entities.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CustomerDAO implements ICustomerDAO {
    private static SessionFactory sessionFactory;

    public CustomerDAO(){
        sessionFactory = SessionFactorySingletone.getSessionFactory();
    }

    public void addCustomer(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }

    public List<Customer> readCustomers() throws JsonProcessingException{
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List <Customer> customers = new ArrayList<Customer>(session.createQuery("from Customer").list());
        transaction.commit();
        session.close();
        return customers;
    }

    public List<Customer> findCustomersName(String name) throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Customer.class).add(Restrictions.like("customerLogin", name));
        session.close();
        return criteria.list();
    }

    public void deleteCustomer(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        session.delete(customer);
        transaction.commit();
        session.close();
    }

    public Collection<Customer> loginCheck(String login, String password) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Customer.class).add(Restrictions.eq("customerLogin", login));
        criteria.add(Restrictions.eq("customerPassword", password.hashCode()));
        List<Customer> customerList = criteria.list();
        session.close();
        return customerList;
    }

}
