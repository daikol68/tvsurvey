package de.daikol.tvsurvey.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by daikol on 15.05.2016.
 */
// XML ANNOTATIONS
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
// JPA ANNOTATIONS
@Entity
@Table(name = "TB_SURVEY")
@NamedQueries({
        @NamedQuery(name = "Survey.getAll", query = "SELECT s FROM Survey s"),
        @NamedQuery(name = "Survey.getById", query = "SELECT s FROM Survey s where s.id = :id"),
        @NamedQuery(name = "Survey.getByName", query = "SELECT s FROM Survey s where s.name = :name"),
        @NamedQuery(name = "Survey.getBySender", query = "SELECT s FROM Survey s where s.sender = :sender")
})
public class Survey implements Serializable {

    /**
     * <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = -5385852611818649133L;

    /**
     * The ID is used as technical key to identify the entity in database.
     */
    // JPA ANNOTATIONS
    @Id
    @SequenceGenerator(name = "SURVEY_SEQUENCE_GENERATOR", sequenceName = "SEQ_SURVEY", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SURVEY_SEQUENCE_GENERATOR")
    @Column(name = "ID")
    @XmlAttribute
    private long id;

    /**
     * The name of the sender.
     */
    @Column(name = "SENDER", nullable = false)
    @XmlElement
    private String sender;

    /**
     * The name of the survey.
     */
    @Column(name = "NAME", nullable = false)
    @XmlElement
    private String name;

    /**
     * The fee for sending one of the categories.
     */
    @Column(name = "FEE", nullable = false)
    @XmlElement
    private float fee;

    /**
     * The question of the survey.
     */
    @Column(name = "QUESTION", nullable = false)
    @XmlElement
    private String question;

    /**
     * The link to the http page.
     */
    @Column(name = "HTTP_LINK")
    @XmlElement
    private String httpLink;

    /**
     * The time the survey is valid from.
     */
    @Column(name = "VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    @XmlElement
    private Date validFrom;

    /**
     * The time the survey is valid to.
     */
    @Column(name = "VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    @XmlElement
    private Date validTo;

    /**
     * The categories from which the user can choose.
     */
    @XmlElementWrapper
    @XmlElement(name = "category", type = Category.class)
    // JPA ANNOTATIONS
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "SURVEY_FK", nullable = false)
    private List<Category> categories;

    /**
     * Constructor.
     */
    public Survey() {
        // nothing to do
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getHttpLink() {
        return httpLink;
    }

    public void setHttpLink(String httpLink) {
        this.httpLink = httpLink;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Survey survey = (Survey) o;

        if (id != survey.id) return false;
        if (Float.compare(survey.fee, fee) != 0) return false;
        if (sender != null ? !sender.equals(survey.sender) : survey.sender != null) return false;
        if (name != null ? !name.equals(survey.name) : survey.name != null) return false;
        if (question != null ? !question.equals(survey.question) : survey.question != null) return false;
        if (httpLink != null ? !httpLink.equals(survey.httpLink) : survey.httpLink != null) return false;
        if (validFrom != null ? !validFrom.equals(survey.validFrom) : survey.validFrom != null) return false;
        if (validTo != null ? !validTo.equals(survey.validTo) : survey.validTo != null) return false;
        return categories != null ? categories.equals(survey.categories) : survey.categories == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (fee != +0.0f ? Float.floatToIntBits(fee) : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (httpLink != null ? httpLink.hashCode() : 0);
        result = 31 * result + (validFrom != null ? validFrom.hashCode() : 0);
        result = 31 * result + (validTo != null ? validTo.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", name='" + name + '\'' +
                ", fee=" + fee +
                ", question='" + question + '\'' +
                ", httpLink='" + httpLink + '\'' +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                ", categories=" + categories +
                '}';
    }
}
