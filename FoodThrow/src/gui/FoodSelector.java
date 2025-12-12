package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Food selector.
 */
public class FoodSelector implements KeyListener {

	private Food[] foods;
	private int index = 0;
	private Runnable onChange;

	/**
	 * Creates a food selector.
	 * @param foods array of foods
	 */
	public FoodSelector(Food[] foods) {
		this.foods = foods;
	}

	/**
	 * Changes the runnable.
	 * @param r the runnable it changes to
	 */
	public void setOnChange(Runnable r) {
		this.onChange = r;
	}

	/**
	 * Returns the current food its on.
	 * @return the current food
	 */
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

	/**
	 * Returns current index.
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets current index to new index.
	 * @param index2 new index
	 */
	public void setIndex(Integer index2) {
		this.index = index2;
	}
}
