package backend;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

/**
 * Object that represents a java class
 * Extends the com.github.javaparser.ast.visitor.VoidVisitorAdapter class
 * 
 * @author ES-2Sem-2021-Grupo-8
 * @version 1.0
 *
 */
public class Excel_Reader {

	/**
	 * IS of the row
	 */
	private String id;
	/**
	 * ID of the method
	 */
	private int MethodID;
	/**
	 * Name of the package
	 */
	private String Exc_package;
	/**
	 * Name of the class
	 */
	private String Exc_class;
	/**
	 * Name of the method
	 */
	private String method;
	/**
	 * Number of methods of the class
	 */
	private int NOM_class;
	/**
	 * Number of lines of the class; cyclomatic complexity of all class methods; number of lines of the method; cyclomatic complexity of the method
	 */
	private int LOC_class, WMC_class, LOC_method, CYCLO_method;
	/**
	 * Boolean that indicates rather the class is classified as a GodClass or not; Boolean that indicates rather the method is classified as a LongMethod or not
	 */
	private boolean is_God_Class, is_Long_Method;

	
	/**
	 * Creates an instance of an Excel_Reader
	 * @param id {@link Integer} ID of the row
	 * @param methodID {@link Integer} ID of the method
	 * @param exc_package {@link String} Name of the package
	 * @param exc_class {@link String} Name of the class
	 * @param method {@link String} Name of the method
	 * @param nOM_class {@link String} Number of methods of the class
	 * @param lOC_class {@link String} Number of lines of the class
	 * @param wMC_class {@link String} Cyclomatic complexity of all class methods
	 * @param lOC_method {@link String} Number of lines of the method
	 * @param cYCLO_method {@link String} Cyclomatic complexity of the method
	 * @param is_God_Class {@link String} Boolean that indicates rather the class is classified as a GodClass or not
	 * @param is_Long_Method {@link String} Boolean that indicates rather the method is classified as a LongMethod or not
	 */
	public Excel_Reader(String id, int methodID, String exc_package, String exc_class, String method, int nOM_class,
			int lOC_class, int wMC_class, int lOC_method, int cYCLO_method, boolean is_God_Class,
			boolean is_Long_Method) {
		super();
		this.id = id;
		this.MethodID = methodID;
		this.Exc_package = exc_package;
		this.Exc_class = exc_class;
		this.method = method;
		this.NOM_class = nOM_class;
		this.LOC_class = lOC_class;
		this.WMC_class = wMC_class;
		this.LOC_method = lOC_method;
		this.CYCLO_method = cYCLO_method;
		this.is_God_Class = is_God_Class;
		this.is_Long_Method = is_Long_Method;
	}

	Excel_Reader() {
		super();
	}

	/**
	 * Get the ID of the row
	 * @return {@link String} of the row
	 */
	public String getId() {
		return id;
	}

	/**
	 * Defines the value of the row's ID
	 * @param id {@link String} defines the value that the ID of the row is going to take
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get the ID of the method
	 * @return {@link Integer} of the method
	 */
	public int getMethodID() {
		return MethodID;
	}

	/**
	 * Defines the value of the method's ID
	 * @param methodID {@link String} defines the value that the ID of the method is going to take
	 */
	public void setMethodID(int methodID) {
		MethodID = methodID;
	}

	/**
	 * Get the name of the package
	 * @return {@link String} Name of the package
	 */
	public String getExc_package() {
		return Exc_package;
	}

	/**
	 * Defines the name of the package
	 * @param exc_package {@link String} defines the value that the package's name is going to take
	 */
	public void setExc_package(String exc_package) {
		Exc_package = exc_package;
	}

	/**
	 * Get the name of the class
	 * @return {@link String} Name of the class
	 */
	public String getExc_class() {
		return Exc_class;
	}

	/**
	 * Defines the name of the class
	 * @param exc_class {@link String} defines the value that the name of the class is going to take
	 */
	public void setExc_class(String exc_class) {
		Exc_class = exc_class;
	}

	/**
	 * Get the name of the method
	 * @return {@link String} Name of the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Defines the name of the method
	 * @param method {@link String} defines the value that the name of the method is going to take
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * Get the number of methods in the class
	 * @return {@link Integer} Number of methods in the class
	 */
	public int getNOM_class() {
		return NOM_class;
	}

	/**
	 * Defines the number of methods in the class
	 * @param nOM_class {@link Integer} defines the value that the number of methods in the class is going to take
	 */
	public void setNOM_class(int nOM_class) {
		NOM_class = nOM_class;
	}

	/**
	 * Get the number of lines of code in the class
	 * @return {@link Integer} Number of lines of code in the class
	 */
	public int getLOC_class() {
		return LOC_class;
	}

	/**
	 * Defines the number of lines of code in the class
	 * @param lOC_class {@link Integer} defines the value that the number of lines of code in the class is going to take
	 */
	public void setLOC_class(int lOC_class) {
		LOC_class = lOC_class;
	}

	/**
	 * Get the cyclomatic complexity of the java class
	 * @return {@link Integer} Cyclomatic complexity of the java class
	 */
	public int getWMC_class() {
		return WMC_class;
	}

	/**
	 * Defines the cyclomatic complexity of the java class
	 * @param wMC_class {@link Integer} defines the value that the cyclomatic complexity of the java class is going to take
	 */
	public void setWMC_class(int wMC_class) {
		WMC_class = wMC_class;
	}

	/**
	 * Get the number of lines of code of the method
	 * @return {@link Integer} lines of code of the method
	 */
	public int getLOC_method() {
		return LOC_method;
	}

	/**
	 * Defines the number of lines of code of the method
	 * @param lOC_method {@link Integer} defines the value that the number of lines of code of the method is going to take
	 */
	public void setLOC_method(int lOC_method) {
		LOC_method = lOC_method;
	}

	/**
	 * Get the cyclomatic complexity of the method
	 * @return {@link Integer} Cyclomatic complexity of the method
	 */
	public int getCYCLO_method() {
		return CYCLO_method;
	}

	/**
	 * Defines the cyclomatic complexity of the method
	 * @param cYCLO_method {@link Integer} defines the value that the cyclomatic complexity of the method is going to take
	 */
	public void setCYCLO_method(int cYCLO_method) {
		CYCLO_method = cYCLO_method;
	}

	/**
	 * Get the information about rather the class is classified as a GodClass or not
	 * @return {@link Boolean} Information about rather the class is classified as a GodClass or not
	 */
	public boolean isIs_God_Class() {
		return is_God_Class;
	}

	/**
	 * Defines the value of the boolean that indicates rather the class is classified as a GodClass or not
	 * @param is_God_Class {@link Integer} defines the value of the boolean that indicates rather the class is classified as a GodClass or not
	 */
	public void setIs_God_Class(boolean is_God_Class) {
		this.is_God_Class = is_God_Class;
	}

	/**
	 * Get the information about rather the class is classified as a LongMethod or not
	 * @return {@link Boolean} Information about rather the class is classified as a LongMethod or not
	 */
	public boolean isIs_Long_Method() {
		return is_Long_Method;
	}

	/**
	 * Defines the value of the boolean that indicates rather the class is classified as a LongMethod or not
	 * @param is_Long_Method {@link Integer} defines the value of the boolean that indicates rather the class is classified as a LongMethod or not
	 */
	public void setIs_Long_Method(boolean is_Long_Method) {
		this.is_Long_Method = is_Long_Method;
	}

}
