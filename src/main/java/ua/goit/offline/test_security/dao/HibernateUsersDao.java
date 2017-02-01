package ua.goit.offline.test_security.dao;

import org.hibernate.SessionFactory;

/**
 * Created by andreymi on 1/31/2017.
 */
public class HibernateUsersDao implements UsersDao {
    private SessionFactory sessionFactory;

    public HibernateUsersDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User find(String username) {
        return sessionFactory.getCurrentSession().get(User.class, username);
    }
}
