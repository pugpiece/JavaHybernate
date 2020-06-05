package DAO.Implementations;

import DAO.Interfaces.IInstrumentDAO;
import DAO.SessionFactory.SessionFactorySingletone;
import Entities.Instrument;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;


public class InstrumentDAO implements IInstrumentDAO {

    private static SessionFactory sessionFactory;

    public InstrumentDAO(){
        sessionFactory = SessionFactorySingletone.getSessionFactory();
    }
    public void addInstrument(Instrument instrument) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(instrument);
        transaction.commit();
        session.close();
    }

    public List<Instrument> readInstruments() throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List <Instrument> instruments = new ArrayList<Instrument>(session.createQuery("from Instrument").list());
        transaction.commit();
        session.close();
        return instruments;
    }

    public List<Instrument> findInstrumentsName(String name) throws JsonProcessingException{
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Instrument.class).add(Restrictions.like("instrumentName", name));
        session.close();
        return criteria.list();
    }

    public List<Instrument> findInstrumentsCost(float cost) throws JsonProcessingException{
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Instrument.class).add(Restrictions.like("instrumentCost", cost));
        session.close();
        return criteria.list();
    }

    public void deleteInstrument(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Instrument instrument = session.get(Instrument.class, id);
        session.delete(instrument);
        transaction.commit();
        session.close();
    }
}
