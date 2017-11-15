/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.ejb.facade;

import application.ejb.facade.interfaces.UserFacadeLocal;
import application.ejb.entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Łukasz
 */
@Stateless
public class UserFacade implements UserFacadeLocal {

    @PersistenceContext(unitName = "fastfoodpersistence")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(User user) {
        getEntityManager().persist(user);
    }

    @Override
    public void edit(User user) {
        getEntityManager().merge(user);
    }

    @Override
    public void remove(User user) {
        getEntityManager().remove(getEntityManager().merge(user));
    }

    @Override
    public User find(Object id) {
        return getEntityManager().find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(User.class));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public User login(User user) {
        try {
            Query namedQuery = em.createNamedQuery("User.validate").setParameter("username", user.getUsername()).setParameter("password", user.getPassword());
            return (User) namedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
