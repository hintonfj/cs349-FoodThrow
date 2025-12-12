package gui.reader;

import java.awt.image.BufferedImage;

/**
 * Reads in BufferedImages.
 */
public class BufferedImageReader
{
  /**
   * Converts a buffered Image to an array of ints.
   * 
   * @param image
   *          the image
   * @return the array of ints
   */
  public static int[][] convertToArray(final BufferedImage image)
  {
    int w = image.getWidth();
    int h = image.getHeight();
    int[][] pixels = new int[w][h];

    int[] pixelData = image.getRGB(0, 0, w, h, null, 0, w);

    for (int i = 0; i < w; i++)
    {
      for (int j = 0; j < h; j++)
      {
        pixels[i][j] = pixelData[j * w + i];
      }
    }

    return pixels;
  }
}
