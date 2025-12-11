package gui.reader;

// Java Library
import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;

// Multimedia Library
import io.*;

/**
 * A class for reading Polygon information using a ResourceFinder.
 *
 * @author Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class PolygonReader
{
  private ResourceFinder finder;

  /**
   * Default Constructor.
   */
  public PolygonReader()
  {
    this.finder = null;
  }
  
  /**
   * Explicit Value Constructor.
   *
   * @param finder  The ResourceFinder to use
   */
  public PolygonReader(final ResourceFinder finder)
  {
    this.finder = finder;
  }

  /**
   * Read a Polygon.
   *
   * @param name The name of the resource containing the polygon
   * @return A Shape object encapsulating the polygon
   */
  public Shape read(final String name) throws IOException
  {
    BufferedReader reader;
    float x, y;
    Path2D.Float polygon;
    InputStream is;
    int type;
    String line, token;
    StringTokenizer tokenizer;

    if (finder != null)
    {
      is = finder.findInputStream(name);
      reader = new BufferedReader(new InputStreamReader(is));
    }
    else
    {
      reader = new BufferedReader(new FileReader(name));
    }

    polygon = new Path2D.Float();

    while ((line = reader.readLine()) != null)
    {
      try
      {
        tokenizer = new StringTokenizer(line, ",");

        token = tokenizer.nextToken();
        type = Integer.parseInt(token);

        token = tokenizer.nextToken();
        x = Float.parseFloat(token);

        token = tokenizer.nextToken();
        y = Float.parseFloat(token);

        if (type == 4)
          polygon.moveTo(x, y);
        else
          polygon.lineTo(x, y);
      }
      catch (NumberFormatException nfe)
      {
        // Ignore
      }
      catch (NoSuchElementException nsee)
      {
        // Ignore
      }
    }
    polygon.closePath();

    return polygon;
  }

}
