package com.axonivy.connectivity.rest.provider;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import ch.ivyteam.api.API;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.File;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * URI for file upload: http://localhost:8081/designer/api/file
 * @since 7.3.0
 */
@Path("file")
@Tag(name = ApiConstants.DEMO_TAG)
public class FileService {
  @GET
  @Path("/{fileName}")
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public Response downloadFile(@PathParam("fileName") String fileName) throws IOException {
    File ivyFile = new File(fileName);
    InputStream fis = new FileInputStream(ivyFile.getJavaFile());
    return Response
        .ok(fis, MediaType.APPLICATION_OCTET_STREAM)
        .header("content-disposition", "attachment; filename = " + fileName)
        .build();

  }

  /**
   * Adds documents to the workflow application <b>without using multi-part forms</b>.
   *
   * <p>Pure MediaType#APPLICATION_OCTET_STREAM based APIs can be an easier easier to use for some clients.</p>
   *
   * @param fileName as mandatory path parameter: since the payload does not declare the name.
   * @param payload the binary input representing a file.
   * @return upload message
   * @throws IOException
   */
  @POST
  @Path("/{fileName}")
  @Consumes(MediaType.APPLICATION_OCTET_STREAM)
  public Response addDocument(@PathParam("fileName") String fileName, byte[] payload) throws IOException {
    checkExtension(fileName);
    try (InputStream is = new ByteArrayInputStream(payload)) {
      File newFile = createIvyFile(is, fileName);
      String result = "File was uploaded succesfully to: " + newFile.getAbsolutePath();
      return Response.status(201)
          .header("uploadedFile", fileName)
          .entity(result)
          .build();
    }
  }

  /**
   * Adds documents to the workflow application through a multi-part form interface.
   *
   * @param fileUploadStream the stream containing binary data
   * @param fileUploadDetail informations on the file being sent das binary
   * @return upload message
   * @throws IOException
   */
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public Response uploadFile(
      @FormDataParam("file") InputStream fileUploadStream,
      @Parameter(required = true) @FormDataParam("file") FormDataContentDisposition fileUploadDetail,
      @FormDataParam("description") String description,
      @FormDataParam("ownerId") Long ownerId) throws IOException {
    String fileName = fileUploadDetail.getFileName();
    checkExtension(fileName);
    API.checkNotNull(fileUploadDetail, "fileUploadDetail");
    File ivyFile = createIvyFile(fileUploadStream, fileName);
    String result = "File was uploaded succesfully to: " + ivyFile.getAbsolutePath();
    return Response.status(201)
        .header("uploadedFile", fileName)
        .header("description", description)
        .header("ownerId", ownerId)
        .entity(result)
        .build();
  }

  /**
   * demonstrates a service resource that consumes multiple files at once through
   * a multi-part request.
   */
  @PUT
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.TEXT_PLAIN)
  @Path("multi")
  public Response shareFiles(
      @FormDataParam("files") List<FormDataBodyPart> fileParts,
      @FormDataParam("description") @DefaultValue("#") String description,
      @FormDataParam("ownerId") @DefaultValue("-1") Integer ownerId) throws IOException {
    List<File> uploads = new ArrayList<>();
    if (fileParts != null) {
      for (FormDataBodyPart filePart : fileParts) {
        BodyPartEntity bodyPartEntity = (BodyPartEntity) filePart.getEntity();
        File aFile = createIvyFile(bodyPartEntity.getInputStream(), filePart.getContentDisposition().getFileName());
        uploads.add(aFile);
      }
    }
    String fileNames = uploads.stream().map(File::getName).collect(Collectors.joining(", "));
    return Response.status(201)
        .header("uploadedFiles", fileNames)
        .header("description", description)
        .header("ownerId", ownerId)
        .entity("got " + uploads.size() + " files.")
        .build();
  }

  private static File createIvyFile(InputStream fileUploadStream, String fileName)
      throws IOException {
    File ivyFile = new File(fileName, false);
    try (OutputStream os = new FileOutputStream(ivyFile.getJavaFile())) {
      IOUtils.copy(fileUploadStream, os);
      return ivyFile;
    } catch (IOException ex) {
      Ivy.log().fatal("File could not be uploaded: " + fileName, ex);
      throw new IOException("File could not be uploaded: " + fileName, ex);
    }
  }

  private static void checkExtension(String fileName) {
    String extension = FilenameUtils.getExtension(fileName);
    if (!checkIfStringContainsList(extension)) {
      Ivy.log().fatal("The file is not allowed! Your file is: '." + extension + "'");
      throw new IllegalArgumentException("The file is not allowed! Your file is: '." + extension + "'");
    }
  }

  public static List<String> whitelistedExtensions = Arrays.asList("pdf", "txt", "jpg", "jpeg", "png");

  private static boolean checkIfStringContainsList(String extension) {
    return whitelistedExtensions.contains(extension);
  }
}
