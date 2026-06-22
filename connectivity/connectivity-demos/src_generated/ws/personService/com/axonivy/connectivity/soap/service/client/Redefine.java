
package com.axonivy.connectivity.soap.service.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.Generated;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
 *     &lt;extension base="{http://www.w3.org/2001/XMLSchema}openAttrs"&gt;
 *       &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *         &lt;element ref="{http://www.w3.org/2001/XMLSchema}annotation"/&gt;
 *         &lt;group ref="{http://www.w3.org/2001/XMLSchema}redefinable"/&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="schemaLocation" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
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
    "annotationOrSimpleTypeOrComplexType"
})
@XmlRootElement(name = "redefine")
@Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v2.3.5", date = "2026-06-17T07:38:27+02:00")
public class Redefine
    extends OpenAttrs
    implements Serializable
{

    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v2.3.5", date = "2026-06-17T07:38:27+02:00")
    private final static long serialVersionUID = 1L;
    @XmlElements({
        @XmlElement(name = "annotation", type = Annotation.class),
        @XmlElement(name = "simpleType", type = TopLevelSimpleType.class),
        @XmlElement(name = "complexType", type = TopLevelComplexType.class),
        @XmlElement(name = "group", type = NamedGroup.class),
        @XmlElement(name = "attributeGroup", type = NamedAttributeGroup.class)
    })
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v2.3.5", date = "2026-06-17T07:38:27+02:00")
    protected List<OpenAttrs> annotationOrSimpleTypeOrComplexType;
    @XmlAttribute(name = "schemaLocation", required = true)
    @XmlSchemaType(name = "anyURI")
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v2.3.5", date = "2026-06-17T07:38:27+02:00")
    protected String schemaLocation;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v2.3.5", date = "2026-06-17T07:38:27+02:00")
    protected String id;

    /**
     * Gets the value of the annotationOrSimpleTypeOrComplexType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the annotationOrSimpleTypeOrComplexType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnnotationOrSimpleTypeOrComplexType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Annotation }
     * {@link NamedAttributeGroup }
     * {@link NamedGroup }
     * {@link TopLevelComplexType }
     * {@link TopLevelSimpleType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v2.3.5", date = "2026-06-17T07:38:27+02:00")
    public List<OpenAttrs> getAnnotationOrSimpleTypeOrComplexType() {
        if (annotationOrSimpleTypeOrComplexType == null) {
            annotationOrSimpleTypeOrComplexType = new ArrayList<OpenAttrs>();
        }
        return this.annotationOrSimpleTypeOrComplexType;
    }

    /**
     * Gets the value of the schemaLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v2.3.5", date = "2026-06-17T07:38:27+02:00")
    public String getSchemaLocation() {
        return schemaLocation;
    }

    /**
     * Sets the value of the schemaLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v2.3.5", date = "2026-06-17T07:38:27+02:00")
    public void setSchemaLocation(String value) {
        this.schemaLocation = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v2.3.5", date = "2026-06-17T07:38:27+02:00")
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
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v2.3.5", date = "2026-06-17T07:38:27+02:00")
    public void setId(String value) {
        this.id = value;
    }

    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v2.3.5", date = "2026-06-17T07:38:27+02:00")
    public void setAnnotationOrSimpleTypeOrComplexType(List<OpenAttrs> value) {
        this.annotationOrSimpleTypeOrComplexType = null;
        if (value!= null) {
            List<OpenAttrs> draftl = this.getAnnotationOrSimpleTypeOrComplexType();
            draftl.addAll(value);
        }
    }

    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v2.3.5", date = "2026-06-17T07:38:27+02:00")
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v2.3.5", date = "2026-06-17T07:38:27+02:00")
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v2.3.5", date = "2026-06-17T07:38:27+02:00")
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
