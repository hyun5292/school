package user;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import pcop.dao.MemberUpdateDAO;
import pcop.dto.MemberUpdateDTO;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

public class AccessScreen extends JFrame {
	private Font font_L = new Font("???? ????", Font.BOLD, 25);
	private Font font_M = new Font("???? ????", Font.BOLD, 20);
	private Font font_S = new Font("???? ????", Font.BOLD, 18);
	private ImageIcon Mark = new ImageIcon("image/PCOP.png");
	private ImageIcon Over = new ImageIcon("image/noselected/Over.png");
	private ImageIcon Over_s = new ImageIcon("image/selected/Over_s.png");
	private ImageIcon Food = new ImageIcon("image/noselected/Food.png");
	private ImageIcon Food_s = new ImageIcon("image/selected/Food_s.png");
	private ImageIcon MInfoReset = new ImageIcon("image/noselected/MInfoReset.png");
	private ImageIcon MInfoReset_s = new ImageIcon("image/selected/MInfoReset_s.png");

	private JPanel MainFrame; // Panel ?????ؼ? ??ü?? ????
	private JButton btn_Exit, btn_Food, btn_Info;
	private JLabel lbl_No, lbl_ID, lbl_UseMoney, lbl_UseTime, lbl_StartTime, lbl_LeftTime;
	private JTextArea textArea;

	private Calendar start, use, remain;
	private SimpleDateFormat sdf;

