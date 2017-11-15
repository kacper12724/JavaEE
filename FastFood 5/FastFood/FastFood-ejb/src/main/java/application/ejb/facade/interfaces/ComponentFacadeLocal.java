/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.ejb.facade.interfaces;

import application.ejb.entity.Component;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Łukasz
 */
@Local
public interface ComponentFacadeLocal {

    void create(Component component);

    void edit(Component component);

    void remove(Component component);

    Component find(Object id);

    List<Component> findAll();

}
