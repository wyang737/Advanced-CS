import java.util.ArrayList;
public class ExpressionGenerator {

	ArrayList<String> numberPermutations = new ArrayList<>();
	ArrayList<String> binaryOperatorPermutations = new ArrayList<>();
	ArrayList<String> expressions = new ArrayList<>();
	ArrayList<String> parentheses = new ArrayList<>();

	public ExpressionGenerator(String input){
		permuteNumbers("", input);
		char[] operators = new char[]{'+', '-', '*', '/', '^', '$',};
		permuteBinaryOperators(operators, 3, new String());
		combine();
		permuteParentheses();
	}

	public ArrayList<String> getExpressions(){
		return parentheses;
	}
	
	private void permuteNumbers(String prefix, String str) {
		int n = str.length();
		if (n == 0){
			numberPermutations.add(prefix);
		} 
		for (int i = 0; i < n; i++){
			permuteNumbers(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
		}
	}

	private void permuteBinaryOperators(char[] ops, int length, String start){
		if (start.length() == length){
			binaryOperatorPermutations.add(start);
		} else {
			for (int i = 0; i < ops.length; i ++){
				permuteBinaryOperators(ops, length, start + ops[i]);
			}
		}
	}

	private void permuteParentheses(){
		for (int i = 0; i < expressions.size(); i ++){
			String expression = expressions.get(i);
			parentheses.add(expression);
			for (int j = 4; j <= 6; j += 2){ // open first index
				StringBuilder builder = new StringBuilder();
				builder.append("(");
				builder.append(expression.substring(0, j - 1));
				builder.append(")");
				builder.append(expression.substring(j - 1, expression.length()));
				parentheses.add(builder.toString());
			}
			
			for (int x = 6; x <= 8; x += 2){ // open 3rd index
				StringBuilder builder = new StringBuilder();
				builder.append(expression.substring(0, 2));
				builder.append("(");
				builder.append(expression.substring(2, x - 1));
				builder.append(")");
				builder.append(expression.substring(x - 1));
				parentheses.add(builder.toString());
			} 
			
			StringBuilder builder = new StringBuilder();
			builder.append(expression.substring(0, 4));
			builder.append("(");
			builder.append(expression.substring(4, 7));
			builder.append(")");
			parentheses.add(builder.toString());
		}
	}
	
	private void combine(){
		for (int i = 0; i < numberPermutations.size(); i++){
			String numbers = numberPermutations.get(i);
			for (int j = 0; j < binaryOperatorPermutations.size(); j ++){
				String operators = binaryOperatorPermutations.get(j);
				String expression = "";
				for (int k = 0; k < operators.length(); k ++){
					expression += numbers.charAt(k);
					expression += operators.charAt(k);
				}
				expression += numbers.charAt(numbers.length() - 1);
				expressions.add(expression);
			}
		}
	}

}
 
