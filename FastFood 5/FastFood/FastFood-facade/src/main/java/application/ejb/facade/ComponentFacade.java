package application.ejb.facade;

import application.ejb.entity.Component;
import application.ejb.facade.interfaces.ComponentFacadeLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Łukasz
 */
@Stateless
public class ComponentFacade implements ComponentFacadeLocal {

    @PersistenceContext(unitName = "fastfoodpersistence")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(Component component) {
        getEntityManager().persist(component);
    }

    @Override
    public void edit(Component component) {
        getEntityManager().merge(component);
    }

    @Override
    public void remove(Component component) {
        getEntityManager().remove(getEntityManager().merge(component));
    }

    @Override
    public Component find(Object id) {
        return getEntityManager().find(Component.class, id);
    }

    @Override
    public List<Component> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Component.class));
        return getEntityManager().createQuery(cq).getResultList();
    }

}
