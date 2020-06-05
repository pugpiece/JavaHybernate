package DAO.SessionFactory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class SessionFactorySingletone {
    private static SessionFactory sessionFactory;

    private SessionFactorySingletone() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
