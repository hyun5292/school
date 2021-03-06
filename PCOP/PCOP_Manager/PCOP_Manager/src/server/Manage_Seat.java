package server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import pcop.dao.Food_Order_DAO;
import pcop.dao.Food_Stock_DAO;
import pcop.dao.Member_DAO;
import pcop.dao.Seat_DAO;
import pcop.dto.Food_Order_DTO;
import pcop.dto.Food_Stock_DTO;
import pcop.dto.Member_DTO;
import pcop.dto.Seat_DTO;

public class Manage_Seat {
	Font font_L = new Font("???? ????", Font.BOLD, 25);
	Font font_MB = new Font("???? ????", Font.BOLD, 15);
	Font font_S = new Font("???? ????", Font.BOLD, 20);
	JTextField txtMoney, txtStart, txtLast, txtID, txtSeat;
	JLabel label, Seat_State;
	JButton btnRepair, btnReceipt, btnComplete, btnClear;
	JTextArea txtSpace;
	JTextArea[] Seat;
	JTable FoodList;
	private JScrollPane scroll;
	private Vector<String> col = new Vector<String>(); // ???̺? ?÷?
	private Vector row = new Vector(); // ???̺? ????
	private DefaultTableModel model;
	int w = 115, h = 75;

	ImageIcon Fixing = new ImageIcon("image/noselected/Fixing.png");
	ImageIcon Fixing_s = new ImageIcon("image/selected/Fixing_s.png");
	ImageIcon Cleaning = new ImageIcon("image/noselected/Cleaning.png");
	ImageIcon Cleaning_s = new ImageIcon("image/selected/Cleaning_s.png");
	ImageIcon Receive = new ImageIcon("image/noselected/Receive.png");
	ImageIcon Receive_s = new ImageIcon("image/selected/Receive_s.png");
	ImageIcon PayComplete = new ImageIcon("image/noselected/PayComplete.png");
	ImageIcon PayComplete_s = new ImageIcon("image/selected/PayComplete_s.png");

	private Seat_DAO sDao = new Seat_DAO();
	private Seat_DTO sDto = new Seat_DTO();
	private Member_DAO mDao = new Member_DAO();
	private Member_DTO mDto = new Member_DTO();
	private Food_Order_DAO foDao = new Food_Order_DAO();
	private Food_Order_DTO foDto = new Food_Order_DTO();
	private Food_Stock_DAO fsDao = new Food_Stock_DAO();
	private Food_Stock_DTO fsDto = new Food_Stock_DTO();

	private List<String> SeatState = sDao.GetAllSeat();

