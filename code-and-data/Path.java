public class Path{
  private Point[] path;
  public Path(Point[] path){
    this.path = path;
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
  public int PathSize(){
      return path.length();
  }
}

