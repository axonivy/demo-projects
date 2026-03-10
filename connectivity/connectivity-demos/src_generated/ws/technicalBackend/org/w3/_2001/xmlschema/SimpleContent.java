
package org.w3._2001.xmlschema;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.w3.org/2001/XMLSchema}annotated"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="restriction" type="{http://www.w3.org/2001/XMLSchema}simpleRestrictionType"/&gt;
 *         &lt;element name="extension" type="{http://www.w3.org/2001/XMLSchema}simpleExtensionType"/&gt;
 *       &lt;/choice&gt;
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
    "restriction",
    "extension"
})
@XmlRootElement(name = "simpleContent")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
public class SimpleContent
    extends Annotated
    implements Serializable
{

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    private final static long serialVersionUID = 1L;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    protected SimpleRestrictionType restriction;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    protected SimpleExtensionType extension;

    /**
     * Gets the value of the restriction property.
     * 
     * @return
     *     possible object is
     *     {@link SimpleRestrictionType }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public SimpleRestrictionType getRestriction() {
        return restriction;
    }

    /**
     * Sets the value of the restriction property.
     * 
     * @param value
     *     allowed object is
     *     {@link SimpleRestrictionType }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public void setRestriction(SimpleRestrictionType value) {
        this.restriction = value;
    }

    /**
     * Gets the value of the extension property.
     * 
     * @return
     *     possible object is
     *     {@link SimpleExtensionType }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public SimpleExtensionType getExtension() {
        return extension;
    }

    /**
     * Sets the value of the extension property.
     * 
     * @param value
     *     allowed object is
     *     {@link SimpleExtensionType }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public void setExtension(SimpleExtensionType value) {
        this.extension = value;
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
