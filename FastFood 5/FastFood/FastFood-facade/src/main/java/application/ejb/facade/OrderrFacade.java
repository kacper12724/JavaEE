/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.ejb.facade;

import application.ejb.entity.Food;
import application.ejb.facade.interfaces.OrderrFacadeLocal;
import application.ejb.entity.Orderr;
import application.ejb.entity.User;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Łukasz
 */
@Stateless
public class OrderrFacade implements OrderrFacadeLocal {

    @PersistenceContext(unitName = "fastfoodpersistence")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(Orderr orderr) {
        getEntityManager().persist(orderr);
    }

    @Override
    public void edit(Orderr orderr) {
        getEntityManager().merge(orderr);
    }

    @Override
    public void remove(Orderr orderr) {
        getEntityManager().remove(getEntityManager().merge(orderr));
    }

    @Override
    public Orderr find(Object id) {
        return getEntityManager().find(Orderr.class, id);
    }

    @Override
    public List<Orderr> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Orderr.class));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public void makeOrder(List<Orderr> cart) {
        for (Orderr orderFromCart : cart) {
            em.persist(orderFromCart);
        }
    }

    @Override
    public Orderr createOrder(Food food, User user) {
        Orderr order = new Orderr();
        order.setIdFood(food);
        order.setIdUser(user);
        Date date = new Date();
        order.setDate(date);
        return order;
    }

}
