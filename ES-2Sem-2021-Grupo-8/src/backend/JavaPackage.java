package backend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;

public class JavaPackage {

	/**
	 * Name of the package
	 */
	private String name;
	/**
	 * java.io.File instance of the .java file
	 */
	private File file;
	/**
	 * List of class objects that constitute the package
	 */
	private List<JavaClass> class_list = new ArrayList<JavaClass>();

	/**
	 * Creates an instance of a java method 
	 * @param name {@link String} Name of the package
	 * @param file {@link File} java.io.File instance of the .java file
	 */
	public JavaPackage(String name, File file) {
		this.name = name;
		this.file = file;
		loadClasses();
	}

	/**
	 * Get the list of class objects that constitute the package
	 * @return {@link List} List of class objects that constitute the package
	 */	
	public List<JavaClass> getClasses() {
		return class_list;
	}

	/**
	 * Get the name of the package
	 * @return {@link String} the name of the package
	 */	
	public String getName() {
		return name;
	}

	/** Saves each class of the package in a List of Java Class objects
	 */
	private void loadClasses() {
		for (File f : file.listFiles()) {
			if (f.isFile()) {
				if (f.getName().matches(".*\\.java")) {
					JavaClass jc = new JavaClass(f.getName(), f);
					class_list.add(jc);
				}
			}
		}
	}

	/**
	 * Get the number of classes in the package
	 * @return {@link Integer} the number of classes in the package
	 */	
	public int numberOfClasses() {
		return class_list.size();
	}
	
	/**
	 * Get the number of methods in the package
	 * @return {@link Integer} the number of methods in the package
	 */	
	public int number_of_methods_in_package() {
		int total = 0;
		for (int i = 0; i<this.class_list.size(); i++) {
			JavaClass a = this.class_list.get(i);
			total += a.getNOMClass();
		}
		return total;
	}
	
	/**
	 * Get the list of class objects that constitute the package
	 * @return {@link List} List of class objects that constitute the package
	 */	
	public List<JavaClass> getClass_list() {
		return class_list;
	}
}
