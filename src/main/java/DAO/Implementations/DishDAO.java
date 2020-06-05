package DAO.Implementations;

import DAO.Interfaces.IDishDAO;
import DAO.SessionFactory.SessionFactorySingletone;
import Entities.Dish;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;


public class DishDAO implements IDishDAO {
    private static SessionFactory sessionFactory;

    public DishDAO(){
        sessionFactory = SessionFactorySingletone.getSessionFactory();
    }

    public void addDish(Dish dish) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(dish);
        transaction.commit();
        session.close();
    }

    public List<Dish> readDishes() throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List <Dish> dishes = new ArrayList<Dish>(session.createQuery("from Dish").list());
        transaction.commit();
        session.close();
        return dishes;
    }

    public List<Dish> findDishesName(String name) throws JsonProcessingException{
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Dish.class).add(Restrictions.like("dishName", name));
        session.close();
        return criteria.list();
    }

    public void deleteDish(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Dish dish = session.get(Dish.class, id);
        session.delete(dish);
        transaction.commit();
        session.close();
    }
}
