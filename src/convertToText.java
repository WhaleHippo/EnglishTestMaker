import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class convertToText { // 읽은xlsx파일을 비스킷 폼에 맞게 txt형태로 변환하는 클래

	private static String URL = "/home/whalehippo/Dropbox/englishtest convert";
	//private static String URL = "D:/dropbox/Dropbox/englishtest convert";
	public static void convert(FileInputStream file, String name) {
		FileWriter convertText = null;
		XSSFWorkbook wb = null;
		try {
			convertText = new FileWriter(URL +"/"+ name + ".txt");
			wb = new XSSFWorkbook(file);
		} catch (IOException e) {
			System.out.println("변환에 필요한 파일을 만들지 못했습니다.");
			e.printStackTrace();
		}

		XSSFSheet sheet = wb.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();// 몇줄 인지
		System.out.println("행수 : " + rows);
		for (int r = 0; r < 50; r++) {
			XSSFRow row = sheet.getRow(r);// 한줄 얻어오기
			if (row == null){	continue;	}// 못읽어오면 다음줄로
			
			String value = "";

			XSSFCell cell = row.getCell(0);// 한칸 얻어오기
			value = value + cell.getStringCellValue() + "\t" + " " + " ";
			cell = row.getCell(1);// 한칸 얻어오기
			value = value + cell.getStringCellValue() + "\t";
			value = value + "0" + "\n";
			//System.out.println("열수 : " + cells);
			//System.out.print(r + ":" + value);
			try {
				convertText.write(value);
			} catch (IOException e) {
				System.out.println("변환파일을 쓰지 못하였습니다.");
			}

			value = "";
		}

		try {
			wb.close();
			convertText.close();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void convert(database DB, int option){
		convertAll(DB);
		convertwordonly(DB);
		convertwordonlymeantoword(DB);
	}
	
	private static void convertAll(database DB){
		FileWriter FW = null;
		try {
			FW = new FileWriter("/home/whalehippo/Dropbox/앱/Biscuit"+"/convertAll.txt");
		} catch (IOException e) {
			System.out.println("병합할 파일을 생성하지 못하였습니다");
			e.printStackTrace();
		}
		
		XSSFSheet readSheet = DB.getDB().getSheetAt(0);
	
		for(int i = 1;i<readSheet.getPhysicalNumberOfRows();i++){
			XSSFRow readRow = readSheet.getRow(i);
			try {
				FW.write(readRow.getCell(1).getStringCellValue()+"\t  "+readRow.getCell(2).getStringCellValue()+"\t0\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			FW.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void convertwordonly(database DB){
		FileWriter FW = null;
		try {
			FW = new FileWriter("/home/whalehippo/Dropbox/앱/Biscuit"+"/convertwordonly.txt");
		} catch (IOException e) {
			System.out.println("병합할 파일을 생성하지 못하였습니다");
			e.printStackTrace();
		}
		
		XSSFSheet readSheet = DB.getDB().getSheetAt(0);
	
		for(int i = 1;i<readSheet.getPhysicalNumberOfRows();i++){
			XSSFRow readRow = readSheet.getRow(i);
			if(readRow.getCell(3).getNumericCellValue() == 1){
				continue;
			}
			try {
				FW.write(readRow.getCell(1).getStringCellValue()+"\t  "+readRow.getCell(2).getStringCellValue()+"\t0\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			FW.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void convertwordonlymeantoword(database DB){
		FileWriter FW = null;
		try {
			FW = new FileWriter("/home/whalehippo/Dropbox/앱/Biscuit"+"/convertwordonlymeantoword.txt");
		} catch (IOException e) {
			System.out.println("병합할 파일을 생성하지 못하였습니다");
			e.printStackTrace();
		}
		
		XSSFSheet readSheet = DB.getDB().getSheetAt(0);
	
		for(int i = 1;i<readSheet.getPhysicalNumberOfRows();i++){
			XSSFRow readRow = readSheet.getRow(i);
			if(readRow.getCell(3).getNumericCellValue() == 1){
				continue;
			}
			try {
				FW.write(readRow.getCell(1).getStringCellValue()+"\t  "+readRow.getCell(2).getStringCellValue()+"\t0\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			FW.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getURL(){
		return URL;
	}
}
