package server;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import pcop.dao.Food_Order_DAO;
import pcop.dao.Member_DAO;
import pcop.dao.Seat_DAO;
import pcop.dto.Member_DTO;

public class Manage_Member extends MouseAdapter {
	Font font_L = new Font("���� ���", Font.BOLD, 30);
	Font font = new Font("���� ���", Font.BOLD, 20);
	Font font_B = new Font("���� ���", Font.BOLD, 25);
	JTextField text_ID;
	JTable table_Member;
	private Vector<String> col = new Vector<String>(); // ���̺� �÷�
	private Vector row; // ���̺� ����
	private DefaultTableModel model;
	private JScrollPane scroll;
	JLabel label, lbl_ID, lbl_Name, lbl_Birth, lbl_Tel;
	JTextArea tArea_Info;
	JButton btn_Search, btn_getout, btn_Pw;
	ImageIcon MWithdraw = new ImageIcon("image/noselected/MWithdraw.png");
	ImageIcon MWithdraw_s = new ImageIcon("image/selected/MWithdraw_s.png");
	ImageIcon PwReset = new ImageIcon("image/noselected/PwReset.png");
	ImageIcon PwReset_s = new ImageIcon("image/selected/PwReset_s.png");
	ImageIcon ReadingGlass = new ImageIcon("image/noselected/ReadingGlass.png");
	ImageIcon ReadingGlass_s = new ImageIcon("image/selected/ReadingGlass_s.png");

	private Member_DAO dao = new Member_DAO();
	private Member_DTO dto = new Member_DTO();
	private Seat_DAO sDao = new Seat_DAO();

