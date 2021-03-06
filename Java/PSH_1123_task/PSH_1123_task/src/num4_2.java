import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class num4_2 extends JFrame{
	public num4_2() {
		setTitle("진동하는 프레임 만들기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(300, 300);
		setVisible(true);
		
		VibLabelThread th = new VibLabelThread(this);
		th.start();
	}

	public static void main(String[] args) {
		new num4_2();
	}
}

class VibLabelThread extends Thread{
	private Container contentPane;
	public VibLabelThread(Container contentPane) {
		this.contentPane = contentPane;
	}
	public void run() {
		int chk = 1;
		int x = 100, y = 100;
		JLabel label = new JLabel("진동 레이블");
		while(true) {
			try {
				Thread.sleep(10);
				contentPane.remove(label);
			}
			catch(Exception e) {
				return;
			}
			
			int loc_x = x + ((int)(Math.random()*5*chk));
			int loc_y = y + ((int)(Math.random()*5*chk));
			
			label.setSize(80, 30);
			label.setLocation(loc_x, loc_y);
			contentPane.add(label);
			contentPane.repaint();
			
			if(chk == -1) {
				chk = 1;
			}
			else {
				chk = -1;
			}
		}
	}
}
