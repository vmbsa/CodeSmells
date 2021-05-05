import java.util.Scanner;
import java.io.File;

public class ClassOfDefaultPackage {
	
	// this is a comment and shouldn't be counted to the LOCClass metric
	
	private File f;
	
	public void getFile() {
		this.f = new File("ficheiro_de_teste");
		if (f.isFile())
			printFile();
		else
			throw new IllegalArgumentException();
	}
	
	public void printFile() {
		Scanner scn = new Scanner(this.f);
		while(scn.hasNextLine()) {
			System.out.println(scn.nextLine());
		}
	}
	
	public static void main(String[] args) {
		ClassOfDefaultPackage test = new ClassOfDefaultPackage();
		test.getFile();
	}

}
