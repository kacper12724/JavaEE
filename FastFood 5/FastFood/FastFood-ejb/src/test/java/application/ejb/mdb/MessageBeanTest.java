/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.ejb.mdb;

import application.ejb.facade.interfaces.MailFacadeLocal;
import application.ejb.smtp.MailEvent;
import java.io.IOException;
import java.lang.reflect.Field;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.mail.MessagingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
/**
 *
 * @author Jakub
 */
public class MessageBeanTest {

    
    public MessageBeanTest() {
    }
    private MessageBean messageDriven;
        @Mock
    private MailFacadeLocal mailService;

    @Mock
    private ObjectMessage objectMessage;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        messageDriven = new MessageBean();
        Field field = MessageBean.class.getDeclaredField("mailService");
        field.setAccessible(true);
        field.set(messageDriven, mailService); }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of onMessage method, of class MessageBean.
     * @throws java.io.IOException
     * @throws javax.mail.MessagingException
     * @throws javax.jms.JMSException
     */
    @Test
    public void testOnMessage() throws IOException, MessagingException, JMSException  {
        MailEvent event = new MailEvent();
        doReturn(event).when(objectMessage).getObject();
        messageDriven.onMessage(objectMessage);
        Mockito.verify(mailService).sendMail((MailEvent) event);
    } 
    
}
