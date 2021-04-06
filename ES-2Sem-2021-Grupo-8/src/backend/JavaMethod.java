package backend;

import com.github.javaparser.ast.body.MethodDeclaration;

public class JavaMethod {

	private String name;
	private MethodDeclaration md;
	
	public JavaMethod(String name, MethodDeclaration md) {
		this.name = name;
		this.md = md;
	}

	public String getName() {
		return name;
	}
	
}
