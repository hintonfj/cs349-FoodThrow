package paint;

import java.awt.*;
import java.awt.geom.*;

import visual.statik.SimpleContent;


public class PaintContent implements SimpleContent{

  protected Color color;
  protected Point2D location;

  public PaintContent(final Color color, final Point2D location)
  {
      this.color = color;
      this.location = location;
  }

  @Override
  public void render(final Graphics g)
  {
    Graphics2D g2 = (Graphics2D) g;

    Color oldColor = g2.getColor();
    g2.setColor(color);

    // Render the Image
    double y = location.getY();
    double x = location.getX();
    double width = 20;
    double height = 20;
    Rectangle2D rectangle= new Rectangle2D.Double(x, y ,width, height);
    g2.fill(rectangle);
    g2.draw(rectangle);
    g2.setColor(oldColor);
  }
}

