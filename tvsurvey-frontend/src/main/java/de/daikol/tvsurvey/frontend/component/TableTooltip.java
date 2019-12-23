package de.daikol.tvsurvey.frontend.component;

import com.vaadin.ui.AbstractSelect.ItemDescriptionGenerator;
import com.vaadin.ui.Component;

/**
 * Einfache Klasse zum Anzeigen eines Tooltip.
 */
public class TableTooltip implements ItemDescriptionGenerator {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -2719732581763190622L;

    /**
     * Der Text, der im Tooltip angezeigt werden soll.
     */
    String tooltipText;

    /**
     * Constructor for a new Tooltip.
     *
     * @param tooltipText
     *            Der Text, der im Tooltip angezeigt werden soll.
     */
    public TableTooltip(String tooltipText) {

        this.tooltipText = tooltipText;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String generateDescription(Component source, Object itemId, Object propertyId) {
        return this.tooltipText;
    }

    /**
     * Setter for <code>tooltipText</code>.
     *
     * @param tooltipText
     *            New value used for <code>tooltipText</code>.
     */
    public void setTooltipText(String tooltipText) {
        this.tooltipText = tooltipText;
    }

}