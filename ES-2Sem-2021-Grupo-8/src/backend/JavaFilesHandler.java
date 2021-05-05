package backend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaFilesHandler {

	private List<JavaPackage> package_list = new ArrayList<JavaPackage>();
	private int numberOfPackages = 0;
	private File src;
	private String project_name;

	public JavaFilesHandler(String project_path) throws Exception {
		this.project_name = new File(project_path).getName();
		this.src = new File(project_path + "\\src");
		searchInSrc(src);
		search(src);
	}

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

	public int getNumberOfPackages() {
		return numberOfPackages;
	}

	public int countTotalOfClasses() {
		int total = 0;
		for (int i = 0; i < package_list.size(); i++) {
			JavaPackage p = package_list.get(i);
			total = total + p.numberOfClasses();
		}
		return total;
	}
	
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

	public String getProjectName() {
		return project_name;
	}

	public List<JavaPackage> getPackage_list() {
		return package_list;
	}
}
