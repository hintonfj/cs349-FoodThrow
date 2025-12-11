package gui.gamemodes;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JLabel;

import gui.Food;
import gui.FoodSelector;
import gui.PaintWall;
import gui.reader.BaseMapReader;
import gui.reader.CompareImages;
import io.ResourceFinder;
import paint.PaintContent;
import resources.Marker;
import visual.statik.described.TransformableContent;

public class FillWall extends PaintWall{

    private ResourceFinder jarFinder;
    private TransformableContent border;
    private int shotsRemaining;
    private int numPixelsToFill;
    
    public FillWall(int width, int height, FoodSelector selector, Color Interior) throws IOException {
        super(width, height, selector);
        jarFinder = ResourceFinder.createInstance(new Marker());
        BaseMapReader mapReader = new BaseMapReader(jarFinder);
        border = mapReader.read("harrisonburg.map", Color.BLACK, Interior);
        shotsRemaining = 100;
        numPixelsToFill = -1;
        add(border);
    }

    @Override
    protected void handleProjectile(final int x, final int y, final Food food) {
		PaintContent content = factory.createContent(x, y, food);
		add(content);
        if (numPixelsToFill == -1)
        {
            numPixelsToFill = CompareImages.numColors(getView(), Color.WHITE);
        }
        boolean completed = !CompareImages.containsColor(getView(), Color.WHITE, 1, numPixelsToFill);
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

    //Temp win
    private void win()
    {
        JDialog d = new JDialog();
        d.setTitle("Photo");
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setSize(400, 100);
        d.setLocationRelativeTo(null);
        d.setModal(true);
        d.setResizable(false);
        d.add(new JLabel(String.format("Won with %d fruits remaining", shotsRemaining)));
        d.setVisible(true);
    }

    //Temp lose
    private void lose()
    {
        JDialog d = new JDialog();
        d.setTitle("Photo");
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setSize(100, 100);
        d.setLocationRelativeTo(null);
        d.setModal(true);
        d.setResizable(false);
        d.add(new JLabel("Lose :("));
        d.setVisible(true);
    }


}
