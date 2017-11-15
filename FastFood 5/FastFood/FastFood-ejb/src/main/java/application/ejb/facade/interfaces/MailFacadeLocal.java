/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.ejb.facade.interfaces;

import application.ejb.smtp.MailEvent;

/**
 *
 * @author Łukasz
 */
public interface MailFacadeLocal {

    void sendMail(MailEvent event);

}
