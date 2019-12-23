package de.daikol.tvsurvey.frontend.form;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import de.daikol.tvsurvey.frontend.component.CategoryListComponent;
import de.daikol.tvsurvey.frontend.component.DateConverter;
import de.daikol.tvsurvey.frontend.view.SurveyView;
import de.daikol.tvsurvey.model.Category;
import de.daikol.tvsurvey.model.Survey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Diese Klasse wird verwendet um eine Umfrage zu bearbeiten.
 */
public class SurveyForm extends VerticalLayout implements FieldGroup.CommitHandler {

    /**
     * SLF4J-Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(SurveyForm.class);

    /**
     * The binding to the element.
     */
    BeanFieldGroup<Survey> surveyBinding = new BeanFieldGroup<>(Survey.class);

    /**
     * Die Überschrift für die Bearbeitung.
     */
    Label label;

    /**
     * Die Überschrift für die Bearbeitung der Antwortmöglichkeiten.
     */
    Label categoriesLabel;

    /**
     * Das Textfeld für den Sender.
     */
    TextField sender;

    /**
     * Das Textfeld für den Namen.
     */
    TextField name;

    /**
     * Das Textfeld für die Gebühr.
     */
    TextField fee;

    /**
     * Das Textfeld für die Frage.
     */
    TextField question;

    /**
     * Das Textfeld für die Angabe Gültig von.
     */
    TextField validFrom;

    /**
     * Das Textfeld für die Angabe Gültig bis.
     */
    TextField validTo;

    /**
     * Das Textfeld für den HTTP Link.
     */
    TextField httpLink;

    /**
     * Der Button zum Hinzufügen einer Antwortmöglichkeit.
     */
    Button add;

    /**
     * Die ComboBox für die Kategorien.
     */
    CategoryListComponent categories;

    /**
     * Konstruktor.
     */
    public SurveyForm() {

        this.label = new Label("Umfrage bearbeiten");
        this.label.setStyleName("h1");
        this.categoriesLabel = new Label("Antwortmöglichkeiten");
        this.categoriesLabel.setStyleName("h2");


        this.add = new Button("Hinzufügen", new AddListener());
        this.add.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        this.categories = new CategoryListComponent(new CategoryListListener(), this);

        this.sender = new TextField("Sender");
        this.name = new TextField("Name");
        this.fee = new TextField("Gebühr");
        this.question = new TextField("Frage");
        this.validFrom = new TextField("Gültig von");
        this.validTo = new TextField("Gültig bis");
        this.httpLink = new TextField("Link");

        this.sender.setDescription("Hier wird der Sender eingetragen.");
        this.name.setDescription("Hier wird der Name der Sendung eingetragen.");
        this.fee.setDescription("Hier werden die Gebühren pro SMS eingetragen.");
        this.question.setDescription("Hier wird die Frage eingetragen.");
        this.validFrom.setDescription("Gültig von im Format 'TT.MM.JJJJ SS:MM'.");
        this.validTo.setDescription("Gültig bis im Format 'TT.MM.JJJJ SS:MM'.");
        this.httpLink.setDescription("Hier wird der HTTP Link zu Sendung eingetragen.");

        this.sender.setNullRepresentation("");
        this.name.setNullRepresentation("");
        this.fee.setNullRepresentation("");
        this.question.setNullRepresentation("");
        this.validFrom.setNullRepresentation("");
        this.validTo.setNullRepresentation("");
        this.httpLink.setNullRepresentation("");

        this.sender.setRequired(true);
        this.name.setRequired(true);
        this.fee.setRequired(true);
        this.question.setRequired(true);

        this.sender.setWidth(100, Sizeable.Unit.PERCENTAGE);
        this.name.setWidth(100, Sizeable.Unit.PERCENTAGE);
        this.fee.setWidth(100, Sizeable.Unit.PERCENTAGE);
        this.question.setWidth(100, Sizeable.Unit.PERCENTAGE);
        this.validFrom.setWidth(100, Sizeable.Unit.PERCENTAGE);
        this.validTo.setWidth(100, Sizeable.Unit.PERCENTAGE);
        this.httpLink.setWidth(100, Sizeable.Unit.PERCENTAGE);

        this.validFrom.setConverter(new DateConverter());
        this.validTo.setConverter(new DateConverter());

        GridLayout grid = new GridLayout(2, 9);
        grid.setWidth(100, Sizeable.Unit.PERCENTAGE);
        grid.addComponent(this.sender, 0, 0, 1, 0);
        grid.addComponent(this.name, 0, 1, 1, 1);
        grid.addComponent(this.fee, 0, 2, 1, 2);
        grid.addComponent(this.question, 0, 3, 1, 3);
        grid.addComponent(this.validFrom, 0, 4, 1, 4);
        grid.addComponent(this.validTo, 0, 5, 1, 5);
        grid.addComponent(this.httpLink, 0, 6, 1, 6);
        grid.addComponent(categoriesLabel, 0, 7, 0, 7);
        grid.addComponent(this.add, 1, 7, 1, 7);
        grid.addComponent(this.categories, 0, 8, 1, 8);

        grid.setComponentAlignment(this.add, Alignment.MIDDLE_RIGHT);

        this.surveyBinding.bindMemberFields(this);

        addComponents(this.label, grid);
    }

