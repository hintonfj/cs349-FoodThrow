package gui;

import java.io.IOException;

import paint.*;

import java.awt.Color;
import visual.*;

public class PaintWall extends Visualization{
    private static final Color BACKGROUND_COLOR = new Color(225, 222, 213);
    protected PaintContentFactory factory;

    public PaintWall(final int width, final int height) throws IOException
    {
        super();
        VisualizationView view = getView();
        view.setBounds(0, 0, width, height);
        view.setSize(width, height);
        view.setBackground(BACKGROUND_COLOR);
        factory = new PaintContentFactory();
    }

    public void handleProjectile(final int x, final int y, final Color c)
    {
        PaintContent content = factory.createContent(x, y, c);
        add(content);
    }
  
}
