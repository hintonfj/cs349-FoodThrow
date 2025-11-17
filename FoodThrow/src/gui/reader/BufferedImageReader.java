package gui.reader;

import java.awt.image.BufferedImage;

public class BufferedImageReader 
{
public static int[][] convertToArray(BufferedImage image) {
    int w = image.getWidth();
    int h = image.getHeight();
    int[][] pixels = new int[w][h];
    
    int[] pixelData = image.getRGB(0, 0, w, h, null, 0, w);
    
    for (int i = 0; i < w; i++) {
        for (int j = 0; j < h; j++) {
            pixels[i][j] = pixelData[j * w + i];
        }
    }
    
    return pixels;
}
}
