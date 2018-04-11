import java.util.Random;

public class AntSystem {
	
	private Problem prob;
	private Configuration conf;
	
	private Ant[] ants;
	
	private double[][] pheros;
	
	private double alpha;
	private double beta;
	private double evaporationValue;
	private double Q;
	
	public AntSystem(Problem p, Configuration c){
		prob = p;
		conf = c;
		ants = new Ant[c.getPopSize()];
		pheros = new double[p.getInstance().length][p.getInstance().length];
		Q = 1;
	}

	public double run() {
		initilize();
		int itr = 0;
		while(itr < conf.getMaxitr()){
			constructSolutionsWithAnts();
			updatePheromones();
			updateSolution();
			itr++;
		}
		// TODO Auto-generated method stub
		return prob.getBestSolutionValue();
	}

	private void updateSolution() {
		// TODO Auto-generated method stub
		double minTour = ants[0].getFitness();
		Ant itrBestAnt = ants[0];
		for(int i = 1; i < ants.length; i++){
			if(ants[i].getFitness() < minTour)
				itrBestAnt = ants[i];
		}
		
		if(itrBestAnt.getFitness() < prob.getBestSolutionValue()){
			prob.setBestSolution(itrBestAnt.getMemory());
			prob.setBestSolutionValue(itrBestAnt.getFitness());
		}
	}

	private void updatePheromones() {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < ants.length; i++){
			ants[i].calculateFitness();
			applyPheromoneUpdatingByAnt(ants[i]);
		}
	}

	private void applyPheromoneUpdatingByAnt(Ant ant) {
		// TODO Auto-generated method stub
		int[] antMemory = ant.getMemory();
		for(int i = 0; i < antMemory.length-1; i++){
			int currentCity = antMemory[i];
			int nextCity = antMemory[i+1];
			pheros[currentCity][nextCity] = pheros[currentCity][nextCity] * (1-evaporationValue) + Q / ant.getFitness();
		}
	}

	private void constructSolutionsWithAnts() {
		// TODO Auto-generated method stub
		initAnts();
		for(int i = 1; i < prob.getInstance().length; i++){
			moveAllAntsToNextCity();
		}
		
		for(int i = 0; i < ants.length; i++)
			ants[i].moveTo(ants[i].getMemory()[0]);
	}

	private void moveAllAntsToNextCity() {
		// TODO Auto-generated method stub
		for(int i = 0; i < ants.length; i++)
			moveAntToNextCity(ants[i]);
	}

	private void moveAntToNextCity(Ant ant) {
		// TODO Auto-generated method stub
		int nextCity = decideNextCity(ant);
		ant.moveTo(nextCity);
	}

	public int decideNextCity(Ant a) {
		// TODO Auto-generated method stub
		int currentCity = a.getCurrentCity();
		
		double[] numerators = new double[prob.getInstance().length];
		double denominator = 0;
		for(int i = 0; i < numerators.length; i++){
			if(!a.visitedTo(i)){
				double anumerator = Math.pow(pheros[currentCity][i], alpha) *
							 Math.pow(1.0/prob.getInstance()[currentCity][i], beta);
				numerators[i] = anumerator;
				denominator += anumerator;
			}
		}
		
		double product = denominator * Math.random();
		double sumOfNumerators = 0.0;
		for(int i = 0; i < numerators.length; i++){
			if(!a.visitedTo(i)){
				sumOfNumerators += numerators[i];
				if(product <= sumOfNumerators)
					return i;
			}
		}
		return -1;
	}
	


	private void initilize() {
		// TODO Auto-generated method stub
		initializeParameters();
		createAnts();
		initPheromones();
	}

	private void createAnts() {
		// TODO Auto-generated method stub
		for(int i = 0; i < ants.length; i++)
			ants[i] = new Ant(i, prob);
	}

	private void initAnts() {
		// TODO Auto-generated method stub
		initAntsMemory();
		placeAntsToInitialPositions();
	}

	private void placeAntsToInitialPositions() {
		// TODO Auto-generated method stub
		Random r = new Random();
		for(int i = 0; i < ants.length; i++){
			int firstCity = r.nextInt(prob.getInstance().length);
			ants[i].moveTo(firstCity);
		}
	}

	private void initAntsMemory() {
		// TODO Auto-generated method stub
		for(int i = 0; i < ants.length; i++){
			ants[i].initMemory();
			ants[i].setFitness(Double.MAX_VALUE);
		}
		
		
	}

	private void initPheromones() {
		// TODO Auto-generated method stub
		for(int i = 0; i < pheros.length; i++)
			for(int j = 0; j < pheros.length; j++)
				pheros[i][j] = 0.000001;
	}	

	private void initializeParameters() {
		// TODO Auto-generated method stub
		alpha = conf.getAlpha();
		beta = conf.getBeta();
		evaporationValue = conf.getEvaporationCoefficient();
	}

	public int[] getSolution() {
		// TODO Auto-generated method stub
		return prob.getBestSolution();
	}

	public double getSolutionValue(){
		return prob.getBestSolutionValue();
	}
	

}
