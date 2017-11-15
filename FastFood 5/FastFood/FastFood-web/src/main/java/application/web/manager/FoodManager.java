package application.web.manager;

import application.ejb.entity.Food;
import application.ejb.facade.interfaces.FoodFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

@Named(value = "foodManager")
@Stateless
public class FoodManager implements Serializable {

    private Food food;

    @EJB
    private FoodFacadeLocal foodFacade;

    @PostConstruct
    public void init() {
        food = new Food();
    }

    public List<Food> selectAllFood() {
        return foodFacade.findAll();
    }

    //getters/setters
    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public FoodFacadeLocal getFoodFacadeLocal() {
        return foodFacade;
    }

    public void setFoodFacadeLocal(FoodFacadeLocal foodFacade) {
        this.foodFacade = foodFacade;
    }
}
