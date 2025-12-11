package gui.reader;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import io.ResourceFinder;
import resources.images.CompareImages.CompareMarker;
import visual.statik.sampled.ImageFactory;

public class CompareImages 
{
    public static double CompareBufferedImages(BufferedImage img1, BufferedImage img2)
    {
        return Compare(img1, img2);
    }

    public static double CompareBufferedImages(JComponent img1, BufferedImage img2)
    {
        
        BufferedImage buffered1 = ImageConverter.convertToBuffered(img1);
        return CompareBufferedImages(buffered1, img2);
    }

    public static double CompareBufferedImages(JComponent img1, String img2)
    {
        ResourceFinder rf = ResourceFinder.createInstance(new CompareMarker());
        ImageFactory imageFactory = new ImageFactory(rf);
        BufferedImage buffered2 = imageFactory.createBufferedImage(img2);
        BufferedImage buffered1 = ImageConverter.convertToBuffered(img1);
        return CompareBufferedImages(buffered1, buffered2);
    }


    private static double Compare(BufferedImage img1, BufferedImage img2)
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

    public static boolean containsColor(JComponent img1, Color c, int percent, int numPixels)
    {
        
        BufferedImage buffered1 = ImageConverter.convertToBuffered(img1);
        return containsColor(buffered1, c, percent, numPixels);
    }

    public static boolean containsColor(BufferedImage img, Color c, int percent, int numPixels)
    {
        final int target = c.getRGB() & 0x00FFFFFF;

        int width = img.getWidth();
        int height = img.getHeight();

        int threshold = (int)(numPixels * (percent / 100.0));

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

    public static int numColors(JComponent img1, Color c)
    {
        
        BufferedImage buffered1 = ImageConverter.convertToBuffered(img1);
        return numColors(buffered1, c);
    }

    public static int numColors(BufferedImage img, Color c)
    {
        final int target = c.getRGB() & 0x00FFFFFF;

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
