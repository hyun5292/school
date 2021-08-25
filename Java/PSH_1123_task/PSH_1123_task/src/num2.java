import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class num2 extends JFrame{
	private MyPanel panel = new MyPanel();
	public num2() {
		setTitle("원을 0.5초 간격으로 이동시키기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		
		setSize(300, 300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new num2();
	}
}

class MyPanel extends JPanel{
	public MyPanel() {
		addMouseListener(new MouseAdapter() {
			boolean chk = true;
			public void mouseClicked(MouseEvent e) {
				if(chk)
				{
					chk = false;
					CircleThread th = new CircleThread();
					th.start();
				}
			}
		});
	}

	class CircleThread extends Thread{
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(500);
				}
				catch(Exception e) {
					return;
				}
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		int x = ((int)(Math.random()*getWidth()));
		int y = ((int)(Math.random()*getHeight()));
		g.drawOval(x, y, 50, 50);
	}

}




