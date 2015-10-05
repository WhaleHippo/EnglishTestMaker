import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class convertToText { // 읽은xlsx파일을 비스킷 폼에 맞게 txt형태로 변환하는 클래
	/*
	 * main 코드 출처 http://jsonobject.tistory.com/127
	 * http://blog.naver.com/sarah7_2000/220414217387
	 */
	private static String URL = "/home/whalehippo/english test/convert";
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
			int cells = row.getPhysicalNumberOfCells();// 몇칸인지
			
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
}
