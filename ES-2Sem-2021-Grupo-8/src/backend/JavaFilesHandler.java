package backend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class JavaFilesHandler {
	
	private List<JavaPackage> package_list = new ArrayList<JavaPackage>();
	private int numberOfPackages = 0;
	
	
    public static void main(String[] args) {
    	
        File folder = new File("C:\\Users\\vasco\\Desktop\\teste\\src");
        List<String> result = new ArrayList<>();
        
        JavaFilesHandler j = new JavaFilesHandler();
        j.search(folder, result);
        System.out.println(j.getNumberOfPackages());
        
        
    }

    
    
    public void search(File folder, List<String> result) {
    	
        for (final File f : folder.listFiles()) {
            if (f.isDirectory()) {
                search(f, result);
                JavaPackage p = new JavaPackage(f.getName(), f);
                package_list.add(p);
                numberOfPackages++;
        
            }
        }
    }
    
    
    public int getNumberOfPackages() {
    	return numberOfPackages;
    }
    
}
