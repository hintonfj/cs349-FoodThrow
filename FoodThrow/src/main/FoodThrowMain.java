package main;

import gui.*;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import app.JApplication;

public class FoodThrowMain extends FoodThrowApplication {
	private PaintWall wall;
	private PalettePlate palette;
	private FoodSelector selector; 

	/**
	 * Explicit value constructor.
	 * 
	 * @param args The command line arguments
	 */
	public FoodThrowMain(final String[] args) throws IOException {
		super(args);

		// images
		BufferedImage plate = ImageIO.read(getClass().getResource("/images/plate.png"));
		BufferedImage tomato = ImageIO.read(getClass().getResource("/images/tomato.png"));
		BufferedImage orange = ImageIO.read(getClass().getResource("/images/orange.png"));
		BufferedImage banana = ImageIO.read(getClass().getResource("/images/banana.png"));
		BufferedImage pepper = ImageIO.read(getClass().getResource("/images/pepper.png"));
		BufferedImage blueberry = ImageIO.read(getClass().getResource("/images/blueberry.png"));
		BufferedImage grape = ImageIO.read(getClass().getResource("/images/grape.png"));
		BufferedImage peach = ImageIO.read(getClass().getResource("/images/peach.png"));

		// group foods together
		Food[] foods = new Food[] { new Food("Tomato", Color.red, tomato), new Food("Orange", Color.orange, orange),
				new Food("Banana", Color.yellow, banana), new Food("Pepper", new Color(0, 156, 5), pepper),
				new Food("Blueberry", Color.blue, blueberry), new Food("Grape", new Color(159, 39, 245), grape),
				new Food("Peach", Color.pink, peach) };

		selector = new FoodSelector(foods);
		palette = new PalettePlate(plate, foods);

		// Keep selector & palette synch'ed
		selector.setOnChange(() -> {
			palette.setSelectedIndex(selector.getIndex());
		});
		palette.setOnSelect(index -> {
			selector.setIndex(index);
		});

		wall = new PaintWall(width, height, selector);

	}

	/**
	 * Get the GUI component that will be used to display the weather information.
	 * 
	 * @return The WeatherObserverPanel
	 */
	@Override
	protected JComponent getGUIComponent() {
		JPanel panel = new JPanel(null);
		JPanel gamePanel = new JPanel(null);

		// Palette at the top
		palette.setBounds(0, 0, WIDTH, 120);
		gamePanel.add(palette);

		// Wall under the palette
		JComponent wallView = wall.getView();
		wallView.setBounds(0, 120, WIDTH, HEIGHT - 120);
		gamePanel.add(wallView);
		gamePanel.setBounds(0, 0, WIDTH, HEIGHT);
		gamePanel.setVisible(false); // hidden to show game screen at first

	    final OpeningScreen[] openingRef = new OpeningScreen[1];

	    //  opening screen 
	    openingRef[0] = new OpeningScreen(() -> {
	        openingRef[0].setVisible(false);
	        gamePanel.setVisible(true);
	    });

	    OpeningScreen opening = openingRef[0];
	    opening.setBounds(0, 0, WIDTH, HEIGHT);

	    panel.add(opening);
	    panel.add(gamePanel);
		return panel;
	}

	/**
	 * Construct and invoke (in the event dispatch thread) an instance of this
	 * JApplication.
	 * 
	 * @param args The command line arguments
	 */
	public static void main(final String[] args) throws IOException {
		JApplication app = new FoodThrowMain(args);
		invokeInEventDispatchThread(app);
	}
}