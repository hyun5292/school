package pay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import pcop.dao.Seat_DAO;
import pcop.dto.Seat_DTO;

public class SeatState extends JPanel {
	int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	int w = x / 4 - 150, h = (y - 100) / 4 - 55;

	private MainFrame win;
	private Font font_L = new Font("???? ????", Font.BOLD, 40 + y / 300);
	private Font font_M = new Font("???? ????", Font.BOLD, 20 + y / 200);
	private Font font_S = new Font("???? ????", Font.BOLD, 15 + y / 250);

	private ImageIcon icon_Charge = new ImageIcon("image/noselected/Charge.png");
	private ImageIcon icon_Charge_s = new ImageIcon("image/selected/Charge_s.png");
	private ImageIcon icon_SeatState_s = new ImageIcon("image/selected/SeatState_s.png");

	private JLabel label, Seat_State, Counter, Machine, Gate, Drawer;
	private JButton btn_Charge, btn_SeatState;
	private JTextArea[] Seat;

	private Seat_DAO sDao = new Seat_DAO();
	private List<String> dtos = sDao.GetAllSeat();

	public SeatState(MainFrame win) {
		setLayout(null);
		this.win = win;
		setBackground(new java.awt.Color(50, 50, 52));

		/* Button - ???????? */
		btn_Charge = new JButton(icon_Charge);
		btn_Charge.setSelectedIcon(icon_Charge_s);
		btn_Charge.setRolloverIcon(icon_Charge_s);
		btn_Charge.setBorderPainted(false);
		btn_Charge.setFocusPainted(false);
		btn_Charge.setContentAreaFilled(false);
		btn_Charge.setBounds(x - 340, 20, 150, 40);
		btn_Charge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				win.Change("Charge");
			}
		});

		add(btn_Charge);

		/* Label - '/' */
		label = new JLabel("/");
		label.setFont(font_L);
		label.setBounds(x - 185, 15, 50, 40);
		label.setForeground(Color.WHITE);

		add(label);

		/* Button - ?¼???Ȳ */
		btn_SeatState = new JButton(icon_SeatState_s);
		btn_SeatState.setBorderPainted(false);
		btn_SeatState.setFocusPainted(false);
		btn_SeatState.setContentAreaFilled(false);
		btn_SeatState.setBounds(x - 170, 20, 150, 40);

		add(btn_SeatState);

		/* ???? ?¼? ???? ???????? ?¼? */
		Seat_State = new JLabel("2/16");
		Seat_State.setBounds(10, 5, 500, 70);
		Seat_State.setForeground(new java.awt.Color(255, 166, 0));
		Seat_State.setFont(font_L);
		add(Seat_State);

		/* JTextArea - ?ؽ?Ʈ???????? ?밡?ٰ? ???۵ȴ? */
		Seat = new JTextArea[16];

		// 1??
		Seat[0] = new JTextArea();
		Seat[0].setBounds(10, 80, w, h);
		add(Seat[0]);

		Seat[1] = new JTextArea();
		Seat[1].setBounds(10, (80 + h) + 10, w, h);
		add(Seat[1]);

		Seat[2] = new JTextArea();
		Seat[2].setBounds(10, (80 + 2 * h) + 20, w, h);
		add(Seat[2]);

		Seat[3] = new JTextArea();
		Seat[3].setBounds(10, (80 + 3 * h) + 30, w, h);
		add(Seat[3]);

		// 2??
		Seat[4] = new JTextArea();
		Seat[4].setBounds(x / 4, 80, w, h);
		add(Seat[4]);

		Seat[5] = new JTextArea();
		Seat[5].setBounds(x / 4, (80 + h) + 10, w, h);
		add(Seat[5]);

		Seat[6] = new JTextArea();
		Seat[6].setBounds(x / 4, (80 + 2 * h) + 20, w, h);
		add(Seat[6]);

		Seat[7] = new JTextArea();
		Seat[7].setBounds(x / 4, (80 + 3 * h) + 30, w, h);
		add(Seat[7]);

		// 3??
		Seat[8] = new JTextArea();
		Seat[8].setBounds(x / 2 - 30, 80, w, h);
		add(Seat[8]);

		Seat[9] = new JTextArea();
		Seat[9].setBounds(x / 2 - 30, (80 + h) + 10, w, h);
		add(Seat[9]);

		Seat[10] = new JTextArea();
		Seat[10].setBounds(x / 2 - 30, (80 + 2 * h) + 20, w, h);
		add(Seat[10]);

		Seat[11] = new JTextArea();
		Seat[11].setBounds(x / 2 - 30, (80 + 3 * h) + 30, w, h);
		add(Seat[11]);

		// 4??
		Seat[12] = new JTextArea();
		Seat[12].setBounds((x - 110) - w - 10, 80, w, h);
		add(Seat[12]);

		Seat[13] = new JTextArea();
		Seat[13].setBounds((x - 110) - w - 10, (80 + h) + 10, w, h);
		add(Seat[13]);

		Seat[14] = new JTextArea();
		Seat[14].setBounds((x - 110) - w - 10, (80 + 2 * h) + 20, w, h);
		add(Seat[14]);

		Seat[15] = new JTextArea();
		Seat[15].setBounds((x - 110) - w - 10, (80 + 3 * h) + 30, w, h);
		add(Seat[15]);

		for (int i = 0; i < Seat.length; i++) {
			Seat[i].setBackground(Color.lightGray);
			Seat[i].setFont(font_S);
			Seat[i].setForeground(Color.BLACK);
			Seat[i].setEditable(false);
		}
		Refresh();

		/* JLabel - ī???? */
		Counter = new JLabel("ī????");
		Counter.setBackground(new java.awt.Color(0, 37, 77));
		Counter.setBorder(new LineBorder(Color.DARK_GRAY, 5));
		Counter.setFont(font_S);
		Counter.setOpaque(true);
		Counter.setForeground(Color.WHITE);
		Counter.setHorizontalAlignment(getWidth() / 2);
		Counter.setBounds(x - 110, 80, 100, (80 + 3 * h) + 55);
		add(Counter);

		/* JLabel - ???? */
		Drawer = new JLabel("????");
		Drawer.setOpaque(true);
		Drawer.setBackground(new java.awt.Color(0, 37, 77));
		Drawer.setBorder(new LineBorder(Color.DARK_GRAY, 5));
		Drawer.setFont(font_S);
		Drawer.setForeground(Color.WHITE);
		Drawer.setHorizontalAlignment(getWidth() / 2);
		Drawer.setBounds(10, y - 90, x / 2, 50);
		add(Drawer);

		/* JLabel - ???????????? */
		Machine = new JLabel("????????????");
		Machine.setOpaque(true);
		Machine.setBackground(new java.awt.Color(0, 37, 77));
		Machine.setBorder(new LineBorder(Color.DARK_GRAY, 5));
		Machine.setFont(font_S);
		Machine.setForeground(Color.WHITE);
		Machine.setHorizontalAlignment(getWidth() / 2);
		Machine.setBounds(x / 2 + 15, y - 90, x / 7, 50);
		add(Machine);

		/* JLabel - ???Ա? */
		Gate = new JLabel("???Ա?");
		Gate.setOpaque(true);
		Gate.setBackground(new java.awt.Color(0, 37, 77));
		Gate.setBorder(new LineBorder(Color.DARK_GRAY, 5));
		Gate.setFont(font_S);
		Gate.setForeground(Color.WHITE);
		Gate.setHorizontalAlignment(getWidth() / 2);
		Gate.setBounds(x / 2 + x / 6 - 10, y - 90, x / 3, 50);
		add(Gate);

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				dtos = sDao.GetAllSeat();
				for (int i = 0; i < Seat.length; i++) {
					Seat[i].setText("No" + (i + 1) + dtos.get(i).toString());
				}
			}
		};
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 0, 30000, TimeUnit.MILLISECONDS);
	}

	public void Refresh() {
		dtos = sDao.GetAllSeat();
		for (int i = 0; i < Seat.length; i++) {
			Seat[i].setText("No" + (i + 1) + dtos.get(i).toString());
		}
	}
}
