import java.util.Scanner;
import java.util.Calendar;

class Player{
	String player;
	private Scanner scanner = new Scanner(System.in);
	public Player(String player) {
		this.player = player;
	}
	
	public String GetName() {
		return this.player;
	}
	
	public int Run() {
		Calendar cal = Calendar.getInstance();
		
		System.out.print(this.player + " ���� <EnterŰ>>");
		String start = scanner.nextLine();
		int StartPoint = cal.get(Calendar.SECOND);
		System.out.println("\t���� �� �ð� = " + StartPoint);
		System.out.print("10�� ���� �� <EnterŰ>>");
		String end = scanner.nextLine();
		cal = Calendar.getInstance();
		int EndPoint = cal.get(Calendar.SECOND);
		System.out.println("\t���� �� �ð� = " + EndPoint);
		
		int result;
		if(StartPoint > EndPoint) {
			result = (60 - StartPoint) + EndPoint;
		}
		else {
			result = EndPoint - StartPoint;
		}
		
		return result;
	}
}

public class Num6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		Player p[] = new Player[2];
		p[0] = new Player("Ȳ����");
		p[1] = new Player("���繮");
		
		System.out.println("10�ʿ� ����� ����� �̱�� �����Դϴ�.");
		int result_player1 = p[0].Run();
		int result_player2 = p[1].Run();
		
		System.out.print(p[0].GetName() + "�� ��� " + result_player1 + ", ");
		System.out.print(p[1].GetName() + "�� ��� " + result_player2 + ", ");
		if(Math.abs(10 - result_player1) < Math.abs(10 - result_player2)) {
			System.out.println("���ڴ� " + p[0].GetName());
		}
		else if(Math.abs(10 - result_player1) > Math.abs(10 - result_player2)) {
			System.out.println("���ڴ� " + p[1].GetName());
		}
		else {
			System.out.println("���º�");
		}
	}

}