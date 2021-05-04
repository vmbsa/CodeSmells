package backend;

public class Excel_Reader {

	private String id;
	private int MethodID;
	private String Exc_package;
	private String Exc_class;
	private String method;
	private int NOM_class;
	private int LOC_class, WMC_class, LOC_method, CYCLO_method;
	private boolean is_God_Class, is_Long_Method;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMethodID() {
		return MethodID;
	}

	public void setMethodID(int methodID) {
		MethodID = methodID;
	}

	public String getExc_package() {
		return Exc_package;
	}

	public void setExc_package(String exc_package) {
		Exc_package = exc_package;
	}

	public String getExc_class() {
		return Exc_class;
	}

	public void setExc_class(String exc_class) {
		Exc_class = exc_class;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getNOM_class() {
		return NOM_class;
	}

	public void setNOM_class(int nOM_class) {
		NOM_class = nOM_class;
	}

	public int getLOC_class() {
		return LOC_class;
	}

	public void setLOC_class(int lOC_class) {
		LOC_class = lOC_class;
	}

	public int getWMC_class() {
		return WMC_class;
	}

	public void setWMC_class(int wMC_class) {
		WMC_class = wMC_class;
	}

	public int getLOC_method() {
		return LOC_method;
	}

	public void setLOC_method(int lOC_method) {
		LOC_method = lOC_method;
	}

	public int getCYCLO_method() {
		return CYCLO_method;
	}

	public void setCYCLO_method(int cYCLO_method) {
		CYCLO_method = cYCLO_method;
	}

	public boolean isIs_God_Class() {
		return is_God_Class;
	}

	public void setIs_God_Class(boolean is_God_Class) {
		this.is_God_Class = is_God_Class;
	}

	public boolean isIs_Long_Method() {
		return is_Long_Method;
	}

	public void setIs_Long_Method(boolean is_Long_Method) {
		this.is_Long_Method = is_Long_Method;
	}

}
