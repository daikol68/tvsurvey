/**
 * $Id: AddressManagerQualifier.java 3353 2015-09-24 14:45:33Z d.kowol $
 *
 * Copyright (c) 2015 Deutsche Post Direkt GmbH
 *
 * http://www.postdirekt.de
 */
package de.daikol.tvsurvey.backend.service.qualifier;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Qualifier annotated class to provide injectable resources.
 * 
 * @author Roman Reinert (r.reinert@postdirekt.de)
 * @version $Revision: 3353 $
 */
@Qualifier
@Retention(RUNTIME)
@Target({ TYPE, METHOD, FIELD, PARAMETER })
public @interface SurveyManagerQualifier {
    // just used as qualifier.
}
