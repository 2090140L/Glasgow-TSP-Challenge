import java.lang.Math;

public class Point{
  private int id;
  private double x;
  private double y;
  public Point(double x, double y, int id){
    this.x = x;
    this.y = y;
    this.id = id;
  }
  public double[] getPoint(){
    return new double[] {x,y};
  }
  public int getID(){
    return id;
  }
  public double getDistance(Point p){
    double[] secondPoint = p.getPoint();
    double nablaX = secondPoint[0] - this.x;
    double nablaY = secondPoint[1] - this.y;
    return Math.sqrt(nablaX*nablaX + nablaY*nablaY);
  }
}
