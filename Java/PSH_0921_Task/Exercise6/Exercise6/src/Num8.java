import java.util.Scanner;

public class Num8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("문자열을 입력하세요. 빈칸이 있어도 되고 영어 한글 모두 됩니다.");
		String str = scanner.nextLine();
		
		for(int i = 1; i < str.length() + 1; i++) {
			String start = str.substring(0, i);
			String Last = str.substring(i);
			String result = Last + start;
			System.out.println(result);
		}
		scanner.close();
	}

}
