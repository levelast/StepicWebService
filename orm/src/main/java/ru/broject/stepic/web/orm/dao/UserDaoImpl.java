package ru.broject.stepic.web.orm.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.broject.stepic.web.orm.model.UserProfile;
import ru.broject.stepic.web.orm.support.HibernateHelper;

/**
 * Created by vyacheslav.svininyh on 15.01.2016.
 */
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;
    private static UserDao userDao;
    private static final Object lock = new Object();

    private UserDaoImpl() {
        this.sessionFactory = HibernateHelper.getSessionFactory();
    }

    public static UserDao getInstance() {
        if (userDao == null) {
            synchronized (lock) {
                if (userDao == null) {
                    userDao = new UserDaoImpl();
                }
            }
        }
        return userDao;
    }

    @Override
    public UserProfile getUser(String login) {
        Session session = null;
        UserProfile userProfile = null;
        try {
            session = sessionFactory.openSession();
            userProfile = (UserProfile) session
                    .createQuery("select u from UserProfile u where u.login = :login")
                    .setParameter("login", login)
                    .uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return userProfile;
    }

    @Override
    public void insertUser(UserProfile userProfile) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(userProfile);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (session != null && session.isOpen()) {
                if (tx != null) {
                    tx.rollback();
                }
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
