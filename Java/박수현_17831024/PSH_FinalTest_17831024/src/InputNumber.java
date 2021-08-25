import java.io.*;
import java.util.Scanner;

public class InputNumber {

	public static void main(String[] args) {
		FileWriter fout = null;
		Scanner scanner = new Scanner(System.in);
		File f = new File("C:\\temp\\phone.txt");
		try
		{
			fout = new FileWriter(f);
			System.out.println("전화번호 입력 프로그램입니다.");
			while(true) {
				System.out.print("이름 전화번호 >> ");
				String line = scanner.nextLine();
				if(line.equals("그만")) {
					break;
				}
				fout.write(line, 0, line.length());
				fout.write("\r\n", 0, 2);
			}
			fout.close();
			System.out.println(f.getPath() + "에 저장하였습니다.");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		scanner.close();
	}
}
