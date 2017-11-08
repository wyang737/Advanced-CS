import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary 
{

	private ArrayList<String>wordList = new ArrayList<String>();

	/**
	 * Default constructor. Loads the word list from disk.
	 */
	public Dictionary()//default constructor 
	{
		Scanner scan = null;

		try 
		{
			scan = new Scanner (new File ("Boggle.txt"));
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

		String word;
		while (scan.hasNext()) 
		{
			word = scan.next();
			wordList.add(word.substring(0, word.length()-1));         
		}
		scan.close();
	}

	/**
	 * 
	 * @param s  The word to be checked
	 * @return True, if the word is found in the dictionary, case ignored.
	 */
	public boolean isWord(String s)
	{
		if(wordList.contains(s.toLowerCase()))
			return true;
		return false;
	}

	/**
	 * 
	 * @param i The index of the desired word
	 * @return The word at that index, or null if the index is out of range.
	 */
	public String getWord(int i)
	{
		if (i >= 0 && i < wordList.size())
			return wordList.get(i);
		else
			return null;
	}

	/**
	 * 
	 * @return The number of words in the dictionary
	 */
	public int getNumWords()
	{
		return wordList.size();
	}
	
	/*
	 * Builds a dictionary and tests its functionality.
	 */
	public static void testMe() //tester code 
	{
		System.out.println("Beginning Dictionary unit test:");

		Dictionary d = new Dictionary();
		System.out.println(d.isWord("bridge"));
		System.out.println(d.isWord("BRIDGE"));
		System.out.println(d.isWord("Peddie"));

		for (int i = 0; i < 30; i++)
		{
			int x = (int)(Math.random()*d.getNumWords());
			System.out.print(d.getWord(x)+"  ");
		}
		System.out.println();
		System.out.println("Testing complete.");
	} 
}
