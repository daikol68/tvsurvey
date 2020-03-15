package de.daikol.tvsurvey.backend.service;

import de.daikol.tvsurvey.api.ISurveyManager;
import de.daikol.tvsurvey.model.Category;
import de.daikol.tvsurvey.model.ServiceResponse;
import de.daikol.tvsurvey.model.ServiceStatusType;
import de.daikol.tvsurvey.model.Survey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by daikol on 23.05.2016.
 */
@Stateless
public class SurveyManager implements ISurveyManager {

    /**
     * SLF4J-Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(SurveyManager.class);

    /**
     * Inject the entity manager provided by resource producer
     */
    @Inject
    EntityManager em;

    @Override
    public ServiceResponse<Survey> listSurveys() {

        ServiceResponse<Survey> response = new ServiceResponse<Survey>();

        logger.trace("Listing all Surveys.");
        List<Survey> results = this.em
                .createNamedQuery("Survey.getAll", Survey.class)
                .getResultList();

        Date now = new Date();

        Iterator<Survey> iterator = results.iterator();
        while (iterator.hasNext()) {

            Survey survey = iterator.next();

            if (survey.getValidFrom() != null && survey.getValidFrom().getTime() > now.getTime()) {
                iterator.remove();
            }
            if (survey.getValidTo() != null && survey.getValidTo().getTime() < now.getTime()) {
                iterator.remove();
            }
            for (Category category : survey.getCategories()) {
                category.getName();
            }
        }

        logger.trace("Returning listed Surveys.");
        response.setStatus(ServiceStatusType.SUCCESS);
        response.setMessage("Surveys listed successfully.");
        response.setResults(results);

        return response;

    }

    @Override
    public ServiceResponse<Survey> getSurveys() {

        ServiceResponse<Survey> response = new ServiceResponse<Survey>();

        logger.trace("Fetching all Surveys.");
        List<Survey> results = this.em
                .createNamedQuery("Survey.getAll", Survey.class)
                .getResultList();

        for (Survey survey : results) {
            for (Category category : survey.getCategories()) {
                category.getName();
            }
        }

        logger.trace("Returning fetched Surveys.");
        response.setStatus(ServiceStatusType.SUCCESS);
        response.setMessage("Surveys retrieved successfully.");
        response.setResults(results);

        return response;

    }

    @Override
    public ServiceResponse<Survey> createSurvey(Survey survey) {

        // create response
        ServiceResponse<Survey> response = new ServiceResponse<>();

        // log creation
        logger.debug("Creating Survey [{}].", survey);

        logger.trace("Removing category ids");
        if (survey.getCategories() == null) {
            throw new IllegalArgumentException("Es muss mindestens eine Antwortmöglichkeit angegeben werden.");
        }
        for (Category category : survey.getCategories()) {
            category.setId(0);
        }

        logger.trace("Persisting Survey.");
        this.em.persist(survey);
        this.em.flush();

        logger.trace("Refreshing Survey.");
        this.em.refresh(survey);

        response.setStatus(ServiceStatusType.SUCCESS);
        response.setMessage("Survey created successfully.");
        response.setSingleResult(survey);

        // return response
        return response;

    }

    @Override
    public ServiceResponse<Survey> readSurvey(Long id) {

        ServiceResponse<Survey> response = new ServiceResponse<Survey>();

        try {

            logger.trace("Fetching Survey with ID [{}].", id);
            Survey result = this.em
                    .createNamedQuery("Survey.getById", Survey.class)
                    .setParameter("id", id).getSingleResult();

            logger.trace("Loading categories.");
            for (Category category : result.getCategories()) {
                category.getName();
            }

            logger.trace("Returning found Survey.");
            response.setStatus(ServiceStatusType.SUCCESS);
            response.setMessage("Survey retrieved successfully.");
            response.setSingleResult(result);

        } catch (NoResultException e) {
            logger.trace("Survey with ID [{}] could not be found.", id);
            response.setStatus(ServiceStatusType.ERROR);
            response.setMessage("Survey with ID [" + id + "] can not be found.");

        }

        return response;

    }

    @Override
    public ServiceResponse<Survey> updateSurvey(Survey survey) {

        ServiceResponse<Survey> response = new ServiceResponse<>();

        logger.trace("Fetching Survey with ID [{}].", survey.getId());
        ServiceResponse<Survey> surveyResponse = readSurvey(survey.getId());
        if (surveyResponse.getStatus() == ServiceStatusType.SUCCESS) {

            // get loaded business party
            Survey loaded = surveyResponse.getSingleResult();

            // detach object to avoid automatic updates
            this.em.detach(loaded);

            // update object
            loaded.setName(survey.getName());
            loaded.setFee(survey.getFee());
            loaded.setHttpLink(survey.getHttpLink());
            loaded.setQuestion(survey.getQuestion());
            loaded.setSender(survey.getSender());
            loaded.setValidFrom(survey.getValidFrom());
            loaded.setValidTo(survey.getValidTo());
            loaded.setCategories(survey.getCategories());

            // merge information
            this.em.merge(loaded);
            this.em.flush();

            response.setStatus(ServiceStatusType.SUCCESS);
            response.setMessage("Survey updated successfully.");
            response.setSingleResult(loaded);

        } else {
            response.setStatus(surveyResponse.getStatus());
            response.setMessage(surveyResponse.getMessage());
        }

        return response;

    }

    @Override
    public ServiceResponse<Survey> deleteSurvey(Long id) {
        logger.debug("Deleting Survey with ID [{}].", id);

        ServiceResponse<Survey> response = new ServiceResponse<Survey>();

        logger.trace("Fetching Survey with ID [{}].", id);
        ServiceResponse<Survey> aResponse = readSurvey(id);
        if (aResponse.getStatus() == ServiceStatusType.SUCCESS) {

            logger.trace("Loaded Survey with ID [{}].", id);
            Survey loaded = aResponse.getSingleResult();

            logger.trace("Deleting Survey with ID [{}].", id);
            this.em.remove(loaded);
            this.em.flush();

            logger.trace("Finalizing ServiceResponse.");
            response.setStatus(ServiceStatusType.SUCCESS);
            response.setMessage("Survey deleted successfully.");
            response.setSingleResult(loaded);

        } else {
            logger.trace("Could not find Survey with ID [{}].", id);
            response.setStatus(aResponse.getStatus());
            response.setMessage(aResponse.getMessage());
        }
        return response;
    }
}
