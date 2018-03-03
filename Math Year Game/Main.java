import java.util.ArrayList;
import java.util.HashMap;
public class Main {

	public static void main(String[] args){
		ExpressionParser ep = new ExpressionParser();
		ExpressionGenerator eg = new ExpressionGenerator("2018");
		ArrayList<String> expressions = eg.getExpressions();
		HashMap<Double, String> answers = new HashMap<>();
		System.out.println("Answer: " + ep.getAnswer("8+2)))))"));
		for (int j = 0; j < 100; j ++){
			answers.put((double) j, "0");
		}
		for (int i = 0; i < expressions.size(); i ++){
			String expression = expressions.get(i);
			Double answer = ep.getAnswer(expression);
			if (answers.containsKey(answer)){
				answers.put(answer, expression);
			}
		}
		for (int k = 0; k < answers.size(); k ++){
			System.out.println(k + ": " + answers.get((double) k));
		}  
	}
}
