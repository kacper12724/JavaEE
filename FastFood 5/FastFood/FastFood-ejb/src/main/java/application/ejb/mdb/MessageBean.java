package application.ejb.mdb;

import application.ejb.facade.interfaces.MailFacadeLocal;
import application.ejb.smtp.MailEvent;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author Łukasz
 */
@MessageDriven(mappedName = "jms/queueDest", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
    , @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MessageBean implements MessageListener {

    @EJB
    private MailFacadeLocal mailService;

    public MessageBean() {
    }

    @Override
    public void onMessage(Message message) {
        ObjectMessage tmp = (ObjectMessage) message;
        Serializable mail;
        try {
            mail = tmp.getObject();
            mailService.sendMail((MailEvent) mail);
        } catch (JMSException ex) {
            Logger.getLogger(MessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
