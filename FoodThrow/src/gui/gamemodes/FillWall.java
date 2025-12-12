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
import resources.images.FillMaps.FillMarker;
import visual.statik.described.TransformableContent;

/**
 * Fill wall minigame.
 */
public class FillWall extends PaintWall{

	private int shots = 0;
	private javax.swing.JLabel shotLabel;
    private ResourceFinder jarFinder;
    private TransformableContent border;
    private int shotsRemaining;
    private ColorChecker colorComparer;
    
    /**
     * Fill wall minigame.
     * @param width window width
     * @param height window height
     * @param selector Selector
     * @param Interior Color of the shape
     * @throws IOException
     */
    public FillWall(int width, int height, FoodSelector selector, Color Interior) throws IOException {
        super(width, height, selector);
        
        //shot label
        shotLabel = new javax.swing.JLabel("Shots: 0 | Left: 100");
        shotLabel.setFont(new java.awt.Font("Monaco", java.awt.Font.BOLD, 15));
        shotLabel.setForeground(java.awt.Color.BLACK);
        
        jarFinder = ResourceFinder.createInstance(new FillMarker());
        BaseMapReader mapReader = new BaseMapReader(jarFinder);
        border = mapReader.read("squares.map", Color.BLACK, Interior);
        shotsRemaining = 100;
        colorComparer = new ColorChecker(Interior);
        add(border);
    }

    @Override
    protected void handleProjectile(final int x, final int y, final Food food) {
		PaintContent content = factory.createContent(x, y, food);
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
    
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        super.mouseClicked(e);

        shots++;
        shotLabel.setText("Shots: " + shots + " | Left: " + shotsRemaining);
    }

    
    public int getShotsRemaining()
    {
        return shotsRemaining;
    }

    public javax.swing.JLabel getShotLabel()
    {
        return shotLabel;
    }



}
