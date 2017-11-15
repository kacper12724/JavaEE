/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.ejb.facade;

import application.ejb.facade.interfaces.FoodFacadeLocal;
import application.ejb.entity.Food;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Łukasz
 */
@Stateless
public class FoodFacade implements FoodFacadeLocal {

    @PersistenceContext(unitName = "fastfoodpersistence")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(Food food) {
        getEntityManager().persist(food);
    }

    @Override
    public void edit(Food food) {
        getEntityManager().merge(food);
    }

    @Override
    public void remove(Food food) {
        getEntityManager().remove(getEntityManager().merge(food));
    }

    @Override
    public Food find(Object id) {
        return getEntityManager().find(Food.class, id);
    }

    @Override
    public List<Food> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Food.class));
        return getEntityManager().createQuery(cq).getResultList();
    }

}
