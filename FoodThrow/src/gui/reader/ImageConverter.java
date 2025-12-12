package gui.reader;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

/**
 * Converts a Jcomponent to BufferedImage.
 */
public class ImageConverter
{

  /**
   * Converts a JComponent to a BufferedImage.
   * 
   * @param component
   *          the Jcomponent
   * @return BufferedImage
   */
  public static BufferedImage convertToBuffered(final JComponent component)
  {
    BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(),
        BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = image.createGraphics();
    component.paint(g2d);
    g2d.dispose();
    return image;
  }
}
