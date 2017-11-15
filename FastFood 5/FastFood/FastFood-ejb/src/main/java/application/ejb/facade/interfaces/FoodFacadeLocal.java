/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.ejb.facade.interfaces;

import application.ejb.entity.Food;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Łukasz
 */
@Local
public interface FoodFacadeLocal {

    void create(Food food);

    void edit(Food food);

    void remove(Food food);

    Food find(Object id);

    List<Food> findAll();

}
