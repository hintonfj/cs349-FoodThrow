package gui.gamemodes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gui.FoodSelector;
import gui.PaintWall;
import gui.reader.ImageConverter;

public class FreePaint extends PaintWall implements KeyListener{

    public FreePaint(int width, int height, FoodSelector selector) throws IOException {
        super(width, height, selector);
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    private void saveFile()
	{
		try {
    		BufferedImage bi = ImageConverter.convertToBuffered(getView());  // retrieve image
			File outputfile = new File("saved.png");
			ImageIO.write(bi, "png", outputfile);
		} catch (IOException e) {
			// handle exception
		}
	}
}
