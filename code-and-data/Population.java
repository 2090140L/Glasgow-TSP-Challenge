public class Population {

    // Holds population of tours
    Path[] paths;

    // Construct a population
    public Population(int populationSize, Path path) {
        paths = new Path[populationSize];
        paths[0] = path;
        for(int i=1; i < populationSize; i++) {
          paths[i] = path.shufflePath();
        }
    }

    public Population(int populationSize) {
      paths = new Path[populationSize];
    }

    // Saves a path
    public void savePath(int index, Path path) {
        paths[index] = path;
    }

    // Gets a path from population
    public Path getPath(int index) {
        return paths[index];
    }

    // Gets the best tour in the population
    public Path getFittest() {
        Path fittest = paths[0];
        // Loop through individuals to find fittest
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getPath(i).getFitness()) {
                fittest = getPath(i);
            }
        }
        return fittest;
    }

    // Gets population size
    public int populationSize() {
        return paths.length;
    }
}
