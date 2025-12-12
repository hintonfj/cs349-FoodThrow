package gui.gamemodes;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JLabel;

import gui.Food;
import gui.FoodSelector;
import gui.PaintWall;
import gui.reader.BaseMapReader;
import gui.reader.ColorChecker;
import io.ResourceFinder;
import paint.PaintContent;
import paint.PaintContentFactory;
import resources.images.FillMaps.FillMarker;
import visual.statik.described.TransformableContent;

/**
 * Fill wall minigame.
 */
public class FillWall extends PaintWall
{

  private int shots = 0;
  private javax.swing.JLabel shotLabel;
  private ResourceFinder jarFinder;
  private TransformableContent border;
  private int shotsRemaining;
  private ColorChecker colorComparer;
  private String[] maps = {"harrisonburg.map", "square.map", "squares.map"};

  /**
   * Fill wall minigame.
   * 
   * @param width
   *          window width
   * @param height
   *          window height
   * @param selector
   *          Selector
   * @param interior
   *          Color of the shape
   * @throws IOException
   */
  public FillWall(final int width, final int height, final FoodSelector selector,
      final Color interior) throws IOException
  {
    super(width, height, selector);

    // shot label
    shotsRemaining = 75;
    shotLabel = new javax.swing.JLabel("Shots: 0 | Left: " + shotsRemaining);
    shotLabel.setFont(new java.awt.Font("Monaco", java.awt.Font.BOLD, 15));
    shotLabel.setForeground(java.awt.Color.BLACK);

    jarFinder = ResourceFinder.createInstance(new FillMarker());
    BaseMapReader mapReader = new BaseMapReader(jarFinder);
    border = mapReader.read(maps[random.nextInt(3)], Color.BLACK, interior);
    colorComparer = new ColorChecker(interior);
    add(border);
  }

  @Override
  protected void handleProjectile(final int x, final int y, final Food food)
  {
    PaintContent content = PaintContentFactory.createContent(x, y, food);
    add(content);
    boolean completed = !colorComparer.containsColor(getView(), 0.1);
    shotsRemaining--;
    if (completed)
    {
      win();
    }
    else if (shotsRemaining <= 0)
    {
      lose();
    }
  }

  // Temp win
  private void win()
  {
    JDialog d = new JDialog();
    d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    d.setSize(400, 100);
    d.setLocationRelativeTo(null);
    d.setModal(true);
    d.setResizable(false);
    d.add(new JLabel(String.format("    Won with %d fruits remaining", shotsRemaining)));
    d.setVisible(true);
  }

  // Temp lose
  private void lose()
  {
    JDialog d = new JDialog();
    d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    d.setSize(100, 100);
    d.setLocationRelativeTo(null);
    d.setModal(true);
    d.setResizable(false);
    d.add(new JLabel("    You Lost :("));
    d.setVisible(true);
  }

  @Override
  public void mouseClicked(final java.awt.event.MouseEvent e)
  {
    super.mouseClicked(e);

    shots++;
    shotLabel.setText("Shots: " + shots + " | Left: " + shotsRemaining);
  }

  /**
   * Shots remaining getter.
   * 
   * @return shots remaining
   */
  public int getShotsRemaining()
  {
    return shotsRemaining;
  }

  /**
   * Shot Label getter.
   * 
   * @return label
   */
  public javax.swing.JLabel getShotLabel()
  {
    return shotLabel;
  }

}