    /**
     * Diese Methode setzt das Binding.
     *
     * @param survey Die Umfrage die als Binding gesetzt werden soll.
     */
    public void setBinding(Survey survey) {
        this.surveyBinding.setItemDataSource(survey);
        if (survey.getId() == 0) {
            this.label.setValue("Umfrage erstellen");
        } else {
            this.label.setValue("Umfrage bearbeiten");
        }
        this.categories.refresh(survey.getCategories());
    }

    /**
     * Gbt an ob ein Binding existiert.
     *
     * @return True wenn eins existiert, sonst false.
     */
    public boolean hasBinding() {
        return this.surveyBinding.getItemDataSource() != null;
    }

    /**
     * Diese Methode liefert das Binding.
     *
     * @return Die Umfrage die als Binding gesetzt ist.
     */
    public Survey getBinding() {
        return this.surveyBinding.getItemDataSource().getBean();
    }

    /**
     * Formular abschicken. Alle gebundenen Felder werden in ein Survey-Objekt geschrieben.
     *
     * @return ein Survey.
     */
    public Survey submit() throws FieldGroup.CommitException {
        this.surveyBinding.commit();
        Survey survey = this.surveyBinding.getItemDataSource().getBean();
        return survey;
    }

    public void add(Category category) {
        Survey survey = this.surveyBinding.getItemDataSource().getBean();
        if (survey.getCategories() == null) {
            survey.setCategories(new ArrayList<Category>());
        }
        if (category.getName() == null || category.getName().isEmpty()){
            if (!survey.getCategories().contains(category)) {
                category.setId(-survey.getCategories().size());
                survey.getCategories().add(category);
            }
        } else {
            survey.getCategories().add(category);
        }

        this.categories.refresh(survey.getCategories());
    }

    public void delete(Category category) {
        Survey survey = this.surveyBinding.getItemDataSource().getBean();
        survey.getCategories().remove(category);
        this.categories.refresh(survey.getCategories());
    }

    @Override
    public void preCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {
        // nothing to do
    }

    @Override
    public void postCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {
        // nothing to do
    }

    /**
     * Der Listener der beim Klick auf den Logout-Button getriggert wird.
     */
    class AddListener implements Button.ClickListener {

        /**
         * serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * {@inheritDoc}
         */
        @Override
        public void buttonClick(Button.ClickEvent event) {
            Category category = new Category();
            category.setName("");
            category.setPhoneNumber("");
            category.setSmsNumber("");
            category.setSmsValue("");
            add(category);
        }

    }

    class CategoryListListener implements ItemClickEvent.ItemClickListener {

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
            Category category = ((BeanItem<Category>) event.getItem()).getBean();
        }

    }


}
