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
			System.out.println("��ȭ��ȣ �Է� ���α׷��Դϴ�.");
			while(true) {
				System.out.print("�̸� ��ȭ��ȣ >> ");
				String line = scanner.nextLine();
				if(line.equals("�׸�")) {
					break;
				}
				fout.write(line, 0, line.length());
				fout.write("\r\n", 0, 2);
			}
			fout.close();
			System.out.println(f.getPath() + "�� �����Ͽ����ϴ�.");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		scanner.close();
	}
}
