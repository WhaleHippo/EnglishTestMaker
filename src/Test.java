import java.io.File;
import java.io.FileNotFoundException;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		/*
		 * readFiles.readFilesforConvert();
		 * readFiles.makeTotalFile(); //
		 * readFiles.readFilesforMakeTest(250);
		 */

		writeAllData();
		
		System.out.println("종료");
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