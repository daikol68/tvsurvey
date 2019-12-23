package de.daikol.tvsurvey.frontend.view.impl;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import de.daikol.tvsurvey.api.ISurveyManager;
import de.daikol.tvsurvey.frontend.component.*;
import de.daikol.tvsurvey.frontend.form.SurveyForm;
import de.daikol.tvsurvey.frontend.model.User;
import de.daikol.tvsurvey.frontend.view.LoginView;
import de.daikol.tvsurvey.frontend.view.SurveyEditView;
import de.daikol.tvsurvey.frontend.view.SurveyView;
import de.daikol.tvsurvey.model.Survey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;

/**
 * Created by daikol on 03.06.2016.
 */
public class SurveyEditViewImpl extends VerticalLayout implements View, SurveyEditView {

    /**
     * SLF4J-Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(SurveyEditViewImpl.class);

    /**
     * Der Listener der auf Ereignisse reagiert.
     */
    SurveyEditView.SurveyEditViewListener listener;

    /**
     * Der Manager für die Umfragen.
     */
    ISurveyManager manager;

    /**
     * Die Umfrage die bearbeitet werden soll.
     */
    Survey survey;

    /**
     * Die Form mit der die Daten bearbeitet werden.
     */
    SurveyForm form;

    /**
     * Der Button zum Abbrechen der Operation.
     */
    Button abort;

    /**
     * Der Button zum Update einer Umfrage.
     */
    Button update;

    /**
     * Der Button zum Logout.
     */
    Button logout;

    /**
     * Konstruktor.
     *
     * @param manager
     *            Der Manager für die CSV.
     */
    public SurveyEditViewImpl(ISurveyManager manager) {
        this.manager = manager;
        this.form = new SurveyForm();

        setSpacing(true);
        setMargin(true);

        // create button line
        this.abort = new Button("Abbrechen", new AbortListener());
        this.logout = new Button("Logout", new LogoutListener());
        this.update = new Button("Speichern", new UpdateListener());

        GridLayout grid = new GridLayout(3, 1);
        grid.setWidth(100, Sizeable.Unit.PERCENTAGE);
        grid.addComponent(this.abort, 0, 0, 0, 0);
        grid.addComponent(this.update, 1, 0, 1, 0);
        grid.addComponent(this.logout, 2, 0, 2, 0);
        grid.setComponentAlignment(this.logout, Alignment.MIDDLE_RIGHT);

        addComponents(this.form, grid);

        Page.getCurrent().setTitle("TV Survey");

        this.update.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }



    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        User user = VaadinSession.getCurrent().getAttribute(User.class);
        if (user == null) {
            UI.getCurrent().getNavigator().navigateTo(LoginView.NAME);
        }
    }

    @Override
    public void setSurvey(Survey survey) {
        this.survey = survey;
        this.form.setBinding(survey);
    }

    @Override
    public void abort() {
        this.listener.onAbort();
    }

    @Override
    public void logout() {
        logger.debug("Logout clicked.");
        this.listener.onLogout();
    }

    @Override
    public void save() {
        try {
            this.survey = this.form.submit();
            this.listener.onSave(this.survey);
        } catch (FieldGroup.CommitException e) {
            Notification.show(e.getCause().getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }

    /**
     * Diese Methode wird verwendet um den Listener zu setzen mit dem auf bestimmte Ereignisse reagiert wird.
     *
     * @param listener
     *            Der Listener der gesetzt wird.
     */
    public void setListener(SurveyEditViewListener listener) {
        this.listener = listener;
    }

    /**
     * Der Listener der beim Klick auf den Abbrechen-Button getriggert wird.
     */
    class AbortListener implements Button.ClickListener {

        /**
         * serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * {@inheritDoc}
         */
        @Override
        public void buttonClick(Button.ClickEvent event) {
            abort();
        }

    }

    /**
     * Der Listener der beim Klick auf den Update-Button getriggert wird.
     */
    class UpdateListener implements Button.ClickListener {

        /**
         * serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * {@inheritDoc}
         */
        @Override
        public void buttonClick(Button.ClickEvent event) {
            save();
        }

    }

    /**
     * Der Listener der beim Klick auf den Logout-Button getriggert wird.
     */
    class LogoutListener implements Button.ClickListener {

        /**
         * serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * {@inheritDoc}
         */
        @Override
        public void buttonClick(Button.ClickEvent event) {
            logout();
        }

    }

}
