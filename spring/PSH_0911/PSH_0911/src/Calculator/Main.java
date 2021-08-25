package Calculator;
import java.util.Scanner;

public class Main {
	private static float x, y;
	private static String op;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("계산할 두 수를 입력하세요.");
		System.out.print("X의 값 : ");
		x = scanner.nextFloat();
		System.out.print("Y의 값 : ");
		y = scanner.nextFloat();
		
		System.out.println("어떤 연산을 하시겠습니까?(+, -, *, /, %)");
		op = scanner.next();
		
		Operate O = new Operate(x, y, op);
	}

}
