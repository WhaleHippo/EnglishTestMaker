import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class convertToText { // 읽은xlsx파일을 비스킷 폼에 맞게 txt형태로 변환하는 클래

	private static String URL = "/home/whalehippo/Dropbox/englishtest convert";
	private static String BISCUIT = "/home/whalehippo/Dropbox/앱/Biscuit";
	//private static String URL = "D:/dropbox/Dropbox/englishtest convert";
	//private static String BISCUIT = "D:/dropbox/Dropbox/앱/Biscuit";
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
	
	public static void convert(File file, int option){
		database DB = new database(file);
		settingOption(option);
		convert(DB,firstRead,secondRead, wordOnly,1000);
	}
	
	public static void convert(database DB, int option, int numData){
		settingOption(option);
		convert(DB,firstRead,secondRead, wordOnly,numData);
	}
	

	
	private static void convert(database DB, int firstRead, int secondRead, boolean wordOnly, int numData){
		FileWriter FW = null;
		try {
			FW = new FileWriter(BISCUIT+"/"+System.currentTimeMillis()%10000+wordOnly+".txt");
		} catch (IOException e) {
			System.out.println("병합할 파일을 생성하지 못하였습니다");
			e.printStackTrace();
		}
		
		XSSFSheet readSheet = DB.getDB().getSheetAt(0);
		numData = Math.min(numData, readSheet.getPhysicalNumberOfRows());
		
		for(int i = 1;i<=numData;i++){
			XSSFRow readRow = readSheet.getRow(i);
			if(wordOnly && (readRow.getCell(3).getNumericCellValue() == 1)){
				System.out.println("숙어 아웃");
				numData++;
				continue;
			}
			try {
				FW.write(readRow.getCell(firstRead).getStringCellValue()+"\t  "+readRow.getCell(secondRead).getStringCellValue()+"\t0\n");
			} catch (IOException e) {
				e.printStackTrace();
				break;
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
	
	private static int firstRead = 0;
	private static int secondRead = 0;
	private static boolean wordOnly = false;
	private static void settingOption(int option) { // 변환에 사용할 옵션들을 세팅
		switch (option) {
		case 1: // 단어 + 숙어 데이터 출력
			firstRead = 1;
			secondRead = 2;
			wordOnly = false;
			break;
		case 2: // 단어만 출력
			firstRead = 1;
			secondRead = 2;
			wordOnly = true;
			break;
		case 3: // 단어만 출력하되, 뜻 -> 단어 순으로 출력
			firstRead = 2;
			secondRead = 1;
			wordOnly = true;
			break;
		}
	}
	
}