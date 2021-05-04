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
		String[] cyclos = {"if(","if (", "for(", "for (", "while(", "while (", "do {", "do{", "switch (", "switch("};
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
	
	public boolean is_long_method(String data) {
		boolean bol = false;
		String[] data2 = data.split(" ");
		for(int i = 0; i<data2.length; i++) {
			if(data2[i] == "LOC_method") {
				int loc = this.getLOCMethod();
				int value = Integer.parseInt(data2[i+2]);
				if(data2[i+1] == ">") {
					if(loc > value) {
						bol = true;
					}else{
						return false;
					}
				}else {
					if(loc < value) {
						bol = true;
					}else{
						return false;
					}
				}
			}else if(data2[i] == "CYCLO_method") {
				int loc = this.getCYCLO_method();
				int value = Integer.parseInt(data2[i+2]);
				if(data2[i+1] == ">") {
					if(loc > value) {
						bol = true;
					}else{
						return false;
					}
				}else {
					if(loc < value) {
						bol = true;
					}else{
						return false;
					}
				}
			}
		}
		return bol;
	}

	public boolean falso_positivo(String data) {
		boolean fp = false;
		if(this.is_long_method(data) == false) {
			fp = true;
		}
		return fp;
	}
	
}