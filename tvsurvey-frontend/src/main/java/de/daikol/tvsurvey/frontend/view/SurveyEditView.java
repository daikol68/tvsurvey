package de.daikol.tvsurvey.frontend.view;

import de.daikol.tvsurvey.model.Survey;

/**
 * Dieses Interface stellt die Logik zur Verfügung eine einzelne Umfrage zu bearbeiten.
 */
public interface SurveyEditView {

    /**
     * NAME.
     */
    String NAME = "survey/edit";

    /**
     * A listener interface that is called by the view.
     *
     * @author Marcel Becker (becker.marcel@postdirekt.de)
     * @version $Revision: $
     *
     */
    interface SurveyEditViewListener {

        /**
         * Triggered as a survey should be saved.
         * @param survey The survey to save.
         */
        void onSave(Survey survey);

        /**
         * Presenters may call this method in order to initialize the view with a Survey.
         *
         * @param survey the Survey.
         */
        void onPresent(Survey survey);

        /**
         * Presenters may call this method in order to abort the operation.
         */
        void onAbort();

        /**
         * If the user does the logout.
         */
        void onLogout();

    }

    /**
     * This method is used to pass a Survey object to the view.
     *
     * @param survey
     *            the Survey
     */
    void setSurvey(Survey survey);

    /**
     * If someone aborts the operation.
     */
    void abort();

    /**
     * If the user does the logout.
     */
    void logout();

    /**
     * If someones saves a survey.
     */
    void save();

}
