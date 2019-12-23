package de.daikol.tvsurvey.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Survey.class})
public class ServiceResponse<T> implements Serializable {

    /**
     * <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = 5799532612232007771L;

    /**
     * This attribute is used to determine the Status. This attribute would be used whether a specific intent could be
     * achieved. Getting an Address by UUID is called with the intent to get an Address. Even if no (technical) error
     * occurs such a call may a) return an Address with the given UUID, as one exists in reference b) return nothing
     * because such Address with given UUID does not exist or c) return multiple Addresses if each have the given UUID
     * (theoretical).
     * 
     * As each possibility would return an instance of RemoteCallResult, this attribute can be used as an indication for
     * the calling application how to proceed.
     */
    @XmlAttribute
    private ServiceStatusType status;

    /**
     * The date of the call. The date should be generated when creating the Status right at the beginning of the call.
     */
    @XmlAttribute
    private long startTime;

    /**
     * This attribute is used to determine the time in milliseconds the system needed from receiving the message to
     * responding. After receiving a message a Status should be created. The date could be used at the end of the call
     * to calculate the time.
     */
    @XmlAttribute
    private long responseTime;

    /**
     * The message that is used to describe the reason of the status (e.g. The BusinessPartyAddress could not be created
     * due to missing BusinessParty for UUID [ajköl-holkjfsad-flhsjkdn]). The message should be checked especially
     * whenever an error occured.
     * 
     * Never the less the calling application should first check whether a message exists, as in some cases this message
     * might be missing.
     */
    @XmlElement
    private String message;

    /**
     * The responses of the call. The responses might be null if the call does not return any further information than
     * the status.
     */
    @XmlElementWrapper
    @XmlAnyElement(lax = true)
    private List<T> results;

    /**
     * Constructor for a new RemoteCallResult.
     */
    public ServiceResponse() {
        // Bean
    }

    /**
     * This method is a getter for the member <code>status</code>.
     * 
     * @return Returns the member {@link ServiceResponse#status}.
     */
    public ServiceStatusType getStatus() {
        return this.status;
    }

    /**
     * This method is a setter for the member <code>status</code>.
     *
     * @param status
     *            The new value to be used for {@link ServiceResponse#status}.
     */
    public void setStatus(ServiceStatusType status) {
        this.status = status;
    }

    /**
     * This method is a getter for the member <code>startTime</code>.
     * 
     * @return Returns the member {@link ServiceResponse#startTime}.
     */
    public long getStartTime() {
        return this.startTime;
    }

    /**
     * This method is a setter for the member <code>startTime</code>.
     *
     * @param startTime
     *            The new value to be used for {@link ServiceResponse#startTime}.
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * This method is a getter for the member <code>responseTime</code>.
     * 
     * @return Returns the member {@link ServiceResponse#responseTime}.
     */
    public long getResponseTime() {
        return this.responseTime;
    }

    /**
     * This method is a setter for the member <code>responseTime</code>.
     *
     * @param responseTime
     *            The new value to be used for {@link ServiceResponse#responseTime}.
     */
    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    /**
     * This method is a getter for the member <code>message</code>.
     * 
     * @return Returns the member {@link ServiceResponse#message}.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * This method is a setter for the member <code>message</code>.
     *
     * @param message
     *            The new value to be used for {@link ServiceResponse#message}.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * This method is a getter for the member <code>results</code>.
     * 
     * @return Returns the member {@link ServiceResponse#results}.
     */
    public List<T> getResults() {
        return this.results;
    }

    /**
     * This method is a setter for the member <code>results</code>.
     *
     * @param results
     *            The new value to be used for {@link ServiceResponse#results}.
     */
    public void setResults(List<T> results) {
        this.results = results;
    }

    /**
     * This method is used for simple access a single object.
     *
     * @return The first result if such exists.
     */
    public T getSingleResult() {
        if (this.results != null && !this.results.isEmpty()) {
            return this.results.get(0);
        }
        return null;
    }

    /**
     * This method is used to set a single result.
     *
     * @param result
     *            The result to be set.
     */
    public void setSingleResult(T result) {

        if (this.results == null) {
            this.results = new ArrayList<T>(1);
        }

        this.results.add(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).
            append("status", getStatus()).
            append("startTime", getStartTime()).
            append("responseTime", getResponseTime()).
            append("message", getMessage()).
            append("results", getResults()).
            toString();
    }

    /**
     * {@inheritDoc}
     * 
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(getStatus());
        builder.append(getStartTime());
        builder.append(getResponseTime());
        builder.append(getMessage());
        builder.append(getResults());
        return builder.toHashCode();
    }

    /**
     * {@inheritDoc}
     *
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object obj) {
        // null check
        if (obj == null) {
            return false;
        }

        if (obj instanceof ServiceResponse) {
            ServiceResponse<?> other = (ServiceResponse<?>) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(getStatus(), other.getStatus());
            builder.append(getStartTime(), other.getStartTime());
            builder.append(getResponseTime(), other.getResponseTime());
            builder.append(getMessage(), other.getMessage());
            builder.append(getResults(), other.getResults());
            return builder.isEquals();
        }
        return false;
    }

}