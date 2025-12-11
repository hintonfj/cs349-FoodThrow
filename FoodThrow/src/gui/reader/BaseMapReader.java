package gui.reader;

// Java Library
import java.awt.*;
import java.io.*;

// Multimedia Library
import io.*;
import visual.statik.described.*;


/**
 * A class for reading BaseMap information from a BufferedReader.
 *
 * @author Prof. David Bernstein, James Madison University
 * @version 1.0
 *
 */
public class BaseMapReader
{
  private PolygonReader reader;

  /**
   * Explicit Value Constructor.
   *
   * @param finder  The ResourceFinder to use
   */
  public BaseMapReader(final ResourceFinder finder) 
  {
    this.reader = new PolygonReader(finder);
  }

  /**
   * Read a BaseMap.
   *
   * @param name  The name of the map
   * @param boundaryColor  The Color to use for the boundary
   * @param interiorColor  The Color to use for the interior
   * @return A Content object encapsulating the BaseMap
   */
  public Content read(final String name, final Color boundaryColor, final Color interiorColor) 
      throws IOException
  {
    Content map;
    Shape polygon;

    polygon = reader.read(name);

    map = new Content(polygon, boundaryColor, interiorColor, new BasicStroke());
    map.setLocation(0, 0);

    return map;
  }
}
