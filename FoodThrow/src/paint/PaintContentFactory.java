package paint;

import java.awt.geom.*;

import gui.Food;

/**
 * Paint Content factory.
 */
public class PaintContentFactory
{

  /**
   * Makes paint Content.
   * @param x x location
   * @param y y location
   * @param food type of food
   * @return Paint Content
   */
  public static PaintContent createContent(final int x, final int y, final Food food)
  {
    return new PaintContent(food, new Point2D.Double(x, y));
  }
}
