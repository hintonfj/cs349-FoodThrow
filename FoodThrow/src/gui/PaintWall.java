package gui;

import java.io.IOException;

import paint.*;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


import visual.*;

public class PaintWall extends Visualization implements MouseListener{
    private static final Color BACKGROUND_COLOR = new Color(225, 222, 213);
    protected PaintContentFactory factory;
    protected ColorSelector colors;

    public PaintWall(final int width, final int height) throws IOException
    {
        super();
        VisualizationView view = getView();
        view.setBounds(0, 0, width, height);
        view.setSize(width, height);
        view.setBackground(BACKGROUND_COLOR);
        factory = new PaintContentFactory();
        addMouseListener(this);
        colors = new ColorSelector();
        addKeyListener(colors);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        handleProjectile(e.getX(), e.getY(), colors.getColor());
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void handleProjectile(final int x, final int y, final Color c)
    {
        PaintContent content = factory.createContent(x, y, c);
        add(content);
    }
  
}
