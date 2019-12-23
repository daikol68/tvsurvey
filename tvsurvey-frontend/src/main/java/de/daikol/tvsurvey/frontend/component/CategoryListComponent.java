package de.daikol.tvsurvey.frontend.component;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import de.daikol.tvsurvey.frontend.form.SurveyForm;
import de.daikol.tvsurvey.frontend.view.SurveyView;
import de.daikol.tvsurvey.model.Category;
import de.daikol.tvsurvey.model.Survey;

import java.util.List;

public class CategoryListComponent extends VerticalLayout {

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
    BeanContainer<Integer, Category> tableBinding;

    /**
     * table.
     */
    Table table;

    /**
     * Die Form die auf Ereignisse reagiert.
     */
    SurveyForm form;

    /**
     * Constructor for a new AnliegenListComponent.
     *
     * @param itemClickListener
     *            the listener
     */
    public CategoryListComponent(ItemClickListener itemClickListener, SurveyForm form) {

        this.itemClickListener = itemClickListener;
        this.form = form;
        this.tableBinding = new BeanContainer<Integer, Category>(Category.class);
        this.tableBinding.setBeanIdProperty("id");

        this.table = new Table();
        this.table.setWidth(100, Unit.PERCENTAGE);
        this.table.setEditable(true);

        this.table.setContainerDataSource(this.tableBinding);
        this.table.addItemClickListener(itemClickListener);

        this.table.setColumnHeader("name", "Name");
        this.table.setColumnHeader("phoneNumber", "Telefonnummer");
        this.table.setColumnHeader("smsNumber", "SMS Nummer");
        this.table.setColumnHeader("smsValue", "SMS Nachricht");
        this.table.setColumnHeader("delete", "");

        this.table.addGeneratedColumn("delete", new DeleteColumnGenerator(new DeleteListener()));

        this.table.setVisibleColumns("name", "phoneNumber", "smsNumber", "smsValue", "delete");

        addComponents(this.table);
    }

    public void refresh(List<Category> categories) {

        this.setVisible(true);

        this.tableBinding.removeAllItems();
        if (categories != null) {
            this.tableBinding.addAll(categories);
            showTable(categories.size());
        } else {
            showTable(0);
        }

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
        Category category = this.tableBinding.getItem(id).getBean();
        this.form.delete(category);
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
