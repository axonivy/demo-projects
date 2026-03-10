
package nike.project.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the nike.project.client package. 
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

    private final static QName _Produce_QNAME = new QName("http://project.nike/", "produce");
    private final static QName _ProduceResponse_QNAME = new QName("http://project.nike/", "produceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: nike.project.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Produce }
     * 
     */
    public Produce createProduce() {
        return new Produce();
    }

    /**
     * Create an instance of {@link ProduceResponse }
     * 
     */
    public ProduceResponse createProduceResponse() {
        return new ProduceResponse();
    }

    /**
     * Create an instance of {@link Production }
     * 
     */
    public Production createProduction() {
        return new Production();
    }

    /**
     * Create an instance of {@link List }
     * 
     */
    public List createList() {
        return new List();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Produce }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Produce }{@code >}
     */
    @XmlElementDecl(namespace = "http://project.nike/", name = "produce")
    public JAXBElement<Produce> createProduce(Produce value) {
        return new JAXBElement<Produce>(_Produce_QNAME, Produce.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProduceResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ProduceResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://project.nike/", name = "produceResponse")
    public JAXBElement<ProduceResponse> createProduceResponse(ProduceResponse value) {
        return new JAXBElement<ProduceResponse>(_ProduceResponse_QNAME, ProduceResponse.class, null, value);
    }

}
