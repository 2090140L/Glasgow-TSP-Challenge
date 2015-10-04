import java.util.ArrayList;

public class HillClimbingAlgorithm extends Algorithm{
  private final static int HEURISTICCONSTANT = 15;
  public HillClimbingAlgorithm(Path p){
    super(p);
  }
  public HillClimbingAlgorithm(Point[] p){
    super(p);
  }
  public void RunAlgorithm(){
    this.setBestPath(search(this.getPath().getPoint(0)));
  }
  public void RunNTimes(long n){
    RunAlgorithm();
  }
  public Path search(Point p){
    Point current = p;
    ArrayList<Point> newPath = new ArrayList<Point>();
    newPath.add(current);
    while(newPath.size() < this.getPath().pathSize()){
      Point chosen = null;
      for(Point pa : this.getPath().getPoints()){
        if( ! in(pa, newPath) && chosen == null){
          chosen = pa;
        } else if (! in(pa, newPath) && chosen.getDistance(current) > p.getDistance(current)){
          chosen = pa;
        }
      }
      newPath.add(chosen);
      current = chosen;
    }
    newPath.add(current);
    return new Path(newPath);
  }
  public boolean in(Point p, ArrayList<Point> l){
    for(Point po : l){
      if(po.getID() == p.getID()){
        return true;
      }
    }
    return false;
  }
}
