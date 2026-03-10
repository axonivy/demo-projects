
package com.axonivy.connectivity.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.axonivy.connectivity.client package. 
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

    private final static QName _LongRunningBatchOperation_QNAME = new QName("http://connectivity.axonivy.com/", "longRunningBatchOperation");
    private final static QName _LongRunningBatchOperationResponse_QNAME = new QName("http://connectivity.axonivy.com/", "longRunningBatchOperationResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.axonivy.connectivity.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LongRunningBatchOperation }
     * 
     */
    public LongRunningBatchOperation createLongRunningBatchOperation() {
        return new LongRunningBatchOperation();
    }

    /**
     * Create an instance of {@link LongRunningBatchOperationResponse }
     * 
     */
    public LongRunningBatchOperationResponse createLongRunningBatchOperationResponse() {
        return new LongRunningBatchOperationResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LongRunningBatchOperation }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LongRunningBatchOperation }{@code >}
     */
    @XmlElementDecl(namespace = "http://connectivity.axonivy.com/", name = "longRunningBatchOperation")
    public JAXBElement<LongRunningBatchOperation> createLongRunningBatchOperation(LongRunningBatchOperation value) {
        return new JAXBElement<LongRunningBatchOperation>(_LongRunningBatchOperation_QNAME, LongRunningBatchOperation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LongRunningBatchOperationResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LongRunningBatchOperationResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://connectivity.axonivy.com/", name = "longRunningBatchOperationResponse")
    public JAXBElement<LongRunningBatchOperationResponse> createLongRunningBatchOperationResponse(LongRunningBatchOperationResponse value) {
        return new JAXBElement<LongRunningBatchOperationResponse>(_LongRunningBatchOperationResponse_QNAME, LongRunningBatchOperationResponse.class, null, value);
    }

}
