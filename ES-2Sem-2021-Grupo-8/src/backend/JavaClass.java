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

public class JavaClass extends VoidVisitorAdapter<Void> {

	private String name;
	private File file;
	private JavaPackage javaPackage;
	private List<JavaMethod> methods_list = new ArrayList<JavaMethod>();
	private int size;

	public JavaClass(String name, File file) {
		this.name = name;
		this.file = file;
		this.javaPackage = javaPackage;
		try {
			getMethods();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<JavaMethod> getMethods_list() {
		return methods_list;
	}

	public String getName() {
		return name;
	}

	@Override
	public void visit(MethodDeclaration md, Void arg) {
		super.visit(md, arg);
		methods_list.add(new JavaMethod(md));
	}

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

	private void getMethods() throws FileNotFoundException {
		final CompilationUnit comp = StaticJavaParser.parse(file);
		visit(comp, null);
	}
	
	private void isGodMethod(String string) {
	
	}
	
	public int getLOCClass() {
		return size;
	}
	
	public int getNOMClass() {
		return methods_list.size();
	}

	public int getWMCClass() {
		int WMCCounter = 0;
		for (JavaMethod method : methods_list) {
			WMCCounter += method.getCYCLO_method();
		}
		return WMCCounter;
	}
	
	public boolean isGodClass(String data) {
		boolean bol = false;
		if(data.contains("OR")) {
			bol = orGodClass(data);
		}else if(data.contains("AND")) {
			bol = andGodClass(data);
		}else{
			String[] data2 = data.split(" ");
			for(int i = 0; i<data2.length; i++) {
				if(data2[i] == "WMC_class") {
					int loc = this.getWMCClass();
					int value = Integer.parseInt(data2[i+2]);
					if(data2[i+1] == ">") {
							if(loc > value) {
								bol = true;
							}
						}else {
							if(loc < value) {
								bol = true;
							}
						}
					}else if(data2[i] == "NOM_class") {
						int loc = this.getNOMClass();
						int value = Integer.parseInt(data2[i+2]);
						if(data2[i+1] == ">") {
							if(loc > value) {
								bol = true;
							}
						}else {
							if(loc < value) {
								bol = true;
							}
						}
					}
			}
		}
		return bol;
	}

	public boolean andGodClass(String data) {
		boolean bol = false;
		String[] data2 = data.split(" ");
		for(int i = 0; i<data2.length; i++) {
			if(data2[i] == "WMC_class") {
				int loc = this.getWMCClass();
				int value = Integer.parseInt(data2[i+2]);
				if(data2[i+1] == ">") {
						if(loc > value) {
							bol = true;
						}else{
							if(loc <= value) 
								return false;
						}
					}else if(data2[i+1] == "<"){
						if(loc < value) {
							bol = true;
						}else{
							if(loc >= value)
								return false;
						}
					}
				}else if(data2[i] == "NOM_class") {
					int loc = this.getNOMClass();
					int value = Integer.parseInt(data2[i+2]);
					if(data2[i+1] == ">") {
						if(loc > value) {
							bol = true;
						}else{
							if(loc <= value) 
								return false;
						}
					}else if(data2[i+1] == "<"){
						if(loc < value) {
							bol = true;
						}else{
							if(loc >= value)
								return false;
						}
					}
				}
		}
		return bol;
	}

	public boolean orGodClass(String data) {
		boolean bol = false;
		String[] data2 = data.split(" ");
		for(int i = 0; i<data2.length; i++) {
			if(data2[i] == "WMC_class") {
				int loc = this.getWMCClass();
				int value = Integer.parseInt(data2[i+2]);
				if(data2[i+1] == ">") {
						if(loc > value) {
							bol = true;
						}else{
							if(loc <= value) 
								bol = false;
						}
					}else if(data2[i+1] == "<"){
						if(loc < value) {
							bol = true;
						}else{
							if(loc >= value)
								bol = false;
						}
					}
				}else if(data2[i] == "NOM_class") {
					int loc = this.getNOMClass();
					int value = Integer.parseInt(data2[i+2]);
					if(data2[i+1] == ">") {
						if(loc > value) {
							bol = true;
						}else{
							if(loc <= value) 
								bol = false;
						}
					}else if(data2[i+1] == "<"){
						if(loc < value) {
							bol = true;
						}else{
							if(loc >= value)
								bol = false;
						}
					}
				}
		}
		return bol;
	}

}