package Reader;
import java.util.*;
import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {
    private String inputFile;
    
    public void setInputFile(String inputFile) {
	this.inputFile = inputFile;
    }
    public String airports[][] = new String[535][6];
    public ReadExcel() {
	
    }
    public String[][] read() {
	File inputWorkbook = new File(inputFile);
	Workbook w;
	
	try {
	    w = Workbook.getWorkbook(inputWorkbook);
	    Sheet sheet = w.getSheet(0); 
	    
	    for (int col = 0; col < 6; col++) {
		for(int row = 1; row <= 534; row++) {
		    Cell cell = sheet.getCell(col, row);
		    airports[row][col] = cell.getContents();
		}
	    }
	    
	    
	} 
	catch (BiffException e) {
	    e.printStackTrace();
	} 
	catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return airports;

    }
    public String[][] getArray() {
	System.out.print(airports[1][1]);
	return this.airports;
    }
 }