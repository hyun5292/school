import java.util.Scanner;

public class StringTurn {

	public static void main(String[] args) {
		System.out.println("���ڿ��� �Է��ϼ���. �� ĭ�� �־ �ǰ� ���� �ѱ� ��� �˴ϴ�.");
		Scanner scanner = new Scanner(System.in);
		String text = scanner.nextLine();
		for(int i = 0; i < text.length(); i++) {
			String first = text.substring(0, 1);
			String last = text.substring(1);
			text = last + first;
			System.out.println(text);
		}
		scanner.close();
	}

}
