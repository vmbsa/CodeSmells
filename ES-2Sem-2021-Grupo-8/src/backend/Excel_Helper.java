package backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Helper {

	private static String XLSX_FILE_NAME;
	private ArrayList<String> rows = new ArrayList<String>();

	public Excel_Helper(String name) {
		super();
		Excel_Helper.XLSX_FILE_NAME = name;
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
//					System.out.println(row);
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
			//System.out.print(currentCell.getRichStringCellValue().getString() + "|");
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(currentCell)) {
				row=row.concat(currentCell.getDateCellValue() + "|");
				//System.out.print(currentCell.getDateCellValue() + "|");
			} else {
				row=row.concat(currentCell.getNumericCellValue() + "|");
				//System.out.print(currentCell.getNumericCellValue() + "|");
			}
			break;
		case BOOLEAN:
			row=row.concat(currentCell.getBooleanCellValue() + "|");
			//System.out.print(currentCell.getBooleanCellValue() + "|");
			break;
		case FORMULA:
			row=row.concat(currentCell.getCellFormula() + "|");
			//System.out.print(currentCell.getCellFormula() + "|");
			break;
		case BLANK:
			row=row.concat("");
			//System.out.print("");
			break;
		default:
			row=row.concat("/n");
			//System.out.println();
		}
//		System.out.println(row);
		return row;
	}
}