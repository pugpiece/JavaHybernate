package DAO.Implementations;

import DAO.Interfaces.IChefDAO;
import DAO.SessionFactory.SessionFactorySingletone;
import Entities.Chef;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

public class ChefDAO implements IChefDAO {
    private static SessionFactory sessionFactory;

    public ChefDAO(){
        sessionFactory = SessionFactorySingletone.getSessionFactory();
    }

    public void addChef(Chef chef) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(chef);
        transaction.commit();
        session.close();
    }

    public List<Chef> readChefs() throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List <Chef> chefs = new ArrayList<Chef>(session.createQuery("from Chef").list());
        transaction.commit();
        session.close();
        return chefs;
    }

    public List<Chef> findChefsName(String name) throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        Criteria criteria = session.createCriteria(Chef.class).add(Restrictions.like("customerLogin", name));
        session.close();
        return criteria.list();
    }

    public List<Chef> findChefsRating(float rating) throws JsonProcessingException{
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Chef.class).add(Restrictions.like("chefRating",rating));
        session.close();
        return criteria.list();
    }

    public void deleteChef(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Chef chef = session.get(Chef.class, id);
        session.delete(chef);
        transaction.commit();
        session.close();
    }

    public Collection<Chef> loginCheck(String login, String password) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Chef.class).add(Restrictions.eq("customerLogin", login));
        criteria.add(Restrictions.eq("customerPassword", password.hashCode()));
        List<Chef> customerList = criteria.list();
        session.close();
        return customerList;
    }
}
