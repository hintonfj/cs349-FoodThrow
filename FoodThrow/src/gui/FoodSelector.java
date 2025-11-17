package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FoodSelector implements KeyListener {

	private Food[] foods;
	private int index = 0;
	private Runnable onChange;

	public FoodSelector(Food[] foods) {
		this.foods = foods;
	}

	public void setOnChange(Runnable r) {
		this.onChange = r;
	}

	public Food getFood() {
		return foods[index];
	}

	// swapping colors
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_LEFT) {
			index = (index - 1 + foods.length) % foods.length;
			if (onChange != null)
				onChange.run();
		} else if (code == KeyEvent.VK_RIGHT) {
			index = (index + 1) % foods.length;
			if (onChange != null)
				onChange.run();
		}
	}

	// unused
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(Integer index2) {
		this.index = index2;
	}
}
