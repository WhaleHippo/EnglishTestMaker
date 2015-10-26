import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class readFiles { // 폴더 내의 파일을 읽는 클래스. xlsx파일을 변환할때나, 변환된 txt파일로 테스트지를 만들때 사용 
	private static String URL = "/home/whalehippo/Dropbox/englishtest"; // 변환할 xlsx 파일이 저장된 폴더
	private static String TESTURL = "/home/whalehippo/Dropbox/앱/Biscuit"; // 병합된 텍스트파일, 테스트파일을 저장하는 폴더
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
	
	public static void makeTotalFile(){ // 변환된 파일을 병합하는 거
		String convertURL = convertToText.getURL();
		dir = new File(convertURL);
		FileWriter FW = null; // 병합된 파일
		BufferedReader BR = null;
		try {
			FW = new FileWriter(TESTURL+"/hippo.txt");
		} catch (IOException e) {
			System.out.println("병합할 파일을 생성하지 못하였습니다");
			e.printStackTrace();
		}
		if(dir.exists()){
			int max = dir.list().length;
			for(int i =0;i<max;i++){
				try {
					BR = new BufferedReader(new FileReader(convertURL+"/"+dir.list()[i]));
				} catch (FileNotFoundException e) {
					System.out.println("변환된 파일을 읽지 못하였습니다.");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try{
					for(String line = BR.readLine();line!=null;line = BR.readLine()){
						if(line!=null){FW.write(line+"\n");}
					}
				}catch(Exception e){
					System.out.println("파일을 읽는 중에 에러 발생");
					e.printStackTrace();
				}
			}
		}
		try {
			FW.close();
			BR.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readFilesforMakeTest(int num){
		BufferedReader BR = null;
		FileWriter FW = null;
		Vector<String> V = new Vector<String>(num);
		try{
			BR = new BufferedReader(new FileReader(TESTURL+"/hippo.txt"));
			FW = new FileWriter(TESTURL+"/english test.txt");
			for(String line = BR.readLine() ; line!=null ; line = BR.readLine()){
				V.addElement(line);
			}
			
			if(num>V.size()){num=V.size()%num;}
			int index = 0;
			for(int i = 0 ; i<num ; i++){
				index = ((int)(Math.random()*2*V.size())%V.size());
				FW.write(V.get(index)+"\n");
				V.remove(index);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try {
			FW.close();
			BR.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void readFilesforMakeTest(){
		readFilesforMakeTest(50);
	}
}
