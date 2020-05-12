package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao{
    private SessionFactory sessionFactory;

    @Autowired
    @Transactional
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role getRoleById(long id) {
        Role role = sessionFactory.getCurrentSession().get(Role.class, id);
        return role;
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        sessionFactory.getCurrentSession().save(role);
    }

    @Override
    @Transactional
    public void editRole(Role role) {
        sessionFactory.getCurrentSession().update(role);
    }

    @Override
    @Transactional
    public void deleteRole(long id) {
        sessionFactory.getCurrentSession().delete(getRoleById(id));
    }

    @Override
    public List<Role> getRoles() {
        TypedQuery<Role> query = sessionFactory.getCurrentSession().createQuery("from Role");
        System.out.println("From Roles");
        return query.getResultList();
    }
}
