package gui;

import java.io.IOException;

import paint.*;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import visual.*;

public class PaintWall extends Visualization implements MouseListener {
	private static final Color BACKGROUND_COLOR = new Color(225, 222, 213);
	protected PaintContentFactory factory;
	protected FoodSelector foodSelector;

	public PaintWall(final int width, final int height, FoodSelector selector) throws IOException {
		super();
		VisualizationView view = getView();
		view.setBounds(0, 0, width, height);
		view.setSize(width, height);
		view.setBackground(BACKGROUND_COLOR);

		this.foodSelector = selector;
		this.factory = new PaintContentFactory();

		addMouseListener(this);
		addKeyListener(selector);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Food food = foodSelector.getFood();
		handleProjectile(e.getX(), e.getY(), food);
	}

	// unused mouse methods
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

	public void handleProjectile(final int x, final int y, final Food food) {
		PaintContent content = factory.createContent(x, y, food);
		add(content);
	}

}
