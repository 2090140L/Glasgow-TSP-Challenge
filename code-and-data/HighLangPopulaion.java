public class HighLangPopulaion extends Population{
  public HighLangPopulaion(int size, Path p){
    super(p.pathSize());
    HillClimbingAlgorithm ha = new HillClimbingAlgorithm(p);
    for(int i = 0; i < p.pathSize(); i ++){
      paths[i] = ha.search(p.getPoint(i));
    }
  }
  public HighLangPopulaion(int size){
    super(size);
  }
  
}
