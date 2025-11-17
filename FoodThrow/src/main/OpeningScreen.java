package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OpeningScreen extends JPanel {

    public OpeningScreen(Runnable onStart) {

        setLayout(null);
        setBackground(new Color(245, 245, 245));

        // --- Load Logo ---
        BufferedImage logoImage = null;
        try {
            logoImage = ImageIO.read(getClass().getResource("/images/logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (logoImage != null) {

            int targetWidth = 450;      // make this bigger or smaller
            int originalWidth = logoImage.getWidth();
            int originalHeight = logoImage.getHeight();

            // Maintain aspect ratio
            double scale = (double) targetWidth / originalWidth;
            int scaledWidth = targetWidth;
            int scaledHeight = (int) (originalHeight * scale);

            Image scaled = logoImage.getScaledInstance(
                    scaledWidth, scaledHeight,
                    Image.SCALE_SMOOTH
            );

            JLabel logo = new JLabel(new ImageIcon(scaled));

            // Center it horizontally
            logo.setBounds((600 - scaledWidth) / 2, -20, scaledWidth, scaledHeight);

            add(logo);
        }

        // Free paint mode
        JButton startButton = new JButton("Free Paint");
        startButton.setFont(new Font("Monaco", Font.BOLD, 18));
        startButton.setBounds(225, 410, 150, 50);
        startButton.addActionListener(e -> onStart.run());
        add(startButton);

        // game mode 1
        JButton opt1 = new JButton("Coming Soon");
        opt1.setFont(new Font("Monaco", Font.PLAIN, 18));
        opt1.setBounds(225, 465, 150, 50);
        add(opt1);

        // game mode 2
        JButton opt2 = new JButton("Coming Soon");
        opt2.setFont(new Font("Monaco", Font.PLAIN, 18));
        opt2.setBounds(225, 520, 150, 50);
        add(opt2);
    }
}
