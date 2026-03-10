
package com.axonivy.connectivity.createorder.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.axonivy.connectivity.createorder.client package. 
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

    private final static QName _Call_QNAME = new QName("http://connectivity.axonivy.com/", "call");
    private final static QName _CallResponse_QNAME = new QName("http://connectivity.axonivy.com/", "callResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.axonivy.connectivity.createorder.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Call }
     * 
     */
    public Call createCall() {
        return new Call();
    }

    /**
     * Create an instance of {@link CallResponse }
     * 
     */
    public CallResponse createCallResponse() {
        return new CallResponse();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link Task }
     * 
     */
    public Task createTask() {
        return new Task();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Call }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://connectivity.axonivy.com/", name = "call")
    public JAXBElement<Call> createCall(Call value) {
        return new JAXBElement<Call>(_Call_QNAME, Call.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CallResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://connectivity.axonivy.com/", name = "callResponse")
    public JAXBElement<CallResponse> createCallResponse(CallResponse value) {
        return new JAXBElement<CallResponse>(_CallResponse_QNAME, CallResponse.class, null, value);
    }

}
