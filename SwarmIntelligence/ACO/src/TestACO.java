
public class TestACO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem p = new Problem();
		Configuration config = new Configuration();
		
		AntSystem as = new AntSystem(p, config);
		
		double result = as.run();
		int[] solution = as.getSolution();
		System.out.println("Problem çözümü "+ as.getSolutionValue()+"\n çözüm yolu şu şekildedir;");
		System.out.print("Çözüm = { "+solution[0]);
		for(int i = 1; i < solution.length; i++){
			System.out.print(", "+solution[i]);
		}
		System.out.println(" }");
	}

}
