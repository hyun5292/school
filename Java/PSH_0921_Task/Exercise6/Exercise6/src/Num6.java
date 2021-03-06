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
		
		System.out.print(this.player + " 시작 <Enter키>>");
		String start = scanner.nextLine();
		int StartPoint = cal.get(Calendar.SECOND);
		System.out.println("\t현재 초 시간 = " + StartPoint);
		System.out.print("10초 예상 후 <Enter키>>");
		String end = scanner.nextLine();
		cal = Calendar.getInstance();
		int EndPoint = cal.get(Calendar.SECOND);
		System.out.println("\t현재 초 시간 = " + EndPoint);
		
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
		p[0] = new Player("황기태");
		p[1] = new Player("이재문");
		
		System.out.println("10초에 가까운 사람이 이기는 게임입니다.");
		int result_player1 = p[0].Run();
		int result_player2 = p[1].Run();
		
		System.out.print(p[0].GetName() + "의 결과 " + result_player1 + ", ");
		System.out.print(p[1].GetName() + "의 결과 " + result_player2 + ", ");
		if(Math.abs(10 - result_player1) < Math.abs(10 - result_player2)) {
			System.out.println("승자는 " + p[0].GetName());
		}
		else if(Math.abs(10 - result_player1) > Math.abs(10 - result_player2)) {
			System.out.println("승자는 " + p[1].GetName());
		}
		else {
			System.out.println("무승부");
		}
	}

}
