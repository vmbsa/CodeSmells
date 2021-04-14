package backend;

import com.github.javaparser.ast.body.MethodDeclaration;

public class JavaMethod {

	private String name;
	private MethodDeclaration md;
	private int size;

	public JavaMethod(String name, MethodDeclaration md) {
		this.name = name;
		this.md = md;
		getLOCMethod();
	}

	public String getName() {
		return name;
	}

	private void getLOCMethod() {
		String[] lines = md.getBody().toString().split("\r\n|\r|\n");
		size = lines.length;
		// apagar depois de verem que funcemina
		//System.out.println("metodo " + name + " tem " + size + " linhas");
	}
	
	private int getCYCLO_method() {
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
