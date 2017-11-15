package application.web.manager;

import application.ejb.entity.Food;
import application.ejb.entity.Orderr;
import application.ejb.entity.User;
import application.ejb.facade.interfaces.OrderrFacadeLocal;
import application.ejb.facade.interfaces.PdfFacadeLocal;
import application.ejb.smtp.MailEvent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

/**
 *
 * @author Łukasz
 */
@Named(value = "orderManager")
@SessionScoped
public class OrderManager implements Serializable {

    private final List<Orderr> cart = new ArrayList<>();
    private BigDecimal fullPrice = new BigDecimal(0);

    @EJB
    private OrderrFacadeLocal orderFacade;
    @EJB
    private PdfFacadeLocal pdfFacade;

    @Resource(mappedName = "jms/queueDest")
    private Queue queueDest;
    @Inject
    @JMSConnectionFactory("jms/queue")
    private JMSContext context;

    private static final String SUBJECT = "Fast Food potwierdzenie zamówienia";
    private static final String MSG_PART1 = "Potwierdzenie zamówienia.\n";
    private static final String MEAL_NAME = "Nazwa dania: ";
    private static final String PRICE = " Cena: ";
    private static final String END_LINE = "\n";
    private static final String ORDER_CONFIRMED = "order-confirmed";

    @PostConstruct
    public void init() {
    }

    public String makeOrder(User user) {
        orderFacade.makeOrder(cart);

        MailEvent mailEvent = new MailEvent();
        mailEvent.setMailTo(user.getEmail());
        mailEvent.setSubject(SUBJECT);

        StringBuilder sb = new StringBuilder();
        sb.append(MSG_PART1);
        for (Orderr order : cart) {
            sb.append(MEAL_NAME).append(order.getIdFood().getName()).append(PRICE).append(order.getIdFood().getPrice()).append(END_LINE);
        }
        mailEvent.setMessage(sb.toString());
        //TODO dobra pdfy cos tam działaja teraz jak to dołączyc do emiala!
        mailEvent.setAttachment(pdfFacade.createPdf(cart));

        sendJMSMessageToQueueDest(mailEvent);

        return ORDER_CONFIRMED;
    }

    public void addToCard(Food f, User u) {
        cart.add(orderFacade.createOrder(f, u));
        increasePrice(f.getPrice().doubleValue());
    }

    public void removeFromCart(Orderr o) {
        cart.remove(o);
        decresePrice(o.getIdFood().getPrice().doubleValue());
    }

    public boolean isCartEmpty() {
        return !cart.isEmpty();
    }

    //private methods
    private void sendJMSMessageToQueueDest(MailEvent mail) {
        try {
            ObjectMessage message = context.createObjectMessage();
            message.setObject(mail);
            context.createProducer().send(queueDest, message);
        } catch (JMSException ex) {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //private methods
    private void increasePrice(double x) {
        fullPrice = BigDecimal.valueOf(Double.sum(fullPrice.doubleValue(), x));
        fullPrice.setScale(2, RoundingMode.DOWN);
    }

    private void decresePrice(double x) {
        fullPrice = BigDecimal.valueOf(Double.sum(fullPrice.doubleValue(), -x));
        fullPrice.setScale(2, RoundingMode.DOWN);
    }

    //getters/setters
    public List<Orderr> getCart() {
        return cart;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

}
