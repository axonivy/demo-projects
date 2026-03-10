
package soap.intercepted.client.person.ivyteam.ch.client;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * <p>Java class for person complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="person"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="firstname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lastname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "person", propOrder = {
    "firstname",
    "id",
    "lastname"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
public class Person
    implements Serializable
{

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    private final static long serialVersionUID = 1L;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    protected String firstname;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    protected String id;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    protected String lastname;

    /**
     * Gets the value of the firstname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the value of the firstname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public void setFirstname(String value) {
        this.firstname = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the lastname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the value of the lastname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public void setLastname(String value) {
        this.lastname = value;
    }

    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
