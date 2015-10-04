public class GeneticAlgorithm extends Algorithm {

  private static final double mutationRate = 0.015;
  private static final int pathGroupSize = 5;
  private static final boolean elitism = true;

  public GeneticAlgorithm(Path initialPath) {
    super(initialPath);
  }
  // Evolves a population over one generation
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize());

        // Keep our best individual if elitism is enabled
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.savePath(0, pop.getFittest());
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            // Select parents
            Path parent1 = pathGroupSelection(pop);
            Path parent2 = pathGroupSelection(pop);
            // Crossover parents
            Path child = crossover(parent1, parent2);
            // Add child to new population
            newPopulation.savePath(i, child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getPath(i));
        }

        return newPopulation;
    }

    // Applies crossover to a set of parents and creates offspring
    public static Path crossover(Path parent1, Path parent2) {
        // Create new child path
        Path child = new Path(parent1.pathSize());

        // Get start and end sub path positions for parent1's path
        int startPos = (int) (Math.random() * parent1.pathSize());
        int endPos = (int) (Math.random() * parent1.pathSize());

        // Loop and add the sub path from parent1 to our child
        for (int i = 0; i < child.pathSize(); i++) {
            // If our start position is less than the end position
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setPoint(i, parent1.getPoint(i));
            } // If our start position is larger
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setPoint(i, parent1.getPoint(i));
                }
            }
        }

        // Loop through parent2's point path
        for (int i = 0; i < parent2.pathSize(); i++) {
            // If child doesn't have the point add it
            if (!child.containsPoint(parent2.getPoint(i))) {
                // Loop to find a spare position in the child's path
                for (int ii = 0; ii < child.pathSize(); ii++) {
                    // Spare position found, add point
                    if (child.getPoint(ii) == null) {
                        child.setPoint(ii, parent2.getPoint(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    // Mutate a path using swap mutation
    private static void mutate(Path path) {
        // Loop through path cities
        for(int pathPos1=0; pathPos1 < path.pathSize(); pathPos1++){
            // Apply mutation rate
            if(Math.random() < mutationRate){
                // Get a second random position in the path
                int pathPos2 = (int) (path.pathSize() * Math.random());

                // Get the cities at target position in path
                Point point1 = path.getPoint(pathPos1);
                Point point2 = path.getPoint(pathPos2);

                // Swap them around
                path.setPoint(pathPos2, point1);
                path.setPoint(pathPos1, point2);
            }
        }
    }

    // Selects candidate path for crossover
    private static Path pathGroupSelection(Population pop) {
        // Create a path population
        Population paths = new Population(pathGroupSize);
        // For each place in the paths get a random candidate path and
        // add it
        for (int i = 0; i < pathGroupSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            paths.savePath(i, pop.getPath(randomId));
        }
        // Get the fittest path
        Path fittest = paths.getFittest();
        return fittest;
    }


  public void RunAlgorithm() {
    Population pop = new Population(100, super.getPath());
    for (int i=0; i < 1000; i++) {
      pop = GeneticAlgorithm.evolvePopulation(pop);
    }
    super.setBestPath(pop.getFittest());
  }

  public void RunNTimes(long n) {
    Population pop = new Population(100, super.getPath());
    super.setBestPath(super.getPath());
    for (int i=0; i < n; i++) {
      pop = GeneticAlgorithm.evolvePopulation(pop);
      if (i % 1000 == 0) {

        if (super.getBestPath().getDistance() > pop.getFittest().getDistance()) {
          super.setBestPath(pop.getFittest());
        }
        //System.out.println("Current best: " + super.getBestPath().getDistance());

      }
    }
    super.setBestPath(pop.getFittest());
  }

}
