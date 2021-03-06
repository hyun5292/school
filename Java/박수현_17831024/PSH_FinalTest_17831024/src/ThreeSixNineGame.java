import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ThreeSixNineGame extends JFrame{
	private Thread th;
	private JLabel la = new JLabel();
	private Container c;
	private int n = 1;
	private int mouse_count = 0;
	
	public ThreeSixNineGame() {
		setTitle("369 게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		c.setLayout(new FlowLayout());
		
		NumberCount nc = new NumberCount();
		th = new Thread(nc);
		
		c.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mouse_count++;
			}
		});
		
		la.setFont(new Font("Gothic", Font.ITALIC, 100));
		c.add(la);
		
		setSize(300, 300);
		setVisible(true);
		th.start();
	}
	
	public void Check() {
		int n_t = (n - 1) / 10;  //십의 자리
		int n_o = (n - 1) % 10;  //일의 자리
		int cnt = 0;
		
		if(n_t != 0 && n_t % 3 == 0) {
			cnt++;
		}
		if(n_o != 0 && n_o % 3 == 0) {
			cnt++;
		}
		
		if((mouse_count != cnt) || (cnt == 0 && mouse_count != 0)) {
			if(cnt == 2) {
				la.setText("Fail^^");
				th.interrupt();
			}
			else if(cnt == 1){
				la.setText("Fail^");
				th.interrupt();
			}
			else {
				la.setText("Fail");
				th.interrupt();
			}
		}
	}
	
	public static void main(String[] args) {
		new ThreeSixNineGame();
	}
	
	class NumberCount implements Runnable{
		
		public void run() {
			while(true) {
				la.setText(Integer.toString(n));
				try {
					Check();
					mouse_count = 0;
					Thread.sleep(700);
				}
				catch(InterruptedException e) {
					return;
				}
				
				if(n <= 99) {
					n++;
				}
				else {
					la.setText("Win!!");
					th.interrupt();
				}
			}
		}
	}
}
