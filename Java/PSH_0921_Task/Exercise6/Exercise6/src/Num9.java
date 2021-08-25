import java.util.Scanner;

class RockScissorsPaper{
	private int num;
	public RockScissorsPaper(int num) {
		this.num = num;
	}
	
	public int Check() {
		int com = (int)(Math.random() * 3 + 1);
		int result = 0;
		if(this.num == 1) { //가위일 때
			System.out.print("철수(가위) : 컴퓨터(");
			if(com == 1) {  //가위
				System.out.println("가위)");
				result = 3;
			}
			else if(com == 2) {  //바위
				System.out.println("바위)");
				result = 2;
			}
			else if(com == 3) {  //보
				System.out.println("보)");
				result = 1;
			}
		}
		else if(this.num == 2) {  //바위일 때
			System.out.print("철수(바위) : 컴퓨터(");
			if(com == 1) {  //가위
				System.out.println("가위)");
				result = 1;
			}
			else if(com == 2) {  //바위
				System.out.println("바위)");
				result = 3;
			}
			else if(com == 3) {  //보
				System.out.println("보)");
				result = 2;
			}
		}
		else if(this.num == 3) {  //보일 때
			System.out.print("철수(보) : 컴퓨터(");
			if(com == 1) {  //가위
				System.out.println("가위)");
				result = 2;
			}
			else if(com == 2) {  //바위
				System.out.println("바위)");
				result = 1;
			}
			else if(com == 3) {  //보
				System.out.println("보)");
				result = 3;
			}
		}
		return result;
	}
}

public class Num9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.print("철수[가위(1), 바위(2), 보(3), 끝내기(4)]>>");
			int num = scanner.nextInt();
			
			if(num == 4) {
				System.out.println("종료합니다...");
				break;
			}
			else {
				RockScissorsPaper c = new RockScissorsPaper(num);
				int chk = c.Check();
				if(chk == 1) {
					System.out.println("철수가 이겼습니다.");
				}
				else if(chk == 2){
					System.out.println("컴퓨터가 이겼습니다.");
				}
				else {
					System.out.println("무승부입니다.");
				}
			}
		}
	}

}
