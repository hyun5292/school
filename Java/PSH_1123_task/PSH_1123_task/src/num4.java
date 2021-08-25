import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class num4 extends JFrame{
	public num4() {
		setTitle("진동하는 프레임 만들기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(300, 300);
		setLocation(100, 100);
		setVisible(true);
		
		VibContainerThread th = new VibContainerThread(this);
		th.start();
	}
	
	public static void main(String[] args) {
		new num4();
	}
}

class VibContainerThread extends Thread{
	private Container contentPane;
	public VibContainerThread(Container contentPane) {
		this.contentPane = contentPane;
	}
	
	public void run() {
		int chk = 1;
		int x = contentPane.getX();
		int y = contentPane.getY();
		while(true) {
			try {
				Thread.sleep(10);
			}
			catch(Exception e) {
				return;
			}
			
			int loc_x = x + ((int)(Math.random()*10*chk));
			int loc_y = y + ((int)(Math.random()*10*chk));
			this.contentPane.setLocation(loc_x, loc_y);
			
			if(chk == -1) {
				chk = 1;
			}
			else {
				chk = -1;
			}
		}
	}
}
