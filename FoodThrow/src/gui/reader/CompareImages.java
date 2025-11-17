package gui.reader;

import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import images.Marker;
import io.ResourceFinder;
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
        ResourceFinder rf = ResourceFinder.createInstance(new Marker());
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
}
