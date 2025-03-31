package com.axonivy.connectivity.rest.client.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.glassfish.jersey.media.multipart.Boundary;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.project.IIvyProject;

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

  public static Response upload(WebTarget target, IFile resource) throws IOException {
    File finishedFile = toTempIoFile(resource);
    Response response = upload(target, finishedFile);
    finishedFile.delete();
    return response;
  }

  public static File toTempIoFile(IFile resource) throws IOException {
    String name = StringUtils.substringBeforeLast(resource.getName(), ".");
    String extension = "." + resource.getFileExtension();
    File tempFile = Files.createTempFile(name, extension).toFile();
    try {
      IOUtils.copy(resource.getContents(), new FileOutputStream(tempFile));
    } catch (CoreException ex) {
      throw new IOException("There was some problem while creating tempFile", ex);
    }
    return tempFile;
  }

  /**
   * Files from an IvyProject must be read with the eclipse resource layer to
   * support packed and bare projects.
   *
   * @param dialogId dot separated user dialog identifier (e.g.
   *          com.axonivy.MyDialog)
   * @param pathToFileInDialog path to a file in the html dialog (e.g.
   *          resources/myGif.gif)
   * @return an {@link IFile} resource
   */
  public static IFile getHdResource(String dialogId, String pathToFileInDialog) throws FileNotFoundException {
    IProject eclipseProject = IIvyProject.of(Ivy.request().project()).getProject();
    String dialogPath = dialogId.replace(".", "/");

    IFolder dialogDir = eclipseProject.getFolder("src_hd").getFolder(dialogPath);
    IFile resource = dialogDir.getFile(pathToFileInDialog);
    if (!resource.exists()) {
      throw new FileNotFoundException("File " + pathToFileInDialog + " does not exist in " + dialogId);
    }
    return resource;
  }

  public static File getIvyLogo() throws IOException {
    IFile ivyLogoResource = getHdResource("com.axonivy.connectivity.rest.FileUpload", "resources/ivy_favicon_48.png");
    return toTempIoFile(ivyLogoResource);
  }

}
