package backend;

import com.github.javaparser.ast.body.MethodDeclaration;

/**
 * Object that represents a java method
 * 
 * @author ES-2Sem-2021-Grupo-8
 * @version 1.0
 *
 */
public class JavaMethod {

	/**
	 * Name of the method
	 */
	private String name;
	/**
	 * A Method Declaration
	 */
	private MethodDeclaration md;
	/**
	 * Size of the method
	 */
	private int size;
	private boolean isLongMethod;

	
	/**
	 * Creates an instance of a java method 
	 * @param md {@link MethodDeclaration} a Method Declaration
	 */
	public JavaMethod(MethodDeclaration md) {
		this.name = md.getDeclarationAsString();
		this.md = md;
	}

	/**
	 * Get the name of the method
	 * @return {@link String} the name of the method
	 */	
	public String getName() {
		return name;
	}

	/**
	 * Get the number of lines of code of the method
	 * @return {@link String} the number of lines of code of the method
	 */	
	public int getLOCMethod() {
		String[] lines = md.getBody().toString().split("\r\n|\r|\n");
		size = lines.length;
		return size;
	}

	public boolean getIsLongMethod() {
		return isLongMethod;
	}
	
	/** Gets cyclomatic complexity of the method
	 * @return {@link Integer} cyclomatic complexity of the method
	 */
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
	

	/** Gets the indication rather the method is classified as a LongMethod or not
	 * @param data {@link String} containing the definition of the rule
	 * @return {@link Boolean} indicates rather the method is classified as a LongMethod or not
	 */
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
				}else if(data2[i] == "CYCLO_method") {
					int loc = this.getCYCLO_method();
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
	
	public boolean and_long_method(String data) {
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
				}else if(data2[i] == "CYCLO_method") {
					int loc = this.getCYCLO_method();
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
	
	
	//usar esta função is_long_method
	public boolean is_long_method(String data) {
		boolean bol = false;
		if(data.contains("OR")) {
			bol = or_long_method(data);
		}else if(data.contains("AND")) {
			bol = and_long_method(data);
		}else{
			String[] data2 = data.split(" ");
			for(int i = 0; i<data2.length; i++) {
				if(data2[i] == "LOC_method") {
					int loc = this.getLOCMethod();
					int value = Integer.parseInt(data2[i+2]);
					if(data2[i+1] == ">") {
							if(loc > value) {
								bol = true;
							}else{
								bol = false;
							}
						}else {
							if(loc < value) {
								bol = true;
							}else{
								bol = false;
							}
						}
					}else if(data2[i] == "CYCLO_method") {
						int loc = this.getCYCLO_method();
						int value = Integer.parseInt(data2[i+2]);
						if(data2[i+1] == ">") {
							if(loc > value) {
								bol = true;
							}else{
								bol = false;
							}
						}else {
							if(loc < value) {
								bol = true;
							}else{
								bol = false;
							}
						}
					}
			}
		}
		return bol;
	}

	/** 
	 * @param data {@link String} 
	 */
	public boolean falso_positivo(String data) {
		boolean fp = false;
		if(this.is_long_method(data) == false) {
			fp = true;
		}
		return fp;
	}
	
	public boolean verdadeiro_positivo(String data) {
		boolean vp = false;
		if (this.is_long_method(data) == true) {
			vp = true;
		}
		return vp;
	}
	
}