	public Manage_Seat(JPanel panel) {
		panel.setLayout(null); // ???? ?ٽ? §??
		panel.setBackground(new java.awt.Color(50, 50, 52)); // ?¼????? ?? ??????

		/* JTextArea - ?ؽ?Ʈ???????? ?밡?ٰ? ???۵ȴ? */
		Seat = new JTextArea[16];

		// 1??
		Seat[0] = new JTextArea();
		Seat[0].setBounds(5, 5, w, h);
		Seat[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MakeInfo(1);
			}
		});

		Seat[1] = new JTextArea();
		Seat[1].setBounds(5, 85, w, h);
		Seat[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MakeInfo(2);
			}
		});

		Seat[2] = new JTextArea();
		Seat[2].setBounds(5, 165, w, h);
		Seat[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MakeInfo(3);
			}
		});

		Seat[3] = new JTextArea();
		Seat[3].setBounds(5, 245, w, h);
		Seat[3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MakeInfo(4);
			}
		});

		// 2??
		Seat[4] = new JTextArea();
		Seat[4].setBounds(205, 5, w, h);
		Seat[4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MakeInfo(5);
			}
		});

		Seat[5] = new JTextArea();
		Seat[5].setBounds(205, 85, w, h);
		Seat[5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MakeInfo(6);
			}
		});

		Seat[6] = new JTextArea();
		Seat[6].setBounds(205, 165, w, h);
		Seat[6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MakeInfo(7);
			}
		});

		Seat[7] = new JTextArea();
		Seat[7].setBounds(205, 245, w, h);
		Seat[7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MakeInfo(8);
			}
		});

		// 3??
		Seat[8] = new JTextArea();
		Seat[8].setBounds(325, 5, w, h);
		Seat[8].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MakeInfo(9);
			}
		});

		Seat[9] = new JTextArea();
		Seat[9].setBounds(325, 85, w, h);
		Seat[9].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MakeInfo(10);
			}
		});

		Seat[10] = new JTextArea();
		Seat[10].setBounds(325, 165, w, h);
		Seat[10].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MakeInfo(11);
			}
		});

		Seat[11] = new JTextArea();
		Seat[11].setBounds(325, 245, w, h);
		Seat[11].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MakeInfo(12);
			}
		});

		// 4??
		Seat[12] = new JTextArea();
		Seat[12].setBounds(525, 5, w, h);
		Seat[12].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MakeInfo(13);
			}
		});

		Seat[13] = new JTextArea();
		Seat[13].setBounds(525, 85, w, h);
		Seat[13].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MakeInfo(14);
			}
		});

		Seat[14] = new JTextArea();
		Seat[14].setBounds(525, 165, w, h);
		Seat[14].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MakeInfo(15);
			}
		});

		Seat[15] = new JTextArea();
		Seat[15].setBounds(525, 245, w, h);
		Seat[15].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MakeInfo(16);
			}
		});

		for (int i = 0; i < Seat.length; i++) {
			Seat[i].setBackground(Color.lightGray);
			Seat[i].setFont(font_MB);
			Seat[i].setText("NO" + (i + 1) + SeatState.get(i).toString());
			Seat[i].setForeground(Color.BLACK);
			Seat[i].setEditable(false);
			panel.add(Seat[i]);
		}

		/* ???? ?¼? ???? ???????? ?¼? */
		Seat_State = new JLabel(sDao.getUsingSeat() + "/" + Seat.length);
		Seat_State.setBounds(655, 3, 100, 40);
		Seat_State.setForeground(new java.awt.Color(255, 166, 0));
		Seat_State.setFont(new Font("???? ????", Font.BOLD, 30));
		panel.add(Seat_State);

		/* ???õ? ?¼? ȸ?? ???? ?????ִ? TextArea */
		txtSpace = new JTextArea();
		txtSpace.setBounds(655, 50, 300, 270);
		txtSpace.setFont(font_L);
		txtSpace.setEditable(false);
		txtSpace.setBackground(new java.awt.Color(227, 225, 230));
		txtSpace.setBorder(new LineBorder(Color.DARK_GRAY, 5));
		txtSpace.setLineWrap(true);
		panel.add(txtSpace);

		/* ?ش? ?¼? ?ֹ? ???? ???? */
		label = new JLabel("?ش? ?¼? ?ֹ? ???? ????");
		label.setFont(font_L);
		label.setBounds(5, 325, 300, 25);
		label.setForeground(Color.WHITE);
		panel.add(label);

		/* Table - ȸ?? ???? ?????ִ? */
		setCell();
		model = new DefaultTableModel(row, col) {
			// ?? ???? ???ϰ? ?ϴ? ?κ?
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		FoodList = new JTable(model);
		// FoodList.setBounds(5, 360, 635, 140);
		FoodList.setFont(font_MB);
		FoodList.setRowHeight(50);
		FoodList.setBackground(new java.awt.Color(227, 225, 230));
		FoodList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				foDto = new Food_Order_DTO();
				String str;
				int row = FoodList.getSelectedRow();
				TableModel md = FoodList.getModel();
				String memo = foDao.getMemo(md, row);
				if (memo == null)
					str = "NO" + sDto.getS_NUM() + SeatState.get(sDto.getS_NUM() - 1) + "\r\n??û????: ";
				else
					str = "NO" + sDto.getS_NUM() + SeatState.get(sDto.getS_NUM() - 1) + "\r\n??û????: " + memo;
				txtSpace.setText(str);

				foDto.setS_NUM(sDto.getS_NUM());
				foDto.setFS_PRODUCT(md.getValueAt(row, 1).toString());
				foDto.setFO_PRICE(Integer.parseInt(md.getValueAt(row, 2).toString()));
				foDto.setFO_COUNT(Integer.parseInt(md.getValueAt(row, 3).toString()));
				foDto.setFO_WAY(md.getValueAt(row, 5).toString());
			}
		});

		scroll = new JScrollPane(FoodList);
		scroll.setViewportView(FoodList);
		scroll.setBounds(5, 360, 635, 140);

		panel.add(scroll); // ??ũ?? ?߰?

		/* ?¼? ???? ó?? */
		label = new JLabel("?¼? ???? ó??");
		label.setFont(font_L);
		label.setBounds(655, 325, 180, 25);
		label.setForeground(Color.WHITE);
		panel.add(label);

		/* ?????? ??ư */
		btnRepair = new JButton(Fixing);
		btnRepair.setSelectedIcon(Fixing_s);
		btnRepair.setRolloverIcon(Fixing_s);
		btnRepair.setBorderPainted(false);
		btnRepair.setFocusPainted(false);
		btnRepair.setContentAreaFilled(false);
		btnRepair.setBounds(655, 355, 140, 60);
		btnRepair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sDto.getS_NUM() == 0) {
					JOptionPane.showMessageDialog(null, "?¼??? ???? ?????ϼ???", "????", JOptionPane.INFORMATION_MESSAGE);
				} else if (sDao.CheckUsing(sDto.getS_NUM()).equals("Y")) {
					JOptionPane.showMessageDialog(null, "???? ???????Դϴ?", "????", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String result = sDao.CheckCleen(sDto.getS_NUM());
					if (result.equals("Y")) {
						JOptionPane.showMessageDialog(null, "???? û?????Դϴ?", "????", JOptionPane.INFORMATION_MESSAGE);
					} else if (result.equals("N")) {
						result = sDao.CheckFixing(sDto.getS_NUM());
						if (result.equals("Y")) {
							sDao.MakeFixing(sDto.getS_NUM(), "N");
							Refresh();
						} else if (result.equals("N")) {
							sDao.MakeFixing(sDto.getS_NUM(), "Y");
							Refresh();
						}
					}
				}
			}
		});
		panel.add(btnRepair);

		/* û???? ?Ϸ? */
		btnClear = new JButton(Cleaning);
		btnClear.setSelectedIcon(Cleaning_s);
		btnClear.setRolloverIcon(Cleaning_s);
		btnClear.setBorderPainted(false);
		btnClear.setFocusPainted(false);
		btnClear.setContentAreaFilled(false);
		btnClear.setBounds(815, 355, 140, 60);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sDto.getS_NUM() == 0) {
					JOptionPane.showMessageDialog(null, "?¼??? ???? ?????ϼ???", "????", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String result = sDao.CheckFixing(sDto.getS_NUM());
					if (result.equals("Y")) {
						JOptionPane.showMessageDialog(null, "???? ???????Դϴ?", "????", JOptionPane.INFORMATION_MESSAGE);
					} else if (result.equals("N")) {
						result = sDao.CheckCleen(sDto.getS_NUM());
						if (result.equals("Y")) {
							sDao.MakeCleaning(sDto.getS_NUM(), "N");
							Refresh();
						} else if (result.equals("N")) {
							sDao.MakeCleaning(sDto.getS_NUM(), "Y");
							Refresh();
						}
					}
				}
			}
		});
		panel.add(btnClear);

		/* ?¼? ???? ó?? */
		label = new JLabel("?¼? ???? ó??");
		label.setFont(font_L);
		label.setBounds(655, 415, 180, 25);
		label.setForeground(Color.WHITE);
		panel.add(label);

		/* ???? ???? ??ư */
		btnReceipt = new JButton(Receive);
		btnReceipt.setSelectedIcon(Receive_s);
		btnReceipt.setRolloverIcon(Receive_s);
		btnReceipt.setBorderPainted(false);
		btnReceipt.setFocusPainted(false);
		btnReceipt.setContentAreaFilled(false);
		btnReceipt.setBounds(655, 445, 140, 60);
		btnReceipt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (foDto.getS_NUM() == 0) {
					JOptionPane.showMessageDialog(null, "?ֹ??׸??? ???? ?????ϼ???", "????", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String result = foDao.CheckState(foDto);
					if (result.equals("????????")) {
						JOptionPane.showMessageDialog(null, "?̹? ?????? ???? ?Ǿ????ϴ?", "????",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (result.equals("?????Ϸ?")) {
						JOptionPane.showMessageDialog(null, "?̹? ?????? ?Ϸ? ?Ǿ????ϴ?", "????",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (result.equals("????????")) {
						JOptionPane.showMessageDialog(null, "?????? ???ҵǾ? ???? ???Ҹ? ?Ϸ? ?? ?? ?????ϴ?", "????",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (result.equals("?????Ϸ?")) {
						boolean rs_cancel = foDao.MakeCancel(foDto, "????????");
						if (rs_cancel) {
							JOptionPane.showMessageDialog(null, "?????? ???ҵǾ????ϴ?", "????????", JOptionPane.INFORMATION_MESSAGE);
							Refresh();
						} else {
							JOptionPane.showMessageDialog(null, "???? ???ҿ? ???? ?Ͽ????ϴ?", "????????", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
		panel.add(btnReceipt);

		/* ???? ?Ϸ? */
		btnComplete = new JButton(PayComplete);
		btnComplete.setSelectedIcon(PayComplete_s);
		btnComplete.setRolloverIcon(PayComplete_s);
		btnComplete.setBorderPainted(false);
		btnComplete.setFocusPainted(false);
		btnComplete.setContentAreaFilled(false);
		btnComplete.setBounds(815, 445, 140, 60);
		btnComplete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (foDto.getS_NUM() == 0) {
					JOptionPane.showMessageDialog(null, "?ֹ??׸??? ???? ?????ϼ???", "????", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String result = foDao.CheckState(foDto);
					if (result.equals("????????")) {
						JOptionPane.showMessageDialog(null, "?????? ?ּҵǾ? ?????? ?Ϸ? ?? ?? ?????ϴ?", "????",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (result.equals("?????Ϸ?")) {
						boolean rs_pay = foDao.MakePayed(foDto, "?????Ϸ?");
						if (rs_pay) {
							fsDao.MinusCount(foDto);
							JOptionPane.showMessageDialog(null, "?????Ϸ??Ǿ????ϴ?", "?????Ϸ?", JOptionPane.INFORMATION_MESSAGE);
							Refresh();
						} else {
							JOptionPane.showMessageDialog(null, "?????? ???? ?Ͽ????ϴ?", "????????", JOptionPane.INFORMATION_MESSAGE);
						}
					} else if (result.equals("?????Ϸ?")) {
						boolean rs_cancel = foDao.MakePayed(foDto, "????????");
						if (rs_cancel) {
							fsDao.PlusCount(foDto);
							JOptionPane.showMessageDialog(null, "?????? ???ҵǾ????ϴ?", "????????", JOptionPane.INFORMATION_MESSAGE);
							Refresh();
						} else {
							JOptionPane.showMessageDialog(null, "?????? ???? ?Ͽ????ϴ?", "????????", JOptionPane.INFORMATION_MESSAGE);
						}
					} else if (result.equals("????????")) {
						JOptionPane.showMessageDialog(null, "?????? ???ҵǾ? ?Ϸ? ?? ?? ?????ϴ?", "????",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		panel.add(btnComplete);
	}

	// ???̺? ??
	public void setCell() {
		col.addElement("??ȣ");
		col.addElement("??ǰ??");
		col.addElement("????");
		col.addElement("????");
		col.addElement("?Ѱ???");
		col.addElement("????????");
		col.addElement("ó??????");
	}

	// ?¼? ???? ?? ?̺?Ʈ
	public void MakeInfo(int num) {
		txtSpace.setText("NO" + num + SeatState.get(num - 1));

		sDto.setS_NUM(num);

		row = foDao.getMemberOrder(num);
		model = new DefaultTableModel(row, col) {
			// ?? ???? ???ϰ? ?ϴ? ?κ?
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		FoodList.setModel(model);
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				SeatState = sDao.GetAllSeat();
				for (int i = 0; i < Seat.length; i++) {
					Seat[i].setText("NO" + (i + 1) + SeatState.get(i).toString());
				}
			}
		};
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 0, 1000, TimeUnit.MILLISECONDS);
	}

	// ????
	public void Refresh() {
		sDao = new Seat_DAO();
		sDto = new Seat_DTO();
		mDao = new Member_DAO();
		mDto = new Member_DTO();
		foDao = new Food_Order_DAO();
		foDto = new Food_Order_DTO();

		row = new Vector();
		model = new DefaultTableModel(row, col) {
			// ?? ???? ???ϰ? ?ϴ? ?κ?
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		FoodList.setModel(model);

		SeatState = sDao.GetAllSeat();
		for (int i = 0; i < Seat.length; i++) {
			Seat[i].setText("NO" + (i + 1) + SeatState.get(i).toString());
		}
		txtSpace.setText("");
	}
}
