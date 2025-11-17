package gui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Food {
	public String name;
	public Color color;
	public BufferedImage image;

	public Food(String name, Color color, BufferedImage image) {
		this.name = name;
		this.color = color;
		this.image = image;
	}
}
