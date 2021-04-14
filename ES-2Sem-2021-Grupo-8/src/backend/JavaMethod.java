package backend;

import com.github.javaparser.ast.body.MethodDeclaration;

public class JavaMethod {

	private String name;
	private MethodDeclaration md;
	private int size;

	public JavaMethod(MethodDeclaration md) {
		this.name = md.getDeclarationAsString();
		this.md = md;
	}

	public String getName() {
		return name;
	}

	public int getLOCMethod() {
		String[] lines = md.getBody().toString().split("\r\n|\r|\n");
		size = lines.length;
		return size;
	}
	
	public int getCYCLO_method() {
		String[] cyclos = {"if", "for", "while", "do", "case"};
		int total = 1; 
		String[] lines = md.getBody().toString().split("\r\n|\r|\n");
		for(int i = 0; i<lines.length ; i++) {
			for (int j = 0; j<cyclos.length ; j++) {
				if(lines[i].contains(cyclos[j])) {
					total ++;
				}
			}
		}
		return total;
	}

}