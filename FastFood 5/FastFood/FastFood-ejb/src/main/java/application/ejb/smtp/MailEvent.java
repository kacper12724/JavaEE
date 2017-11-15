package application.ejb.smtp;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

public class MailEvent implements Serializable {

    private String mailTo;
    private String subject;
    private String message;
    private byte[] attachment;

    public ByteArrayInputStream getInputStream() {
        return new ByteArrayInputStream(attachment);
    }

    //getters/setters
    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
