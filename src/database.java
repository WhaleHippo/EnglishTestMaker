import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class database {
	
	/*
	 * main 코드 출처 http://jsonobject.tistory.com/127
	 * http://blog.naver.com/sarah7_2000/220414217387
	 */
	
	private String url = "/home/whalehippo/Dropbox/englishDatabase/";
	//private String url = "D:/dropbox/Dropbox/englishDatabase";
	private XSSFWorkbook readData = null; // 입력될 데이터
	private XSSFWorkbook database = null; // DB...라고 쓰고 그냥 엑셀파일이라 읽는다 ㅇㅅㅇ

	public database(String name) {
		seturl(name);
		try {
			database = new XSSFWorkbook(new FileInputStream(url));
			System.out.println("데이터베이스 열수 : "+database.getSheetAt(0).getPhysicalNumberOfRows());
		} catch (Exception e) {
			System.out.println("database파일을 읽을수 없다.");
			e.printStackTrace();
			return;
		}
	}
	public database(File file){ // 절찬 작업중 문제 발생 : 크레이트파일메소드로 엑셀파일을 만든다 한들, 그저 확장자만 엑셀인 0바이트의 파일에 불과함. 그래도 작동할까?
		seturl(file.getName());
		file = new File(url);
		if(!file.isFile()){ creatDatabase(file); }
		try {
			database = new XSSFWorkbook(new FileInputStream(file));
			System.out.println("데이터베이스 열수 : "+database.getSheetAt(0).getPhysicalNumberOfRows());
		} catch (Exception e) {
			System.out.println("database파일을 읽을수 없다.");
			e.printStackTrace();
			return ;
		}
	}
	
	private void creatDatabase(File file){ // db파일에 해당될 엑셀파일이 없을경우, 생성하기 위한 메소드 당연하지만 아직덜만듬ㅋ
		try {
			System.out.println("db파일이 없어서 생성합니다.");
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public XSSFWorkbook getDB(){
		return database;
	}
	
	
	public void insert(File file){
		try {
			readData = new XSSFWorkbook(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		XSSFSheet readSheet = readData.getSheetAt(0);
		XSSFSheet writeSheet = database.getSheetAt(0);
		System.out.println("읽을 파일의 열수 "+readSheet.getPhysicalNumberOfRows());
		for(int i = 0; i<readSheet.getPhysicalNumberOfRows();i++){
			XSSFRow readRow = readSheet.getRow(i);
			try {
				if (readRow.getCell(0).getStringCellValue() == null || readRow.getCell(0).getStringCellValue().equalsIgnoreCase("")) {
					break;
				}
			} catch (Exception e) {
				break;
			}
			XSSFRow writeRow = writeSheet.createRow(writeSheet.getPhysicalNumberOfRows());
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
	
	public void insert(String a){ // 절찬 작업중
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
	
	public void update(){ // database의 자료등을 업데이트 할때 사용
		
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