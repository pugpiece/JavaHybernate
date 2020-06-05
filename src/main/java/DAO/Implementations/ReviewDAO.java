package DAO.Implementations;

import DAO.Interfaces.IReviewDAO;
import DAO.SessionFactory.SessionFactorySingletone;
import Entities.Chef;
import Entities.Review;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;


public class ReviewDAO implements IReviewDAO {
    private static SessionFactory sessionFactory;

    public ReviewDAO(){
        sessionFactory = SessionFactorySingletone.getSessionFactory();
    }
    public void addReview(Review review) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(review);
        transaction.commit();
        session.close();
    }

    public List<Review> readReviews() throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List <Review> reviews = new ArrayList<Review>(session.createQuery("from Review").list());
        transaction.commit();
        session.close();
        return reviews;
    }

    public List<Review> findReviewsId(int id) throws JsonProcessingException{
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Review.class).add(Restrictions.idEq(id));
        session.close();
        return  criteria.list();
    }

    public List<Review> findReviewsRating(float rating) throws JsonProcessingException{
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Review.class).add(Restrictions.like("reviewRating",rating));
        session.close();
        return criteria.list();
    }

    public void deleteReview(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Review review = session.get(Review.class, id);
        session.delete(review);
        transaction.commit();
        session.close();
    }
}
