package gui.reader;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

/**
 * Checks to see if a color still exists in a image.
 */
public class ColorChecker {

    private int target;
    private int numPixelsToFill;

    /**
     * Creates a ColorChecker Object.
     * @param c Color to check for
     */
    public ColorChecker(final Color c)
    {
        target = c.getRGB() & 0x00FFFFFF;
        numPixelsToFill = -1;
    }


    /**
     * Checks if the color still exists in a JComponent.
     * @param img1 the JCompenent to check
     * @param percent The percentage threshold that the user can have not filled 
     * @return If there exists pixels above the threshold of a certain color
     */
    public boolean containsColor(final JComponent img1, final double percent)
    {
        
        BufferedImage buffered1 = ImageConverter.convertToBuffered(img1);
        return containsColor(buffered1, percent);
    }

    
    /**
     * Checks if the color still exists in a JComponent.
     * @param img the BufferedImage to check
     * @param percent The percentage threshold that the user can have not filled 
     * @return If there exists pixels above the threshold of a certain color
     */
    public boolean containsColor(final BufferedImage img, final double percent)
    {
        if (numPixelsToFill == -1)
        {
            numPixelsToFill = numColors(img);
        }

        int width = img.getWidth();
        int height = img.getHeight();

        int threshold = (int)(numPixelsToFill * (percent / 100.0));

        int matches = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                int rgb = img.getRGB(x, y) & 0x00FFFFFF;

                if (rgb == target) {
                    if (++matches >= threshold) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * The number of pixels in an image.
     * @param img the image to check
     * @return The number of pixels in the image
     */
    private int numColors(final BufferedImage img)
    {
        int width = img.getWidth();
        int height = img.getHeight();

        int matches = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                int rgb = img.getRGB(x, y) & 0x00FFFFFF;

                if (rgb == target) {
                    matches++;
                }
            }
        }

        return matches;
    }
}
