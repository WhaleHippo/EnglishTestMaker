import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class database {
	//private String url = "/home/whalehippo/Dropbox/englishDatabase/";
	private String url = "D:/dropbox/Dropbox/englishDatabase";
	XSSFWorkbook readData = null; // 입력될 데이터
	XSSFWorkbook database = null; // DB...라고 쓰고 그냥 엑셀파일이라 읽는다 ㅇㅅㅇ

	public database(String name) {
		seturl(name);
		try {
			database = new XSSFWorkbook(new FileInputStream(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	public void insert(File file){ // 절찬 작업중
		try {
			readData = new XSSFWorkbook(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		XSSFSheet readSheet = readData.getSheetAt(0);
		XSSFSheet writeSheet = database.getSheetAt(0);
		for(int i = 0; i<readSheet.getPhysicalNumberOfRows();i++){ // 지금 작업하고 있는 부분
			XSSFRow readRow = readSheet.getRow(i);
			XSSFRow writeRow = writeSheet.getRow(writeSheet.getPhysicalNumberOfRows()+1);
			
			writeRow.getCell(0).setCellValue(file.getName());
			writeRow.getCell(1).setCellValue(readRow.getCell(0).getStringCellValue());
			writeRow.getCell(2).setCellValue(readRow.getCell(1).getStringCellValue());
			writeRow.getCell(3).setCellValue(this.isword(readRow.getCell(0).getStringCellValue()));
		}

	}
	
	public void insert(String a){
		
	}

	private void seturl(String a) {
		url = url + "/"+ a + ".xlsx";
	}
	
	public void close(){
		
	}
	
	private int isword(String word) { // 단어인지 숙어인지 파악하는 메소드
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == ' ') {
				return 1; // 숙어
			}
		}
		return 0; // 단어
	}
}