package usrcrud.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import usrcrud.model.Role;

import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Role getRoleById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Role.class, id);
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.save(role);
    }

    @Override
    @Transactional
    public void editRole(Role role) {
        Session session = sessionFactory.getCurrentSession();
        Role result = getRoleById(role.getId());
        result.setRole(role.getAuthority());
        session.update(result);
    }

    @Override
    @Transactional
    public void deleteRole(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getRoleById(id));
    }

    @Override
    @Transactional
    public List<Role> getRoles() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Role").getResultList();
    }
}
