package DAO.Implementations;

import DAO.Interfaces.ICountryDAO;
import DAO.SessionFactory.SessionFactorySingletone;
import Entities.Country;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;


public class CountryDAO implements ICountryDAO  {
    private static SessionFactory sessionFactory;

    public CountryDAO(){
        sessionFactory = SessionFactorySingletone.getSessionFactory();
    }

    public void addCountry(Country country) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(country);
        transaction.commit();
        session.close();
    }

    public List<Country> readCountries() throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List <Country> countries = new ArrayList<Country>(session.createQuery("from Country").list());
        transaction.commit();
        session.close();
        return countries;
    }

    public List<Country> findCountriesName(String name) throws JsonProcessingException{
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Country.class).add(Restrictions.like("countryName", name));
        session.close();
        return criteria.list();
    }

    public void deleteCountry(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Country country = session.get(Country.class, id);
        session.delete(country);
        transaction.commit();
        session.close();
    }
}
