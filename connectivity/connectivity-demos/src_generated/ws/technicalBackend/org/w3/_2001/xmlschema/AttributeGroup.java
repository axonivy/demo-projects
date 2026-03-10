
package org.w3._2001.xmlschema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * <p>Java class for attributeGroup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="attributeGroup"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.w3.org/2001/XMLSchema}annotated"&gt;
 *       &lt;group ref="{http://www.w3.org/2001/XMLSchema}attrDecls"/&gt;
 *       &lt;attGroup ref="{http://www.w3.org/2001/XMLSchema}defRef"/&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "attributeGroup", propOrder = {
    "attributeOrAttributeGroup",
    "anyAttribute"
})
@XmlSeeAlso({
    NamedAttributeGroup.class,
    AttributeGroupRef.class
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
public abstract class AttributeGroup
    extends Annotated
    implements Serializable
{

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    private final static long serialVersionUID = 1L;
    @XmlElements({
        @XmlElement(name = "attribute", type = Attribute.class),
        @XmlElement(name = "attributeGroup", type = AttributeGroupRef.class)
    })
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    protected List<Annotated> attributeOrAttributeGroup;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    protected Wildcard anyAttribute;
    @XmlAttribute(name = "name")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    protected String name;
    @XmlAttribute(name = "ref")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    protected QName ref;

    /**
     * Gets the value of the attributeOrAttributeGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributeOrAttributeGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributeOrAttributeGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Attribute }
     * {@link AttributeGroupRef }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public List<Annotated> getAttributeOrAttributeGroup() {
        if (attributeOrAttributeGroup == null) {
            attributeOrAttributeGroup = new ArrayList<Annotated>();
        }
        return this.attributeOrAttributeGroup;
    }

    /**
     * Gets the value of the anyAttribute property.
     * 
     * @return
     *     possible object is
     *     {@link Wildcard }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public Wildcard getAnyAttribute() {
        return anyAttribute;
    }

    /**
     * Sets the value of the anyAttribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link Wildcard }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public void setAnyAttribute(Wildcard value) {
        this.anyAttribute = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the ref property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public QName getRef() {
        return ref;
    }

    /**
     * Sets the value of the ref property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public void setRef(QName value) {
        this.ref = value;
    }

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
    public void setAttributeOrAttributeGroup(List<Annotated> value) {
        this.attributeOrAttributeGroup = null;
        if (value!= null) {
            List<Annotated> draftl = this.getAttributeOrAttributeGroup();
            draftl.addAll(value);
        }
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
