package backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparsermodel.contexts.ClassOrInterfaceDeclarationContext;
import com.google.common.io.Files;

public class JavaClass extends VoidVisitorAdapter<Void> {

	private String name;
	private File file;
	private List<JavaMethod> methods_list = new ArrayList<JavaMethod>();
	private List<String> string_list = new ArrayList<String>();
	private int size;

	public JavaClass(String name, File file) {
		this.name = name;
		this.file = file;
		try {
			getMethods();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	@Override
	public void visit(MethodDeclaration md, Void arg) {
		super.visit(md, arg);
		methods_list.add(new JavaMethod(md.getNameAsString(), md));
	}

	@Override
	public void visit(ClassOrInterfaceDeclaration cd, Void arg) {
		super.visit(cd, arg);
		size = cd.getRange().map(range -> range.end.line - range.begin.line).orElse(0);
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			int empty = 0;
			while ((line = br.readLine()) != null) {
				if (line.trim().isEmpty()) {
					empty++;
				}
			}
			size = size - empty + 1;
			System.out.println(empty);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("A classe " + name + " tem: " + size);
	}

	private void getMethods() throws FileNotFoundException {
		final CompilationUnit comp = StaticJavaParser.parse(file);
		visit(comp, null);
	}

	public int getNOMClass() {
		return methods_list.size();
	}

}