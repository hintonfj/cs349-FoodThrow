package gui.gamemodes;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import gui.FoodSelector;
import gui.PaintWall;
import gui.PictureDialog;
import gui.TimerArcContent;
import gui.reader.CompareImages;
import io.ResourceFinder;
import resources.images.CompareImages.CompareMarker;
import visual.VisualizationView;
import visual.statik.sampled.ImageFactory;

/**
 * Compare Wall minigame.
 */
public class CompareWall extends PaintWall
{

  private BufferedImage comparedImage;
  private int seconds = 60;
  private int remainingMilliseconds;
  private Timer timer;
  // private JLabel timerLabel;
  private TimerArcContent timerArc;
  private JDialog dialog;
  private String[] images = {"olympics.png", "red-square.png", "smile.png"};

  /**
   * Compare Wall Minigame.
   * 
   * @param width
   *          window width
   * @param height
   *          window height
   * @param selector
   *          Selector
   * @throws IOException
   */
  public CompareWall(final int width, final int height, final FoodSelector selector)
      throws IOException
  {
    super(width, height, selector);
    VisualizationView view = getView();
    changePhoto(images[random.nextInt(3)]);
    handleImageDisplay(width, height);

    view.setLayout(null);

    // Create circular timer as described dynamic visual content
    timerArc = new TimerArcContent(100); // radius of circle
    add(timerArc);
    timerArc.setLocation(5, width - 100);

    // Start countdown
    startTimer();
  }

  /**
   * Changes the photo to compare to.
   * 
   * @param filename
   *          file name
   */
  public void changePhoto(final String filename)
  {
    ResourceFinder rf = ResourceFinder.createInstance(new CompareMarker());
    ImageFactory imageFactory = new ImageFactory(rf);
    comparedImage = imageFactory.createBufferedImage(filename);
  }

  /**
   * Displays the image to compare.
   * 
   * @param width
   *          width of dialog
   * @param height
   *          height of dialog
   */
  private void handleImageDisplay(final int width, final int height)
  {
    if (comparedImage == null)
    {
      System.out.println(":(");
    }
    float scaleW = 0.5f;
    float scaleH = 0.5f;
    this.dialog = new PictureDialog(scaleW, scaleH, comparedImage);
    dialog.setModal(false);
    dialog.setModalityType(java.awt.Dialog.ModalityType.MODELESS);

    dialog.repaint();
    dialog.validate();
    dialog.setVisible(true);
  }

  /**
   * Makes sure the timer is in front.
   */
  public void bringTimerToFront()
  {
    // re-add timerArc to ensure top layer
    remove(timerArc);
    add(timerArc);
  }

  /**
   * Ends the game and calculates the percentage.
   * 
   * @param complete if completed
   */
  private void gameEnd(final boolean complete)
  {
    timer.cancel();
    remove(timerArc);
    double percent = CompareImages.compareBufferedImages(getView(), comparedImage) * 100;
    double timeRemaining = (remainingMilliseconds / 1000.0);
    double score = percent * (timeRemaining + 1);
    if (complete)
    {
      JOptionPane.showMessageDialog(getView(), String
          .format("You got: %%%.2f!\nTime left: %.2f!\nScore:%.2f", percent, timeRemaining, score));
    }
    remainingMilliseconds = 0;
  }

  /**
   * Updates the timer.
   */
  private void updateTimerArc()
  {
    double secondsLeft = remainingMilliseconds / 1000.0;
    double percentRemaining = secondsLeft / seconds;

    timerArc.setPercent(percentRemaining);
    getView().repaint();
  }

  /**
   * Ends the game early.
   * 
   * @param complete if completed
   */
  public void endEarly(final boolean complete)
  {
    gameEnd(complete);
  }

  /**
   * Starts a timer.
   */
  private void startTimer()
  {
    remainingMilliseconds = seconds * 1000;

    timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask()
    {
      @Override
      public void run()
      {
        if (remainingMilliseconds > 0)
        {
          remainingMilliseconds -= 100;
          updateTimerArc();
        }
        else
        {
          timer.cancel();
          javax.swing.SwingUtilities.invokeLater(() -> gameEnd(true));
        }
      }
    }, 0, 100);
  }

  /**
   * Returns active timer arc.
   * 
   * @return Timer.
   */
  public TimerArcContent getTimerArc()
  {
    return timerArc;
  }

  /**
   * Closes the preview on close of the game.
   */
  public void closePreview()
  {
    if (dialog != null)
    {
      dialog.dispose();
    }
  }
}
