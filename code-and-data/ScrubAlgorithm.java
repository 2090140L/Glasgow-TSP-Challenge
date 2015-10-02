public class ScrubAlgorithm extends Algorithm{
  public ScrubAlgorithm(Path p){
    super(p);
  }
  public ScrubAlgorithm(Points[] p){
    super(p);
  }
  public void RunAlgorithm(){
    this.setPath(Scrub(this.getPath()));
  }
  public void RunNTimes(long n){
    this.setPath(Scrub(this.getPath()));
  }
  public Path
}
