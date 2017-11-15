package application.ejb.facade;

import application.ejb.facade.interfaces.MailFacadeLocal;
import application.ejb.smtp.MailEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

/**
 *
 * @author Łukasz
 */
@Singleton
public class MailFacade implements MailFacadeLocal {

    @Resource(name = "mail/gmail")
    private Session mailSession;

    private static final String FORMAT_PDF = "application/pdf";
    private static final String CODE = "utf-8";
    private static final String FILE_NAME = "Faktura.pdf";

    @Override
    public void sendMail(MailEvent event) {
        try {
            Message m = new MimeMessage(mailSession);
            Address[] to = new InternetAddress[]{new InternetAddress(event.getMailTo())};

            m.setRecipients(Message.RecipientType.TO, to);
            m.setSubject(event.getSubject());
            m.setSentDate(new java.util.Date());
            m.setContent(createMail(event));
            m.saveChanges();

            Transport.send(m);
        } catch (MessagingException e) {
            Logger.getLogger(MailFacade.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private Multipart createMail(MailEvent mailEvent) {
        Multipart multipart = new MimeMultipart();
        try {
            MimeBodyPart text = new MimeBodyPart();
            text.setText(mailEvent.getMessage(), CODE);
            multipart.addBodyPart(text);

            MimeBodyPart attachment = new MimeBodyPart();
            ByteArrayDataSource ds = new ByteArrayDataSource(mailEvent.getInputStream(), FORMAT_PDF);
            attachment.setDataHandler(new DataHandler(ds));
            attachment.setFileName(FILE_NAME);
            multipart.addBodyPart(attachment);
        } catch (MessagingException | IOException ex) {
            Logger.getLogger(MailFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return multipart;
    }

}
