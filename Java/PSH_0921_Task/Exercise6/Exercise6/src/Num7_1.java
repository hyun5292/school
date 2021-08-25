import java.util.StringTokenizer;
import java.util.Scanner;

public class Num7_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.print(">>");
			String str = scanner.nextLine();
			if(str.equals("그만")) {
				System.out.println("종료합니다...");
				break;
			}
			else {
				StringTokenizer st = new StringTokenizer(str, " ");
				int num = st.countTokens();
				System.out.println("어절 개수는 " + num);
			}
		}
	}

}
