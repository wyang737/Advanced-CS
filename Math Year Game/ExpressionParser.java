import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
public class ExpressionParser {

	private HashMap<String, Integer> precedence = new HashMap<>();
	private boolean debug = true;

	public ExpressionParser(){
		definePrecedence();
	}

	public Double getAnswer(String input){
		Tokenizer t = new Tokenizer();
		Token[] tokens = t.tokenize(input);
		return Double.parseDouble(getAnswer_Internal(tokens).getValue());
	}

	private Token getAnswer_Internal(Token[] tokens){
		ArrayList<Integer> indices = findPrecedence(tokens);
		if (indices.size() == 0){
			return tokens[0];
		}
		if (Arrays.asList(tokens).contains(new Token("("))){
			return evaluateParentheses(tokens);
		}
		return getAnswer_Internal(combine(tokens, indices));
	}

	//deals with parentheses
	private Token evaluateParentheses(Token[] tokens){
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < tokens.length; i ++){
			if (tokens[i].getValue().equals("(")){
				stack.push(i);
			} else {
				if (tokens[i].getValue().equals(")")){
					int openIndex = stack.pop();
					Token[] subArray = new Token[i - openIndex - 1];
					for (int j = 0; j < i - openIndex - 1; j ++){
						subArray[j] = tokens[j + openIndex + 1]; // copy all elements within the parentheses
					}
					Token result = new ValToken(getAnswer_Internal(subArray).getValue()); 
					Token[] output = new Token[openIndex + tokens.length - i];
					for (int k = 0; k < openIndex; k ++){
						output[k] = tokens[k]; // copy all elements before the parentheses
					}
					output[openIndex] = result; // insert answer of subArray 
					for (int l = 0; l < tokens.length - (i + 1); l ++){
						output[openIndex + l + 1] = tokens[i + l + 1]; // copy all elements after the parentheses
					}
					return evaluateParentheses(output);
				}
			}
		}
		return getAnswer_Internal(tokens);
	}



	// return the indices of the highest precedence operators (no parentheses)
	private ArrayList<Integer> findPrecedence(Token[] tokens){
		int max = 0;
		ArrayList<Integer> indices = new ArrayList<>();
		for (int i = 0; i < tokens.length; i ++){
			Token t = tokens[i]; 
			String s = t.getValue();
			if (precedence.containsKey(s)){
				if (precedence.get(s) > max){
					max = precedence.get(s);
					indices.clear();
					indices.add(i);
				} else {
					if (precedence.get(s) == max){
						indices.add(i);
					}
				}
			}
		}
		return indices;
	}

	//deals with the given list of indices of highest precedence operators
	private Token[] combine(Token[] tokens, ArrayList<Integer> indices){
		if (indices.size() == 0){
			return tokens;
		}
		int start = indices.get(0);
		Token operator = tokens[start];
		Token result;
		ArrayList<Integer> outputList = new ArrayList<>();
		Token[] output;
		if (start == 0){ // first character is operator
			result = new ValToken(evaluate(null, tokens[1], operator).getValue()); // shrink array size by 1
			output = new Token[tokens.length - 1];
			output[0] = result;
			for (int i = 1; i < tokens.length - 1; i ++){
				output[i] = tokens[i + 1]; // shifted copy
			}
			for (int j = 1; j < indices.size(); j ++){
				outputList.add(indices.get(j) - 1); // shifting of indices
			}
		} else {
			if (!Character.isDigit(tokens[start - 1].getValue().charAt(0))){ // previous token is an operator
				result = new ValToken(evaluate(null, tokens[start + 1], operator).getValue());
				output = new Token[tokens.length - 1];
				output[start] = result;
				for (int i = 0; i < start; i ++){
					output[i] = tokens[i];
				}
				for (int j = start + 1; j < tokens.length - 1; j ++){
					output[j] = tokens[j + 1];
				}
				for (int k = 1; k < indices.size(); k ++){
					outputList.add(indices.get(k) - 1);
				}
			} else {
				if(start == tokens.length - 1){					
					result = new ValToken(evaluate(null, tokens[start - 1], operator).getValue());
					output = new Token[tokens.length - 1];
					output[output.length - 1] = result;
					for (int i = 0; i < start - 1; i ++){
						output[i] = tokens[i];
					}
					for (int j = start + 1; j < tokens.length - 1; j ++){
						output[j] = tokens[j + 1];
					}
					for (int k = 1; k < indices.size(); k ++){
						outputList.add(indices.get(k) - 1);
					}
				} else {
					if (start < tokens.length - 1 && !Character.isDigit(tokens[start + 1].getValue().charAt(0))){ // next token is an operator
						result = new ValToken(evaluate(null, tokens[start - 1], operator).getValue());
						output = new Token[tokens.length - 1];
						output[start - 1] = result;
						for (int i = 0; i < start - 1; i ++){
							output[i] = tokens[i];
						}
						for (int j = start; j < tokens.length - 1; j ++){
							output[j] = tokens[j + 1];
						}
						for (int k = 1; k < indices.size(); k ++){
							outputList.add(indices.get(k) - 1);
						}
					} else {
						result = new ValToken(evaluate(tokens[start - 1], tokens[start + 1], operator).getValue());
						
						output = new Token[tokens.length - 2];
						output[start - 1] = result;
						for (int i = 0; i < start - 1; i ++){
							output[i] = tokens[i];
						}
						for (int j = start; j < tokens.length - 2; j ++){
							output[j] = tokens[j + 2];
						}
						for (int k = 1; k < indices.size(); k ++){
							outputList.add(indices.get(k) - 2);
						}
					}
				}
			}
		}
		return combine(output, outputList);
	}

	//deals with the operation
	private Token evaluate(Token inputOperand1, Token inputOperand2, Token inputOperator){
		if (inputOperand1 != null){ //binary operators
			double operand1 = Double.parseDouble(inputOperand1.getValue());
			double operand2 = Double.parseDouble(inputOperand2.getValue());
			String operator = inputOperator.getValue();
			double output = 0;
			switch (operator) {
			case "+": 
				output = operand1 + operand2;
				break;
			case "-":
				output = operand1 - operand2;
				break;
			case "*":
				output = operand1 * operand2;
				break;
			case "/":
				output = operand1 / operand2;
				break;
			case "^":
				output = Math.pow(operand1, operand2);
				break;
			case "$":
				int length = inputOperand2.getValue().length();
				output = (operand1*Math.pow(10, length) + operand2);
				break;
			}
			return new ValToken(Double.toString(output));
		} else { // unary operators (send op1 as null if operation is unary)
			double operand = Double.parseDouble(inputOperand2.getValue());
			String operator = inputOperator.getValue();
			double output = 0;
			switch (operator) {
			case "-":
				output = 0 - operand;
				break;
			case "sin":
				output = Math.sin(operand);
				break;
			case "cos":
				output = Math.cos(operand);
				break;
			case "tan":
				output = Math.tan(operand);
				break;
			case "sqrt":
				output = Math.sqrt(operand);
				break;
			case "!":
				if (operand == 0){
					output = 1;
					break;
				}
				output = 1;
				for (int i = 1; i <= operand; i ++){
					output *= i;
				}
				break;
			}
			return new ValToken(Double.toString(output));
		}

	}

	//defines all possible operators and their precedence
	private void definePrecedence(){
		precedence.put("+", 1);
		precedence.put("-", 1);
		precedence.put("*", 2);
		precedence.put("/", 2);
		precedence.put("^", 3);
		precedence.put("$", 4);
		precedence.put("(", 5);
		precedence.put(")", 5);
		precedence.put("sin", 6);
		precedence.put("cos", 6);
		precedence.put("tan", 6);
		precedence.put("sqrt", 6);
		precedence.put("!", 6);
	}
}
