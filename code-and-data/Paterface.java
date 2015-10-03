import java.util.*;
import java.io.*;

public class Paterface {

  public static void main(String[] args) throws IOException{
    // construct the Path from the origonal file
    Path path = FileReader.readFile(args[0]);
    // get the algorithm and run it
    Algorithm algorithm = new SimulatedAnnealingAlgorithm(path);
    algorithm.RunAlgorithm();
    FileReader.save("test.txt", algorithm.getBestPath());
    // create a TSP
    //TSP tsp = new TSP(args[0]);
    //tsp.plot(path.getIDs());
    System.out.println(algorithm.getBestPath().toString());
    System.out.println(algorithm.getBestPath().getDistance());

  }

}

/*
convert the TSP file into a path
pass this through the algorithm
validate the path (see code in display) - now done in algorithm
use tsp class to display the result
*/
