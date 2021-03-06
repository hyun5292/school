package user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import pcop.dao.Member_DAO;
import pcop.dto.Member_DTO;

public class Login extends JFrame {
	int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	int info_H = y / 5 + 23;
	int btn_W = x / 6, btn_H = info_H/2;
	int JoMark_H = y - (info_H + 50);
	int info_X = x / 4 - 60, info_Y = JoMark_H + 10;
	int Icon_W = x / 4 - 70, Icon_H = y / 5 + 25;
	int info_W = x - (2*(btn_W+5)+Icon_W+10);
	
	// ?۾?ü
	private Font font_L = new Font("???? ????", Font.BOLD, 30 + y / 120);
	private Font font_MB = new Font("???? ????", Font.BOLD, 20 + y / 120);
	private Font font_S = new Font("???? ????", Font.BOLD, 18 + y / 120);

	private JPanel panel = new JPanel();

	private ImageIcon Mark = new ImageIcon("image/PCOP.png");
	private ImageIcon JoMark_Before = new ImageIcon("image/JoMark.png");
	private Image JoMark_i = JoMark_Before.getImage();
	private Image JoMark_ch = JoMark_i.getScaledInstance(x*2/3, JoMark_H*2/3, Image.SCALE_SMOOTH);
	private ImageIcon JoMark = new ImageIcon(JoMark_ch);

	private ImageIcon MainIcon_Before = new ImageIcon("image/MainIcon.png");
	private Image MainIcon_i = MainIcon_Before.getImage();
	private Image MainIcon_ch = MainIcon_i.getScaledInstance(Icon_W, Icon_H, Image.SCALE_SMOOTH);
	private ImageIcon MainIcon = new ImageIcon(MainIcon_ch);

	private ImageIcon Login_Before = new ImageIcon("image/noselected/Login.png");
	private Image Login_i = Login_Before.getImage();
	private Image Login_ch = Login_i.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon Login = new ImageIcon(Login_ch);

	private ImageIcon Login_s_Before = new ImageIcon("image/selected/Login_s.png");
	private Image Login_s_i = Login_s_Before.getImage();
	private Image Login_s_ch = Login_s_i.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon Login_s = new ImageIcon(Login_s_ch);

	private ImageIcon Join_Before = new ImageIcon("image/noselected/Join.png");
	private Image Join_i = Join_Before.getImage();
	private Image Join_ch = Join_i.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon Join = new ImageIcon(Join_ch);

	private ImageIcon Join_s_Before = new ImageIcon("image/selected/Join_s.png");
	private Image Join_s_i = Join_s_Before.getImage();
	private Image Join_s_ch = Join_s_i.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon Join_s = new ImageIcon(Join_s_ch);

	private ImageIcon Clean_Before = new ImageIcon("image/noselected/Clean.png");
	private Image Clean_i = Clean_Before.getImage();
	private Image Clean_ch = Clean_i.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon Clean = new ImageIcon(Clean_ch);

	private ImageIcon Clean_s_Before = new ImageIcon("image/selected/Clean_s.png");
	private Image Clean_s_i = Clean_s_Before.getImage();
	private Image Clean_s_ch = Clean_s_i.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon Clean_s = new ImageIcon(Clean_s_ch);

	private ImageIcon IdPwFind_Before = new ImageIcon("image/noselected/IdPwFind.png");
	private Image IdPwFind_i = IdPwFind_Before.getImage();
	private Image IdPwFind_ch = IdPwFind_i.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon IdPwFind = new ImageIcon(IdPwFind_ch);

	private ImageIcon IdPwFind_s_Before = new ImageIcon("image/selected/IdPwFind_s.png");
	private Image IdPwFind_s_i = IdPwFind_s_Before.getImage();
	private Image IdPwFind_s_ch = IdPwFind_s_i.getScaledInstance(btn_W, btn_H, Image.SCALE_SMOOTH);
	private ImageIcon IdPwFind_s = new ImageIcon(IdPwFind_s_ch);

