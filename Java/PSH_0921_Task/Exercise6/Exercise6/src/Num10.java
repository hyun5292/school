import java.util.Scanner;

class Person{
	private String player;
	private Scanner scanner = new Scanner(System.in);
	public Person(String player) {
		this.player = player;
	}
	
	public boolean play() {
		System.out.print("[" + this.player + "]:<Enter>");
		String enter = scanner.nextLine();
		int n1 = (int)(Math.random()*3 + 1);
		int n2 = (int)(Math.random()*3 + 1);
		int n3 = (int)(Math.random()*3 + 1);
		
		System.out.print("\t\t" + n1 + " " + n2 + " " + n3 + " ");
		boolean result = false;
		if(n1 == n2 && n2 == n3) {
			result = true;
		}
		
		return result;
	}
}

public class Num10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("1번째 선수 이름>>");
		String player1 = scanner.nextLine();
		Person p1 = new Person(player1);
		System.out.print("2번째 선수 이름>>");
		String player2 = scanner.nextLine();
		Person p2 = new Person(player2);
		
		while(true) {
			if(p1.play()) {
				System.out.println(player1 + "님이 이겼습니다!");
				break;
			}
			else {
				System.out.println("아쉽군요!");
			}
			if(p2.play()) {
				System.out.println(player2 + "님이 이겼습니다!");
				break;
			}
			else {
				System.out.println("아쉽군요!");
			}
			
		}
	}
}