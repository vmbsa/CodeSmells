package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class JavaClass extends VoidVisitorAdapter<Void>{
	
	private String name;
	private File file;
	private List<JavaMethod> methods_list = new ArrayList<JavaMethod>();
	
	
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
		System.out.println(md.getNameAsString());
		methods_list.add(new JavaMethod(md.getNameAsString(), md));
	}
	
	
	private void getMethods() throws FileNotFoundException {
		final CompilationUnit comp = StaticJavaParser.parse(file);
		visit(comp, null);
	}

}

