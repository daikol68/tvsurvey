package de.daikol.tvsurvey.frontend.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import de.daikol.tvsurvey.frontend.presenter.SurveyEditPresenter;
import de.daikol.tvsurvey.frontend.presenter.SurveyPresenter;
import de.daikol.tvsurvey.frontend.presenter.LoginPresenter;
import de.daikol.tvsurvey.frontend.presenter.Presenters;
import de.daikol.tvsurvey.frontend.util.Factory;
import de.daikol.tvsurvey.frontend.view.SurveyEditView;
import de.daikol.tvsurvey.frontend.view.SurveyView;
import de.daikol.tvsurvey.frontend.view.LoginView;
import de.daikol.tvsurvey.frontend.view.impl.SurveyEditViewImpl;
import de.daikol.tvsurvey.frontend.view.impl.SurveyViewImpl;
import de.daikol.tvsurvey.frontend.view.impl.LoginViewImpl;

import javax.servlet.annotation.WebServlet;

/**
 * The actual UI holding each part together.
 */
@Theme("mytheme")
public class TVSurveyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        setNavigator(new com.vaadin.navigator.Navigator(this, this));

        Presenters presenters = new Presenters();

        LoginViewImpl loginView = new LoginViewImpl();
        LoginPresenter loginPresenter = new LoginPresenter(loginView, presenters);
        loginView.setListener(loginPresenter);
        presenters.setLoginPresenter(loginPresenter);

        SurveyViewImpl surveyView = new SurveyViewImpl(Factory.instance().surveyManager());
        SurveyPresenter surveyPresenter = new SurveyPresenter(surveyView, presenters, Factory.instance().surveyManager());
        surveyView.setListener(surveyPresenter);
        presenters.setSurveyPresenter(surveyPresenter);

        SurveyEditViewImpl surveyEditView = new SurveyEditViewImpl(Factory.instance().surveyManager());
        SurveyEditPresenter surveyEditPresenter = new SurveyEditPresenter(Factory.instance().surveyManager(), surveyEditView, presenters);
        surveyEditView.setListener(surveyEditPresenter);
        presenters.setSurveyEditPresenter(surveyEditPresenter);

        getNavigator().addView("", loginView);
        getNavigator().addView(LoginView.NAME, loginView);
        getNavigator().addView(SurveyView.NAME, surveyView);
        getNavigator().addView(SurveyEditView.NAME, surveyEditView);

    }

    /**
     * The {@link VaadinServlet} needs to be mapped to all URLs in order to
     * have Vaadin take control over the application.
     *
     * @author Marcel Becker (becker.marcel@postdirekt.de)
     * @version $Revision: 254 $
     *
     */
    @WebServlet(urlPatterns = "/*", name = "UmfragenBearbeitungUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = TVSurveyUI.class, productionMode = false)
    public static class SperrDBUIServlet extends VaadinServlet {

        /**
         * serialVersionUID.
         */
        private static final long serialVersionUID = 12334233L;
    }

}
