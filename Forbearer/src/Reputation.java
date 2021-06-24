import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class Reputation {

	public static void printTeamInfo(String teamName, double[] scores)
	{
		System.out.println("Team: " + teamName + ":\nHonor: " + scores[0] + "\nRuthlessness: " + scores[1] + "\nAthletics: " + scores[2] + "\nArcana: " + scores[3]);
		System.out.println("Area: " + calculateArea(scores));
	}
	
	public static double calculateArea(double[] scores)
	{
		
		// Given four points, calculate the area of the quadrilateral.
		double xysum = ((-1)*scores[2]) * ((-1)*scores[1]) + scores[3] * scores[0];
		double yxsum = scores[0] * ((-1)*scores[2]) + scores[3] * ((-1)*scores[1]);
		
		return (0.5) * (xysum - yxsum);		
	}
	
	public static int indexOf(String category)
	{
		return ("nthc").indexOf(category.substring(2, 3));
	}
	
	public static double[] calculateScores(double[] scores, String[] categories, double points)
	{
		double[] toReturn = {scores[0], scores[1], scores[2], scores[3]};
		int index = 0;
		String toIncrease = categories[index];
		while (false == toIncrease.equals("AAAAAA"))
		{
			System.out.println("tR: " + calculateArea(toReturn) + ", s: " + calculateArea(scores));
			toIncrease = categories[++index % categories.length];
			toReturn[indexOf(toIncrease)] += 0.01;
			
			if (calculateArea(toReturn) > (calculateArea(scores) + points))
			{
				toIncrease = "AAAAAA";
			}
		}
		return toReturn;
	}
	
	public static double[] passWeek(double[] scores) 
	{
		double[] toReturn = scores;
		int maxIndex = 0;
		int midIndex = 0;
		for (int i = 0; i < 4; i++)
		{
			if(scores[i] > scores[maxIndex])
			{
				maxIndex = i;
			}
		}
		for (int i = 0; i < 4; i++)
		{
			if(scores[i] < scores[maxIndex])
			{
				if(scores[i] > scores[midIndex])
				{
					midIndex = i;
				}
			}
		}
		if (maxIndex == midIndex)
		{
			midIndex = (maxIndex + 1) % 4;
		}
		for (int i = 0; i < 4; i++)
		{
			if (i == midIndex)
			{
				toReturn[i] = toReturn[i] * 0.99;
				continue;
			}
			if (i == maxIndex)
			{
				continue;
			}
			toReturn[i] = toReturn[i] * 0.9;
		}
		return toReturn;
	}
	
	public static void save(String teamName, double[] scores) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter("team.txt"));
		String toFile = teamName + "\n" + scores[0] + "\n" + scores[1] + "\n" + scores[2] + "\n" + scores[3];
		System.out.println("Saved. File reads:\n" + toFile);
		writer.write(toFile);
		writer.close();
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// import the data from the file
		System.out.println("Loading team.txt...");
		File data = new File("team.txt");
		Scanner fileReader = new Scanner(data);
		
		// team.txt format: first line is team name, and next four lines are Honor, Ruthlessness, Atletics, and Arcana scores (in that order)
		String teamName = fileReader.nextLine();
		double[] scores = { Double.parseDouble(fileReader.nextLine()), Double.parseDouble(fileReader.nextLine()), Double.parseDouble(fileReader.nextLine()) , Double.parseDouble(fileReader.nextLine()) };
		
		printTeamInfo(teamName, scores);
		
		Scanner input = new Scanner(System.in);
		
		while (true) {
			System.out.println("Press A to add score to a set of categories.\nPress P to pass a week.\nPress D to see data.\nPress S to save over a data point.\nPress Q to quit.");
			String temp = input.nextLine();
			char cmd = temp.length() > 0 ? temp.charAt(0) : 'Q';
			switch(cmd)
			{
			case 'q':
			case 'Q':
				System.exit(0);
				break;
			case 'd':
			case 'D':
				printTeamInfo(teamName, scores);
				break;
			case 'a':
			case 'A':
				System.out.println("Enter the points earned.");
				temp = input.nextLine();
				double points = Double.parseDouble(temp);
				System.out.println("Enter the categories increased (comma separated).");
				temp = input.nextLine();
				String[] categories = temp.split(", ");
				scores = calculateScores(scores, categories, points);
				save(teamName, scores);
				break;
			case 'p':
			case 'P':
				scores = passWeek(scores);
				save(teamName, scores);
				break;
			case 's':
			case 'S':
				System.out.println("Over which point to save?");
				temp = input.nextLine();
				String category = temp;
				System.out.println("Which value to set " + temp + " to?");
				temp = input.nextLine();
				double set_value = Double.parseDouble(temp);
				scores[indexOf(category)] = set_value;
				save(teamName, scores);
				break;
			}
			
		}
		
	}

}
