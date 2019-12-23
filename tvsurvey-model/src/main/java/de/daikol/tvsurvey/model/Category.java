package de.daikol.tvsurvey.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * Created by daikol on 15.05.2016.
 */
//XML ANNOTATIONS
@XmlAccessorType(XmlAccessType.NONE)
//JPA ANNOTATIONS
@Entity
@Table(name = "TB_CATEGORY")
public class Category implements Serializable {

    /**
     * <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = 71611953697038468L;

    /**
     * The ID is used as technical key to identify the entity in database.
     */
    // JPA ANNOTATIONS
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "CATEGORY_SEQUENCE_GENERATOR", sequenceName = "SEQ_CATEGORY", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQUENCE_GENERATOR")
    @XmlAttribute
    private long id;

    /**
     * The name of the category.
     */
    @Column(name = "NAME", nullable = false)
    @XmlElement
    private String name;

    /**
     * The phone number to be used if sending a sms.
     */
    @Column(name = "PHONE_NUMBER")
    @XmlElement
    private String phoneNumber;

    /**
     * The phone number to be used if sending a sms.
     */
    @Column(name = "SMS_NUMBER")
    @XmlElement
    private String smsNumber;

    /**
     * The value to be used to be putted inside the sms.
     */
    @Column(name = "SMS_VALUE")
    @XmlElement
    private String smsValue;

    /**
     * Public constructor.
     */
    public Category () {
        // nothing to do
    }

    /**
     * Constructor setting all fields.
     *
     * @param name The name of the category.
     * @param smsNumber The phone number.
     * @param smsValue The value for the sms.
     */
    public Category(String name, String smsNumber, String smsValue) {
        this.name = name;
        this.smsNumber = smsNumber;
        this.smsValue = smsValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSmsValue() {
        return smsValue;
    }

    public void setSmsValue(String phoneValue) {
        this.smsValue = phoneValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmsNumber() {
        return smsNumber;
    }

    public void setSmsNumber(String phoneNumber) {
        this.smsNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (id != category.id) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(category.phoneNumber) : category.phoneNumber != null)
            return false;
        if (smsNumber != null ? !smsNumber.equals(category.smsNumber) : category.smsNumber != null) return false;
        return smsValue != null ? smsValue.equals(category.smsValue) : category.smsValue == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (smsNumber != null ? smsNumber.hashCode() : 0);
        result = 31 * result + (smsValue != null ? smsValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", smsNumber='" + smsNumber + '\'' +
                ", smsValue='" + smsValue + '\'' +
                '}';
    }
}
