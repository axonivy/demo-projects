package ch.ivyteam.htmldialog.demo.output;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.imageio.ImageIO;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class ImageGenerator {

  public static StreamedContent generateImage(String text) throws Exception {
    final FacesContext facesContext = FacesContext.getCurrentInstance();
    if (facesContext.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
      // during render response, the src attribute is evaluated,
      // not necessary to return any streamed content see
      // http://stackoverflow.com/questions/8304967/how-to-use-pgraphicimage-with-streamedcontent-within-pdatatable
      return DefaultStreamedContent.builder().build();
    }
    return createImage(text);
  }

  public static StreamedContent createImage(String text) throws Exception {
    BufferedImage bufferedImg = new BufferedImage(120, 40,
        BufferedImage.TYPE_INT_RGB);
    Graphics2D g2 = bufferedImg.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(Color.WHITE);
    g2.fillRect(0, 0, 120, 40);
    g2.setColor(Color.BLACK);
    g2.setFont(new Font("Comic", Font.PLAIN, 20));
    g2.drawString(text, 10, 25);
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    ImageIO.write(bufferedImg, "png", os);
    return DefaultStreamedContent.builder()
        .stream(() -> new ByteArrayInputStream(os.toByteArray()))
        .contentType("image/png")
        .build();
  }
}
