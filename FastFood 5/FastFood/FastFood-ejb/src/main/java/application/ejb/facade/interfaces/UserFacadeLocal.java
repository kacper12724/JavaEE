/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.ejb.facade.interfaces;

import application.ejb.entity.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Łukasz
 */
@Local
public interface UserFacadeLocal {

    void create(User user);

    void edit(User user);

    void remove(User user);

    User find(Object id);

    List<User> findAll();    
    
    User login(User user);
    
}
