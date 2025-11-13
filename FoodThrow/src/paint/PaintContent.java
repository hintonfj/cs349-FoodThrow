package paint;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

import visual.statik.SimpleContent;

public class PaintContent implements SimpleContent {

    protected Color color;
    protected Point2D location;
    protected List<Ellipse2D> blobs;

    public PaintContent(final Color color, final Point2D location)
    {
        this.color = color;
        this.location = location;

        blobs = new ArrayList<>();

        double x = location.getX();
        double y = location.getY();

        // base circle splat
        double base = 25 + Math.random() * 10;
        blobs.add(new Ellipse2D.Double(x - base/2, y - base/2, base, base));

        // creates the blobs around base paint circle
        for (int i = 0; i < 6; i++) {
            double radius = 8 + Math.random() * 15;
            double angle = Math.random() * Math.PI * 2;
            double dist = 5 + Math.random() * 10;

            double bx = x + Math.cos(angle) * dist;
            double by = y + Math.sin(angle) * dist;

            blobs.add(new Ellipse2D.Double(bx - radius/2, by - radius/2, radius, radius));
        }
    }

    @Override
    public void render(final Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Color old = g2.getColor();

        g2.setColor(color);

        // Draw all blobs 
        for (Ellipse2D blob : blobs) {
            g2.fill(blob);
        }

        g2.setColor(old);
    }
}
