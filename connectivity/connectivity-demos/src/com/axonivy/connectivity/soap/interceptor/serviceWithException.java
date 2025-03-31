package com.axonivy.connectivity.soap.interceptor;

@SuppressWarnings("all")
@javax.annotation.processing.Generated(comments = "This class implements the WS endpoints of the process 'serviceWithException'. The class can be customized by moving it to the src-folder. See Designer Guide for more information.", value = {
    "ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessClassBuilder"})
@javax.jws.WebService
@org.apache.cxf.interceptor.OutFaultInterceptors(classes = {ErrorResponseInterceptor.class})
public class serviceWithException
    extends ch.ivyteam.ivy.webservice.process.restricted.AbstractWebServiceProcess {

  public serviceWithException(
      ch.ivyteam.ivy.webservice.process.restricted.IWebServiceProcessBeanRuntime webServiceProcessBeanRuntime) {
    super(webServiceProcessBeanRuntime);
  }

  @javax.jws.WebMethod
  @javax.jws.WebResult(name = "exceptionMessage")
  public java.lang.String call(@javax.jws.WebParam(name = "throwException") java.lang.Boolean throwException,
      @javax.jws.WebParam(name = "exceptionMessage") java.lang.String exceptionMessage)
      throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException,
      ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessBusinessException {
    java.util.Map<String, Object> params = new java.util.HashMap<>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;

    params.put("throwException", throwException);
    params.put("exceptionMessage", exceptionMessage);

    processResult = executeProcessWithUserException("call(Boolean,String)", params);

    return (java.lang.String) getTupleField(processResult, "exceptionMessage");
  }

}
