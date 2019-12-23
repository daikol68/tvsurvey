package de.daikol.tvsurvey.frontend.presenter;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import de.daikol.tvsurvey.frontend.model.User;
import de.daikol.tvsurvey.frontend.view.LoginView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * This class represents the logic behind the login.
 */
public class LoginPresenter implements Presenter, LoginView.LoginViewListener, Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 4202230203590653752L;

    /**
     * SLF4J-Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(LoginPresenter.class);

    /**
     * Die View für den Login.
     */
    LoginView loginView;

    /**
     * Die Presenter.
     */
    Presenters presenters;

    /**
     * Constructor for a new LoginPresenter.
     *
     * @param loginView
     *            Die View.
     * @param presenters
     *            Die Presenter.
     */
    public LoginPresenter(LoginView loginView, Presenters presenters) {
        this.loginView = loginView;
        this.presenters = presenters;
    }

    @Override
    public void login(String user, String pass) {

        logger.trace("User with name [{}] and password [***] tried to login.", user);

        if (user == null) {
            this.loginView.showLoginFailed();
            return;
        }

        if (("patrick".equals(user) && "Weltmeister14".equals(pass))
            || ("david".equals(user) && "DgdGddb#86".equals(pass))
                || ("hagen".equals(user) && "zuffenhausen".equals(pass))
                || ("lina".equals(user) && "schwarz".equals(pass))
                || ("dennis".equals(user) && "glogau23".equals(pass))) {

            logger.info("User login for user [{}].", user);

            User b = new User();
            b.name = user;

            VaadinSession.getCurrent().setAttribute(User.class, b);
            this.presenters.getSurveyPresenter().present();

        } else {
            this.loginView.showLoginFailed();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void present() {
        logger.trace("Present triggered.");
        UI.getCurrent().getNavigator().navigateTo(LoginView.NAME);
    }

}
