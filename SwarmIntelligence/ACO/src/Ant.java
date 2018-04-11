
public class Ant {
	
	private int[] memory;
	
	private double fitness;
	
	private int id;
	
	private int currentIndex;
	
	private Problem prob;
	
	public Ant(int id, Problem p){
		memory = new int[p.getInstance().length+1];
		initMemory();
		fitness = Double.MAX_VALUE;
		this.id = id;
		prob = p;
	}

	

	public int[] getMemory() {
		return memory;
	}


	public void setMemory(int[] memory) {
		this.memory = memory;
	}


	public double getFitness() {
		return fitness;
	}


	public void setFitness(double fitness) {
		this.fitness = fitness;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void initMemory() {
		// TODO Auto-generated method stub
		currentIndex = 0;
		for(int i = 0; i < memory.length; i++)
			memory[i] = -1;
	}


	public void moveTo(int city) {
		// TODO Auto-generated method stub
		memory[currentIndex++] = city;
	}


	public boolean visitedTo(int city) {
		// TODO Auto-generated method stub
		for(int i = 0; i < memory.length; i++){
			if (memory[i] == city)
				return true;
		}
		return false;
	}
	
	public int getCurrentCity() {
		// TODO Auto-generated method stub
		return memory[currentIndex-1];
	}



	public void calculateFitness() {
		// TODO Auto-generated method stub
		fitness = prob.evaluate(memory);
	}


	
	
	
	
}
