import java.io.File;
import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException{

		//useoldversion();
		//writeAllData();
		database db = new database("TOEIC");
		
		//db.insert(new File("null"));
		convertToText.convert(db, 1, 500);
		convertToText.convert(db, 2, 500);
		convertToText.convert(db, 3, 500);
		System.out.println("종료");
	}
	
	public static void useoldversion(){
		 readFiles.readFilesforConvert();
		 //readFiles.makeTotalFile();
		 //readFiles.readFilesforMakeTest(250); 
	}
	
	public static void writeAllData(){
		database db = new database("TOEIC");
		for (int i = 0; i < allData().length; i++) {
			db.insert(new File("/home/whalehippo/Dropbox/englishtest/"+ allData()[i].getName()));
		}
		db.close();
	}

	public static File[] allData() {
		File dir = new File("/home/whalehippo/Dropbox/englishtest");

		return dir.listFiles();
	}
}