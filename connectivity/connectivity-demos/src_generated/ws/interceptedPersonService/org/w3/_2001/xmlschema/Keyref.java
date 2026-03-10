
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
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.w3.org/2001/XMLSchema}keybase"&gt;
 *       &lt;attribute name="refer" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "keyref")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
public class Keyref
    extends Keybase
    implements Serializable
{

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    private final static long serialVersionUID = 1L;
    @XmlAttribute(name = "refer", required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    protected QName refer;

    /**
     * Gets the value of the refer property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public QName getRefer() {
        return refer;
    }

    /**
     * Sets the value of the refer property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public void setRefer(QName value) {
        this.refer = value;
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
