import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class readFiles { // 폴더 내의 파일을 읽는 클래스. xlsx파일을 변환할때나, 변환된 txt파일로 테스트지를 만들때 사용 
	private static String URL = "/home/whalehippo/english test";
	private static File dir = null;
	
	public static void readFilesforConvert() {
		dir = new File(URL);
		if (dir.exists()) {
			int max = dir.listFiles().length;
			for (int i = 0; i < max; i++) {
				System.out.println(i+"번째 파일"+dir.listFiles()[i].getName());
				try {
					convertToText.convert(new FileInputStream(dir.listFiles()[i]), dir.listFiles()[i].getName());
				} catch (FileNotFoundException e) {
					System.out.println("해당 파일 스트림을 만들수 없습니다.\n파일명 : "+dir.listFiles()[i]);
					e.printStackTrace();
				}
			}
		}
		else {
			System.out.println("english test 폴더의 경로를 확인하여 주십시오. \n시스템상 등록되어 있는 경로 : "+URL);
		}
	}
	
	public static void readFilesforMakeTest(){
		
	}
}
