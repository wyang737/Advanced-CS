public class Token {

	private String data;
	
	public Token(String input){
		data = input;
	}
	
	public String getValue(){
		return data;
	}
	
	public boolean equals(Object obj){
		Token t = (Token) obj;
		if (t.getValue().equals(data)){
			return true;
		} else {
			return false;
		}
	}
}
