package de.daikol.tvsurvey.frontend.presenter;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import de.daikol.tvsurvey.api.ISurveyManager;
import de.daikol.tvsurvey.frontend.model.User;
import de.daikol.tvsurvey.frontend.view.LoginView;
import de.daikol.tvsurvey.frontend.view.SurveyEditView;
import de.daikol.tvsurvey.frontend.view.SurveyView;
import de.daikol.tvsurvey.model.Survey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hierbei handelt es sich um einen Presenter zum Bearbeiten von Umfragen.
 */
public class SurveyEditPresenter implements Presenter, SurveyEditView.SurveyEditViewListener{

    /**
     * SLF4J-Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(SurveyEditPresenter.class);

    /**
     * anliegenManager.
     */
    ISurveyManager surveyManager;

    /**
     * anliegenView.
     */
    SurveyEditView surveyView;

    /**
     * presenters.
     */
    Presenters presenters;

    public SurveyEditPresenter (ISurveyManager surveyManager, SurveyEditView surveyView, Presenters presenters) {
        this.surveyManager = surveyManager;
        this.surveyView = surveyView;
        this.presenters = presenters;
    }

    @Override
    public void onSave(Survey survey) {
        logger.trace("onSave triggered.");
        if (survey.getId() != 0) {
            this.surveyManager.updateSurvey(survey);
        } else {
            this.surveyManager.createSurvey(survey);
        }
        UI.getCurrent().getNavigator().navigateTo(SurveyView.NAME);
    }

    @Override
    public void onPresent(Survey survey) {
        logger.trace("onPresent triggered.");
        this.surveyView.setSurvey(survey);
        present();
    }

    @Override
    public void onAbort() {
        logger.trace("onAbort triggered.");
        UI.getCurrent().getNavigator().navigateTo(SurveyView.NAME);
    }

    @Override
    public void onLogout() {
        logger.trace("onLogout triggered.");
        VaadinSession.getCurrent().setAttribute(User.class, null);
        UI.getCurrent().getNavigator().navigateTo(LoginView.NAME);
    }

    @Override
    public void present() {
        logger.trace("present triggered.");
        UI.getCurrent().getNavigator().navigateTo(SurveyEditView.NAME);
    }
}
