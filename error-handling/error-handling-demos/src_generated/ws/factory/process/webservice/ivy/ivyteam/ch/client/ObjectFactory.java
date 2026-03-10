
package process.webservice.ivy.ivyteam.ch.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the process.webservice.ivy.ivyteam.ch.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TechnicalException_QNAME = new QName("ch.ivyteam.ivy.webservice.process", "TechnicalException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: process.webservice.ivy.ivyteam.ch.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "ch.ivyteam.ivy.webservice.process", name = "TechnicalException")
    public JAXBElement<String> createTechnicalException(String value) {
        return new JAXBElement<String>(_TechnicalException_QNAME, String.class, null, value);
    }

}
