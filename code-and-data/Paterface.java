import java.util.*;
import java.io.*;

public class Paterface {

  public static void main(String[] args) throws IOException{
    // construct the Path from the origonal file
    Path path = FileReader.readFile(args[0]);
    // get the algorithm and run it
    //System.out.println(path.toString());
    Algorithm algorithm = null;
    switch(args[1].charAt(0)){
      case 'A':
        algorithm = new SimulatedAnnealingAlgorithm(path);
        break;
      case 'G':
        algorithm = new GeneticAlgorithm(path);
        break;
      case 'S':
        algorithm = new ScrubAlgorithm(path);
        break;
      case 'H':
        algorithm = new HillClimbingAlgorithm(path);
        break;
    }
    long start = System.currentTimeMillis();
    algorithm.RunNTimes(100);
    long end = System.currentTimeMillis();
    FileReader.save(args[1] + "test.txt", algorithm.getBestPath());
    // create a TSP
    TSP tsp = new TSP(args[0]);
    tsp.plot(path.getIDs());
    long difference = (end - start) / 1000;
    System.out.println("Time taken (s): " + difference);
    System.out.println("Path taken: " + algorithm.getBestPath().toString());
    System.out.println("Best: " + algorithm.getBestPath().getDistance());

  }

}

/*
convert the TSP file into a path
pass this through the algorithm
validate the path (see code in display) - now done in algorithm
use tsp class to display the result
*/