	public Manage_Member(JPanel panel) {
		panel.setLayout(null); // ���� �ٽ� §��
		panel.setBackground(new java.awt.Color(50, 50, 52));

		// �޼����ڽ� UI
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", new java.awt.Color(50, 50, 52));
		UI.put("Panel.background", new java.awt.Color(50, 50, 52));
		UI.put("OptionPane.messageFont", font_B);
		UI.put("OptionPane.messageForeground", Color.WHITE);
		UI.put("OptionPane.buttonFont", font_B);

		/* TextField - ȸ�� ID �˻��ϴ� */
		text_ID = new JTextField("�̸��� �Է��ϼ���"); // �ؽ�Ʈ�ʵ� ������
		text_ID.setBounds(10, 8, 445, 40); // ��ġ�� ������
		text_ID.setFont(font); // �۾�ü
		text_ID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				text_ID.setText("");
			}
		});

		panel.add(text_ID); // �ǳڿ� textfield �߰�

		/* Button - ȸ�� �˻� ��ư */
		btn_Search = new JButton(ReadingGlass);
		btn_Search.setSelectedIcon(ReadingGlass_s);
		btn_Search.setRolloverIcon(ReadingGlass_s);
		btn_Search.setBorderPainted(false);
		btn_Search.setFocusPainted(false);
		btn_Search.setContentAreaFilled(false);
		btn_Search.setBounds(460, 5, 45, 45);
		btn_Search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dto = new Member_DTO();
				dto = dao.getMember(text_ID.getText().toString());

				row = dao.getNameMembers(text_ID.getText().toString());
				model = new DefaultTableModel(row, col) {
					// �� ���� ���ϰ� �ϴ� �κ�
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				table_Member.setModel(model);
			}
		});

		panel.add(btn_Search);

		/* TextArea - ȸ�� ���� ����ִ� */
		tArea_Info = new JTextArea();
		tArea_Info.setForeground(Color.DARK_GRAY);
		tArea_Info.setFont(font_L);
		tArea_Info.setBounds(520, 55, 435, 300);
		tArea_Info.setEditable(false);
		tArea_Info.setBackground(new java.awt.Color(227, 225, 230));
		tArea_Info.setBorder(new LineBorder(Color.DARK_GRAY, 5));

		panel.add(tArea_Info);

		/* Table - ȸ�� ���� ����ִ� */
		row = dao.getAllMembers();
		setCell();
		model = new DefaultTableModel(row, col) {
			// �� ���� ���ϰ� �ϴ� �κ�
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_Member = new JTable(model);
		// table_Member.setBounds(5, 55, 500, 450); // ��ġ�� ������
		table_Member.setFont(font); // �۾�ü
		table_Member.setRowHeight(50);
		table_Member.setBackground(new java.awt.Color(227, 225, 230));
		table_Member.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tArea_Info.setText("");
				dto = new Member_DTO();
				String str = "";
				int row = table_Member.getSelectedRow();
				TableModel md = table_Member.getModel();
				dto.setM_ID(md.getValueAt(row, 1).toString());

				dto = dao.getMember(dto.getM_ID());
				str += "ID: " + dto.getM_ID();
				str += "\r\n\r\n����: " + dto.getM_NAME();
				str += "\r\n\r\n��ȭ��ȣ: " + dto.getM_TEL1() + "-" + dto.getM_TEL2() + "-" + dto.getM_TEL3();
				str += "\r\n\r\n�������: " + dto.getM_BIRTH();

				tArea_Info.setText(str);
			}
		});

		scroll = new JScrollPane(table_Member);
		scroll.setViewportView(table_Member);
		scroll.setBounds(5, 55, 500, 450);

		panel.add(scroll); // ��ũ�� �߰�

		/* Label - ȸ������ �۾� */
		label = new JLabel("ȸ������");
		label.setFont(font_L);
		label.setForeground(Color.WHITE);
		label.setBounds(520, 5, 500, 40);

		panel.add(label);

		/* Button - ȸ�� Ż�� ��ư */
		btn_getout = new JButton(MWithdraw);
		btn_getout.setSelectedIcon(MWithdraw_s);
		btn_getout.setRolloverIcon(MWithdraw_s);
		btn_getout.setBorderPainted(false);
		btn_getout.setFocusPainted(false);
		btn_getout.setContentAreaFilled(false);
		btn_getout.setFont(font);
		btn_getout.setBounds(520, 360, 200, 140);
		btn_getout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dto.getM_ID() == null) {
					JOptionPane.showMessageDialog(null, "ȸ���� ���� �������ּ���", "����", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String Answer = JOptionPane.showInputDialog(null, "ȸ�� Ż�� ���Ͻø� \'ȸ��Ż��\'�� �Է��ϼ���", "ȸ��Ż��",
							JOptionPane.INFORMATION_MESSAGE);
					if (Answer == null) {
						JOptionPane.showMessageDialog(null, "ȸ��Ż�� �ߴ��Ͽ����ϴ�", "ȸ��Ż�� ����",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (Answer.equals("ȸ��Ż��")) {
						boolean result_m = dao.getOutMemger(dto.getM_ID());
						sDao.getOut(dto.getM_ID());
						if (result_m) {
							JOptionPane.showMessageDialog(null, "ȸ���� Ż��Ǿ����ϴ�", "ȸ��Ż�� ����",
									JOptionPane.INFORMATION_MESSAGE);
							Refresh();
						} else {
							JOptionPane.showMessageDialog(null, "Ż�� �����Ͽ����ϴ�", "ȸ��Ż�� ����",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ż�� �����Ͽ����ϴ�", "ȸ��Ż�� ����",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

		panel.add(btn_getout);

		/* Button - ��й�ȣ �ʱ�ȭ ��ư */
		btn_Pw = new JButton(PwReset);
		btn_Pw.setSelectedIcon(PwReset_s);
		btn_Pw.setRolloverIcon(PwReset_s);
		btn_Pw.setBorderPainted(false);
		btn_Pw.setFocusPainted(false);
		btn_Pw.setContentAreaFilled(false);
		btn_Pw.setFont(font);
		btn_Pw.setBounds(750, 360, 200, 140);
		btn_Pw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dto.getM_ID() == null) {
					JOptionPane.showMessageDialog(null, "ȸ���� ���� �������ּ���", "����", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String Answer = JOptionPane.showInputDialog(null, "��й�ȣ �ʱ�ȭ�� ���Ͻø� \'�ʱ�ȭ\'�� �Է��ϼ���", "��й�ȣ �ʱ�ȭ",
							JOptionPane.INFORMATION_MESSAGE);
					if (Answer == null) {
						JOptionPane.showMessageDialog(null, "��й�ȣ �ʱ�ȭ�� �ߴ��Ͽ����ϴ�", "��й�ȣ �ʱ�ȭ ����",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (Answer.equals("�ʱ�ȭ")) {
						boolean result = dao.PWReset(dto.getM_ID());
						sDao.getOut(dto.getM_ID());
						if (result) {
							JOptionPane.showMessageDialog(null, "��й�ȣ�� \'a123456789\'�� �ʱ�ȭ �Ǿ����ϴ�", "��й�ȣ �ʱ�ȭ ����",
									JOptionPane.INFORMATION_MESSAGE);
							Refresh();
						} else {
							JOptionPane.showMessageDialog(null, "�ʱ�ȭ�� �����Ͽ����ϴ�", "��й�ȣ �ʱ�ȭ ����",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "�ʱ�ȭ�� �����Ͽ����ϴ�", "��й�ȣ �ʱ�ȭ ����",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

		panel.add(btn_Pw);
	}

	// ���̺� ��
	public void setCell() {
		col.addElement("��ȣ");
		col.addElement("ID");
		col.addElement("����");
		col.addElement("����");
		col.addElement("��ȭ��ȣ");
	}

	// ����
	public void Refresh() {
		/* Table - ȸ�� ���� ����ִ� */
		row = dao.getAllMembers();
		model = new DefaultTableModel(row, col) {
			// �� ���� ���ϰ� �ϴ� �κ�
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_Member.setModel(model);

		tArea_Info.setText("");
		text_ID.setText("�̸��� �Է��ϼ���");
	}
}
