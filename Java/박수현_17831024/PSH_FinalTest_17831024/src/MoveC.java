import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MoveC extends JFrame {
	private JLabel la = new JLabel("C");
	private Container c;
	public MoveC() {
		setTitle("클릭 연습 용 응용프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c = getContentPane();
		c.setLayout(null);
		la.setSize(50, 20);
		la.setLocation(100, 100);
		c.add(la);
		
		setSize(300, 300);
		setVisible(true);
		
		c.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int x = ((int)(Math.random()*c.getWidth()));
				int y = ((int)(Math.random()*c.getHeight()));
				la.setLocation(x, y);
				c.repaint();
			}
		});
	}
	
	public static void main(String[] args) {
		new MoveC();
	}

}
