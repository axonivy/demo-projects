package com.axonivy.connectivity.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.media.multipart.Boundary;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class IntegrationTestFileUpload {

  @Test
  public void checkingRealPdf() throws IOException {
    String fileName = "test.pdf";
    File realPdf = new File(fileName);
    try (InputStream pdf = this.getClass().getResourceAsStream(fileName);
        OutputStream os = new FileOutputStream(realPdf)) {
      IOUtils.copy(pdf, os);
    }
    Response pdfResponse = uploadPdf(realPdf);
    assertThat(pdfResponse.getStatus()).isEqualTo(Status.CREATED.getStatusCode());

    realPdf.delete();
    String uri = EngineUrl.createRestUrl("/file/" + fileName);
    Response downloadResponse = createAuthenticatedClient()
        .target(uri)
        .request().accept(MediaType.APPLICATION_OCTET_STREAM).get();
    assertThat(downloadResponse.getStatus()).isEqualTo(Status.OK.getStatusCode());
    try (InputStream target = this.getClass().getResourceAsStream(fileName);
        InputStream restStream = downloadResponse.readEntity(InputStream.class)) {
      byte[] received = IOUtils.toByteArray(restStream);
      byte[] expected = IOUtils.toByteArray(target);
      assertThat(received).isEqualTo(expected);
    }
  }

  @Test
  public void apacheConnectorProvider() throws IOException {
    String fileName = "test.pdf";
    String uri = EngineUrl.createRestUrl("/file");

    File realPdf = new File(fileName);
    try (InputStream pdf = this.getClass().getResourceAsStream(fileName);
        OutputStream os = new FileOutputStream(realPdf)) {
      IOUtils.copy(pdf, os);
    }

    ClientConfig clientConfig = new ClientConfig();
    clientConfig.connectorProvider(new ApacheConnectorProvider());
    Client client = createClientCustom(clientConfig);

    MediaType contentType = MediaType.MULTIPART_FORM_DATA_TYPE;
    contentType = Boundary.addBoundary(contentType);

    Response apacheConnectorResponse = client
        .target(uri).request()
        .header("X-Requested-By", "ivy")
        .put(Entity.entity(createMultipart(realPdf), contentType));
    assertThat(apacheConnectorResponse.getStatus()).isEqualTo(Status.CREATED.getStatusCode());
    realPdf.delete();
  }

  @Test
  public void httpUrlConnectorProvider() throws IOException {
    String fileName = "test.pdf";
    String uri = EngineUrl.createRestUrl("/file");

    File realPdf = new File(fileName);
    try (InputStream pdf = this.getClass().getResourceAsStream(fileName);
        OutputStream os = new FileOutputStream(realPdf)) {
      IOUtils.copy(pdf, os);
    }

    ClientConfig clientConfig = new ClientConfig();
    clientConfig.connectorProvider(new HttpUrlConnectorProvider());
    Client client = createClientCustom(clientConfig);

    Response httpUrlConnectorResponse = client
        .target(uri).request()
        .header("X-Requested-By", "ivy")
        .put(Entity.entity(createMultipart(realPdf), MediaType.MULTIPART_FORM_DATA_TYPE));
    assertThat(httpUrlConnectorResponse.getStatus()).isEqualTo(Status.CREATED.getStatusCode());
    realPdf.delete();
  }

  @Test
  public void sendPdfFile() throws IOException {
    java.io.File createWrongEmptyFile = createTempFile("test", ".pdf");
    Response pdfResponse = uploadPdf(createWrongEmptyFile);
    assertThat(pdfResponse.getStatus()).isEqualTo(Status.CREATED.getStatusCode());
    assertThat(pdfResponse.readEntity(String.class)).endsWith(createWrongEmptyFile.getName());
  }

  @Test
  public void fileWrongExtension() throws IOException {
    java.io.File createWrongEmptyFile = createTempFile("empty", ".docx");
    Response notPdfResponse = uploadPdf(createWrongEmptyFile);
    assertThat(notPdfResponse.getStatus()).isEqualTo(Status.INTERNAL_SERVER_ERROR.getStatusCode());
  }

  private static Response uploadPdf(java.io.File createWrongEmptyFile) throws IOException {
    String uri = EngineUrl.createRestUrl("/file");
    return createAuthenticatedClient()
        .target(uri).request()
        .header("X-Requested-By", "ivy")
        .header("MIME-Version", "1.0")
        .put(Entity.entity(createMultipart(createWrongEmptyFile), MediaType.MULTIPART_FORM_DATA_TYPE));
  }

  private static FormDataMultiPart createMultipart(java.io.File createWrongEmptyFile) throws IOException {
    FormDataMultiPart multipart;
    try (FormDataMultiPart formDataMultiPart = new FormDataMultiPart()) {
      FileDataBodyPart filePart = new FileDataBodyPart("file", createWrongEmptyFile);
      multipart = (FormDataMultiPart) formDataMultiPart.bodyPart(filePart);
    }
    return multipart;
  }

  private static java.io.File createTempFile(String fileName, String extension) throws IOException {
    return java.nio.file.Files.createTempFile(fileName, extension).toFile();
  }

  private static final String login = "restUser";

  private static Client createAuthenticatedClient() {
    Client httpClient = createClient();
    httpClient.register(HttpAuthenticationFeature.basic(login, login));
    return httpClient;
  }

  private static Client createClient() {
    Client httpClient = ClientBuilder.newClient();
    httpClient.register(JacksonJsonProvider.class);
    httpClient.register(MultiPartFeature.class);
    return httpClient;
  }

  private static Client createClientCustom(ClientConfig config) {
    Client httpClient = ClientBuilder.newClient(config);
    httpClient.register(JacksonJsonProvider.class);
    httpClient.register(MultiPartFeature.class);
    httpClient.register(HttpAuthenticationFeature.basic(login, login));
    return httpClient;
  }
}
