package main;

import javax.swing.*;

import io.ResourceFinder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

/**
 * Opening Screen.
 */
public class OpeningScreen extends JPanel
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String compareHelpText;
  private String fillHelpText;
  private String newLine = "\n";
  private String help = "?";
  private String fontType = "Monaco";

  /**
   * Creates an OpeningScreen.
   * 
   * @param onStartFreePaint
   *          Free Paint Runnable
   * @param onStartOpt1
   *          Compare Paint Runnable
   * @param onStartOpt2
   *          Fill Paint Runnable
   */
  public OpeningScreen(final Runnable onStartFreePaint, final Runnable onStartOpt1,
      final Runnable onStartOpt2)
  {
    compareHelpText = "";
    fillHelpText = "";
    setUp();
    setLayout(null);
    setBackground(new Color(245, 245, 245));

    // --- Load Logo ---
    BufferedImage logoImage = null;
    try
    {
      logoImage = ImageIO.read(getClass().getResource("/resources/images/logo.png"));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    if (logoImage != null)
    {

      int targetWidth = 450; // make this bigger or smaller
      int originalWidth = logoImage.getWidth();
      int originalHeight = logoImage.getHeight();

      // Maintain aspect ratio
      double scale = (double) targetWidth / originalWidth;
      int scaledWidth = targetWidth;
      int scaledHeight = (int) (originalHeight * scale);

      Image scaled = logoImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

      JLabel logo = new JLabel(new ImageIcon(scaled));

      // Center it horizontally
      logo.setBounds((600 - scaledWidth) / 2, -20, scaledWidth, scaledHeight);

      add(logo);
    }
    
    Font fBold = new Font(fontType, Font.BOLD, 18);
    Font fPlain = new Font(fontType, Font.PLAIN, 18);

    // Free paint mode
    JButton startButton = new JButton("Free Paint");
    startButton.setFont(fBold);
    startButton.setBounds(225, 410, 150, 50);
    startButton.addActionListener(e -> onStartFreePaint.run());
    add(startButton);

    // game mode 1
    JButton opt1 = new JButton("Speed Paint");
    opt1.setFont(fBold);
    opt1.setBounds(225, 465, 150, 50);
    opt1.addActionListener(e -> onStartOpt1.run());
    add(opt1);

    JButton help1 = new JButton(help);
    help1.setFont(fPlain);
    help1.setBounds(385, 465, 50, 50);
    help1.addActionListener(e -> printDialog(compareHelpText));
    add(help1);

    // game mode 2
    JButton opt2 = new JButton("Fill Paint");
    opt2.setFont(fBold);
    opt2.setBounds(225, 520, 150, 50);
    opt2.addActionListener(e -> onStartOpt2.run());
    add(opt2);

    JButton help2 = new JButton(help);
    help2.setFont(fPlain);
    help2.setBounds(385, 520, 50, 50);
    help2.addActionListener(e -> printDialog(fillHelpText));
    add(help2);
  }

  /**
   * Prints a JOption with text.
   * 
   * @param text
   *          the text to print
   */
  private void printDialog(final String text)
  {
    JOptionPane.showMessageDialog(null, text);
  }

  /**
   * Sets up the help text documentation.
   */
  private void setUp()
  {
    ResourceFinder rf = ResourceFinder.createInstance(new resources.Marker());
    String current = "";
    try
    {
      InputStream is = rf.findInputStream("compareAbout.txt");
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      while ((current = br.readLine()) != null)
        compareHelpText += current + newLine;
    }
    catch (NullPointerException | IOException e)
    {
      JOptionPane.showMessageDialog(null, "compareAbout.txt cannot be found");
      System.exit(0);
    }
    current = "";
    try
    {
      InputStream is = rf.findInputStream("fillAbout.txt");
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      while ((current = br.readLine()) != null)
        fillHelpText += current + newLine;
    }
    catch (NullPointerException | IOException e)
    {
      JOptionPane.showMessageDialog(null, "fillAbout.txt cannot be found");
      System.exit(0);
    }
  }
}
