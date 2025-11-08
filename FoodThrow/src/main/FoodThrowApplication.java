package main;

import java.awt.event.ActionListener;

import app.JApplication;
import javax.swing.JPanel;
import javax.swing.JComponent;

public abstract class FoodThrowApplication extends JApplication{

  public static final int WIDTH  = 600;
  public static final int HEIGHT = 800;
  
  /**
   * Explicit value constructor.
   * 
   * @param args   The command line arguments
   */
  public FoodThrowApplication(final String[] args)
  {
    super(args, WIDTH, HEIGHT);
  }

    /**
   * Get the GUI components to use.
   * 
   * @return The WeatherObserverPanel
   */
  protected abstract JComponent getGUIComponent();
  

  /**
   * Initialize this JApplication (required by JApplication).
   * Specifically, construct and layout the JFrame.
   */
  @Override
  public void init()
  {
    // Setup the content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.setLayout(null);
    
    JComponent weatherObserverComponent = getGUIComponent();
    weatherObserverComponent.setBounds(0, 60, WIDTH, HEIGHT-60);
    contentPane.add(weatherObserverComponent);
  }
}
