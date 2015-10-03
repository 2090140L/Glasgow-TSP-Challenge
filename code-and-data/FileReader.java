import java.util.*;
import java.io.*;

public class FileReader {
  public static Path readFile(String filename) throws IOException{
    ArrayList<Point> path = new ArrayList<>();
    int n = 0;
    Scanner sc = new Scanner(new File(filename));

    while(sc.hasNext()) {
      path.add(new Point(sc.nextInt(), sc.nextInt(), n++));
    }
    return new Path((Point[]) path.toArray());
  }

  public static void save(String filename, Path path) {
    PrintWriter writer = new PrintWriter(filename, "UTF-8");
    Point[] points = path.getPoints();
    for (Point p : points) {
      writer.println(p.getID());
    }
    writer.close();
  }

}
