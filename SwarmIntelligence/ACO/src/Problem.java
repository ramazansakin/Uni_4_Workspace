
public class Problem {
	private int[][] instance = {{0,5,9,10,7,4},
								{5,0,7,10,8,8},
								{9,7,0,2,7,6},
								{10,10,2,0,1,3},
								{7,8,7,1,0,4},
								{4,8,6,3,4,0}};
	
	private double bestSolutionValue;
	
	private int[] bestSolution;
	
	public void getInstanceFromFile(String filename){
		
	}
	
	public Problem(){
		bestSolutionValue = Double.MAX_VALUE;
		bestSolution = new int[instance.length+1];
	}
	
	public double getBestSolutionValue() {
		return bestSolutionValue;
	}

	public void setBestSolutionValue(double bestSolutionValue) {
		this.bestSolutionValue = bestSolutionValue;
		System.out.println("Şu anki çözüm yolu "+ bestSolutionValue);
	}

	public int[] getBestSolution() {
		
		return bestSolution;
	}

	public void setBestSolution(int[] antSolution) {
		for(int i = 0; i < antSolution.length; i++)
			this.bestSolution[i] = antSolution[i];
	}

	public int[][] getInstance(){
		return instance;
	}

	public double evaluate(int[] antMemory) {
		// TODO Auto-generated method stub
		double tourLenght = 0.0;
		for(int i = 0; i < antMemory.length-1; i++){
			int currentCity = antMemory[i];
			int nextCity = antMemory[i+1];
			tourLenght += instance[currentCity][nextCity];
		}
		return tourLenght;
	}
	
	
 }
