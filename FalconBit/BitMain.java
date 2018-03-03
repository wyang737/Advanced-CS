public class BitMain {

	public static void main(String[] args) {
		DataReader dr = new DataReader("StepData.txt"); // input csv
		StepCounter sc = new StepCounter(dr.getData());
		System.out.println(sc.countSteps());
	}
	
}
