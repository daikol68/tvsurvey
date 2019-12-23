package de.daikol.tvsurvey.frontend.view.impl;

import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import de.daikol.tvsurvey.api.ISurveyManager;
import de.daikol.tvsurvey.frontend.component.*;
import de.daikol.tvsurvey.frontend.model.User;
import de.daikol.tvsurvey.frontend.view.SurveyView;
import de.daikol.tvsurvey.frontend.view.LoginView;
import de.daikol.tvsurvey.model.ServiceResponse;
import de.daikol.tvsurvey.model.ServiceStatusType;
import de.daikol.tvsurvey.model.Survey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SurveyViewImpl extends VerticalLayout implements View, SurveyView {

    /**
     * SLF4J-Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(SurveyViewImpl.class);

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Die Komponente in der die Umfragen gelistet werden.
     */
    SurveyListComponent listComponent;

    /**
     * Der Button zum Hinzufügen einer neuen Umfrage.
     */
    Button add;

    /**
     * Der Button zum Logout.
     */
    Button logout;

    /**
     * Der Listener der auf Ereignisse reagiert.
     */
    SurveyViewListener listener;

    /**
     * Der Manager für die Umfragen.
     */
    ISurveyManager manager;

    /**
     * Konstruktor.
     * 
     * @param manager
     *            Der Manager für die CSV.
     */
    public SurveyViewImpl(ISurveyManager manager) {

        this.manager = manager;
        this.listComponent = new SurveyListComponent(new SurveyListListener(), this);

        setSpacing(true);
        setMargin(true);

        this.add = new Button("Hinzufügen", new AddListener());
        this.logout = new Button("Logout", new LogoutListener());

        GridLayout grid = new GridLayout(2, 1);
        grid.setWidth(100, Sizeable.Unit.PERCENTAGE);
        grid.addComponent(this.add, 0, 0, 0, 0);
        grid.addComponent(this.logout, 1, 0, 1, 0);
        grid.setComponentAlignment(this.logout, Alignment.MIDDLE_RIGHT);

        addComponents(this.listComponent, grid);

        Page.getCurrent().setTitle("TV Survey");

        this.add.setClickShortcut(KeyCode.ENTER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enter(ViewChangeEvent event) {
        this.listener.onReload();
        User user = VaadinSession.getCurrent().getAttribute(User.class);
        if (user == null) {
            UI.getCurrent().getNavigator().navigateTo(LoginView.NAME);
        }
    }

    /**
     * Diese Methode wird verwendet um den Listener zu setzen mit dem auf bestimmte Ereignisse reagiert wird.
     *
     * @param listener
     *            Der Listener der gesetzt wird.
     */
    public void setListener(SurveyViewListener listener) {
        this.listener = listener;
    }

    @Override
    public void reload() {

        ServiceResponse<Survey> response =this.manager.getSurveys();
        if (response.getStatus() == ServiceStatusType.SUCCESS) {
            if (response.getResults() != null) {
                this.listComponent.refresh(response.getResults());
            }
        } else {
            Notification.show(response.getMessage(), Notification.Type.ERROR_MESSAGE);
        }

    }

    @Override
    public void create() {
        this.listener.onCreate();
    }

    @Override
    public void update(Survey survey) {
        this.listener.onUpdate(survey);
    }


    @Override
    public void delete(Long id) {
        this.listener.onDelete(id);
        reload();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void logout() {
        logger.debug("Logout clicked.");
        this.listener.onLogout();
    }

    /**
     * Der Listener der beim Klick auf den Logout-Button getriggert wird.
     */
    class AddListener implements ClickListener {

        /**
         * serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * {@inheritDoc}
         */
        @Override
        public void buttonClick(ClickEvent event) {
            create();
        }

    }

    /**
     * Der Listener der beim Klick auf den Logout-Button getriggert wird.
     */
    class LogoutListener implements ClickListener {

        /**
         * serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * {@inheritDoc}
         */
        @Override
        public void buttonClick(ClickEvent event) {
            logout();
        }

    }

    /**
     * Der Listener der beim Klick auf den Logout-Button getriggert wird.
     */
    class DeleteListener implements Button.ClickListener {

        /**
         * serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * {@inheritDoc}
         */
        @Override
        public void buttonClick(Button.ClickEvent event) {
            // Get the item identifier from the user-defined data.
            Long id = (Long) event.getButton().getData();
            delete(id);
        }

    }

    class SurveyListListener implements ItemClickEvent.ItemClickListener {

        /**
         * serialVersionUID.
         */
        private static final long serialVersionUID = 5946813200278168543L;

        /**
         * {@inheritDoc}
         */
        @Override
        public void itemClick(ItemClickEvent event) {

            logger.trace("item clicked.");

            @SuppressWarnings("unchecked")
            Survey survey = ((BeanItem<Survey>) event.getItem()).getBean();

            update(survey);
        }

    }

}
