import javax.swing.*;
import java.awt.*;

public class OpenChallenge extends JFrame{
	public OpenChallenge() {
		super("Open Challenge 9");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		c.add(new NorthPanel(), BorderLayout.NORTH);
		//c.add(new NorthPanel(), BorderLayout.EAST);
		//c.add(new NorthPanel(), BorderLayout.WEST);
		//c.add(new NorthPanel(), BorderLayout.SOUTH);
		c.add(new CenterPanel(), BorderLayout.CENTER);
		setSize(300, 300);
		setVisible(true);
	}
	
	class NorthPanel extends JPanel {
		public NorthPanel() {
			setBackground(Color.BLACK);
			setLayout(new FlowLayout());
			add(new JButton("Open"));
			add(new JButton("Read"));
			add(new JButton("Close"));
		}
	}
	
	class CenterPanel extends JPanel{
		public CenterPanel() {
			setLayout(null);
			setBackground(Color.WHITE);
			
			JLabel la1 = new JLabel("Hello");
			la1.setSize(100, 20); //문자크기아니고 그냥 박스크기
			la1.setLocation(10, 100);
			
			JLabel la2 = new JLabel("Java");
			la2.setSize(100, 20);
			la2.setLocation(90, 150);
			
			JLabel la3 = new JLabel("Love");
			la3.setSize(100, 20);
			la3.setLocation(200, 10);
			
			add(la1);
			add(la2);
			add(la3);
		}
	}
	
	public static void main(String[] args) {
		new OpenChallenge();
	}

}
