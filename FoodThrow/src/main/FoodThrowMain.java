package main;

import gui.*;
import javax.swing.JComponent;
import java.io.IOException;

import app.JApplication;

public class FoodThrowMain extends FoodThrowApplication
{
  private PaintWall wall;
  
  /**
   * Explicit value constructor.
   * 
   * @param args   The command line arguments
   */
  public FoodThrowMain(final String[] args) throws IOException
  {
    super(args);
    wall = new PaintWall(width, height);
  }
    
  /**
   * Get the GUI component that will be used to display the weather information.
   * 
   * @return The WeatherObserverPanel
   */
  @Override
  protected JComponent getGUIComponent()
  {
    return wall.getView();
  }
  
  /**
   * Construct and invoke  (in the event dispatch thread) 
   * an instance of this JApplication.
   * 
   * @param args The command line arguments
   */
  public static void main(final String[] args) throws IOException
  {
    JApplication app = new FoodThrowMain(args);
    invokeInEventDispatchThread(app);
  }
}