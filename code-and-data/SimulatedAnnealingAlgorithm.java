public class SimulatedAnnealingAlgorithm extends Algorithm {
	
	double temp = 10000;
	double coolingRate = 0.003 ; 

	public static double acceptanceProbability (int energy, int newEnergy, double temp){
		if (newEnergy < energy){
			return 1.0;
		}

		return Math.exp((energy - newEnergy) / temp);
	}
	
	public SimulatedAnnealingAlgorithm(Path p) {
		System.out.println("Initial path distance: " + p.getDistance());
		super(p);
	}

	public SimulatedAnnealingAlgorithm(Points[] p) {
		super(p);
	}

	Path bestPath = new Path("TO BE FILLED IN"); //!!!!!!!!!!!

	//initialize initial solution
	public static void RunAlgorithm(){
		while(temp>1){
			
			Path comparisonPath = new Path("TO BE FILLED IN"); //!!!!!!!!!!!
			
			//get random positions in the path
			int pointPos1 = (int) (comparisonPath.pathSize() * Math.random());
			int pointPos2 = (int) (comparisonPath.pathSize() * Math.random());

			//get the corresponding points
			Point point1 = comparisonPath.getPoint(pointPos1);
			Point point2 = comparisonPath.getPoint(pointPos2);

			//swap the points in the comparison path.
			comparisonPath.setPoint(pointPos1, point1);
			comparisonPath.setPoint(pointPos2, point2);

			//get distances of the original and comparisonPath.
			int currentDistance = path.getDistance();
			int comparisonDistance = comparisonPath.getDistance();

			//Decide which is better
			if (acceptanceProbability(currentDistance, comparisonDistance, temp) > Math.random()){
				super(comparisonPath); //!!!!!!!!!!!!!!
			}

			if (path.getDistance() < best.getDistance()){
				best = new Path("TO BE FILLED IN") //!!!!!!! 
			}
		}
	//Cooldown
	temp *= (1-coolingRate);
	
	}

}
