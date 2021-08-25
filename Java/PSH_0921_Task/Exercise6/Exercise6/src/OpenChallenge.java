import java.util.Scanner;

class Challenge{
	private int[] AToZ_Count = new int[26];
	private char[] Alphabet = new char[26];
	private String line = new String();
	
	public Challenge(String line) {
		this.line = line.toUpperCase();
	}
	
	public void Alphabet_Set() {
		int i = 0;
		for(char c = 'A'; c <= 'Z'; c++) {
			Alphabet[i] = c;
			i++;
		}
	}
	
	public void AToZ_Count_Set() {
		for(int i = 0; i < AToZ_Count.length; i++) {
			AToZ_Count[i] = 0;
		}
		
		for(int i = 0; i < Alphabet.length; i++) {
			for(int j = 0; j < line.length(); j++) {
				char c = line.charAt(j);
				
				if(c == Alphabet[i]) {
					AToZ_Count[i]++;
				}
			}
		}
	}
	
	public void Histogram() {
		for(int i = 0; i < AToZ_Count.length; i++) {
			System.out.print(Alphabet[i]);
			for(int j = 0; j < AToZ_Count[i]; j++) {
				System.out.print("-");
			}
			System.out.println();
		}
	}
}

public class OpenChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("영문 텍스트를 입력하고 세미콜론을 입력하세요.");
		StringBuffer sb = new StringBuffer();
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			String line = scanner.nextLine();
			if(line.length() == 1 && line.charAt(0) == ';') {
				break;
			}
			sb.append(line);
		}
		Challenge ch = new Challenge(sb.toString());
		
		System.out.println("히스토그램을 그립니다.");
		ch.Alphabet_Set();
		ch.AToZ_Count_Set();
		ch.Histogram();
	}

}
