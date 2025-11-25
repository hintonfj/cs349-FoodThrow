package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

import javax.swing.JComponent;

public class PalettePlate extends JComponent implements MouseListener {

	private BufferedImage plateImage;
	private Food[] foods;
	private int selectedIndex = 0;

	private Consumer<Integer> onSelect;

	public void setOnSelect(Consumer<Integer> c) {
		this.onSelect = c;
	}

	public PalettePlate(BufferedImage plate, Food[] foods) {
		this.plateImage = plate;
		this.foods = foods;
		addMouseListener(this);
		setPreferredSize(new Dimension(600, 120));
	}

	public void setSelectedIndex(int index) {
		this.selectedIndex = index;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		int w = getWidth();
		int spacing = 80;
		int ph = plateImage.getHeight();

// spaced food 
		int totalWidth = foods.length * spacing;
		int startX = (w - totalWidth) / 2;
		int y = ph / 2 - 30;

		// putting plate under selected food
		int selectedFoodX = startX + selectedIndex * spacing;
		int plateX = selectedFoodX + 30 - plateImage.getWidth() / 2;
		int plateY = 0;
		g2.drawImage(plateImage, plateX, plateY, null);

		// drawing food
		for (int i = 0; i < foods.length; i++) {
			int x = startX + i * spacing;

			g2.drawImage(foods[i].image, x, y, 60, 60, null);
		}
	}

	// supported mouse clicking food too
	@Override
	public void mouseClicked(MouseEvent e) {

		int w = getWidth();
		int spacing = 80;
		int ph = plateImage.getHeight();

		int totalWidth = foods.length * spacing;
		int startX = (w - totalWidth) / 2;
		int y = ph / 2 - 30;

		for (int i = 0; i < foods.length; i++) {
			int x = startX + i * spacing;

			Rectangle hit = new Rectangle(x, y, 60, 60);

			if (hit.contains(e.getPoint())) {
				selectedIndex = i;
				repaint();

				if (onSelect != null) {
					onSelect.accept(i);
				}
			}
		}
	}

	// unused methods
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
}
