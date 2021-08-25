import java.util.Scanner;

public class Num7_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.print(">>");
			String str = scanner.nextLine();
			if(str.equals("그만")) {
				System.out.println("종료합니다...");
				break;
			}
			else {
				String s = new String(str);
				String result[] = s.split(" ");
				System.out.println("어절 개수는 " + result.length);
			}
		}
	}

}
