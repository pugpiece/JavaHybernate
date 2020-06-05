package DAO.Implementations;

import DAO.Interfaces.IProductDAO;
import DAO.SessionFactory.SessionFactorySingletone;
import Entities.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;


public class ProductDAO implements IProductDAO {
    private static SessionFactory sessionFactory;

    public ProductDAO(){
        sessionFactory = SessionFactorySingletone.getSessionFactory();
    }

    public void addProduct(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        session.close();
    }

    public List<Product> readProducts() throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List <Product> products = new ArrayList<Product>(session.createQuery("from Product").list());
        transaction.commit();
        session.close();
        return products;
    }

    public List<Product> findProductsName(String name) throws JsonProcessingException{
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Product.class).add(Restrictions.like("productName", name));
        session.close();
        return criteria.list();
    }

    public List<Product> findProductsCalories(float calories) throws JsonProcessingException{
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Product.class).add(Restrictions.like("productCalories", calories));
        session.close();
        return criteria.list();
    }

    public void deleteProduct(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.delete(product);
        transaction.commit();
        session.close();
    }
}
