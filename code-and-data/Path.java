import java.lang.StringBuilder;
import java.util.*;
import java.io.*;

public class Path {
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

  public Path(int n) {
    this.path = new Point[n];
  }

  public Path shufflePath() {
    int pointPos1, pointPos2;
    Path newPath = duplicate();
    for (int i = 0; i < 100; i++) {
      pointPos1 = pointPos2 = 0;
      while (pointPos1 == 0 || pointPos2 == 0) {
				pointPos1 = (int) (newPath.pathSize() * Math.random());
				pointPos2 = (int) (newPath.pathSize() * Math.random());
			}
			Point point1 = newPath.getPoint(pointPos1);
      Point point2 = newPath.getPoint(pointPos2);
      newPath.setPoint(pointPos1, point2);
      newPath.setPoint(pointPos2, point1);
    }
    return newPath;
  }

  public Path(Path path) {
    this.path = path.getPoints();
  }

  public Point[] getPoints(){
    return this.path;
  }

  public double getDistance(){
    double d = path[0].getDistance(path[path.length-1]);
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

  public boolean containsPoint(Point p) {
    if (p == null) return false;
    for(int i = 0; i < path.length; i++) {
      if(path[i] != null && path[i].equals(p)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Point p : path) {
      sb.append(p.getID() + " ");
    }
    return sb.toString();
  }

  public Path duplicate() {
    Path newPath = new Path(pathSize());
    for(int i=0; i < pathSize(); i++) {
      Point point = getPoint(i);
      newPath.setPoint(i, new Point(point.getX(), point.getY(), i));
    }
    return newPath;
  }

  public double getFitness() {
    return 1/(double)getDistance();
  }

}
