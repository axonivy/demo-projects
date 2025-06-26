package com.axonivy.connectivity.rest.client.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.media.multipart.Boundary;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

/**
 * <p>
 * See <b>Processes/rest/fileUpload/fileClient.ivp</b> for a demo
 * </p>
 *
 * @author jla
 * @since 7.4.0
 */
public class FileUpload {
  public static Response upload(WebTarget target, java.io.File file) throws IOException {
    FormDataMultiPart multipart;
    try (FormDataMultiPart formDataMultiPart = new FormDataMultiPart()) {
      FileDataBodyPart filePart = new FileDataBodyPart("file", file);
      multipart = (FormDataMultiPart) formDataMultiPart.bodyPart(filePart);
    }
    MediaType contentType = MediaType.MULTIPART_FORM_DATA_TYPE;
    contentType = Boundary.addBoundary(contentType);

    return target.request()
        .header("X-Requested-By", "ivy")
        .put(Entity.entity(multipart, contentType));
  }

  public static File toTempIoFile(String fileName, InputStream resource) throws IOException {
    String name = StringUtils.substringBeforeLast(fileName, ".");
    String extension = "." + StringUtils.substringAfterLast(fileName, ".");
    File tempFile = Files.createTempFile(name, extension).toFile();
    try(var os = new FileOutputStream(tempFile)) {
      IOUtils.copy(resource, os);
    }
    return tempFile;
  }

  public static File getIvyLogo() throws IOException {
    return getResource("ivy_favicon_48.png");
  }

  public static File getResource(String fileName) throws IOException {
    try(var resource = FileUpload.class.getResourceAsStream(fileName)) {
      return toTempIoFile(fileName, resource);
    }
  }

}
