
package org.w3._2001.xmlschema;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for formChoice.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="formChoice"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
 *     &lt;enumeration value="qualified"/&gt;
 *     &lt;enumeration value="unqualified"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "formChoice")
@XmlEnum
@Generated(value = "com.sun.tools.xjc.Driver", date = "2020-10-22T09:44:47+02:00", comments = "JAXB RI v2.3.2")
public enum FormChoice {

    @XmlEnumValue("qualified")
    QUALIFIED("qualified"),
    @XmlEnumValue("unqualified")
    UNQUALIFIED("unqualified");
    private final String value;

    FormChoice(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FormChoice fromValue(String v) {
        for (FormChoice c: FormChoice.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
