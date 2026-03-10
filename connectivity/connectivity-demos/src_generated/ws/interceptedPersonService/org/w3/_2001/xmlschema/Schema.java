
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
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
 *       &lt;sequence&gt;
 *         &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;element ref="{http://www.w3.org/2001/XMLSchema}include"/&gt;
 *           &lt;element ref="{http://www.w3.org/2001/XMLSchema}import"/&gt;
 *           &lt;element ref="{http://www.w3.org/2001/XMLSchema}redefine"/&gt;
 *           &lt;element ref="{http://www.w3.org/2001/XMLSchema}annotation"/&gt;
 *         &lt;/choice&gt;
 *         &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;group ref="{http://www.w3.org/2001/XMLSchema}schemaTop"/&gt;
 *           &lt;element ref="{http://www.w3.org/2001/XMLSchema}annotation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;/sequence&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="targetNamespace" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}normalizedString" /&gt;
 *       &lt;attribute name="finalDefault" type="{http://www.w3.org/2001/XMLSchema}derivationSet" default="" /&gt;
 *       &lt;attribute name="blockDefault" type="{http://www.w3.org/2001/XMLSchema}blockSet" default="" /&gt;
 *       &lt;attribute name="attributeFormDefault" type="{http://www.w3.org/2001/XMLSchema}formChoice" default="unqualified" /&gt;
 *       &lt;attribute name="elementFormDefault" type="{http://www.w3.org/2001/XMLSchema}formChoice" default="unqualified" /&gt;
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang"/&gt;
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
    "includeOrImportOrRedefine",
    "simpleTypeOrComplexTypeOrGroup"
})
@XmlRootElement(name = "schema")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
public class Schema
    extends OpenAttrs
    implements Serializable
{

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    private final static long serialVersionUID = 1L;
    @XmlElements({
        @XmlElement(name = "include", type = Include.class),
        @XmlElement(name = "import", type = Import.class),
        @XmlElement(name = "redefine", type = Redefine.class),
        @XmlElement(name = "annotation", type = Annotation.class)
    })
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    protected List<OpenAttrs> includeOrImportOrRedefine;
    @XmlElements({
        @XmlElement(name = "simpleType", type = TopLevelSimpleType.class),
        @XmlElement(name = "complexType", type = TopLevelComplexType.class),
        @XmlElement(name = "group", type = NamedGroup.class),
        @XmlElement(name = "attributeGroup", type = NamedAttributeGroup.class),
        @XmlElement(name = "element", type = TopLevelElement.class),
        @XmlElement(name = "attribute", type = TopLevelAttribute.class),
        @XmlElement(name = "notation", type = Notation.class),
        @XmlElement(name = "annotation", type = Annotation.class)
    })
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    protected List<OpenAttrs> simpleTypeOrComplexTypeOrGroup;
    @XmlAttribute(name = "targetNamespace")
    @XmlSchemaType(name = "anyURI")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    protected String targetNamespace;
    @XmlAttribute(name = "version")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    protected String version;
    @XmlAttribute(name = "finalDefault")
    @XmlSchemaType(name = "derivationSet")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    protected List<String> finalDefault;
    @XmlAttribute(name = "blockDefault")
    @XmlSchemaType(name = "blockSet")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    protected List<String> blockDefault;
    @XmlAttribute(name = "attributeFormDefault")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    protected FormChoice attributeFormDefault;
    @XmlAttribute(name = "elementFormDefault")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    protected FormChoice elementFormDefault;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    protected String id;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    protected String lang;

    /**
     * Gets the value of the includeOrImportOrRedefine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the includeOrImportOrRedefine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncludeOrImportOrRedefine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Include }
     * {@link Import }
     * {@link Redefine }
     * {@link Annotation }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public List<OpenAttrs> getIncludeOrImportOrRedefine() {
        if (includeOrImportOrRedefine == null) {
            includeOrImportOrRedefine = new ArrayList<OpenAttrs>();
        }
        return this.includeOrImportOrRedefine;
    }

    /**
     * Gets the value of the simpleTypeOrComplexTypeOrGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the simpleTypeOrComplexTypeOrGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSimpleTypeOrComplexTypeOrGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TopLevelSimpleType }
     * {@link TopLevelComplexType }
     * {@link NamedGroup }
     * {@link NamedAttributeGroup }
     * {@link TopLevelElement }
     * {@link TopLevelAttribute }
     * {@link Notation }
     * {@link Annotation }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public List<OpenAttrs> getSimpleTypeOrComplexTypeOrGroup() {
        if (simpleTypeOrComplexTypeOrGroup == null) {
            simpleTypeOrComplexTypeOrGroup = new ArrayList<OpenAttrs>();
        }
        return this.simpleTypeOrComplexTypeOrGroup;
    }

    /**
     * Gets the value of the targetNamespace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public String getTargetNamespace() {
        return targetNamespace;
    }

    /**
     * Sets the value of the targetNamespace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public void setTargetNamespace(String value) {
        this.targetNamespace = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the finalDefault property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the finalDefault property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFinalDefault().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public List<String> getFinalDefault() {
        if (finalDefault == null) {
            finalDefault = new ArrayList<String>();
        }
        return this.finalDefault;
    }

    /**
     * Gets the value of the blockDefault property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the blockDefault property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBlockDefault().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public List<String> getBlockDefault() {
        if (blockDefault == null) {
            blockDefault = new ArrayList<String>();
        }
        return this.blockDefault;
    }

    /**
     * Gets the value of the attributeFormDefault property.
     * 
     * @return
     *     possible object is
     *     {@link FormChoice }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public FormChoice getAttributeFormDefault() {
        if (attributeFormDefault == null) {
            return FormChoice.UNQUALIFIED;
        } else {
            return attributeFormDefault;
        }
    }

    /**
     * Sets the value of the attributeFormDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormChoice }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public void setAttributeFormDefault(FormChoice value) {
        this.attributeFormDefault = value;
    }

    /**
     * Gets the value of the elementFormDefault property.
     * 
     * @return
     *     possible object is
     *     {@link FormChoice }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public FormChoice getElementFormDefault() {
        if (elementFormDefault == null) {
            return FormChoice.UNQUALIFIED;
        } else {
            return elementFormDefault;
        }
    }

    /**
     * Sets the value of the elementFormDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormChoice }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public void setElementFormDefault(FormChoice value) {
        this.elementFormDefault = value;
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
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public void setLang(String value) {
        this.lang = value;
    }

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public void setIncludeOrImportOrRedefine(List<OpenAttrs> value) {
        this.includeOrImportOrRedefine = null;
        if (value!= null) {
            List<OpenAttrs> draftl = this.getIncludeOrImportOrRedefine();
            draftl.addAll(value);
        }
    }

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public void setSimpleTypeOrComplexTypeOrGroup(List<OpenAttrs> value) {
        this.simpleTypeOrComplexTypeOrGroup = null;
        if (value!= null) {
            List<OpenAttrs> draftl = this.getSimpleTypeOrComplexTypeOrGroup();
            draftl.addAll(value);
        }
    }

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public void setFinalDefault(List<String> value) {
        this.finalDefault = null;
        if (value!= null) {
            List<String> draftl = this.getFinalDefault();
            draftl.addAll(value);
        }
    }

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2023-09-13T08:45:29+02:00", comments = "JAXB RI v2.3.2")
    public void setBlockDefault(List<String> value) {
        this.blockDefault = null;
        if (value!= null) {
            List<String> draftl = this.getBlockDefault();
            draftl.addAll(value);
        }
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
