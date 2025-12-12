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

/**
 * Free Paint minigame.
 */
public class FreePaint extends PaintWall implements KeyListener{

    /**
     * Free Paint minigame.
     * @param width width of the window
     * @param height height of the window
     * @param selector FoodSelector
     * @throws IOException
     */
    public FreePaint(final int width, final int height, final FoodSelector selector) throws IOException {
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

    /**
     * Saves the image.
     */
    public void saveFile()
    {
        try {
            // Convert the current view into an image
            BufferedImage bi = ImageConverter.convertToBuffered(getView());

            // Open a file chooser
            javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
            chooser.setDialogTitle("Save Your Painting");
            chooser.setSelectedFile(new File("painting.png")); // suggested default name

            int option = chooser.showSaveDialog(null);

            // User pressed "Cancel"
            if (option != javax.swing.JFileChooser.APPROVE_OPTION) {
                return;
            }

            File file = chooser.getSelectedFile();

            // Add .png if needed
            if (!file.getName().toLowerCase().endsWith(".png")) {
                file = new File(file.getAbsolutePath() + ".png");
            }

            // Write image to disk
            ImageIO.write(bi, "png", file);

            // Show confirmation
            javax.swing.JOptionPane.showMessageDialog(
                null,
                "Saved painting as:\n" + file.getName(),
                "Saved!",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(
                null,
                "An error occurred while saving the image.",
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

}
