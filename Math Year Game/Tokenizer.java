import java.util.ArrayList;
public class Tokenizer {

	private ArrayList<String> operators = new ArrayList<>();
	
	public Tokenizer(){
		defineOperators();
	}

	public Token[] tokenize(String input){
		ArrayList<Token> list = new ArrayList<>();
		int counter = 0;
		while (counter < input.length()){
			char c = input.charAt(counter);
			if (!Character.isDigit(c)){
				StringBuilder builder = new StringBuilder();
				while (counter < input.length()){
					if(isOperator(builder.toString())){
						break;
					} else {
						builder.append(input.charAt(counter));
						counter ++;
					}
				}
				OpToken token = new OpToken(builder.toString());
				list.add(token);
			} else {
				StringBuilder builder = new StringBuilder();
				while (counter < input.length()){
					if(input.charAt(counter) != '.' && !Character.isDigit(input.charAt(counter))){
						break;
					} else {
						builder.append(input.charAt(counter));
						counter ++;
					}
				}
				ValToken token = new ValToken(builder.toString());
				list.add(token);
			}
		}
		Token[] ret = new Token[list.size()];
		for (int i = 0; i < ret.length; i ++){
			ret[i] = list.get(i);
		}
		return ret;
	}
	
	private boolean isOperator(String input){
		if(operators.contains(input)){
			return true;
		} else {
			return false;
		}
	}
	
	private void defineOperators(){
		operators.add("+");
		operators.add("-");
		operators.add("*");
		operators.add("/");
		operators.add("^");
		operators.add("$");
		operators.add("(");
		operators.add(")");
		operators.add("sin");
		operators.add("cos");
		operators.add("tan");
		operators.add("sqrt");
		operators.add("!");
	}
}
