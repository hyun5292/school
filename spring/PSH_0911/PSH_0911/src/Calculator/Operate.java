package Calculator;

public class Operate {
	
	public Operate(float x, float y, String op) {
		float result;
		
		if (op.equals("+")) {
			result = x + y;
			System.out.println("결과 :" + result);
		} else if (op.equals("-")) {
			result = x - y;
			System.out.println("결과 :" + result);
		} else if (op.equals("*")) {
			result = x * y;
			System.out.println("결과 :" + result);
		} else if (op.equals("/")) {
			if(y == 0.0) {
				System.out.println("0으로 나누면 내 컴퓨터가 힘들어한다!");
			}
			else {
				result = x / y;
				System.out.println("결과 :" + result);
			}
		} else if (op.equals("%")) {
			if(y == 0.0) {
				System.out.println("0으로 나누면 내 컴퓨터가 힘들어한다!");
			}
			else {
				result = x % y;
				System.out.println("결과 :" + result);
			}
		} else {
			System.out.println("계산 실패!");
		}
	}
}
