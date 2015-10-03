import java.util.ArrayList;

public class ScrubAlgorithm extends Algorithm{
  public ScrubAlgorithm(Path p){
    super(p);
  }
  public ScrubAlgorithm(Point[] p){
    super(p);
  }
  public void RunAlgorithm(){
    this.setPath(Scrub(this.getPath()));
  }
  public void RunNTimes(long n){
    this.setPath(Scrub(this.getPath()));
  }
  public Point getCenter(Path path){
    double x = 0;
    double y = 0;
    for(Point p : path.getPoints()){
      x += p.getPoint()[0];
      y += p.getPoint()[1];
    }
    x /= path.pathSize();
    y /= path.pathSize();
    return new Point(x,y,-1);
  }
  public Path Scrub(Path p){
    Path p1 = null;
    Path p2 = null;
    Path p3 = null;
    if(p.pathSize() > 3){
      Point origin = getOrigin(p);
      Point top = getTop(p);
      Point right = getRight(p);
      ArrayList<Point> pl1 = new ArrayList<Point>();
      ArrayList<Point> pl2 = new ArrayList<Point>();
      ArrayList<Point> pl3 = new ArrayList<Point>();
      pl1.add(origin);
      pl2.add(top);
      pl3.add(right);
      for(Point point : p.getPoints()){
        if(point.getID() != origin.getID() && point.getID() != top.getID() && point.getID() != right.getID()){
          double d1 = point.getDistance(pl1.get(0));
          double d2 = point.getDistance(pl2.get(0));
          double d3 = point.getDistance(pl3.get(0));
          if(d1 >= d2 && d1 >= d3){
            pl1.add(point);
          } else if(d2 >= d1 && d2 >= d3){
            pl2.add(point);
          } else{
            pl3.add(point);
          }
        }
      }
      p1 = Scrub(new Path(pl1));
      p2 = Scrub(new Path(pl2));
      p3 = Scrub(new Path(pl3));
    } else {
      p1 = new Path(new Point[] {p.getPoints()[0]});
      if(p.pathSize() > 1){
        p2 = new Path(new Point[] {p.getPoints()[1]});
      }
      if(p.pathSize() > 2){
        p3 = new Path(new Point[] {p.getPoints()[2]});
      }
    }
    if( p2 == null){
      return p1;
    } else if( p3 == null){
      return new Path(new Point[] {p1.getPoint(0), p2.getPoint(2)});
    } else {
      Point c1 = getCenter(p1);
      Point c2 = getCenter(p2);
      Point c3 = getCenter(p3);
      double c1c2 = c1.getDistance(c2);
      double c1c3 = c1.getDistance(c3);
      double c2c3 = c2.getDistance(c3);
      Path pa = null;
      if(c1c2 >= c1c3 && c1c2 >= c2c3){
        // Join 1 and 3, and 3 and 2
        pa = pathJoin(p1,p3);
        pa = pathJoin(pa,p2);
      } else if(c1c3 >= c1c2 && c1c3 >= c2c3){
        // Join 1 and 2 and 2 and 3
        pa = pathJoin(p1,p2);
        pa = pathJoin(pa,p3);
      } else {
        // Join 2 and 1, and 1 and 3
        pa = pathJoin(p2,p1);
        pa = pathJoin(p1,p2);
      }
      return pa;
    }
  }
  public Path pathJoin(Path p1, Path p2){
    Point initial1 = getCenter(p1);
    Point initial2 = p2.getPoint(0);
    for(Point p : p2.getPoints()){
      if(p.getDistance(initial1) > initial2.getDistance(initial1)){
        initial2 = p;
      }
    }
    Point second1 = p1.getPoint(0);
    for(Point p : p1.getPoints()){
      if(p.getDistance(initial2) > second1.getDistance(initial2)){
        second1 = p;
      }
    }
    Point second2 = initial2;
    for(Point p : p2.getPoints()){
      if(p.getDistance(second1) > second2.getDistance(second1)){
        second2 = p;
      }
    }
    Path tmp1 = cycle(p1, second1);
    Path tmp2 = cycle(p2, second2);
    tmp1 = cycle(tmp1, tmp1.getPoint(tmp1.pathSize() - 1));
    Point[] ret = new Point[p1.pathSize() + p2.pathSize()];
    int count = 0;
    for(Point p : tmp1.getPoints()){
      ret[count] = p;
      count ++;
    }
    for(Point p : tmp2.getPoints()){
      ret[count] = p;
      count ++;
    }
    return new Path(ret);
  }
  public Path cycle(Path path, Point point){
    Point[] newPath = new Point[path.pathSize()];
    int end = 0;
    boolean found = false;
    int count = 0;
    for(Point p : path.getPoints()){
      if(p.getID() == point.getID()){
        found = true;
      }
      if(! found){
        end ++;
      } else {
        newPath[count] = p;
        count ++;
      }
    }
    for(int i = 0; i < end; i ++){
      newPath[i + count] = path.getPoint(i);
    }
    return new Path(newPath);
  }
  public Point getOrigin(Path path){
    Point O = new Point(0,0,-1);
    Point ret = null;
    for(Point p : path.getPoints()){
      double d = p.getDistance(O);
      if(d == 0){
        return p;
      }
      if(d < p.getDistance(ret)){
        ret = p;
      }
    }
    return ret;
  }
  public Point getTop(Path path){
    Point top = new Point(0,0,-1);
    for(Point p : path.getPoints()){
      if(p.getPoint()[0] > top.getPoint()[1]){
        top = p;
      }
    }
    return top;
  }
  public Point getRight(Path path){
    Point right = new Point(0,0,-1);
    for(Point p : path.getPoints()){
      if(p.getPoint()[1] > right.getPoint()[1]){
        right = p;
      }
    }
    return right;
  }
}
