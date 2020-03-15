/**
 * $Id: EntityManagerProducer.java 3353 2015-09-24 14:45:33Z d.kowol $
 *
 * Copyright (c) 2015 Deutsche Post Direkt GmbH
 *
 * http://www.postdirekt.de
 */
package de.daikol.tvsurvey.backend.persistence;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This class is used as producer for an Entity Manager.
 * 
 * @author Ralf Wastl (r.wastl@postdirekt.de)
 * @version $Revision: 3353 $
 */
@ApplicationScoped
public class EntityManagerProducer {

    /**
     * The name of the persistence unit that is used.
     */
    public final static String PERSISTENCE_UNIT = "SURVEY-PU";
    
    /**
     * The Producer.
     */
    @Produces
    @PersistenceContext(unitName = PERSISTENCE_UNIT)
    private EntityManager em;

}
