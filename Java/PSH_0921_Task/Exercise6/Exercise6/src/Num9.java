import java.util.Scanner;

class RockScissorsPaper{
	private int num;
	public RockScissorsPaper(int num) {
		this.num = num;
	}
	
	public int Check() {
		int com = (int)(Math.random() * 3 + 1);
		int result = 0;
		if(this.num == 1) { //������ ��
			System.out.print("ö��(����) : ��ǻ��(");
			if(com == 1) {  //����
				System.out.println("����)");
				result = 3;
			}
			else if(com == 2) {  //����
				System.out.println("����)");
				result = 2;
			}
			else if(com == 3) {  //��
				System.out.println("��)");
				result = 1;
			}
		}
		else if(this.num == 2) {  //������ ��
			System.out.print("ö��(����) : ��ǻ��(");
			if(com == 1) {  //����
				System.out.println("����)");
				result = 1;
			}
			else if(com == 2) {  //����
				System.out.println("����)");
				result = 3;
			}
			else if(com == 3) {  //��
				System.out.println("��)");
				result = 2;
			}
		}
		else if(this.num == 3) {  //���� ��
			System.out.print("ö��(��) : ��ǻ��(");
			if(com == 1) {  //����
				System.out.println("����)");
				result = 2;
			}
			else if(com == 2) {  //����
				System.out.println("����)");
				result = 1;
			}
			else if(com == 3) {  //��
				System.out.println("��)");
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
			System.out.print("ö��[����(1), ����(2), ��(3), ������(4)]>>");
			int num = scanner.nextInt();
			
			if(num == 4) {
				System.out.println("�����մϴ�...");
				break;
			}
			else {
				RockScissorsPaper c = new RockScissorsPaper(num);
				int chk = c.Check();
				if(chk == 1) {
					System.out.println("ö���� �̰���ϴ�.");
				}
				else if(chk == 2){
					System.out.println("��ǻ�Ͱ� �̰���ϴ�.");
				}
				else {
					System.out.println("���º��Դϴ�.");
				}
			}
		}
	}

}
