package dialogue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class YesNoScriptGenerator {
	
	Scanner myInput;
	BufferedWriter myWriter;
	
	public void generateScript() throws IOException{

		myInput = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		System.out.println("Enter the desired name of the file (NOT including the file extension)");
		String filename = myInput.nextLine();
		
		FileWriter out = new FileWriter(filename + ".txt");
		myWriter = new BufferedWriter(out);
		
		System.out.println("Please indicated questions by typing '##Q' and then a single space before each question." +
				"To indicated an endpoint (a statement which require no response), don't add anything before the statement.");
		System.out.println("Type in the first question:  ");
		String firstQuestion = myInput.nextLine();
		myWriter.write(firstQuestion + "\n");
		recursiveCall(firstQuestion);
		myWriter.close();
		
	}
	
	private void recursiveCall(String question) throws IOException{
		
		System.out.println("The question is: " + question);
		System.out.println("If the player says yes, the next message should be: ");
		String yesAnswer = myInput.nextLine();
		System.out.println("If the player says no, the next message should be: ");
		String noAnswer = myInput.nextLine();
		
		myWriter.write(yesAnswer + "\n");
		
		if (yesAnswer.startsWith("##Q "))
			recursiveCall(yesAnswer.substring(4));
		myWriter.write(noAnswer + "\n");
		if (noAnswer.startsWith("##Q "))
			recursiveCall(noAnswer.substring(4));
		return;
		
	}
		
	public static void main(String[] args) throws IOException{
		YesNoScriptGenerator generator = new YesNoScriptGenerator();
		generator.generateScript();
	}

}
