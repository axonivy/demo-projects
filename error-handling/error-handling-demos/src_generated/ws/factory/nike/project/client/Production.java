
package nike.project.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * <p>Java class for production complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="production"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://project.nike/}compositeObject"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="shoes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "production", propOrder = {
    "shoes"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2020-01-28T02:02:24+01:00", comments = "JAXB RI v2.3.2")
public class Production
    extends CompositeObject
    implements Serializable
{

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-01-28T02:02:24+01:00", comments = "JAXB RI v2.3.2")
    private final static long serialVersionUID = 1L;
    @XmlElement(nillable = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-01-28T02:02:24+01:00", comments = "JAXB RI v2.3.2")
    protected List<String> shoes;

    /**
     * Gets the value of the shoes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shoes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShoes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-01-28T02:02:24+01:00", comments = "JAXB RI v2.3.2")
    public List<String> getShoes() {
        if (shoes == null) {
            shoes = new ArrayList<String>();
        }
        return this.shoes;
    }

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-01-28T02:02:24+01:00", comments = "JAXB RI v2.3.2")
    public void setShoes(List<String> value) {
        this.shoes = null;
        if (value!= null) {
            List<String> draftl = this.getShoes();
            draftl.addAll(value);
        }
    }

    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-01-28T02:02:24+01:00", comments = "JAXB RI v2.3.2")
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-01-28T02:02:24+01:00", comments = "JAXB RI v2.3.2")
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2020-01-28T02:02:24+01:00", comments = "JAXB RI v2.3.2")
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
