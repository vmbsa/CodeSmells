package backend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Object that handles the search for java packages
 * 
 * @author ES-2Sem-2021-Grupo-8
 * @version 1.0
 *
 */
public class JavaFilesHandler {

	/**
	 * List of every package that constitute a certain project
	 */
	private List<JavaPackage> package_list = new ArrayList<JavaPackage>();
	/**
	 * Number of packages of the project
	 */
	private int numberOfPackages = 0;
	/**
	 * java.io.File instance of the .java file
	 */
	private File src;
	/**
	 * Name of the project that is going to be analyzed
	 */
	private String project_name;

	/**
	 * Creates an instance of a java files handler
	 * @param project_path {@link String} path of the project that is going to bee analyzed
	 * @throws Exception that indicates conditions that a reasonable application might want to catch
	 */
	public JavaFilesHandler(String project_path) throws Exception {
		this.project_name = new File(project_path).getName();
		this.src = new File(project_path + "\\src");
		searchInSrc(src);
		search(src);
	}

	/**
	 * Searches for folders, creating instances of packages, adding them to a list, and updating the total number of packages
	 * @param folder {@link File} java.io.File instance of the java project
	 */	
	public void search(File folder) {
		for (final File f : folder.listFiles()) {
			if (f.isDirectory()) {
				search(f);
				File parent = new File(f.getParent());
				JavaPackage p;
				if (!parent.getName().equals("src"))
					p = new JavaPackage(parent.getName() + "." + f.getName(), f);
				else
					p = new JavaPackage(f.getName(), f);
				package_list.add(p);
				numberOfPackages++;
			}
		}
	}

	/**
	 * Searches for .java files that have no package, and if there is any, creates a "default" package where this .java files will belong
	 * @param folder {@link File} java.io.File instance of the java project
	 */	
	public void searchInSrc(File folder) {
		for (File f : folder.listFiles()) {
			if (f.isFile()) {
				if (f.getName().matches(".*\\.java")) {
					JavaPackage p = new JavaPackage("default", folder);
					package_list.add(p);
					numberOfPackages++;
					break;
				}
			}
		}
	}

	/** Gets the total number of packages in the project
	 * @return {@link Integer} total number of packages
	 */
	public int getNumberOfPackages() {
		return numberOfPackages;
	}

	/** Gets the total number of classes in the project
	 * @return {@link Integer} total number of classes
	 */
	public int countTotalOfClasses() {
		int total = 0;
		for (int i = 0; i < package_list.size(); i++) {
			JavaPackage p = package_list.get(i);
			total = total + p.numberOfClasses();
		}
		return total;
	}
	
	/** Gets the total number of lines of code in the project
	 * @return {@link Integer} total number of lines of code
	 */
	public int countTotalOfCodeLines() {
		int total = 0;
		for (int i = 0; i < package_list.size(); i++) {
			JavaPackage p = package_list.get(i);
			List<JavaClass> class_list = p.getClasses();
			for (int j = 0; j<class_list.size(); j++) {
				JavaClass jc = class_list.get(j);
				total = total + jc.getLOCClass();
			}
		}
		return total;
	}

	/** Gets the name of the project
	 * @return {@link String} name of the project
	 */
	public String getProjectName() {
		return project_name;
	}

	/** Gets the list of every package that constitute a certain project
	 * @return {@link List} list of every package that constitute a certain project
	 */
	public List<JavaPackage> getPackage_list() {
		return package_list;
	}
}
