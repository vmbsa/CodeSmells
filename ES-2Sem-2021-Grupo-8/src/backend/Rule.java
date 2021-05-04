package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Rule {

	
	private String name;
	
	
	public Rule(String name) {
		this.name=name;
	}

	
	
	public String getRule(File filename) {
		String data=null;
		try {
			Scanner myReader= new Scanner(filename);
			while(myReader.hasNextLine()) {
				data=myReader.nextLine();
			}
			myReader.close();
				
			
		} catch (FileNotFoundException e1) {
			System.out.println("An error Occurred");
			e1.printStackTrace();
		}
		return data;
	}
	
	
	public String getName() {
		return name;
	}

}
