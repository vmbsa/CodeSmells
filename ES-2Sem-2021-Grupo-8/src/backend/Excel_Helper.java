package backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Object that helps reading and writing on Excel sheets. Modifies its structure
 * and gets the content of specific cells.
 * 
 * @author ES-2Sem-2021-Grupo-8
 * @version 1.0
 *
 */

public class Excel_Helper {

	/**
	 * Name of the Excel file to be read.
	 */
	private static String XLSX_FILE_NAME;
	/**
	 * List of rows that constitutes the file that its being read.
	 */
	private ArrayList<String> rows = new ArrayList<String>();
	/**
	 * Location where the generated Excel file will be placed.
	 */
	private String file_path;
	/**
	 * Project to be analyzed
	 */
	private JavaFilesHandler project;

	/**
	 * Name of the class
	 */
	public Excel_Helper(String name) {
		super();
		Excel_Helper.XLSX_FILE_NAME = name;
	}

	/**
	 * Creates an instance of a Excel Helper
	 * 
	 * @param project Project to be analyzed
	 * @param file_path Path to where the output Excel file will be placed
	 */
	public Excel_Helper(JavaFilesHandler project, String file_path) {
		this.file_path = file_path;
		this.project = project;
	}

	/**
	 * Reads an Excel sheet and saves its rows into a List.
	 * 
	 * @param isXlsx Boolean that indicates rather the file is an Excel file or not
	 * @return List of Strings, each one containing a line of the Excel file
	 * @throws FileNotFoundException When the given .java class doesn't exist
	 * @throws IOException If the stream itself is corrupted or some error occurred during reading
	 */
	public ArrayList<String> readExcelSheet(boolean isXlsx) {
		try {
			FileInputStream excelFile = new FileInputStream(new File(XLSX_FILE_NAME));
			try (Workbook workbook = isXlsx ? new XSSFWorkbook(excelFile) : new HSSFWorkbook(excelFile)) {
				Sheet workSheet = workbook.getSheetAt(0);
				String row = "";
				for (Row currentRow : workSheet) {
					for (Cell currentCell : currentRow) {
						String helper = printCellContents(currentCell);
						row = row.concat(helper);

					}
					rows.add(row);
					row = "";
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rows;
	}

	/**
	 * Given a cell of an Excel file, returns its value
	 * 
	 * @param currentCell Cell from which we want to get the value 
	 * @return The value of the indicated cell
	 */
	private static String printCellContents(Cell currentCell) {
		String row = new String("");
		switch (currentCell.getCellType()) {
		case STRING:
			row = row.concat(currentCell.getRichStringCellValue().getString() + "|");
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(currentCell)) {
				row = row.concat(currentCell.getDateCellValue() + "|");
			} else {
				row = row.concat(currentCell.getNumericCellValue() + "|");
			}
			break;
		case BOOLEAN:
			row = row.concat(currentCell.getBooleanCellValue() + "|");
			break;
		default:
			row = row.concat(" ");
		}
		return row;
	}

	/**
	 * Generates an Excel file in the location indicated by the user when the Excel_Helper was initialized
	 * 
	 * @throws IOException If the stream itself is corrupted or some error occurred during reading
	 */
	public void writeExcel() {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Code Smells");
		createHeader(sheet);
		fillRows(sheet);
		adjustColumnSpacing(sheet);

		try (FileOutputStream outputStream = new FileOutputStream(file_path)) {
			workbook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Given a sheet of an Excel file, fills its rows with the information about the project indicated by the user when the Excel_Helper was initialized
	 * 
	 * @param sheet Sheet of an Excel file
	 */
	private void fillRows(XSSFSheet sheet) {
		int methodID = 1;
		for (JavaPackage p : project.getPackage_list()) {
			for (JavaClass c : p.getClass_list()) {
				for (JavaMethod m : c.getMethods_list()) {

					Row row = sheet.createRow(methodID);
					
					Object[] data = {methodID, p.getName(), c.getName(), m.getName(), c.getNOMClass(), c.getLOCClass(), 
							c.getWMCClass(), m.getLOCMethod(), m.getCYCLO_method()};
					
					int column = 0;

					for (Object field : data) {
						Cell cell = row.createCell(column);
						if (field instanceof String)
							cell.setCellValue((String) field);
						else if (field instanceof Integer)
							cell.setCellValue((Integer) field);
						column++;
					}

					methodID++;
				}
			}
		}
	}

	/**
	 * Given a sheet of an Excel file, fills the first row of the Excel file with the header that contains the name of the metrics that are going to be analyzed 
	 * 
	 * @param sheet Sheet of an Excel file
	 */
	private void createHeader(XSSFSheet sheet) {

		String[] headers = {"MethodID", "package", "class", "method", "NOM_class", "LOC_class", "WMC_class", "LOC_method", "CYCLO_method"};
		
		Row row = sheet.createRow(0);

		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		Font font = sheet.getWorkbook().createFont();
		font.setBold(true);
		cellStyle.setFont(font);

		int columnCount = 0;

		for (String field : headers) {
			Cell cell = row.createCell(columnCount);
			cell.setCellValue(field);
			cell.setCellStyle(cellStyle);
			columnCount++;
		}
	}

	/**
	 * Given a sheet of an Excel file, modifies the size of the columns so that every component can be read
	 * 
	 * @param sheet Sheet of an Excel file
	 */
	private void adjustColumnSpacing(XSSFSheet sheet) {
		for (int i = 0; i < 11; i++) {
			sheet.autoSizeColumn(i);
		}

	}	

}