	private ImageIcon OK = new ImageIcon("image/OK.png");

	private JLabel label, lbl_Image, lblIcon;
	private JTextArea tArea_Info;
	private JTextField txt_ID;
	private JPasswordField txt_PW;
	private JButton btn_Login, btn_Join, btn_Find, btn_Clean;
	
	/** dao,dto ???????? **/
	Member_DAO dao = new Member_DAO();
	Member_DTO dto = new Member_DTO();

	public static String mID = "";
	
	public Login(AccessScreen parent) {
		setTitle("?α???"); // Ÿ??Ʋ?? ?ؽ?Ʈ ????
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //x?????ϱ? ???α׷??? ?? ?????????? ?׷???
		// ???Ƴ??? ?길 ????????
		setUndecorated(true); //?̰Ÿ? ???ָ? ???? _??x ?̰? ??????
		setExtendedState(JFrame.MAXIMIZED_BOTH); // ?տ? ?? ???? ?????? ???? ??ġ, ?ڿ? ?? ???? ?????? ??????!
		panel = new JPanel(); // ?????? ?????? ??ü ?ʱ?ȭ
		panel.setBorder(new EmptyBorder(5, 5, 5, 5)); // ?????¿? ??¦?? ???? ?ִ°ž?
		panel.setLayout(new BorderLayout(0, 0)); // ?ֶ? Layout ???????ش?
		setContentPane(panel); // panel_MID?? ???ݱ??? ???????ذɷ? ??????
		setIconImage(Mark.getImage()); // ???? ?????̶? ?۾? ǥ???? ?????? ????
		panel.setBackground(new java.awt.Color(50, 50, 52)); // ?ڿ? ???? ????
		panel.setLayout(null); // ???? ?ٽ? §??
		/** ?޼????ڽ? UI **/
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", new java.awt.Color(50, 50, 52));
		UI.put("Panel.background", new java.awt.Color(50, 50, 52));
		UI.put("OptionPane.messageFont", font_L);
		UI.put("OptionPane.messageForeground", Color.WHITE);
		UI.put("OptionPane.buttonFont", font_L);

		/* Label - ?̹??? */
		lbl_Image = new JLabel(JoMark);
		lbl_Image.setBounds(0, 0, x, JoMark_H);
		lbl_Image.setVerticalAlignment(SwingConstants.CENTER);
		lbl_Image.setHorizontalAlignment(SwingConstants.CENTER);
		//lbl_Image.setBorder(new LineBorder(new java.awt.Color(255, 166, 0), 3));
		
		add(lbl_Image);

		int side_Text = (info_H-2*(65+y/350))/3;
		int side_Label = (info_H-2*(40+y/120))/3;
		
		/* Label - ???̵? */
		label = new JLabel("???̵?");
		label.setFont(font_L);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(info_X + 20, info_Y + side_Label, 150 + y / 120, 40+y / 120);
		label.setForeground(Color.DARK_GRAY);

		add(label);

		/* TextField - ID ?Է?*/
		txt_ID = new JTextField(20); // ?ؽ?Ʈ?ʵ? ??????
		txt_ID.setBounds(info_X + 20 + 150 + 20, info_Y + side_Text, info_W - (50 + 150 + y / 120), 65+y/350); // ??ġ?? ??????
		txt_ID.setFont(font_S); // ?۾?ü

		add(txt_ID); // ?ǳڿ? textfield ?߰?

		/* Label - ???й?ȣ */
		label = new JLabel("???й?ȣ");
		label.setFont(font_L);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(info_X + 20, (info_Y + side_Label) + (40+y/120) + side_Label+5, 150 + y / 120, 40);
		label.setForeground(Color.DARK_GRAY);
		
		add(label);

		/* JTextField - PW ?Է? */
		txt_PW = new JPasswordField();
		txt_PW.setBounds(info_X + 20 + 150 + 20, (info_Y + side_Text) + (65+y/350) + side_Text, info_W - (50 + 150 + y / 120), 65+y/350); // ??ġ?? ??????
		txt_PW.setFont(font_S); // ?۾?ü

		add(txt_PW); // ?ǳڿ? textfield ?߰?

		/* TextArea - ?????Է? ?ڿ? ĭ */
		tArea_Info = new JTextArea();
		tArea_Info.setBounds(5 + Icon_W + 5, info_Y, info_W, info_H);
		tArea_Info.disable();
		tArea_Info.setBackground(new java.awt.Color(227, 225, 230));
		tArea_Info.setBorder(new LineBorder(Color.DARK_GRAY, 2));

		add(tArea_Info);

		/* Label - ?????ϴ? ?????? */
		lblIcon = new JLabel(MainIcon);
		lblIcon.setBounds(5, info_Y - 3, Icon_W, Icon_H);
		lblIcon.setBorder(new LineBorder(Color.DARK_GRAY, 2));

		add(lblIcon);

		/* Button - ?α??? */
		btn_Login = new JButton(Login);
		btn_Login.setSelectedIcon(Login_s);
		btn_Login.setRolloverIcon(Login_s);
		btn_Login.setBorderPainted(false);
		btn_Login.setFocusPainted(false);
		btn_Login.setContentAreaFilled(false);
		btn_Login.setBounds(x - (2*btn_W+5), info_Y, btn_W, btn_H);

		btn_Login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean result = dao.TryLogin(txt_ID.getText().toString(), txt_PW.getText().toString());
				if(result) {
					AccessScreen parent;
					try {
						parent = new AccessScreen(txt_ID.getText().toString());
						parent.setVisible(true);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "???̵? ?Ǵ? ???й?ȣ?? Ȯ???ϼ???", "ID/PW ã??", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_Login.setBorder(new LineBorder(new java.awt.Color(255, 166, 0), 3));

		add(btn_Login);

		/* Button - ID/PW ã?? */
		btn_Find = new JButton(IdPwFind);
		btn_Find.setSelectedIcon(IdPwFind_s);
		btn_Find.setRolloverIcon(IdPwFind_s);
		btn_Find.setBorderPainted(false);
		btn_Find.setFocusPainted(false);
		btn_Find.setContentAreaFilled(false);
		btn_Find.setBounds(x - (btn_W+5), info_Y, btn_W, btn_H);
		btn_Find.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "?????ڿ??? ?????ϼ???.", "ID/PW ã??", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btn_Find.setBorder(new LineBorder(new java.awt.Color(255, 166, 0), 3));

		add(btn_Find);

		/* Button - ȸ?????? */
		btn_Join = new JButton(Join);
		btn_Join.setSelectedIcon(Join_s);
		btn_Join.setRolloverIcon(Join_s);
		btn_Join.setBorderPainted(false);
		btn_Join.setFocusPainted(false);
		btn_Join.setContentAreaFilled(false);
		btn_Join.setBounds(x - (2*btn_W+5), info_Y+(info_H/2), btn_W, btn_H);
		btn_Join.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Join join = new Join();
				join.setVisible(true);
			}
		});
		btn_Join.setBorder(new LineBorder(new java.awt.Color(255, 166, 0), 3));

		add(btn_Join);

		/* Button - û?ҿ?û */
		btn_Clean = new JButton(Clean);
		btn_Clean.setSelectedIcon(Clean_s);
		btn_Clean.setRolloverIcon(Clean_s);
		btn_Clean.setBorderPainted(false);
		btn_Clean.setFocusPainted(false);
		btn_Clean.setContentAreaFilled(false);
		btn_Clean.setBounds(x - (btn_W+5), info_Y+(info_H/2), btn_W, btn_H);
		btn_Clean.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		btn_Clean.setBorder(new LineBorder(new java.awt.Color(255, 166, 0), 3));

		add(btn_Clean);
	}
}
