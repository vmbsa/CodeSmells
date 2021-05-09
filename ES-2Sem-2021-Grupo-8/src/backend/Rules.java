package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Rules {

	private static final File FOLDER = new File("Regras");

	public String getRuleByFilename(String filename) {
		for (File rule_file : FOLDER.listFiles()) {
			if (rule_file.getName() == filename) {
				return getFileContent(rule_file);
			}
		}
		return null;
	}
	
	private String getFileContent(File f) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(f);
			if (scanner.hasNextLine()) {
				String file_data = scanner.nextLine();
				scanner.close();
				return file_data;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scanner.close();
		return null;
	}
	
	public void writeRule(String file_name, String file_content) {
		File new_file = new File(FOLDER.getAbsoluteFile() + "//" + file_name);
		System.out.println(new_file.getAbsolutePath());
		try {
			PrintWriter writer = new PrintWriter(new_file);
			writer.print(file_content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Rules r = new Rules();
		r.writeRule("Testteeeeeeeeeee.txt", "Isto é um teste");
	}

}
