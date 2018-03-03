import java.util.ArrayList;
public class StepCounter {

	ArrayList<Double> timeValue = new ArrayList<>();
	ArrayList<Double> totalValue = new ArrayList<>();

	public StepCounter(ArrayList<String> data){
		parseData(data);
	}

	public int countSteps(){
		double min = totalValue.get(0);
		int minIndex = 0;
		double max = totalValue.get(0);
		int maxIndex = 0;
		int steps = 0;
		boolean dir = true; //true = increasing, false = decreasing
		double previous = totalValue.get(0);
		double forceTolerance = 0.32;
		double timeTolerance = 0.1;
		for (int i = 0; i < totalValue.size(); i ++){
			double current = totalValue.get(i);
			if (dir == true){
				if (current < previous){
					dir = false;
					if (((previous - min) > forceTolerance) && ((timeValue.get(i - 1) - timeValue.get(minIndex)) > timeTolerance)){ 
						steps ++;
					}
					max = previous;
					maxIndex = i - 1;
				} else {
					previous = current;
				}
			} else {
				if (current > previous){
					dir = true;
					if (((max - previous) > forceTolerance) && ((timeValue.get(maxIndex) - timeValue.get(i - 1)) > timeTolerance)){
						steps ++;
					}
					min = previous;
					minIndex = i - 1;
				} else {
					previous = current;
				}
			}
		}
		return steps;
	}

	public void parseData(ArrayList<String> data){
		for (int i = 5; i < data.size(); i ++){
			if (i % 5 == 0){
				timeValue.add(Double.parseDouble(data.get(i)));
			} else {
				if ((i - 4) % 5 == 0){
					totalValue.add(Double.parseDouble(data.get(i)));
				}
			}
		}
	}
}
