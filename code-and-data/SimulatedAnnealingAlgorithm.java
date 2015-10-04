public class SimulatedAnnealingAlgorithm extends Algorithm {

	double temp = 10000;
	double coolingRate = 0.003;

	public static double acceptanceProbability (double energy, double newEnergy, double temp){
		if (newEnergy < energy){
			return 1.0;
		}
		return Math.exp((energy - newEnergy) / temp);
	}

	public SimulatedAnnealingAlgorithm(Path p) {
		super(p);
	}

	public SimulatedAnnealingAlgorithm(Point[] p) {
		super(p);
	}

	//initialize initial solution
	public void RunAlgorithm(){
		int pointPos1, pointPos2;
		while(temp>1){
			pointPos1 = pointPos2 = 0;

			Path comparisonPath = path;

			//get random positions in the path
			while (pointPos1 == 0 || pointPos2 == 0) {
				pointPos1 = (int) (comparisonPath.pathSize() * Math.random());
				pointPos2 = (int) (comparisonPath.pathSize() * Math.random());
			}


			//get the corresponding points
			Point point1 = comparisonPath.getPoint(pointPos1);
			Point point2 = comparisonPath.getPoint(pointPos2);

			//swap the points in the comparison path.
			comparisonPath.setPoint(pointPos1, point2);
			comparisonPath.setPoint(pointPos2, point1);

			//get distances of the original and comparisonPath.
			double currentDistance = path.getDistance();
			double comparisonDistance = comparisonPath.getDistance();

			//Decide which is better
			double acceptanceProbabilityFactor = acceptanceProbability(currentDistance, comparisonDistance, temp);
			//System.out.println(acceptanceProbabilityFactor);
			if (acceptanceProbabilityFactor > Math.random()){
				super.setPath(comparisonPath);
			}

			if (path.getDistance() < bestPath.getDistance()){
				super.setBestPath(super.getPath());
				System.out.println(super.getBestPath().toString());
			}

			//Cooldown
			temp *= (1-coolingRate);
		}

	}

	@Override
	public void RunNTimes(long n){
		super.setBestPath(path.shufflePath());
		while(n>0) {
			temp = 100000;
			RunAlgorithm();
			n--;
		}

	}
}
