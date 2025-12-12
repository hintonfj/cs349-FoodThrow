package main;

import gui.*;
import gui.gamemodes.CompareWall;
import gui.gamemodes.FillWall;
import gui.gamemodes.FreePaint;

import javax.imageio.ImageIO;
import javax.swing.JButton;
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
		BufferedImage plate = ImageIO.read(getClass().getResource("/resources/images/plate.png"));
		BufferedImage tomato = ImageIO.read(getClass().getResource("/resources/images/tomato.png"));
		BufferedImage orange = ImageIO.read(getClass().getResource("/resources/images/orange.png"));
		BufferedImage banana = ImageIO.read(getClass().getResource("/resources/images/banana.png"));
		BufferedImage pepper = ImageIO.read(getClass().getResource("/resources/images/pepper.png"));
		BufferedImage blueberry = ImageIO.read(getClass().getResource("/resources/images/blueberry.png"));
		BufferedImage grape = ImageIO.read(getClass().getResource("/resources/images/grape.png"));
		BufferedImage peach = ImageIO.read(getClass().getResource("/resources/images/peach.png"));

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

	}


	@Override
	protected JComponent getGUIComponent() {
		JPanel panel = new JPanel(null);
		JPanel gamePanel = new JPanel(null);
		gamePanel.setBounds(0, 0, WIDTH, HEIGHT);
		gamePanel.setVisible(false);

		final OpeningScreen[] openingRef = new OpeningScreen[1];

		// Create OpeningScreen with three options
		openingRef[0] = new OpeningScreen(
			() -> { // Free Paint
				try {
					wall = new FreePaint(width, height, selector);
				} catch (IOException e) {
					e.printStackTrace();
				}
				showGamePanel(gamePanel, openingRef[0]);
			},
			() -> { // Compare Minigame
				try {
					wall = new CompareWall(width, height, selector);
				} catch (IOException e) {
					e.printStackTrace();
				}
				showGamePanel(gamePanel, openingRef[0]);
			},
			() -> { // Coming Soon
				try {
					wall = new FillWall(width, height, selector, Color.WHITE);
				} catch (IOException e) {
					e.printStackTrace();
				}
				showGamePanel(gamePanel, openingRef[0]);
			}
		);

		OpeningScreen opening = openingRef[0];
		opening.setBounds(0, 0, WIDTH, HEIGHT);

		panel.add(opening);
		panel.add(gamePanel);

		return panel;
	}

	/**
	 * Shows the game panel and adds the wall and palette after wall is initialized.
	 */
	private void showGamePanel(JPanel gamePanel, OpeningScreen opening) {
		// Hide opening screen
		opening.setVisible(false);

		// Clear the panel in case anything is leftover
		gamePanel.removeAll();

		// Add palette at the top
		palette.setBounds(10, 25, WIDTH, 120);
		gamePanel.add(palette);
		
		// add back button
	    JButton backButton = new JButton("Back");
	    backButton.setBounds(0, 0, 80, 30);
	    backButton.addActionListener(e -> {
	        if (wall instanceof CompareWall compare) {
	            compare.closePreview();
	        }
	        gamePanel.setVisible(false);
	        opening.setVisible(true);
	    });
	    gamePanel.add(backButton);
	    
		if (wall != null) {
			// Add wall view under palette
			JComponent wallView = wall.getView();
			wallView.setBounds(0, 120, WIDTH, HEIGHT - 120);
			gamePanel.add(wallView);
			if (wall instanceof FreePaint freePaint) {
			    JButton saveButton = new JButton("Save");
			    saveButton.setBounds(WIDTH - 100, 0, 80, 30); // position under the palette
			    saveButton.addActionListener(e -> freePaint.saveFile());
			    gamePanel.add(saveButton);
			}

	        if (wall instanceof CompareWall compare) {
	            compare.bringTimerToFront();
	        }
		}
		gamePanel.revalidate();
		gamePanel.repaint();
		gamePanel.setVisible(true);
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