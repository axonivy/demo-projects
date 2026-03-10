
package org.w3._2001.xmlschema;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * <p>Java class for facet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="facet"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.w3.org/2001/XMLSchema}annotated"&gt;
 *       &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *       &lt;attribute name="fixed" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "facet")
@XmlSeeAlso({
    NoFixedFacet.class,
    WhiteSpace.class,
    NumFacet.class
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
public class Facet
    extends Annotated
    implements Serializable
{

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    private final static long serialVersionUID = 1L;
    @XmlAttribute(name = "value", required = true)
    @XmlSchemaType(name = "anySimpleType")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    protected String value;
    @XmlAttribute(name = "fixed")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    protected Boolean fixed;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the fixed property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public boolean isFixed() {
        if (fixed == null) {
            return false;
        } else {
            return fixed;
        }
    }

    /**
     * Sets the value of the fixed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public void setFixed(Boolean value) {
        this.fixed = value;
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
