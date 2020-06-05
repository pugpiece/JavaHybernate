package DAO.Implementations;

import DAO.Interfaces.IShopDAO;
import DAO.SessionFactory.SessionFactorySingletone;
import Entities.Shop;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;


public class ShopDAO implements IShopDAO {
    private static SessionFactory sessionFactory;

    public ShopDAO(){
        sessionFactory = SessionFactorySingletone.getSessionFactory();
    }

    public void addShop(Shop shop) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(shop);
        transaction.commit();
        session.close();
    }

    public List<Shop> readShops() throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List <Shop> shops = new ArrayList<Shop>(session.createQuery("from Shop").list());
        transaction.commit();
        session.close();
        return shops;
    }

    public List<Shop> findShopsName(String name) throws JsonProcessingException{
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Shop.class).add(Restrictions.like("shopName", name));
        session.close();
        return criteria.list();
    }

    public List<Shop> findShopsRating(float rating) throws JsonProcessingException{
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Shop.class).add(Restrictions.like("shopRating", rating));
        session.close();
        return criteria.list();
    }

    public void deleteShop(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Shop shop = session.get(Shop.class, id);
        session.delete(shop);
        transaction.commit();
        session.close();
    }
}
