package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Rules {


	private static File FOLDERNAME;
	
	
	public Rules() {
		
	}

	
	
	private void loadRule() {
		String data=null;
			try {
			Scanner myReader= new Scanner(FOLDERNAME);
			while(myReader.hasNextLine()) {
				data=myReader.nextLine();
			}
			myReader.close();
				
			
		} catch (FileNotFoundException e1) {
			System.out.println("An error Occurred");
			e1.printStackTrace();
		}
	}
	
}
