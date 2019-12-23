package de.daikol.tvsurvey.frontend.component;

import com.vaadin.data.util.converter.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Diese Klasse dient dazu ein Datum mit Zeitstempel zu konvertieren.
 */
public class DateConverter implements Converter<String, Date> {

    /**
     * TIME_PATTERN.
     */
    static final String DATE_PATTERN = "dd.MM.yyyy";

    /**
     * TIME_PATTERN.
     */
    static final String TIME_PATTERN = "dd.MM.yyyy HH:mm";
    
    /**
     * logger.
     */
    static final Logger logger = LoggerFactory.getLogger(DateConverter.class);
    
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 8542215519941501120L;

    /**
     * {@inheritDoc}
     */
    @Override
    public Date convertToModel(String value, Class<? extends Date> targetType, Locale locale) throws ConversionException {

        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            if (value.length() == DATE_PATTERN.length()) {
                return new SimpleDateFormat(DATE_PATTERN).parse(value);
            } else if (value.length() == TIME_PATTERN.length()) {
                return new SimpleDateFormat(TIME_PATTERN).parse(value);
            }
            return null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Es wurde ein falches Muster verwendet: TT.MM.JJJJ SS:MM");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convertToPresentation(Date value, Class<? extends String> targetType, Locale locale) throws ConversionException {
        
        if (value == null) {
            return "";
        }
        
        return new SimpleDateFormat(TIME_PATTERN).format(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Date> getModelType() {
        return Date.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }

}
