package gui;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Food class
 */
public class Food {
	public String name;
	public Color color;
	public BufferedImage image;

	/**
	 * Creates a Food Object.
	 * @param name name of the food
	 * @param color Color of the food
	 * @param image a photo of the food
	 */
	public Food(final String name, final Color color, final BufferedImage image) {
		this.name = name;
		this.color = color;
		this.image = image;
	}
}
