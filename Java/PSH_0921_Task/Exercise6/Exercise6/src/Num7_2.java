import java.util.Scanner;

public class Num7_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.print(">>");
			String str = scanner.nextLine();
			if(str.equals("�׸�")) {
				System.out.println("�����մϴ�...");
				break;
			}
			else {
				String s = new String(str);
				String result[] = s.split(" ");
				System.out.println("���� ������ " + result.length);
			}
		}
	}

}
