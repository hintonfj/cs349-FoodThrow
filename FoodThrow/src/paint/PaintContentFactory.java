package paint;

import java.awt.geom.*;

import gui.Food;


public class PaintContentFactory {
    public PaintContentFactory()
    {

    }

    public PaintContent createContent(final int x, final int y, final Food food)
    {
        return new PaintContent(food, new Point2D.Double(x,y));
    }
}
