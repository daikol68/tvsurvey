package de.daikol.tvsurvey.frontend.util;

import de.daikol.tvsurvey.api.ISurveyManager;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.*;

/**
 * The factory as dynamic proxy for REST-calls.
 */
public class Factory {

    /**
     * BACKEND_URL.
     */
    private static final String PROPERTY_BACKEND_URL = "tvsurvey-backend.endpoint";

    /**
     * SLF4J-Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(Factory.class);

    /**
     * backendEndpoint.
     */
    private String backendEndpoint;

    /**
     * The survey manager.
     */
    private ISurveyManager surveyManager;

    /**
     * instance.
     */
    static Factory instance;

    /**
     * This method is used to retrieve the singleton instance.
     *
     * @return one and only instance
     */
    public static synchronized Factory instance() {

        if (instance == null) {
            instance = new Factory();
        }

        return instance;
    }

    /**
     * Private constructor for a new Factory.
     *
     */
    private Factory() {

        this.backendEndpoint = System.getProperty(PROPERTY_BACKEND_URL);

        if (this.backendEndpoint == null) {
            throw new IllegalStateException(
                "Notwendige Variable fehlt in der Konfiguration. Bitte folgende Systemvariable setzen: [" + PROPERTY_BACKEND_URL + "]");
        }
        
        logger.info("Using backend endpoint [{}].", this.backendEndpoint);
        this.surveyManager = JAXRSClientFactory.create(this.backendEndpoint, ISurveyManager.class);
    }

    /**
     * This method is used to create a new {@link ISurveyManager}.
     *
     * @return new {@link ISurveyManager}
     */
    public synchronized ISurveyManager surveyManager() {
        return this.surveyManager;
    }
}
