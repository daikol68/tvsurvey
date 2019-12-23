package de.daikol.tvsurvey.frontend.component;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.*;
import de.daikol.tvsurvey.frontend.view.SurveyView;
import de.daikol.tvsurvey.model.Survey;

import java.util.List;

public class SurveyListComponent extends VerticalLayout {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 3685513169833215065L;

    /**
     * itemClickListener.
     */
    ItemClickListener itemClickListener;

    /**
     * tableBinding.
     */
    BeanContainer<Integer, Survey> tableBinding;

    /**
     * table.
     */
    Table table;

    /**
     * Die Hauptview.
     */
    SurveyView view;

    /**
     * Constructor for a new AnliegenListComponent.
     *
     * @param itemClickListener
     *            the listener
     */
    public SurveyListComponent(ItemClickListener itemClickListener, SurveyView view) {

        this.itemClickListener = itemClickListener;
        this.view = view;
        this.tableBinding = new BeanContainer<Integer, Survey>(Survey.class);
        this.tableBinding.setBeanIdProperty("id");

        this.table = new Table();
        this.table.setWidth(100, Unit.PERCENTAGE);
        this.table.setSelectable(true);

        this.table.setContainerDataSource(this.tableBinding);
        this.table.addItemClickListener(itemClickListener);

        this.table.addGeneratedColumn("delete", new DeleteColumnGenerator(new DeleteListener()));

        this.table.setColumnHeader("sender", "Sender");
        this.table.setColumnHeader("name", "Name");
        this.table.setColumnHeader("fee", "Gebühren");
        this.table.setColumnHeader("question", "Frage");
        this.table.setColumnHeader("delete", "");

        this.table.setVisibleColumns("sender", "name", "fee", "question", "delete");

        this.table.setItemDescriptionGenerator(new TableTooltip("Klicken, um Umfrage zu bearbeiten ..."));
        Label h2Label = new Label("Umfragen");
        h2Label.setStyleName("h2");

        addComponents(h2Label, this.table);
        addStyleName("grey-stripe");
    }

    public void refresh(List<Survey> surveys) {

        this.setVisible(true);

        this.tableBinding.removeAllItems();
        this.tableBinding.addAll(surveys);

        showTable(surveys.size());

    }

    public void showTable(int size) {

        if (size > 0) {
            this.table.setPageLength(size);
            this.table.setVisible(true);
        } else {
            this.table.setVisible(false);
        }
    }

    public void delete(Long id) {
        this.view.delete(id);
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


}
