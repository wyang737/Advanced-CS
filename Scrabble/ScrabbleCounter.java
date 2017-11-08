import java.util.HashMap;
import java.util.ArrayList;
public class ScrabbleCounter {

	public ScrabbleCounter(){
		buildMap();
		buildWordList();
	}
	
	private HashMap<Character, Integer> points = new HashMap<>();
	private Dictionary dictionary = new Dictionary();
	private ArrayList<String> wordList = new ArrayList<>();
	
	public int getPoints(String s){
		int sum = 0;
		for (int i = 0; i < s.length(); i ++){
			sum += points.get(s.charAt(i));
		}
		return sum;
	}
	
	public void buildMap(){
		int[] num = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
		for (char ch = 'a'; ch <= 'z'; ch++){
			points.put(ch, num[ch - 'a']);
		}
	}
	
	public void buildWordList(){
		for (int i = 0; i < dictionary.getNumWords(); i ++){
			wordList.add(dictionary.getWord(i));
		}
	}
	
	public int[] buildLetterOccurences(String hand){
		int[] output = new int[26];
		hand.toLowerCase();
		
		for (int i = 0; i < hand.length(); i ++){
			if(Character.isLetter(hand.charAt(i))){
				output[hand.charAt(i) - 'a']++;
			}
		}
		return output;
	}
	
	public String getBestWord(String hand){
		int[] occurrences = buildLetterOccurences(hand);
		String output = "";
		for (int i = 0; i < wordList.size(); i ++){
			boolean b = true;
			String word = wordList.get(i);
			int[] count = buildLetterOccurences(word);
			for (int z = 0; z < count.length; z++){
				if (count[z] > occurrences[z]){
					b = false;
					break;
				}
			}
			if (b == true && getPoints(word) > getPoints(output)){
				output = word;
			}
		}
		return output;
	}
}
