package usrcrud.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import usrcrud.model.User;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<User> allUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User").list();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getUserById(id));
    }

    @Override
    @Transactional
    public void editUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(user);
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    @Transactional
    public User getUserByUserName(String userName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User where login = :paramName");
        query.setParameter("paramName", userName);
        return (User) query.getSingleResult();
    }

}
