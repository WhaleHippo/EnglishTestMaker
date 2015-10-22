import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// readFiles.readFilesforConvert();
		// readFiles.makeTotalFile();
		// readFiles.readFilesforMakeTest(250);
		// System.out.print("종료");
		while (true) {
			Scanner s = new Scanner(System.in);
			String word = s.nextLine();
			System.out.println(word);
			if (convertToText.isword(word)) {
				System.out.println("단어입니다.");
			} else {
				System.out.println("숙어입니다.");
			}
		}
	}
}