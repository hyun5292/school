import java.util.StringTokenizer;
import java.util.Scanner;

public class Num7_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.print(">>");
			String str = scanner.nextLine();
			if(str.equals("�׸�")) {
				System.out.println("�����մϴ�...");
				break;
			}
			else {
				StringTokenizer st = new StringTokenizer(str, " ");
				int num = st.countTokens();
				System.out.println("���� ������ " + num);
			}
		}
	}

}
