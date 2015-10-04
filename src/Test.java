import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {
	/*
	 * main 코드 출처 http://jsonobject.tistory.com/127
	 * http://blog.naver.com/sarah7_2000/220414217387
	 */
	public static void main(String[] args) throws IOException {
		FileOutputStream fileOut = null;
		try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("C:/Users/Administrator/Desktop/hippo.xlsx"));

			System.out.println("시트개수 : " + wb.getNumberOfSheets());

			XSSFSheet sheet = wb.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();// 몇줄 인지
			System.out.println("행수 : " + rows);
			for (int r = 0; r < rows; r++) {
				XSSFRow row = sheet.getRow(r);// 한줄 얻어오기
				if (row == null) // 못읽어오면 다음줄로
				{
					continue;
				}
				int cells = row.getPhysicalNumberOfCells();// 몇칸인지
				System.out.println(cells);
				int result = 0;
				for (int c = 0; c < cells; c++)// 칸수 만큼 작업
				{
					XSSFCell cell = row.getCell(c);// 한칸 얻어오기
					String value = cell.getStringCellValue(); // 셀의 데이터를 string으로 가져오기

					System.out.print(value + "\t");// 읽어온 칸의 값을 출력
				}

				row.createCell(5).setCellValue(result);// 그 행의 index가 5인 셀이 result의 밸류를 넣는다.

				System.out.println();// 줄바꿈
			}

			fileOut = new FileOutputStream("C:/Users/Administrator/Desktop/test.xlsx");
			wb.write(fileOut);// 파일에 저장
			wb.close();
		} finally {
			fileOut.close();
			System.out.println("종료");
		}
	}
}