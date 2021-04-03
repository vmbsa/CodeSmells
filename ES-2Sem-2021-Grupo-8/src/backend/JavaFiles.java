package backend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class JavaFiles {
	
    public static void main(String[] args) {
    	
        File folder = new File("C:\\Users\\vasco\\Desktop\\pasta");
        List<String> result = new ArrayList<>();
        
        search(folder, result);

        for (String s : result) {
            System.out.println(s);
        }
    }

    
    
    public static void search(File folder, List<String> result) {
        for (final File f : folder.listFiles()) {

            if (f.isDirectory()) {
                search(f, result);
            }

            if (f.isFile()) {
                if (f.getName().matches(".*\\.java")) {
                    result.add(f.getAbsolutePath());
                }
            }

        }
    }
    
}
