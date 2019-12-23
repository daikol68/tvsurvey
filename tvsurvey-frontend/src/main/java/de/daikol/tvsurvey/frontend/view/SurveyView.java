package de.daikol.tvsurvey.frontend.view;

import de.daikol.tvsurvey.model.Survey;

import java.io.IOException;

/**
 * The view for editing the surveys.
 */
public interface SurveyView {

    /**
     * NAME.
     */
    String NAME = "survey";

    interface SurveyViewListener {

        /**
         * Diese Methode wird aufgerufen wenn die View initialisiert wird.
         *
         */
        void onReload();

        /**
         * Diese Methode wird aufgerufen soabld eine Umfrage erfasst werden soll.
         */
        void onCreate();

        /**
         * Diese Methode wird aufgerufen sobald ein Eintrag editiert werden soll.
         * @param survey Der Eintrag.
         */
        void onUpdate(Survey survey);

        /**
         * Diese Methode wird aufgerufen soabld die Umfrage gelöscht werden soll.
         * 
         */
        void onDelete(Long id);

        /**
         * Diese Methode wird aufgerufen sobald der User ausgeloggt werden kann.
         *
         */
        void onLogout();

    }

    /**
     * Diese Methode wird verwendet um die View zurück zu setzen.
     *
     */
    void reload();

    /**
     * 
     * Diese Methode wird aufgerufen sobald ein Eintrag erfasst werden soll.
     *
     */
    void create();

    /**
     * Diese Methode wird aufgerufen sobald ein Eintrag editiert werden soll.
     */
    void update(Survey survey);

    /**
     *
     * Diese Methode wird aufgerufen sobald ein Eintrag gelöscht werden soll.
     *
     */
    void delete(Long id);

    /**
     * Mit dieser Methode wird der Logout ausgeführt.
     */
    void logout();

}
