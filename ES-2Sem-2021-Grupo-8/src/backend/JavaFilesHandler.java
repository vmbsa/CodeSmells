package backend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class JavaFilesHandler {
	
    public static void main(String[] args) {
    	
        File folder = new File("C:\\Users\\vasco\\Desktop\\teste\\src");
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
                JavaPackage p = new JavaPackage(f.getName(), f);
            }

            

        }
    }
    
}
