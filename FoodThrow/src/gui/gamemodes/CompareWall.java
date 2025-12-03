package gui.gamemodes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import gui.FoodSelector;
import gui.PaintWall;
import gui.reader.CompareImages;
import io.ResourceFinder;
import resources.images.CompareImages.CompareMarker;
import visual.VisualizationView;
import visual.statik.sampled.ImageFactory;

public class CompareWall extends PaintWall implements KeyListener{

    private BufferedImage comparedImage;
    private int seconds = 10;
    private int remainingMilliseconds;
    private Timer timer;
    private JLabel timerLabel;


    public CompareWall(int width, int height, FoodSelector selector) throws IOException {
        super(width, height, selector);
        VisualizationView view = getView();
        addKeyListener(this);
        changePhoto("corner.png");
        handleImageDisplay(width, height);

        /*TIMER NOT WORKING */

        timerLabel = new JLabel();
        timerLabel.setFont(new Font("Monaco", Font.BOLD, 20));
        timerLabel.setForeground(Color.RED);
        timerLabel.setBounds(width - 120, 8, 510, 300);
        view.add(timerLabel);
        startTimer();
    }


    public void changePhoto(String filename)
    {
        ResourceFinder rf = ResourceFinder.createInstance(new CompareMarker());
        ImageFactory imageFactory = new ImageFactory(rf);
        comparedImage = imageFactory.createBufferedImage(filename);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        gameEnd();
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

    private void gameEnd()
    {
        timer.cancel();
        
        double percent = CompareImages.CompareBufferedImages(getView(), comparedImage)*100;
        double timeRemaining = (remainingMilliseconds / 1000.0);
        double score = percent * (timeRemaining + 1);
        JOptionPane.showMessageDialog(null, String.format("You got: %%%.2f!\nTime left: %.2f!\nScore:%.2f", percent, timeRemaining, score));
    }

private void startTimer() {
    remainingMilliseconds = seconds*1000;
    timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
                if (remainingMilliseconds > 0) {
                    remainingMilliseconds--;
                } else {
                    gameEnd();
                }
            }
        }, 1000, 1);
    }
}
