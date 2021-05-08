package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * Object that represents a java class
 * Extends the com.github.javaparser.ast.visitor.VoidVisitorAdapter class
 * 
 * @author ES-2Sem-2021-Grupo-8
 * @version 1.0
 *
 */
public class JavaClass extends VoidVisitorAdapter<Void> {

	/**
	 * Name of the class
	 */
	private String name;
	/**
	 * java.io.File instance of the .java file
	 */
	private File file;
	/**
	 * List of the the methods implemented in the java class
	 */
	private List<JavaMethod> methods_list = new ArrayList<JavaMethod>();
	/**
	 * Number of lines of code of the java class
	 */
	private int size;

	/**
	 * Creates an instance of a java class 
	 * @param name Name of the class
	 * @param file java.io.File instance of the .java file
	 */
	public JavaClass(String name, File file) {
		this.name = name;
		this.file = file;
		try {
			getMethods();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the list of methods implemented in the class
	 * @return List of methods implemented in the class
	 */
	public List<JavaMethod> getMethods_list() {
		return methods_list;
	}

	/**
	 * Get the the name of the class
	 * @return the class's name
	 */	
	public String getName() {
		return name;
	}
	
	
	/**
	 * Method overrided from the com.github.javaparser.ast.visitor.VoidVisitorAdapter class
	 * Visits all the methods implemented in the class and adds them to the methods list
	 */
	@Override
	public void visit(MethodDeclaration md, Void arg) {
		super.visit(md, arg);
		methods_list.add(new JavaMethod(md));
	}

	/**
	 * Method overrided from the com.github.javaparser.ast.visitor.VoidVisitorAdapter class
	 * Visits all of the class content and increments the number of lines of code
	 */
	@Override
	public void visit(ClassOrInterfaceDeclaration cd, Void arg) {
		super.visit(cd, arg);
		String[] lines = cd.toString().split("\r\n|\r|\n");
		int counter = 0;
		for(String s : lines) {
			if(s.isBlank())
				counter++;
		}
		size=lines.length-counter;
	}

	/**
	 * Parses the .java file
	 * @throws FileNotFoundException When the given .java class doesn't exist
	 */
	private void getMethods() throws FileNotFoundException {
		final CompilationUnit comp = StaticJavaParser.parse(file);
		visit(comp, null);
	}
	
	/**
	 * Gets the number of lines of code in the java class
	 * @return Number of lines of code
	 */
	public int getLOCClass() {
		return size;
	}
	
	/**
	 * Gets the number of methods in the java class
	 * @return Number of methods
	 */
	public int getNOMClass() {
		return methods_list.size();
	}

	/** Gets cyclomatic complexity of the java class
	 * @return cyclomatic complexity
	 */
	public int getWMCClass() {
		int WMCCounter = 0;
		for (JavaMethod method : methods_list) {
			WMCCounter += method.getCYCLO_method();
		}
		return WMCCounter;
	}

}