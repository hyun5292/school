import java.util.Calendar;

public class Num5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		int HourOfDay = c.get(Calendar.HOUR_OF_DAY);
		int Minute = c.get(Calendar.MINUTE);
		
		System.out.println("현재 시간은 " + HourOfDay + "시 " + Minute + "분입니다.");
		if(HourOfDay >= 4 && HourOfDay < 12) {
			System.out.println("Good Morning");
		}
		else if(HourOfDay >= 12 && HourOfDay < 18) {
			System.out.println("Good Afternoon");
		}
		else if(HourOfDay >= 18 && HourOfDay < 10) {
			System.out.println("Good Evening");
		}
		else {
			System.out.println("Good Night");
		}
		
	}

}
