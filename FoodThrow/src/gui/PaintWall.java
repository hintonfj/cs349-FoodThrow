package gui;

import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import auditory.sampled.BufferedSound;
import auditory.sampled.BufferedSoundFactory;
import io.ResourceFinder;
import paint.*;
import sound.*;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import visual.*;

/**
 * Paint Wall.
 */
public class PaintWall extends Visualization implements MouseListener
{
  private static final Color BACKGROUND_COLOR = new Color(225, 222, 213);
  protected BufferedSoundFactory soundFactory;
  protected BufferedSound splats[];
  protected FoodSelector foodSelector;
  protected Random random;
  protected Clip clip;

  /**
   * Creates a paint wall.
   * 
   * @param width
   *          window width
   * @param height
   *          window height
   * @param selector
   *          Selector
   * @throws IOException
   */
  public PaintWall(final int width, final int height, final FoodSelector selector)
      throws IOException
  {
    super();
    VisualizationView view = getView();
    view.setBounds(0, 0, width, height);
    view.setSize(width, height);
    view.setBackground(BACKGROUND_COLOR);

    this.foodSelector = selector;

    ResourceFinder rf = ResourceFinder.createInstance(new SoundMarker());
    this.soundFactory = new BufferedSoundFactory(rf);
    this.random = new Random();
    splats = new BufferedSound[3];
    try
    {
      splats[0] = soundFactory.createBufferedSound("fruit-splat.wav");
      splats[1] = soundFactory.createBufferedSound("fruit-splat2.wav");
      splats[2] = soundFactory.createBufferedSound("fruit-splat3.wav");
    }
    catch (IOException | UnsupportedAudioFileException e)
    {
      e.printStackTrace();
    }
    addMouseListener(this);
    addKeyListener(selector);
  }

  @Override
  public void mouseClicked(final MouseEvent e)
  {
    Food food = foodSelector.getFood();
    handleProjectile(e.getX(), e.getY(), food);
    handleSplatSound();
  }

  // unused mouse methods
  @Override
  public void mousePressed(final MouseEvent e)
  {
  }

  @Override
  public void mouseReleased(final MouseEvent e)
  {
  }

  @Override
  public void mouseEntered(final MouseEvent e)
  {
  }

  @Override
  public void mouseExited(final MouseEvent e)
  {
  }

  /**
   * Handles firing the projectile.
   * 
   * @param x
   *          x cord
   * @param y
   *          y cord
   * @param food
   *          food to throw
   */
  protected void handleProjectile(final int x, final int y, final Food food)
  {
    PaintContent content = PaintContentFactory.createContent(x, y, food);
    add(content);
  }

  /**
   * Plays 1 of 3 splat sounds on throw.
   */
  private void handleSplatSound()
  {
    try
    {
      clip = AudioSystem.getClip();
      splats[random.nextInt(3)].render(clip);
    }
    catch (LineUnavailableException e1)
    {
      e1.printStackTrace();
    }
  }
}
