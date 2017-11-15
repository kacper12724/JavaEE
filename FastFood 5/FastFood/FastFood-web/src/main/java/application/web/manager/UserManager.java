package application.web.manager;

import application.ejb.entity.User;
import application.ejb.facade.interfaces.UserFacadeLocal;
import application.ejb.smtp.MailEvent;
import application.web.filter.Util;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Named(value = "userManager")
@SessionScoped
public class UserManager implements Serializable {

    private User user;
    private MailEvent mailEvent;

    @EJB
    private UserFacadeLocal userFacade;

    private static final String SUCCESS = "success";
    private static final String FAIL = "failure";
    private static final String SUBJECT = "Fast Food potwierdzenie rejestracji";
    private static final String MSG_PART1 = "Witamy w FastFood \n";
    private static final String MSG_PART2 = "Nazwa użytkownika: ";
    private static final String MSG_PART3 = "Hasło: ";
    private static final String END_LINE = "\n";
    private static final String LOG_OUT = "logout";
    private static final String EMPTY_FIELDS = "empty";
    private static final String IS_LOGGED = "logged";
    private static final String IS_NOT_LOGGED = "notLogged";
    private static final String NO_VALIDATE = "noValidate";

    @PostConstruct
    public void init() {
        user = new User();
        mailEvent = new MailEvent();
    }

    //TODO przejścia w navigation rules
    public String save() {
        mailEvent = new MailEvent();
        mailEvent.setMailTo(user.getEmail());
        mailEvent.setSubject(SUBJECT);
        mailEvent.setMessage(MSG_PART1
                + MSG_PART2 + user.getUsername() + END_LINE
                + MSG_PART3 + user.getPassword());

        userFacade.create(user);
        return SUCCESS;

    }

    public String login() {
        if ((user = userFacade.login(user)) != null) {
            HttpSession session = Util.getSession();
            session.setAttribute("user", user);
            System.out.println("application.web.manager.UserManager.login()");
            System.out.println(session.getAttribute("user"));
            return SUCCESS;
        } else {
            return FAIL;
        }
    }

    public String cancel() {
        user = new User();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelled new user"));
        return NO_VALIDATE;
    }

    public String checkIfLogged() {
        HttpSession session = Util.getSession();
        if (session.getAttribute("user") != null) {
            return IS_LOGGED;
        }
        return IS_NOT_LOGGED;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        HttpSession session = Util.getSession();
        session.invalidate();
        return LOG_OUT;
    }

    public String fieldsNotEmpty() {
        return EMPTY_FIELDS;
    }

    //getters/setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
