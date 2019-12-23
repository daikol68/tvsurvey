package de.daikol.tvsurvey.frontend.presenter;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import de.daikol.tvsurvey.api.ISurveyManager;
import de.daikol.tvsurvey.frontend.model.User;
import de.daikol.tvsurvey.frontend.view.SurveyView;
import de.daikol.tvsurvey.frontend.view.LoginView;
import de.daikol.tvsurvey.model.Survey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Hierbei handelt es sich um den Presenter für das Anzeigen von Umfragen.
 */
public class SurveyPresenter implements Presenter, SurveyView.SurveyViewListener, Serializable {

    /**
     * SLF4J-Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(SurveyPresenter.class);

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -6913235362395871681L;

    /**
     * surveyView.
     */
    SurveyView surveyView;

    /**
     * presenters.
     */
    Presenters presenters;

    /**
     * Der Manager für die CSV.
     */
    ISurveyManager manager;

    /**
     * Constructor for a new SurveyPresenter.
     *
     * @param surveyView Die view.
     * @param presenters Der Navigator.
     * @param manager    Der Manager für die Umfragen.
     */
    public SurveyPresenter(SurveyView surveyView, Presenters presenters, ISurveyManager manager) {
        this.surveyView = surveyView;
        this.presenters = presenters;
        this.manager = manager;
    }

    @Override
    public void onReload() {
        logger.trace("onReload triggered.");
        this.surveyView.reload();
    }

    @Override
    public void onCreate() {
        logger.trace("onCreate triggered.");
        this.presenters.getSurveyEditPresenter().onPresent(new Survey());
    }

    @Override
    public void onUpdate(Survey survey) {
        logger.trace("onUpdate triggered.");
        this.presenters.getSurveyEditPresenter().onPresent(survey);
    }

    @Override
    public void onDelete(Long id) {
        logger.trace("onDelete triggered.");
        this.manager.deleteSurvey(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void present() {
        logger.trace("present triggered.");
        UI.getCurrent().getNavigator().navigateTo(SurveyView.NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLogout() {
        logger.trace("onLogout triggered.");
        VaadinSession.getCurrent().setAttribute(User.class, null);
        UI.getCurrent().getNavigator().navigateTo(LoginView.NAME);
    }

}
