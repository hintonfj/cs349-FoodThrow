package gui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ColorSelector implements KeyListener {
    private Color[] colors;
    private int index = 0;

    public ColorSelector() {
        this(new Color[] { Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta, Color.pink });
    }

    public ColorSelector(final Color[] colors) {
        this.colors = colors;
        this.index = 0;
    }

    @Override
    public void keyTyped(KeyEvent event) {
        // not used
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int code = event.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            index = (index - 1 + colors.length) % colors.length;
        } else if (code == KeyEvent.VK_RIGHT) {
            index = (index + 1) % colors.length;
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

    public Color getColor() {
        return colors[index];
    }
}