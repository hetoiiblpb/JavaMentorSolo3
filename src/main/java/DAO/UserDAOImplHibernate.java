package DAO;

import Model.User;
import Util.DBConnection;
import exception.DBException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserDAOImplHibernate {
    private static UserDAOImplHibernate instance;
    private static SessionFactory sessionFactory;

    private UserDAOImplHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserDAOImplHibernate getInstance() {
        if (instance == null) {
            instance = new UserDAOImplHibernate(DBConnection.getSessionFactory());
        }
        return instance;
    }


    public List<User> getAllUsers() throws SQLException, DBException {
        List<User> users;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User");
        users = query.list();
        transaction.commit();
        session.close();
        return users;
    }

    public boolean addUser(User user) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }

    public boolean deleteUser(Long id) throws SQLException, DBException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(getUserById(id));
        transaction.commit();
        session.close();
        return true;
    }

    public User getUserById(Long id) throws SQLException, DBException {
        User user;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where id =:param");
        query.setParameter("param",id);
        user = (User) query.getSingleResult();
        transaction.commit();
        session.close();
        return user;
    }

    public boolean updateUser(User user) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
        return true;
    }

    public boolean checkUserByEmail(String email) throws SQLException {     //Проверка наличия user с таким email
        boolean exist;
        Session session =sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where mail=:param");
        query.setParameter("param",email);
        exist = query.getResultList().isEmpty();
        transaction.commit();
        session.close();
        return exist;
    }


}
