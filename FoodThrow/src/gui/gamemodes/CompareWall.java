package gui.gamemodes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import gui.FoodSelector;
import gui.PaintWall;
import gui.reader.CompareImages;
import io.ResourceFinder;
import resources.images.CompareImages.CompareMarker;
import visual.statik.sampled.ImageFactory;

public class CompareWall extends PaintWall implements KeyListener{

    private BufferedImage comparedImage;

    public CompareWall(int width, int height, FoodSelector selector) throws IOException {
        super(width, height, selector);
        addKeyListener(this);
        changePhoto("corner.png");
        handleImageDisplay(width, height);
    }

    public void changePhoto(String filename)
    {
        ResourceFinder rf = ResourceFinder.createInstance(new CompareMarker());
        ImageFactory imageFactory = new ImageFactory(rf);
        comparedImage = imageFactory.createBufferedImage(filename);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        handleCompare();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    private void handleImageDisplay(int width, int height)
    {
        if (comparedImage == null)
        {
            System.out.println(":(");
        }
        JDialog dialog = new JDialog();
        dialog.setTitle("Photo");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(width, height);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.add(new JLabel(new ImageIcon(comparedImage)));
        dialog.repaint();
        dialog.validate();
        dialog.setVisible(true);
    }

    private void handleCompare()
    {
        double percent = CompareImages.CompareBufferedImages(getView(), comparedImage);
        JOptionPane.showMessageDialog(null, String.format("You got %%%f!", percent * 100));
    }
    
}
