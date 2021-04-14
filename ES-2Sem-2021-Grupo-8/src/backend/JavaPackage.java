package backend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaPackage {

	private String name;
	private File file;
	private List<JavaClass> class_list = new ArrayList<JavaClass>();

	public JavaPackage(String name, File file) {
		this.name = name;
		this.file = file;
		loadClasses();
	}

	public List<JavaClass> getClasses() {
		return class_list;
	}

	public String getName() {
		return name;
	}

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

	public List<JavaClass> getClass_list() {
		return class_list;
	}

	public int numberOfClasses() {
		return class_list.size();
	}
}
