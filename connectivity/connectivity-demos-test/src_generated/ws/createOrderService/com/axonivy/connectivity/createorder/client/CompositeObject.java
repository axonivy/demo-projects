
package com.axonivy.connectivity.createorder.client;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for compositeObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="compositeObject">
 *   &lt;complexContent>
 *     &lt;extension base="{http://connectivity.axonivy.com/}nestedObject">
 *       &lt;sequence>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "compositeObject")
@XmlSeeAlso({
    Order.class,
    Task.class
})
public abstract class CompositeObject
    extends NestedObject
    implements Serializable
{

    private final static long serialVersionUID = 1L;

}
