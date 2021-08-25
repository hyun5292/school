import java.util.Scanner;

class Person2 {
	private String player;
	private Scanner scanner = new Scanner(System.in);

	public Person2(String player) {
		this.player = player;
	}
	
	public String GetName() {
		return this.player;
	}

	public boolean play() {
		System.out.print("[" + this.player + "]:<Enter>");
		String enter = scanner.nextLine();
		int n1 = (int) (Math.random() * 3 + 1);
		int n2 = (int) (Math.random() * 3 + 1);
		int n3 = (int) (Math.random() * 3 + 1);

		System.out.print("\t\t" + n1 + " " + n2 + " " + n3 + " ");
		boolean result = false;
		if (n1 == n2 && n2 == n3) {
			result = true;
		}

		return result;
	}
}

public class Num12 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		System.out.print("겜블링 게임에 참여할 선수 숫자>>");
		int num = scanner.nextInt();
		scanner.nextLine();
		Person2[] p = new Person2[num];
		
		for (int i = 0; i < p.length; i++) {
			System.out.print((i + 1) + "번째 선수 이름>>");
			p[i] = new Person2(scanner.nextLine());
		}

		int i = 0;
		while (true) {
			if (p[i].play()) {
				System.out.println(p[i].GetName() + "님이 이겼습니다!");
				break;
			} else {
				System.out.println("아쉽군요!");
				i++;
				i = i % p.length;
			}
		}
	}
}