package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import visual.statik.TransformableContent;

/**
 * Circular countdown timer implemented as DESCRIBED DYNAMIC VISUAL CONTENT.
 */
public class TimerArcContent implements TransformableContent {

    private double percent;
    private int radius;

    // TransformableContent tracking data
    private double x = 0;
    private double y = 0;
    private double scaleX = 1.0;
    private double scaleY = 1.0;
    private double rotation = 0;

    public TimerArcContent(int radius) {
        this.radius = radius;
        this.percent = 1.0;
    }

    public void setPercent(double p) {
        this.percent = Math.max(0, Math.min(1, p));
    }

    @Override
    public void render(Graphics gRaw) {
        Graphics2D g = (Graphics2D) gRaw;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int size = (int)(radius * scaleX);

        // Background circle (gray)
        g.setColor(new Color(231, 214, 239, 80));
        g.fillOval((int)x, (int)y, size, size);

        // Countdown arc
        g.setColor(new Color(197, 183, 223));
        int angle = (int)(360 * percent);
        g.fillArc((int)x, (int)y, size, size, 90, -angle);

        // Border
        g.setColor(Color.BLACK);
        g.drawOval((int)x, (int)y, size, size);
    }

    @Override
    public Rectangle2D getBounds2D(boolean absolute) {
        return new Rectangle2D.Double(x, y, radius * scaleX, radius * scaleY);
    }

    @Override
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setRotation(double r, double cx, double cy) {
        this.rotation = r;
    }

    @Override
    public void setScale(double sx, double sy) {
        this.scaleX = sx;
        this.scaleY = sy;
    }
}
