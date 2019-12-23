package de.daikol.tvsurvey.api;

import de.daikol.tvsurvey.model.ServiceResponse;
import de.daikol.tvsurvey.model.Survey;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Der SurveyManager wird verwendet um die Operationen für eine Umfrage zu bieten.
 */
@Path("/")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public interface ISurveyManager {

    /**
     * Diese Methode listet alle Umfragen auf. Dabei werden die gefiltert die nicht im Gültigskeitszeitraum liegen.
     * @return Die Umfragen die im Gültigkeitszeitraum liegen.
     */
    @Path("listSurveys")
    @GET
    public ServiceResponse<Survey> listSurveys();

    /**
     * Diese Methode liefert alle Umfragen.
     * @return Alle Umfragen.
     */
    @Path("getSurveys")
    @GET
    public ServiceResponse<Survey> getSurveys();

    /**
     * Mit dieser Methode wird eine Umfrage erzeugt.
     * @param survey Die Umfrage die erzeugt werden soll.
     * @return Die Antwort.
     */
    @Path("createSurvey")
    @POST
    public ServiceResponse<Survey> createSurvey(Survey survey);

    /**
     * Diese Methode liefert die Umfrage mit der angegebenen ID.
     * @param id Die ID.
     * @return Die Antwort.
     */
    @Path("readSurvey")
    @GET
    public ServiceResponse<Survey> readSurvey(@QueryParam("id") Long id);

    /**
     * Mit dieser Methode wird eine Umfrage geupdatet.
     * @param survey Die Umfrage.
     * @return Die Antwort.
     */
    @Path("updateSurvey")
    @POST
    public ServiceResponse<Survey> updateSurvey(Survey survey);

    /**
     * Mit dieser Methode wird die Umfrage mit der angegebenen ID gelöscht.
     * @param id Die ID.
     * @return Die Antwort.
     */
    @Path("deleteSurvey")
    @DELETE
    public ServiceResponse<Survey> deleteSurvey(@QueryParam("id") Long id);

}
