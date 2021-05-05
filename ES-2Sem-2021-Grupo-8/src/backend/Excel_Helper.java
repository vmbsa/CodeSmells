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

public class Excel_Helper {

	private static String XLSX_FILE_NAME;
	private ArrayList<String> rows = new ArrayList<String>(); 
	
	private String file_path;
	private JavaFilesHandler project;;

	public Excel_Helper(String name) {
		super();
		Excel_Helper.XLSX_FILE_NAME = name;
	}
	
	public Excel_Helper(JavaFilesHandler project, String file_path) {
		this.file_path = file_path;
		this.project = project;
	}

	public ArrayList<String> readExcelSheet(boolean isXlsx) {
		try {
			FileInputStream excelFile = new FileInputStream(new File(XLSX_FILE_NAME));
			try (Workbook workbook = isXlsx ? new XSSFWorkbook(excelFile) : new HSSFWorkbook(excelFile)) {
				Sheet workSheet = workbook.getSheetAt(0);
				String row = "";
				for (Row currentRow : workSheet) {
					for (Cell currentCell : currentRow) {
						String helper=printCellContents(currentCell);
						row=row.concat(helper);
						
					}
					rows.add(row);
					row="";
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rows;
	}

	private static String printCellContents(Cell currentCell) {
		String row = new String("");
		switch (currentCell.getCellType()) {
		case STRING:
			row=row.concat(currentCell.getRichStringCellValue().getString() + "|");
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(currentCell)) {
				row=row.concat(currentCell.getDateCellValue() + "|");
			} else {
				row = row.concat(currentCell.getNumericCellValue() + "|");
			}
			break;
		case BOOLEAN:
			row=row.concat(currentCell.getBooleanCellValue() + "|");
			break;
		default:
			row=row.concat(" ");
		}
		return row;
	}
	
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
	
	private void fillRows(XSSFSheet sheet) {
		int methodID = 1;
		for (JavaPackage p : project.getPackage_list()) {
			for (JavaClass c : p.getClass_list()) {
				for (JavaMethod m : c.getMethods_list()) {
					
					Row row = sheet.createRow(methodID);
					
					Object[] data = {methodID, p.getName(), c.getName(), m.getName(), c.getNOMClass(), c.getLOCClass(), 
							c.getWMCClass(), "Por fazer", m.getLOCMethod(), m.getCYCLO_method(), "Por fazer"};
					
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
	
	private void createHeader(XSSFSheet sheet) {
		String[] headers = {"MethodID", "package", "class", "method", "NOM_class", "LOC_class", "WMC_class", "is_God_Class", "LOC_method", "CYCLO_method", "is_Long_Method"};
		
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
	
	private void adjustColumnSpacing(XSSFSheet sheet) {
		for (int i = 0; i < 11; i++) {
			sheet.autoSizeColumn(i);
		}
	}
	
	public static void main(String[] args) {
		String path = "C:\\Users\\Lourenco\\Desktop\\LEI\\2º Ano\\PCD\\Aula1";

		JavaFilesHandler j;
		try {
			j = new JavaFilesHandler(path);
			String excel = "C:\\Users\\Lourenco\\Desktop\\LEI\\" + j.getProjectName() + "_metrics.xlsx";
			Excel_Helper e = new Excel_Helper(j, excel);
			e.writeExcel();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
	}
	
	
}