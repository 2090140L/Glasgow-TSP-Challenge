import java.lang.StringBuilder;
import java.util.*;
import java.io.*;

public class Path{
  private Point[] path;

  public Path(ArrayList<Point> array) {
    path = new Point[array.size()];
    for (int i=0; i < array.size(); i++) {
      path[i] = array.get(i);
    }
  }

  public Path(Point[] path){
    this.path = path;
  }

  public Path(Path path) {
    this.path = path.getPoints();
  }

  public Point[] getPoints(){
    return this.path;
  }

  public double getDistance(){
    double d = 0;
    for(int i = 1; i < path.length; i ++){
      d += path[i].getDistance(path[i-1]);
    }
    return d;
  }

  public Point getPoint(int i){
    return path[i];
  }

  public void setPoint(int i, Point newPoint){
    path[i] = newPoint;
  }

  //Retrieve the size of the Path - Alex.
  public int pathSize(){
    return path.length;
  }

  public int[] getIDs() {
    int[] IDs = new int[path.length];
    for (int i = 0; i < IDs.length; i++) {
      IDs[i] = path[i].getID();
    }
    return IDs;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Point p : path) {
      sb.append(p.getID() + "\n");
    }
    return sb.toString();
  }



}
