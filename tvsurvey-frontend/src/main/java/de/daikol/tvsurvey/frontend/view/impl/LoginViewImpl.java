package de.daikol.tvsurvey.frontend.view.impl;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import de.daikol.tvsurvey.frontend.view.LoginView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The implementation of the LoginView.
 */
public class LoginViewImpl extends VerticalLayout implements View, LoginView {

    /**
     * SLF4J-Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(LoginViewImpl.class);
    
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Das Feld für den Benutzernamen.
     */
    TextField tUsername;

    /**
     * Das Feld für das Passwort.
     */
    PasswordField tPassword;

    /**
     * Der Login-Button.
     */
    Button bLogin;

    /**
     * Der Listener für den Login.
     */
    LoginViewListener listener;

    /**
     * Konstruktor.
     */
    public LoginViewImpl() {
        setMargin(true);
        setSpacing(true);

        this.tUsername = new TextField("User");
        this.tUsername.setRequired(true);

        this.tPassword = new PasswordField("Passwort");
        this.tPassword.setRequired(true);

        this.bLogin = new Button("Log in", new LoginListener());

        this.bLogin.setClickShortcut(KeyCode.ENTER);

        addComponent(this.tUsername);
        addComponent(this.tPassword);
        addComponent(this.bLogin);

        Page.getCurrent().setTitle("TV Survey");

        Notification.show("Login");
    }

    /**
     * Diese Methode wird verwendet um den Listener zu setzen.
     *
     * @param l
     *            Der Listener.
     */
    public void setListener(LoginViewListener l) {
        this.listener = l;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enter(ViewChangeEvent event) {
        this.tUsername.clear();
        this.tPassword.clear();
    }

    /**
     * Dieser Listener wird aufgerufen, sobald der Button gedrückt wird. Hierbei wird der Login-Aufruf weiter delegiert.
     */
    class LoginListener implements ClickListener {

        /**
         * serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(ClickEvent event) {

            LoginViewImpl.this.tUsername.validate();
            LoginViewImpl.this.tPassword.validate();

            String user = LoginViewImpl.this.tUsername.getValue();
            String pass = LoginViewImpl.this.tPassword.getValue();

            LoginViewImpl.this.listener.login(user, pass);
        }
    }

    @Override
    public void showLoginFailed() {
        this.tPassword.clear();
        logger.warn("Login as user [{}] failed.", this.tUsername.getValue());
        Notification.show("Authentifizierung fehlgeschlagen!", Notification.Type.ERROR_MESSAGE);
    }

}
