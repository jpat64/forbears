import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Forbears {
	
	public static String capitalize(String input)
	{
		String allLower = input.toLowerCase();
		return allLower.substring(0,1).toUpperCase() + allLower.substring(1);
	}
	
	public static String[] parseNextLine(String line)
	{
		String[] toReturn = {};
		int sylStart = 0;
		for (int i = 0; i < line.length(); i++)
		{
			if (line.substring(i, i+1).equals("/"))
			{
				toReturn = add(toReturn, line.substring(sylStart, i));
				sylStart = i+1;
			}
		}
		toReturn = add(toReturn, line.substring(sylStart));
		return toReturn;
	}
	
	public static String parseNextArray(String[] array)
	{
		String toReturn = "";
		for (int i = 0; i < array.length; i++)
		{
			toReturn += array[i] + "/";
		}
		return toReturn.substring(0, toReturn.length() - 1) + "\n";
	}
	
	public static String[] add(String[] array, String addition)
	{
		String[] newArray = new String[array.length + 1];
		int i;
		for (i = 0; i < array.length; i++)
		{
			if (array[i].equals(addition))
			{
				return array;
			}
			newArray[i] = array[i];
		}
		newArray[i] = addition;
		return newArray;
	}
	
	public static void save(String[][] all_parts) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("syllables.txt"));
		String toFile = "";
		for (int i = 0; i < all_parts.length; i++)
		{
			toFile += parseNextArray(all_parts[i]);
		}
		System.out.println(toFile);
		writer.write(toFile);
		writer.close();
	}

	public static void main(String[] args) throws IOException, FileNotFoundException
	{
		File syllables = new File("syllables.txt");
		String[][] all_parts = {{},{},{}};
		System.out.println("Loading syllables.txt...");
		Scanner fileReader = new Scanner(syllables);
		for (int i = 0; fileReader.hasNextLine(); i++)
		{
			all_parts[i] = parseNextLine(fileReader.nextLine());
		}
		
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.println("What would you like to do?\nPress ENTER to get a random giant name.\nEnter A to add a new syllable.\nEnter D to show the contents of each array.\nEnter Q to quit.");
			String stResponse = in.nextLine();
			char response = stResponse.length() == 0 ? 'Z' : stResponse.toCharArray()[0];
			switch(response)
			{
			case 'A':
			case 'a':
				System.out.println("For which part of the name would you like to add a syllable? (Enter 1, 2, or 3)");
				try { 
					int inResponse = in.nextInt() - 1;
					in.nextLine();
					System.out.println("Which syllable would you like to add? Enter it in readable form.");
					String sylResponse = in.nextLine();
					all_parts[inResponse] = add(all_parts[inResponse], sylResponse);
					save(all_parts);
				} 
				catch (InputMismatchException e) { System.err.println("Not a number"); break; }
				catch (IOException e) { System.err.println("IO Exception"); break; }
				
				break;
			case 'Q':
			case 'q':
				System.exit(1);
				break;
			case 'D':
			case 'd':
				String output = "";
				for(int i = 0; i < all_parts.length; i++)
				{
					output += parseNextArray(all_parts[i]);
				}
				System.out.println(output.substring(0, output.length() - 1));
				break;
			default:
				String word = "";
				for (int i = 0; i < all_parts.length; i++) 
				{
					word += all_parts[i][(int)(Math.random() * all_parts[i].length)];
				}
				System.out.println(capitalize(word));
			}
		}
	}

}
