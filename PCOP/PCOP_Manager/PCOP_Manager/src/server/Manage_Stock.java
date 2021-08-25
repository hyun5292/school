package server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import pcop.dao.Food_Stock_DAO;
import pcop.dto.Food_Stock_DTO;
import pcop.dto.Member_DTO;

public class Manage_Stock {
	private Font font_L = new Font("���� ���", Font.BOLD, 25);
	private Font font_MB = new Font("���� ���", Font.BOLD, 18);
	private Font font_S = new Font("���� ���", Font.BOLD, 15);
	private JTextField text_Product, text_Insert, text_Price1, text_Price2;
	private JTable table_Food;
	private Vector<String> col = new Vector<String>(); // ���̺� �÷�
	private Vector row = new Vector(); // ���̺� ����
	private DefaultTableModel model;
	private JScrollPane scroll;
	private JLabel label, lbl_Product, lbl_Price, lbl_Count, lbl_Stock;
	private JTextArea tArea_Insert, tArea_Info;
	private String[] Kind;
	private JComboBox<String> com_Kind;
	private JButton btn_Search, btn_Reset, btn_Insert, btn_Delete, btn_Stock;
	private Manage_Stock owner = this;
	private ImageIcon FSearch = new ImageIcon("image/noselected/FSearch.png");
	private ImageIcon FSearch_s = new ImageIcon("image/selected/FSearch_s.png");
	private ImageIcon FReset = new ImageIcon("image/noselected/FReset.png");
	private ImageIcon FReset_s = new ImageIcon("image/selected/FReset_s.png");
	private ImageIcon FInsert = new ImageIcon("image/noselected/FInsert.png");
	private ImageIcon FInsert_s = new ImageIcon("image/selected/FInsert_s.png");
	private ImageIcon FDelete = new ImageIcon("image/noselected/FDelete.png");
	private ImageIcon FDelete_s = new ImageIcon("image/selected/FDelete_s.png");
	private ImageIcon StockReset = new ImageIcon("image/noselected/StockReset.png");
	private ImageIcon StockReset_s = new ImageIcon("image/selected/StockReset_s.png");

	private Food_Stock_DAO fsDao = new Food_Stock_DAO();
	private Food_Stock_DTO fsDto = new Food_Stock_DTO();

