import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

class Word {
	private String english;
	private String korean;

	public Word(String english, String korean) {
		this.english = english;
		this.korean = korean;
	}

	public String getEnglish() {
		return english;
	}

	public String getKorean() {
		return korean;
	}

}

public class WordQuiz {

	private String name;
	private Vector<Word> v;
	Scanner scanner = new Scanner(System.in);

	public WordQuiz(String name) {
		this.name = name;

		v = new Vector<Word>();
		v.add(new Word("love", "���"));
		v.add(new Word("animal", "����"));
		v.add(new Word("emotion", "����"));
		v.add(new Word("human", "�ΰ�"));
		v.add(new Word("stock", "�ֽ�"));
		v.add(new Word("trade", "�ŷ�"));
		v.add(new Word("society", "��ȸ"));
		v.add(new Word("baby", "�Ʊ�"));
		v.add(new Word("honey", "��"));
		v.add(new Word("doll", "����"));
		v.add(new Word("bear", "��"));
		v.add(new Word("picture", "����"));
		v.add(new Word("painting", "�׸�"));
		v.add(new Word("fault", "����"));
		v.add(new Word("example", "����"));
		v.add(new Word("eye", "��"));
		v.add(new Word("statue", "������"));
	}

	private int makeExample(int ex[], int answerIndex) {
		int n[] = { -1, -1, -1, -1 };
		int index;
		for (int i = 0; i < n.length; i++) {
			do {
				index = (int) (Math.random() * v.size());
			} while (index == answerIndex || exists(n, index));
			n[i] = index;
		}
		for (int i = 0; i < n.length; i++) {
			ex[i] = n[i];
		}

		return (int) (Math.random() * n.length);
	}

	private boolean exists(int n[], int index) {
		for (int i = 0; i < n.length; i++) {
			if (n[i] == index)
				return true;
		}

		return false;
	}

	public void run() {
		System.out.println("**** ���� �ܾ� �׽�Ʈ ���α׷� " + this.name + " �Դϴ�. ****");
		while(true) {
			System.out.print("�ܾ� �׽�Ʈ:1, �ܾ� ����:2, ����:3>> ");
			int num = scanner.nextInt();
			switch(num) {
			case 1:
				WordTest();
				break;
			case 2:
				InsertValue();
				break;
			case 3:
				Finish();
				return;
			default:
				System.out.println("\n");
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				break;
			}
		}
	}
	
	public void WordTest() {
		System.out.println("\n\"" + name + "\"" + "�� �ܾ� �׽�Ʈ�� �����մϴ�. -1�� �Է��ϸ� �����մϴ�.");
		System.out.println("���� " + v.size() + "���� �ܾ ��� �ֽ��ϴ�.");

		while (true) {
			int answerIndex = (int) (Math.random() * v.size());
			String eng = v.get(answerIndex).getEnglish();
			int example[] = new int[4];
			int answerLoc = makeExample(example, answerIndex);
			example[answerLoc] = answerIndex;

			System.out.println(eng + "?");
			for (int i = 0; i < example.length; i++) {
				System.out.print("(" + (i + 1) + ")" + v.get(example[i]).getKorean() + " ");
			}
			System.out.print(":>");

			try {
				int in = scanner.nextInt();
				if (in == -1) {
					break;
				}
				in--;

				if (in == answerLoc) {
					System.out.println("Excellent !!");
				} else {
					System.out.println("No. !!");
				}
			} catch (InputMismatchException e) {
				scanner.next();
				System.out.println("���ڸ� �Է��ϼ��� !!");
			}
		}
	}
	
	public void InsertValue() {
		System.out.println("���� �ܾ �׸��� �Է��ϸ� �Է��� �����մϴ�.");
		while(true) {
			System.out.print("���� �ѱ� �Է� >>");
			String eng, kor;
			eng = scanner.next();
			kor = scanner.next();
			if(eng.equals("�׸�")) {
				break;
			}
		}
	}
	
	public void Finish() {
		System.out.println("\"" + this.name + "\"�� �����մϴ�.");
		scanner.close();
	}

	public static void main(String[] args) {
		WordQuiz quiz = new WordQuiz("��ǰ����");
		quiz.run();
	}
}