import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class database {
	private String url = "/home/whalehippo/Dropbox/englishDatabase/";
	HSSFWorkbook readdata = null;
	HSSFWorkbook database = null;

	public database(String name) {
		seturl(name);
		try {
			database = new HSSFWorkbook(new FileInputStream(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void insert(File file){
		try {
			readdata = new HSSFWorkbook(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert(String a){
		
	}

	private void seturl(String a) {
		url = url + a + ".xlsx";
	}
	
	public void close(){
		
	}
}
