import java.io.*;
import java.util.*;
import java.util.Scanner;

public class ReadPhoneNumber {
	private static HashMap<String, String> phoneNumbers = new HashMap<String, String>();
	private static void SetNumbers() {
		try {
			Scanner scanner = new Scanner(new FileReader("C:\\temp\\phone.txt"));
			int c;
			while(scanner.hasNext()) {
				String name = scanner.next();
				String phone = scanner.next();
				phoneNumbers.put(name, phone);
			}
		}catch(IOException e) {
			System.out.println("입출력 오류");
		}
		System.out.println("총" + phoneNumbers.size() + "개의 전화번호를 읽었습니다.");
	}
	
	public static void main(String[] args) {
		SetNumbers();
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("이름>> ");
			String name = scanner.nextLine();
			if(name.equals("그만")) {
				break;
			}
			String p = phoneNumbers.get(name);
			if(p == null) {
				System.out.println("찾는 이름이 없습니다.");
			}
			else {
				System.out.println(p);	
			}
		}
		scanner.close();
	}

}