	public Manage_Stock(JPanel panel) {
		panel.setLayout(null); // ���� �ٽ� §��
		panel.setBackground(new java.awt.Color(50, 50, 52));

		/* Table - ȸ�� ���� ����ִ� */
		row = fsDao.getAllStock();
		setCell();
		model = new DefaultTableModel(row, col) {
			// �� ���� ���ϰ� �ϴ� �κ�
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_Food = new JTable(model); // ���̺� ������
		// table_Food.setBounds(5, 5, 500, 500); //��ġ�� ������
		table_Food.setFont(font_S); // �۾�ü
		table_Food.setRowHeight(50);
		table_Food.setBackground(new java.awt.Color(227, 225, 230));
		table_Food.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tArea_Info.setText("");
				String str = "";
				int row = table_Food.getSelectedRow();
				TableModel md = table_Food.getModel();
				fsDto = fsDao.getStock(md.getValueAt(row, 1).toString());

				str = "��ǰ��: " + fsDto.getFS_PRODUCT();
				str += "\r\n����: " + fsDto.getFS_KIND();
				str += "\r\n����: " + fsDto.getFS_PRICE();
				str += "\r\n�ǸŰ���: " + fsDto.getFS_SELL_COUNT();
				str += "\r\n�Ǹűݾ�: " + fsDto.getFS_SELL_PRICE();
				str += "\r\n�������: " + fsDto.getFS_NUM();

				tArea_Info.setText(str);
			}
		});

		scroll = new JScrollPane(table_Food);
		scroll.setViewportView(table_Food);
		scroll.setBounds(5, 5, 500, 500);

		panel.add(scroll); // ��ũ�� �߰�

		/* Label - ��ǰ ��� �� �˻� ���� */
		label = new JLabel("��ǰ ��� �� �˻� ����");
		label.setFont(font_L);
		label.setBounds(520, 3, 500, 40);
		label.setForeground(Color.WHITE);

		panel.add(label);

		/* Label - ��ǰ�� : */
		label = new JLabel("��ǰ�� : ");
		label.setFont(font_MB);
		label.setBounds(570, 53, 500, 40);

		panel.add(label);

		/* TextField - ��ǰ�� �Է� */
		text_Product = new JTextField("��ǰ���� �Է��ϼ���"); // �ؽ�Ʈ�ʵ� ������
		text_Product.setBounds(660, 53, 270, 40); // ��ġ�� ������
		text_Product.setFont(font_MB); // �۾�ü
		text_Product.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				text_Product.setText("");
				com_Kind.setSelectedIndex(0);
				text_Insert.setText("");
				text_Price1.setText("0");
				text_Price2.setText("0");
			}
		});

		panel.add(text_Product); // �ǳڿ� textfield �߰�

		/* Label - ��ǰ���� : */
		label = new JLabel("��ǰ���� : ");
		label.setFont(font_MB);
		label.setBounds(550, 103, 500, 40);

		panel.add(label);

		/* TextField - ��ǰ���� �����Է� */
		text_Insert = new JTextField(20); // �ؽ�Ʈ�ʵ� ������
		text_Insert.setBounds(805, 103, 125, 40); // ��ġ�� ������
		text_Insert.setFont(font_MB); // �۾�ü

		/* ComboBox - ��ǰ���� ���� */
		com_Kind = new JComboBox<String>();
		com_Kind.setFont(font_MB);
		com_Kind.setBounds(660, 105, 125, 35);
		text_Insert.setEditable(true);

		com_Kind.addItem("�����Է�");
		Kind = fsDao.GetFoodKind();

		for (int i = 0; i < Kind.length; i++) {
			com_Kind.addItem(Kind[i]);
		}
		com_Kind.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();
				if (com_Kind.getSelectedItem().toString().equals("�����Է�")) {
					text_Insert.setEditable(true);
				} else {
					text_Insert.setEditable(false);
					text_Insert.setText(com_Kind.getSelectedItem().toString());
				}
			}
		});

		panel.add(text_Insert); // �ǳڿ� textfield �߰�
		panel.add(com_Kind);

		/* Label - �Ǹűݾ� : */
		label = new JLabel("�Ǹűݾ� : ");
		label.setFont(font_MB);
		label.setBounds(550, 153, 500, 40);

		panel.add(label);

		/* TextField - �Ǹűݾ� �Է� */
		text_Price1 = new JTextField("0"); // �ؽ�Ʈ�ʵ� ������
		text_Price1.setBounds(660, 153, 100, 40); // ��ġ�� ������
		text_Price1.setFont(font_MB); // �۾�ü
		text_Price1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				text_Price1.setText("");
			}
		});

		panel.add(text_Price1); // �ǳڿ� textfield �߰�

		/* Label - ~ */
		label = new JLabel("~");
		label.setFont(font_MB);
		label.setBounds(775, 150, 50, 40);

		panel.add(label);

		/* TextField - �Ǹűݾ� �Է� */
		text_Price2 = new JTextField("0"); // �ؽ�Ʈ�ʵ� ������
		text_Price2.setBounds(805, 153, 100, 40); // ��ġ�� ������
		text_Price2.setFont(font_MB); // �۾�ü
		text_Price2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				text_Price2.setText("");
			}
		});

		panel.add(text_Price2); // �ǳڿ� textfield �߰�

		/* Label - �� */
		label = new JLabel("��");
		label.setFont(font_MB);
		label.setBounds(910, 150, 510, 40);

		panel.add(label);

		/* TextArea - ��ǰ ���� ĭ */
		tArea_Insert = new JTextArea();
		tArea_Insert.setBounds(520, 43, 435, 160);
		tArea_Insert.disable();
		tArea_Insert.setBackground(new java.awt.Color(227, 225, 230));
		tArea_Insert.setBorder(new LineBorder(Color.DARK_GRAY, 5));

		panel.add(tArea_Insert);

		/* Button - ��ǰ �˻� ��ư */
		btn_Search = new JButton(FSearch);
		btn_Search.setSelectedIcon(FSearch_s);
		btn_Search.setRolloverIcon(FSearch_s);
		btn_Search.setBorderPainted(false);
		btn_Search.setFocusPainted(false);
		btn_Search.setContentAreaFilled(false);
		btn_Search.setFont(font_S);
		btn_Search.setBounds(520, 210, 140, 40);
		btn_Search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isNumeric(text_Price1.getText().toString()) && !isNumeric(text_Price2.getText().toString())) {
					JOptionPane.showMessageDialog(null, "���� ������ �̻��մϴ�", "����", JOptionPane.INFORMATION_MESSAGE);
					text_Price1.setText("0");
					text_Price2.setText("0");
				} else {
					int p1, p2;
					if (text_Price1.getText().toString() == null || text_Price1.getText().toString() .equals("")) {
						p1 = 0;
					}  else {
						p1 = Integer.parseInt(text_Price1.getText().toString());
					}
					if (text_Price2.getText().toString() == null || text_Price2.getText().toString() .equals("")) {
						p2 = 0;
					}  else {
						p2 = Integer.parseInt(text_Price2.getText().toString());
					}
					if (p1 < p2) {
						JOptionPane.showMessageDialog(null, "���� ������ �̻��մϴ�", "����", JOptionPane.INFORMATION_MESSAGE);
					} else if (text_Product.getText().equals("��ǰ���� �Է��ϼ���"))
						row = fsDao.SearchStock("", text_Insert.getText().toString(), p1, p2);
					else
						row = fsDao.SearchStock(text_Product.getText().toString(), text_Insert.getText().toString(), p1,
								p2);
					model = new DefaultTableModel(row, col) {
						// �� ���� ���ϰ� �ϴ� �κ�
						public boolean isCellEditable(int row, int col) {
							return false;
						}
					};
					table_Food.setModel(model);
				}
			}
		});

		panel.add(btn_Search);

		/* Button - ��ǰ ���� ��ư */
		btn_Reset = new JButton(FReset);
		btn_Reset.setSelectedIcon(FReset_s);
		btn_Reset.setRolloverIcon(FReset_s);
		btn_Reset.setBorderPainted(false);
		btn_Reset.setFocusPainted(false);
		btn_Reset.setContentAreaFilled(false);
		btn_Reset.setFont(font_S);
		btn_Reset.setBounds(668, 210, 140, 40);
		btn_Reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fsDto.getFS_PRODUCT() == null) {
					JOptionPane.showMessageDialog(null, "��ǰ�� �������ּ���", "����", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Manage_Product_Update msu = new Manage_Product_Update(fsDto, panel);
					msu.setVisible(true);
				}
			}
		});

		panel.add(btn_Reset);

		/* Button - ��ǰ ��� ��ư */
		btn_Insert = new JButton(FInsert);
		btn_Insert.setSelectedIcon(FInsert_s);
		btn_Insert.setRolloverIcon(FInsert_s);
		btn_Insert.setBorderPainted(false);
		btn_Insert.setFocusPainted(false);
		btn_Insert.setContentAreaFilled(false);
		btn_Insert.setFont(font_S);
		btn_Insert.setBounds(816, 210, 140, 40);
		btn_Insert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Manage_Product_Insert mpi = new Manage_Product_Insert(panel);
				mpi.setVisible(true);
			}
		});

		panel.add(btn_Insert);

		/* Label - ���� �� �׸� ���� */
		label = new JLabel("���� �� �׸� ����");
		label.setFont(font_L);
		label.setBounds(520, 250, 500, 40);
		label.setForeground(Color.WHITE);

		panel.add(label);

		/* Button - ��ǰ ���� ��ư */
		btn_Delete = new JButton(FDelete);
		btn_Delete.setSelectedIcon(FDelete_s);
		btn_Delete.setRolloverIcon(FDelete_s);
		btn_Delete.setBorderPainted(false);
		btn_Delete.setFocusPainted(false);
		btn_Delete.setContentAreaFilled(false);
		btn_Delete.setFont(font_S);
		btn_Delete.setBounds(520, 460, 140, 40);
		btn_Delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Answer = JOptionPane.showInputDialog(null, "��ǰ ������ ���Ͻø� \'��ǰ����\'�� �Է��ϼ���", "��ǰ����",
						JOptionPane.INFORMATION_MESSAGE);
				if (Answer == null) {
					JOptionPane.showMessageDialog(null, "��ǰ������ �ߴ��Ͽ����ϴ�", "��ǰ���� ����", JOptionPane.INFORMATION_MESSAGE);
				} else if (Answer.equals("��ǰ����")) {
					boolean result = fsDao.RemoveStock(fsDto.getFS_PRODUCT().toString());
					if (result) {
						JOptionPane.showMessageDialog(null, "��ǰ�� �����Ǿ����ϴ�", "��ǰ���� ����", JOptionPane.INFORMATION_MESSAGE);
						Refresh();
					} else {
						JOptionPane.showMessageDialog(null, "������ �����Ͽ����ϴ�", "��ǰ���� ����", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "��ǰ������ �ߴ��Ͽ����ϴ�", "��ǰ���� ����", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		panel.add(btn_Delete);

		/* Button - ��ǰ ���� ��ư */
		btn_Stock = new JButton(StockReset);
		btn_Stock.setSelectedIcon(StockReset_s);
		btn_Stock.setRolloverIcon(StockReset_s);
		btn_Stock.setBorderPainted(false);
		btn_Stock.setFocusPainted(false);
		btn_Stock.setContentAreaFilled(false);
		btn_Stock.setFont(font_S);
		btn_Stock.setBounds(816, 460, 140, 40);
		btn_Stock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fsDto.getFS_PRODUCT() == null) {
					JOptionPane.showMessageDialog(null, "��ǰ�� �������ּ���", "����", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Manage_Stock_Reset msr = new Manage_Stock_Reset(fsDto, panel);
					msr.setVisible(true);
				}
			}

		});

		panel.add(btn_Stock);

		/* TextArea - ���õ� ��ǰ ���� ĭ */
		tArea_Info = new JTextArea();
		tArea_Info.setFont(font_MB);
		tArea_Info.setBounds(520, 290, 435, 160);
		tArea_Info.setEditable(true);
		tArea_Info.setBackground(new java.awt.Color(227, 225, 230));
		tArea_Info.setBorder(new LineBorder(Color.DARK_GRAY, 5));

		panel.add(tArea_Info);
	}

	// ���̺� ��
	public void setCell() {
		col.addElement("��ȣ");
		col.addElement("��ǰ��");
		col.addElement("����");
		col.addElement("����");
		col.addElement("���");
	}

	// �����ΰ�
	public static boolean isNumeric(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// ����
	public void Refresh() {
		fsDto = new Food_Stock_DTO();
		row = fsDao.getAllStock();
		model = new DefaultTableModel(row, col) {
			// �� ���� ���ϰ� �ϴ� �κ�
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_Food.setModel(model);

		tArea_Info.setText("");

		text_Product.setText("��ǰ���� �Է��ϼ���");
		com_Kind.setSelectedIndex(0);
		text_Insert.setText("");
		text_Price1.setText("0");
		text_Price2.setText("0");
	}
}
