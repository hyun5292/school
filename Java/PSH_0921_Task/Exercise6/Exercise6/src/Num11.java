import java.util.Scanner;

public class Num11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		System.out.print(">>");
		String line = scanner.nextLine();

		while (true) {
			System.out.print("����: ");
			String order = scanner.nextLine();

			if (order.equals("�׸�")) {
				System.out.println("�����մϴ�");
				break;
			} else {
				String[] tokens = order.split("!");

				String[] Line_tokens = line.split(" ");

				int num = 0;
				for (int i = 0; i < Line_tokens.length; i++) {
					if (Line_tokens[i].equals(tokens[0])) {
						Line_tokens[i] = tokens[1];
					} else {
						num++;
					}
				}

				if (num == Line_tokens.length) {
					System.out.println("ã�� �� �����ϴ�!");
				} else if (tokens[0].equals("")) {
					System.out.println("�߸��� �����Դϴ�!");
				} else {
					for (int i = 0; i < Line_tokens.length; i++) {
						System.out.print(Line_tokens[i] + " ");
					}
					System.out.println();
				}
			}
		}
	}
}