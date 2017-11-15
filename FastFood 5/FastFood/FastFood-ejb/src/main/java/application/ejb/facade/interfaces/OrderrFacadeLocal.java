/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.ejb.facade.interfaces;

import application.ejb.entity.Food;
import application.ejb.entity.Orderr;
import application.ejb.entity.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Łukasz
 */
@Local
public interface OrderrFacadeLocal {

    void create(Orderr orderr);

    void edit(Orderr orderr);

    void remove(Orderr orderr);

    Orderr find(Object id);

    List<Orderr> findAll();

    public void makeOrder(List<Orderr> cart);

    public Orderr createOrder(Food food, User user);

}
