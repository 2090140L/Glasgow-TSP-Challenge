public abstract class Algorithm{
  Path path;
  public Algorithm(Path path){
    this.path = path;
  }
  public Algorithm(Point[] points){
    this.path = new Path(points);
  }
  public Path getPath(){
    return this.path();
  }
  public void setPath(Path path){
    this.path = path;
  }
  public abstract void RunAlgorithm();
  
  public abstract void RunNTimes(long n);
}
