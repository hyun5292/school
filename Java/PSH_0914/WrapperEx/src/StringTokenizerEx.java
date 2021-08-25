import java.util.StringTokenizer;
public class StringTokenizerEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer("È«±æµ¿/ÀåÈ­/È«·Ã/ÄáÁã/ÆÏÁã", "/");
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
	}

}
