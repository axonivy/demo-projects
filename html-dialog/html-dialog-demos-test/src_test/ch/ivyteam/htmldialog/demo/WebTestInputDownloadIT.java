package ch.ivyteam.htmldialog.demo;

import static ch.ivyteam.htmldialog.server.test.ProcessUtil.startProcess;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.Selenide;

import ch.ivyteam.htmldialog.server.test.ProcessUtil;

@IvyWebTest
public class WebTestInputDownloadIT {

  @BeforeAll
  public static void setUp() {
    Selenide.closeWebDriver();
    Configuration.proxyEnabled = true;
    Configuration.fileDownload = FileDownloadMode.PROXY;
    ProcessUtil.open();
  }

  @AfterAll
  static void cleanUp() {
    Selenide.closeWebDriver();
    Configuration.proxyEnabled = false;
    Configuration.fileDownload = FileDownloadMode.HTTPGET;
    ProcessUtil.open();
  }

  @Test
  public void testSimpleFileUpDownload() throws Exception {
    startProcess("145D18298A3E81CF/FileUploadSimpleModeDemo.ivp");
    File tempFile = File.createTempFile("tempTextFile", ".txt");
    tempFile.deleteOnExit();
    String testContent = "this is a test for the Simple File Up- and Download";
    FileUtils.write(tempFile, testContent, Charset.defaultCharset());
    $(By.id("demoForm:fileUpload")).shouldBe(visible);
    $(By.id("demoForm:fileUpload_input")).sendKeys(tempFile.getAbsolutePath());
    $(By.id("demoForm:fileUploadButton")).click();
    $(By.id("demoForm:showFileButton")).click();
    $(By.id("demoForm:textAreaLabel")).shouldHave(text(testContent));
    File download = $(By.id("demoForm:downloadFileButton")).download();
    assertThat(download).hasContent(testContent);
  }

  @Test
  public void testAdvancedFileUpload() throws Exception {
    startProcess("145D18298A3E81CF/FileUploadAdvancedModeDemo.ivp");
    File tempImage = File.createTempFile("tempImageFile", ".png");
    try (InputStream inputImage = getClass().getResourceAsStream("ExampleImage.png");
        FileOutputStream outputImage = new FileOutputStream(tempImage);) {
      IOUtils.copy(inputImage, outputImage);
    }
    $(By.id("pictureGalleryForm:fileUpload_input")).shouldBe(exist).sendKeys(tempImage.getAbsolutePath());
    $(By.id("pictureGalleryForm:pictureGallery")).findAll(".ui-galleria-item").shouldHave(size(4));
  }
}
