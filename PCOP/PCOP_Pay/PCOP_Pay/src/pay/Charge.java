package pay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Charge extends JPanel {
	int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	int Blue_Line_X = x/2+50, Blue_Line_Y = y-55;
	int Pay_Label_X = Blue_Line_X/2-40, Pay_Label_Y = Blue_Line_Y/4-40;
	int Info_W = x-(x/2+100), Info_H = y/2;
	int Info_X = x-(x-(x/2+100-17)), Info_Y = 80;
	int btn_W = Info_W/2, btn_H = (Blue_Line_Y-(Info_H+80))/2;
	
	private MainFrame win;
	private Font font_L = new Font("���� ���", Font.BOLD, 35+y/250);
	
	private JLabel label, lbl_Charge, lbl_SeatState;
	private JLabel[][] lbl_Pay;
	private JTextArea textArea, textInfo;
	private JTextArea[][] textPay;
	private JButton btn_Charge, btn_SeatState, btn_Money, btn_Card, btn_Recharge, btn_Receipt;
	private JTextPane output = new JTextPane();
	private SimpleAttributeSet attribs = new SimpleAttributeSet();
	
	private ImageIcon icon_Charge_s = new ImageIcon("image/selected/Charge_s.png");
	private ImageIcon icon_SeatState = new ImageIcon("image/noselected/SeatState.png");
	private ImageIcon icon_SeatState_s = new ImageIcon("image/selected/SeatState_s.png");
	
	private ImageIcon Money_Before = new ImageIcon("image/noselected/Money.png");
	private Image Money_Before_i = Money_Before.getImage();
	private Image Money_Before_ch = Money_Before_i.getScaledInstance(btn_W-10, btn_H-10, Image.SCALE_SMOOTH);
	private ImageIcon Money = new ImageIcon(Money_Before_ch);
	
	private ImageIcon Money_s_Before = new ImageIcon("image/selected/Money_s.png");
	private Image Money_s_i = Money_s_Before.getImage();
	private Image Money_s_ch = Money_s_i.getScaledInstance(btn_W-10, btn_H-10, Image.SCALE_SMOOTH);
	private ImageIcon Money_s = new ImageIcon(Money_s_ch);
	
	private ImageIcon Card_Before = new ImageIcon("image/noselected/Card.png");
	private Image Card_i = Card_Before.getImage();
	private Image Card_ch = Card_i.getScaledInstance(btn_W-10, btn_H-10, Image.SCALE_SMOOTH);
	private ImageIcon Card = new ImageIcon(Card_ch);
	
	private ImageIcon Card_s_Before = new ImageIcon("image/selected/Card_s.png");
	private Image Card_s_i = Card_s_Before.getImage();
	private Image Card_s_ch = Card_s_i.getScaledInstance(btn_W-10, btn_H-10, Image.SCALE_SMOOTH);
	private ImageIcon Card_s = new ImageIcon(Card_s_ch);
	
	private ImageIcon ReCharge_Before = new ImageIcon("image/noselected/ReCharge.png");
	private Image ReCharge_i = ReCharge_Before.getImage();
	private Image ReCharge_ch = ReCharge_i.getScaledInstance(btn_W-10, btn_H-10, Image.SCALE_SMOOTH);
	private ImageIcon ReCharge = new ImageIcon(ReCharge_ch);
	
	private ImageIcon ReCharge_s_Before = new ImageIcon("image/selected/ReCharge_s.png");
	private Image ReCharge_s_i = ReCharge_s_Before.getImage();
	private Image ReCharge_s_ch = ReCharge_s_i.getScaledInstance(btn_W-10, btn_H-10, Image.SCALE_SMOOTH);
	private ImageIcon ReCharge_s = new ImageIcon(ReCharge_s_ch);
	
	private ImageIcon Receipt_Before = new ImageIcon("image/noselected/Receipt.png");
	private Image Receipt_i = Receipt_Before.getImage();
	private Image Receipt_ch = Receipt_i.getScaledInstance(btn_W-10, btn_H-10, Image.SCALE_SMOOTH);
	private ImageIcon Receipt = new ImageIcon(Receipt_ch);
	
	private ImageIcon Receipt_s_Before = new ImageIcon("image/selected/Receipt_s.png");
	private Image Receipt_s_i = Receipt_s_Before.getImage();
	private Image Receipt_s_ch = Receipt_s_i.getScaledInstance(btn_W-10, btn_H-10, Image.SCALE_SMOOTH);
	private ImageIcon Receipt_s = new ImageIcon(Receipt_s_ch);
	
	String[][] Pay;
	
	public Charge(MainFrame win) {
		setLayout(null);
		this.win = win;
		setBackground(new java.awt.Color(50, 50, 52));
		
		/* Button - ������� */
		btn_Charge = new JButton(icon_Charge_s);
		btn_Charge.setBorderPainted(false);
		btn_Charge.setFocusPainted(false);
		btn_Charge.setContentAreaFilled(false);
		btn_Charge.setBounds(x - 340, 20, 150, 40);
		
		add(btn_Charge);
		
		/* Label - '/' */
		label = new JLabel("/");
		label.setFont(font_L);
		label.setBounds(x - 185, 15, 50, 40);
		label.setForeground(Color.WHITE);
		
		add(label);
		
		/* Button - �¼���Ȳ */
		btn_SeatState = new JButton(icon_SeatState);
		btn_SeatState.setSelectedIcon(icon_SeatState_s);
		btn_SeatState.setRolloverIcon(icon_SeatState_s);
		btn_SeatState.setBorderPainted(false);
		btn_SeatState.setFocusPainted(false);
		btn_SeatState.setContentAreaFilled(false);
		btn_SeatState.setBounds(x - 170, 20, 150, 40);
		btn_SeatState.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				win.Change("SeatState");
			}
		});
		
		add(btn_SeatState);
		
		/* Label - ��� ��� */
		Pay = new String[2][4];
		Pay[0][0] = "1,000�� 0:50";
		Pay[0][1] = "2,000�� 0:30";
		lbl_Pay = new JLabel[2][4];
		int textX = 25, textY = 30;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 4; j++) {
				lbl_Pay[i][j] = new JLabel(Pay[i][j]);
				lbl_Pay[i][j].setBounds(textX, textY, Pay_Label_X, Pay_Label_Y);
				lbl_Pay[i][j].setFont(font_L);
				lbl_Pay[i][j].setOpaque(true);
				lbl_Pay[i][j].setHorizontalAlignment(getWidth()/2);
				lbl_Pay[i][j].setBackground(new java.awt.Color(227, 225, 230));
				lbl_Pay[i][j].setBorder(new LineBorder(Color.DARK_GRAY, 5));
				
				add(lbl_Pay[i][j]);
				textY += (Pay_Label_Y + (Blue_Line_Y-(30+4*(Pay_Label_Y)))/3);
			}
			textY = 30;
			textX += (Pay_Label_X + (Blue_Line_X-2*(15+Pay_Label_X)));
		}
		
		/* TextArea - ��� �ڿ� �׵θ� */
		textArea = new JTextArea();
		textArea.setBounds(10, 15, Blue_Line_X, Blue_Line_Y);
		textArea.setFont(font_L);
		textArea.setEditable(false);
		textArea.setBackground(new java.awt.Color(50, 50, 52));
		textArea.setBorder(new LineBorder(new java.awt.Color(0, 152, 230), 5));
		
		add(textArea);
		
		/* TextArea - �����ϴ� ȸ�� ���� */
		textInfo = new JTextArea("\r\n ID : HYUN5292\r\n\r\n ���õ� �ݾ� : 1,000��\r\n\r\n ���õ� �ð� : 0:50");
		textInfo.setBounds(Info_X, Info_Y, Info_W, Info_H);
		textInfo.setFont(font_L);
		textInfo.setEditable(false);
		textInfo.setBackground(Color.LIGHT_GRAY);
		textInfo.setBorder(new LineBorder(Color.DARK_GRAY, 5));
		
		add(textInfo);
		
		/* Button - ���ݰ��� */
		btn_Money = new JButton(Money);
		btn_Money.setSelectedIcon(Money_s);
		btn_Money.setRolloverIcon(Money_s);
		btn_Money.setBorderPainted(false);
		btn_Money.setFocusPainted(false);
		btn_Money.setContentAreaFilled(false);
		btn_Money.setBounds(Info_X, Info_Y+Info_H+10, btn_W, btn_H);
		
		add(btn_Money);
		
		/* Button - ī����� */
		btn_Card = new JButton(Card);
		btn_Card.setSelectedIcon(Card_s);
		btn_Card.setRolloverIcon(Card_s);
		btn_Card.setBorderPainted(false);
		btn_Card.setFocusPainted(false);
		btn_Card.setContentAreaFilled(false);
		btn_Card.setBounds(Info_X+Info_W/2, Info_Y+Info_H+10, btn_W, btn_H);
		
		add(btn_Card);
		
		/* Button - �ܵ���ȯ */
		btn_Recharge = new JButton(ReCharge);
		btn_Recharge.setSelectedIcon(ReCharge_s);
		btn_Recharge.setRolloverIcon(ReCharge_s);
		btn_Recharge.setBorderPainted(false);
		btn_Recharge.setFocusPainted(false);
		btn_Recharge.setContentAreaFilled(false);
		btn_Recharge.setBounds(Info_X, (Info_Y+Info_H)+Pay_Label_Y, btn_W, btn_H);
		
		add(btn_Recharge);
		
		/* Button - ���ݰ��� */
		btn_Receipt = new JButton(Receipt);
		btn_Receipt.setSelectedIcon(Receipt_s);
		btn_Receipt.setRolloverIcon(Receipt_s);
		btn_Receipt.setBorderPainted(false);
		btn_Receipt.setFocusPainted(false);
		btn_Receipt.setContentAreaFilled(false);
		btn_Receipt.setBounds(Info_X+Info_W/2, (Info_Y+Info_H)+Pay_Label_Y, btn_W, btn_H);
		
		add(btn_Receipt);
	}
}
