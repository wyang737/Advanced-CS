import java.util.Scanner;
import java.util.ArrayList;
/**
 * Beginnings of a Scrabble game.
 * 
 * @author Tim Corica, Peddie
 * @version 9/5/2017
 */
public class ScrabbleMain {

	public static void main(String[] args) {
		System.out.println("Hey, I'm running!");

		Dictionary.testMe();

		Dictionary d = new Dictionary();
		ScrabbleCounter counter = new ScrabbleCounter();
		Scanner userInput = new Scanner(System.in);
		String s;
		do {
			System.out.print("Enter word to test (END to end):");
			s = userInput.nextLine();
			System.out.println(d.isWord(s));
			if (d.isWord(s)){
				System.out.println(counter.getPoints(s));
			}
		String bestWord = counter.getBestWord("ainasldkfnw");
		System.out.println(bestWord);
		System.out.println(counter.getPoints(bestWord));
		} while (!s.equals("END"));
		userInput.close();
		System.out.println("So long!");
	}
}
