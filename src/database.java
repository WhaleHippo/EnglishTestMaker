import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class database {
	private String url = "/home/whalehippo/Dropbox/englishDatabase/";
	//private String url = "D:/dropbox/Dropbox/englishDatabase";
	XSSFWorkbook readData = null; // 입력될 데이터
	XSSFWorkbook database = null; // DB...라고 쓰고 그냥 엑셀파일이라 읽는다 ㅇㅅㅇ

	public database(String name) {
		seturl(name);
		try {
			database = new XSSFWorkbook(new FileInputStream(url));
		} catch (Exception e) {
			System.out.println("database파일을 읽을수 없다.");
			e.printStackTrace();
		}
		System.out.println("데이터베이스 열수 : "+database.getSheetAt(0).getPhysicalNumberOfRows());
	}
	
	
	public void insert(File file){ // 절찬 작업중
		try {
			readData = new XSSFWorkbook(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		XSSFSheet readSheet = readData.getSheetAt(0);
		XSSFSheet writeSheet = database.getSheetAt(0);
		System.out.println("읽을 파일의 열수 "+readSheet.getPhysicalNumberOfRows());
		for(int i = 0; i<readSheet.getPhysicalNumberOfRows();i++){ // 지금 작업하고 있는 부분
			XSSFRow readRow = readSheet.getRow(i);
			try {
				if (readRow.getCell(0).getStringCellValue() == null || readRow.getCell(0).getStringCellValue().equalsIgnoreCase("")) {
					break;
				}
			} catch (Exception e) {
				break;
			}
			XSSFRow writeRow = writeSheet.createRow(writeSheet
					.getPhysicalNumberOfRows());
			try {
				writeRow.createCell(0).setCellValue(file.getName());
				writeRow.createCell(1).setCellValue(readRow.getCell(0).getStringCellValue());
				writeRow.createCell(2).setCellValue(readRow.getCell(1).getStringCellValue());
				writeRow.createCell(3).setCellValue(this.isword(readRow.getCell(0).getStringCellValue()));
			} catch (Exception e) {
				break;
			}
		}
		save();
	}
	
	public void insert(String a){
		XSSFSheet writeSheet = database.getSheetAt(0);
		XSSFRow writeRow = writeSheet.getRow(0);
		writeRow.createCell(5).setCellValue(a);
		
		save();
		
	}
	
	private void save(){ // 변경사항 저장 함
		try {
			database.write(new FileOutputStream(url));
		} catch (Exception e) {
			
		}
	}
	
	public void print(){
		XSSFSheet writeSheet = database.getSheetAt(0);
		System.out.println("열수" + writeSheet.getPhysicalNumberOfRows());
		for(int i=0;i<writeSheet.getPhysicalNumberOfRows();i++){
			XSSFRow row = writeSheet.getRow(i);
			for(int j=0;j<row.getPhysicalNumberOfCells();j++){
				System.out.print(row.getCell(j).getStringCellValue()+"\t");
			}
			System.out.println("");
		}
	}
	

	private void seturl(String a) {
		url = url + "/"+ a + ".xlsx";
	}
	
	public void close(){
		try {
			database.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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