	int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	/** dao,dto ???????? **/
	MemberUpdateDAO dao = new MemberUpdateDAO();
	static MemberUpdateDTO dto = new MemberUpdateDTO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) { // Main ?Լ???
		EventQueue.invokeLater(new Runnable() { // ?̰? ????
			public void run() { // ???? ?޼???
				try { // ?̰? ?????ؼ?
					AccessScreen as = new AccessScreen("admin");
					Login l = new Login(as);
					l.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AccessScreen(String mID) throws InterruptedException {
		dto.setM_ID(mID);
		setFont(new Font("???? ????", Font.BOLD, 10)); // ?۾?ü
		setTitle("ȯ???մϴ?"); // Ÿ??Ʋ?? ?۾? ????
		setUndecorated(true); //?̰Ÿ? ???ָ? ???? _??x ?̰? ??????
		setBounds(x - 490, 5, 490, 250); // ?տ? ?? ???? ?????? ???? ??ġ, ?ڿ? ?? ???? ?????? ??????!
		MainFrame = new JPanel(); // ?????? ?????? ??ü ?ʱ?ȭ
		MainFrame.setBorder(new EmptyBorder(5, 5, 5, 5)); // ?????¿? ??¦?? ???? ?ִ°ž?
		MainFrame.setLayout(new BorderLayout(0, 0));
		setResizable(false);
		setContentPane(MainFrame); // MainFrame?? ???ݱ??? ???????ذɷ? ??????
		getContentPane().setBackground(new java.awt.Color(50, 50, 52)); // ?? ?ڿ? ?????? £?? ȸ???̾?
		setIconImage(Mark.getImage()); // ???? ?????̶? ?۾? ǥ???? ?????? ????

		setLayout(null);

		/** dao???? ?????? ȣ?? **/
		dto.setS_NUM(dao.SeatSelect(dto.getM_ID()));

		/** Label - ?¼???ȣ **/
		lbl_No = new JLabel("NO." + dto.getS_NUM());
		lbl_No.setFont(font_L);
		lbl_No.setBounds(5, 5, 300, 25);
		lbl_No.setForeground(new java.awt.Color(255, 166, 0));

		add(lbl_No);

		/** JLabel - ID **/
		lbl_ID = new JLabel(dto.getM_ID()); // db?? ?????? ?ؽ?Ʈ?ڽ??? ?ѷ???
		lbl_ID.setFont(font_M);
		lbl_ID.setBounds(20, 5, 285, 25);
		lbl_ID.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_ID.setForeground(new java.awt.Color(255, 166, 0));

		add(lbl_ID);

		/** dao???? ?????? ȣ?? **/
		dto.setUSED_MONEY(dao.AccessSelect(dto.getM_ID()));

		/** ???ڿ? ?޸? ǥ?? **/
		String Money = dto.getUSED_MONEY(); // db?? ?????? ???? Money?? ????
		int useMoney = 0;

		DecimalFormat df = new DecimalFormat("###,###");
		useMoney = Integer.parseInt(Money); // String -> int?? ????ȯ

		/** JLabel - ???????? **/
		lbl_UseMoney = new JLabel("????????: " + df.format(useMoney) + "\\");
		lbl_UseMoney.setFont(font_M);
		lbl_UseMoney.setBounds(15, 55, 250, 25);
		lbl_UseMoney.setForeground(Color.WHITE);

		add(lbl_UseMoney);

		/** ?????ð? ?κ? **/
		start = Calendar.getInstance();
		sdf = new SimpleDateFormat("hh:mm:ss");

		/** JLabel - ???۽ð? **/
		lbl_StartTime = new JLabel("???۽ð?: " + sdf.format(start.getTime()));
		lbl_StartTime.setFont(font_M);
		lbl_StartTime.setBounds(15, 90, 250, 25);
		lbl_StartTime.setForeground(Color.WHITE);

		add(lbl_StartTime);

		/** ???????? ?ð? ???? **/
		use = Calendar.getInstance();

		/** JLabel - ?????ð? **/
		lbl_UseTime = new JLabel("?????ð?: ");
		lbl_UseTime.setFont(font_M);
		lbl_UseTime.setBounds(15, 125, 250, 25);
		lbl_UseTime.setForeground(Color.WHITE);

		add(lbl_UseTime);

		/** JLabel - ?????ð? **/
		lbl_LeftTime = new JLabel("?????ð?: " + dto.getREMAIN_TIME());
		lbl_LeftTime.setFont(font_M);
		lbl_LeftTime.setBounds(15, 160, 250, 25);
		lbl_LeftTime.setForeground(new java.awt.Color(255, 166, 0));

		add(lbl_LeftTime);

		/** TextArea - ȸ?????? ?? ?׵θ? **/
		textArea = new JTextArea();
		textArea.setBounds(5, 35, 300, 170);
		textArea.setFont(font_L);
		textArea.setEditable(false);
		textArea.setBackground(new java.awt.Color(50, 50, 52));
		textArea.setBorder(new LineBorder(new java.awt.Color(255, 166, 0), 3));

		add(textArea);

		/* Button - ???????? */
		btn_Exit = new JButton(Over);
		btn_Exit.setSelectedIcon(Over_s);
		btn_Exit.setRolloverIcon(Over_s);
		btn_Exit.setBorderPainted(false);
		btn_Exit.setFocusPainted(false);
		btn_Exit.setContentAreaFilled(false);
		btn_Exit.setBounds(310, 5, 170, 65);
		btn_Exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Runtime runtime = Runtime.getRuntime();
				try {
					Process process = runtime.exec("C:\\WINDOWS\\system32\\cmd.exe");
					OutputStream os = process.getOutputStream();
					os.write("shutdown -s -f -t 90 \n\r".getBytes());
					os.close();
					process.waitFor();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		
		add(btn_Exit);

		/** Button - ?԰Ÿ? ?ֹ? **/
		btn_Food = new JButton(Food);
		btn_Food.setSelectedIcon(Food_s);
		btn_Food.setRolloverIcon(Food_s);
		btn_Food.setBorderPainted(false);
		btn_Food.setFocusPainted(false);
		btn_Food.setContentAreaFilled(false);
		btn_Food.setBounds(310, 73, 170, 65);
		btn_Food.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FoodOrder fo = new FoodOrder(dto.getM_ID(), dto.getS_NUM());
				fo.setVisible(true);
			}
		});

		add(btn_Food);

		/** Button - ȸ?????????? **/
		btn_Info = new JButton(MInfoReset);
		btn_Info.setSelectedIcon(MInfoReset_s);
		btn_Info.setRolloverIcon(MInfoReset_s);
		btn_Info.setBorderPainted(false);
		btn_Info.setFocusPainted(false);
		btn_Info.setContentAreaFilled(false);
		btn_Info.setBounds(310, 142, 170, 65);
		btn_Info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MemberUpdate mu = new MemberUpdate(dto.getM_ID());
				mu.setVisible(true);
			}
		});

		add(btn_Info);

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				/** ?????ð? **/
				lbl_UseTime.setText("?????ð?: " + useTime(use.getTime()));
			}
		};
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 0, 1000, TimeUnit.MILLISECONDS);

		this.addWindowListener((new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				service.shutdown();
				System.exit(0);
			}
		}));
	}

	/** ?????ð? ?????ϴ? ?޼ҵ? **/
	private String useTime(Date udate) {
		start = Calendar.getInstance(); // ?ð??? ????(???? ?ð? ??ü ??????)

		long intervalMilli = (start.getTimeInMillis() - udate.getTime()) / 1000; // ?ð??? ????
		long minute = 60; // ?ʰ??? ?????? ?????ϴ? ??
		long hour = minute * 60; // ?ʰ??? ?ð????? ?????ϴ? ??

		int useHour = (int) (intervalMilli / hour);
		int useMinute = (int) ((intervalMilli % hour) / minute);
		int useSecond = (int) ((intervalMilli % hour) % minute);

		// ????????
		String tmp = (useHour < 10) ? "0" + useHour : useHour + "";
		tmp += ":";
		tmp += (useMinute < 10) ? "0" + useMinute : useMinute + "";
		tmp += ":";
		tmp += (useSecond < 10) ? "0" + useSecond : useSecond + "";
		return tmp;
	}

}
