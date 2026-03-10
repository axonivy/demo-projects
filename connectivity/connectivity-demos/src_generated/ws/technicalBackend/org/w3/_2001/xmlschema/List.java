
package org.w3._2001.xmlschema;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 
 *           itemType attribute and simpleType child are mutually
 *           exclusive, but one or other is required
 *         
 * 
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.w3.org/2001/XMLSchema}annotated"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="simpleType" type="{http://www.w3.org/2001/XMLSchema}localSimpleType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="itemType" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "simpleType"
})
@XmlRootElement(name = "list")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
public class List
    extends Annotated
    implements Serializable
{

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    private final static long serialVersionUID = 1L;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    protected LocalSimpleType simpleType;
    @XmlAttribute(name = "itemType")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    protected QName itemType;

    /**
     * Gets the value of the simpleType property.
     * 
     * @return
     *     possible object is
     *     {@link LocalSimpleType }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public LocalSimpleType getSimpleType() {
        return simpleType;
    }

    /**
     * Sets the value of the simpleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalSimpleType }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public void setSimpleType(LocalSimpleType value) {
        this.simpleType = value;
    }

    /**
     * Gets the value of the itemType property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public QName getItemType() {
        return itemType;
    }

    /**
     * Sets the value of the itemType property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public void setItemType(QName value) {
        this.itemType = value;
    }

    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
