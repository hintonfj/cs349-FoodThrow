package gui.reader;

import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import io.ResourceFinder;
import resources.images.CompareImages.CompareMarker;
import visual.statik.sampled.ImageFactory;

/**
 * Compares two images.
 */
public class CompareImages 
{
    /**
     * Compares two buffered images.
     * @param img1 image 1
     * @param img2 image 2
     * @return percentage of how close they are (1 = exactly)
     */
    public static double CompareBufferedImages(final BufferedImage img1, final BufferedImage img2)
    {
        return Compare(img1, img2);
    }

    /**
     * Compares a buffered image and a JComponent.
     * @param img1 JComponent
     * @param img2 Image
     * @return percentage of how close they are (1 = exactly)
     */
    public static double CompareBufferedImages(final JComponent img1, final BufferedImage img2)
    {
        
        BufferedImage buffered1 = ImageConverter.convertToBuffered(img1);
        return CompareBufferedImages(buffered1, img2);
    }

    /**
     * Compares a buffered image gotten from a string and a JComponent.
     * @param img1 JComponent
     * @param img2 String
     * @return percentage of how close they are (1 = exactly)
     */
    public static double CompareBufferedImages(final JComponent img1, final String img2)
    {
        ResourceFinder rf = ResourceFinder.createInstance(new CompareMarker());
        ImageFactory imageFactory = new ImageFactory(rf);
        BufferedImage buffered2 = imageFactory.createBufferedImage(img2);
        BufferedImage buffered1 = ImageConverter.convertToBuffered(img1);
        return CompareBufferedImages(buffered1, buffered2);
    }

    /**
     * Compares two buffered images.
     * @param img1 image 1
     * @param img2 image 2
     * @return percentage of how close they are (1 = exactly)
     */
    private static double Compare(final BufferedImage img1, final BufferedImage img2)
    {
        int matchingPixels = 0;
        int totalPixels = 0;
        int[][] img1_pixels = BufferedImageReader.convertToArray(img1);
        int[][] img2_pixels = BufferedImageReader.convertToArray(img2);
        for (int i = 0; i < img1_pixels.length; i++) 
        {
            for (int j = 0; j < img1_pixels[0].length; j++) {
                if (img1_pixels[i][j] == img2_pixels[i][j])
                {
                    matchingPixels += 1;
                }
                totalPixels += 1;
            }
        }
        return (double) matchingPixels/totalPixels;
    }

}
