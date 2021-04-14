package backend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaFilesHandler {

	private File src;

	private List<JavaPackage> package_list = new ArrayList<JavaPackage>();
	private int numberOfPackages = 0;

	public JavaFilesHandler(String project_path) throws Exception {
		this.src = new File(project_path + "\\src");
		searchInSrc(src);
		search(src);
	}

	public void search(File folder) {
		for (final File f : folder.listFiles()) {
			if (f.isDirectory()) {
				search(f);
				JavaPackage p = new JavaPackage(f.getName(), f);
				package_list.add(p);
				numberOfPackages++;
			}
		}
	}

	public void searchInSrc(File folder) {
		for (final File f : folder.listFiles()) {
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

	public static void main(String[] args) {

		String path = "C:\\Users\\Lourenco\\Desktop\\LEI\\2º Ano\\PCD\\Aula1";

		JavaFilesHandler j;
		try {
			j = new JavaFilesHandler(path);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
