package paint;

import java.awt.Color;
import java.awt.*;
import java.awt.geom.*;


public class PaintContentFactory {
    public PaintContentFactory()
    {

    }

    public PaintContent createContent(final int x, final int y, Color c)
    {
        return new PaintContent(c, new Point2D.Double(x,y));
    }
}
