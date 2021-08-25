package pay;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame{
	public Charge panel_Charge = null;
	public SeatState panel_SeatState = null;
	
	private static ImageIcon Mark = new ImageIcon("image/PCOP.png");
	
	public static void main(String[] args) {
		MainFrame win = new MainFrame();
		
		win.setTitle("�������/�¼���Ȳ");
		win.panel_Charge = new Charge(win);
		win.panel_SeatState = new SeatState(win);
		win.add(win.panel_Charge);
		
		//win.setUndecorated(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �̰� �س��� x������ �ƿ� ���α׷��� ���� ��
		win.setExtendedState(JFrame.MAXIMIZED_BOTH);  //â ũ�⸦ ��üȭ������
		win.setIconImage(Mark.getImage());
		win.setVisible(true);
	}
	
	public void Change(String panel) {
		if(panel.equals("Charge")) {
			getContentPane().removeAll();
			getContentPane().add(panel_Charge);
			revalidate();
			repaint();
		} else if(panel.equals("SeatState")) {
			getContentPane().removeAll();
			getContentPane().add(panel_SeatState);
			revalidate();
			repaint();
		}
	}
}