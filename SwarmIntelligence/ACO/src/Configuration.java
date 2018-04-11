import java.util.Scanner;

public class Configuration {

	private int popSize;
	
	private double alpha;
	private double beta;
	
	private int maxitr;
	
	private double p;
	
	public Configuration(){
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		
		System.out.print("Populasyon sayısı int(3-20): ");
		popSize = s.nextInt();
		
		System.out.print("Maksimum iterasyon sayısı int(>10): ");
		maxitr = s.nextInt();

		System.out.print("Alfa parametresi reel(1,5): ");
		alpha = s.nextDouble();

		System.out.print("Beta parametresi reel(1,5): ");
		beta =  s.nextDouble();
		
		System.out.print("Buharlaşma kat sayısı reel(0,1): ");
		p =  s.nextDouble();
	}

	public int getPopSize() {
		return popSize;
	}

	public void setPopSize(int popSize) {
		this.popSize = popSize;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public double getBeta() {
		return beta;
	}

	public int getMaxitr() {
		return maxitr;
	}

	public void setMaxitr(int maxitr) {
		this.maxitr = maxitr;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

	public double getEvaporationCoefficient() {
		return p;
	}

	public void setEvaporationCoefficient(double p) {
		this.p = p;
	}
	
	
	
	
}
