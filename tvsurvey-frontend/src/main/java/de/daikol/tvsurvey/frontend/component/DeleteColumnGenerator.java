package de.daikol.tvsurvey.frontend.component;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;

/**
 * Diese Klasse dient als Generator für die Spalte mit dem Lösch-Button.
 */
public class DeleteColumnGenerator implements Table.ColumnGenerator {

    /**
     * Der Listener der auf den Button reagiert.
     */
    Button.ClickListener clickListener;

    public DeleteColumnGenerator(Button.ClickListener clickListener) {
    this.clickListener = clickListener;
    }

    @Override
    public Object generateCell(Table source, Object itemId, Object columnId) {
        Property prop = source.getItem(itemId).getItemProperty("id");
        Button button = new Button("Löschen", this.clickListener);
        button.setDescription("Mit diesem Button wird die entsprechende Umfrage gelöscht.");
        button.setData(prop.getValue());
        return button;
    }
}
