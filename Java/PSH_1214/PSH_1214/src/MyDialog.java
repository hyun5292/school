import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class MyDialogEx extends JDialog{
	private JTextField tf = new JTextField(10);
	private JButton okButton = new JButton("OK");
	
	public MyDialogEx(JFrame frame, String title) {
		super(frame, title);
		setLayout(new FlowLayout());
		add(tf);
		add(okButton);
		setSize(200, 100);
		
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
}
public class MyDialog extends JFrame{
	private MyDialogEx dialog;
	public MyDialog() {
		super("DialogEx ���� ������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton btn = new JButton("Show Dialog");
		
		dialog = new MyDialogEx(this, "Test Dialog");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
			}
		});
		getContentPane().add(btn);
		setSize(250, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new MyDialog();
	}

}