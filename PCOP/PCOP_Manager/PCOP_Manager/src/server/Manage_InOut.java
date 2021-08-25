package server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import pcop.dao.Input_Come_DAO;
import pcop.dto.Input_Come_DTO;

public class Manage_InOut {
	// �۾�ü
	private Font font_L = new Font("���� ���", Font.BOLD, 23);
	private Font font_MB = new Font("���� ���", Font.BOLD, 18);
	private Font font_S = new Font("���� ���", Font.BOLD, 20);
	private JLabel label;
	private String[] Date = new String[12];
	private Calendar c = Calendar.getInstance(); // ��¥
	private int thisyear = c.get(Calendar.YEAR);
	private int thismonth = c.get(Calendar.MONTH);
	private JComboBox combo_Date;
	private JButton btn_Search, btn_Insert, btn_Delete;
	private JTable table_Income, table_Outcome, table_Result;
	private Vector<String> col_in = new Vector<String>(); // ���̺� �÷�
	private Vector<String> col_out = new Vector<String>(); // ���̺� �÷�
	private Vector<String> col_result = new Vector<String>(); // ���̺� �÷�
	private Vector row_in, row_out, row_result; // ���̺� ����
	private DefaultTableModel model;
	private JScrollPane scroll;
	// �̹���
	private ImageIcon SelDelete = new ImageIcon("image/noselected/SelDelete.png");
	private ImageIcon SelDelete_s = new ImageIcon("image/selected/SelDelete_s.png");
	private ImageIcon SelInsert = new ImageIcon("image/noselected/SelInsert.png");
	private ImageIcon SelInsert_s = new ImageIcon("image/selected/SelInsert_s.png");
	private ImageIcon ReadingGlass = new ImageIcon("image/noselected/ReadingGlass.png");
	private ImageIcon ReadingGlass_s = new ImageIcon("image/selected/ReadingGlass_s.png");

	private Input_Come_DAO dao = new Input_Come_DAO();
	private Input_Come_DTO dto = new Input_Come_DTO();

	// ������
	public Manage_InOut(JPanel panel) {
		panel.setLayout(null); // ���� �ٽ� §��
		panel.setBackground(new java.awt.Color(50, 50, 52)); // �ڿ� ��� ��

		/* Label - ��¥ */
		label = new JLabel("��¥");
		label.setFont(font_L);
		label.setBounds(5, 3, 500, 40);
		label.setForeground(Color.WHITE);

		panel.add(label);

		/* ComboBox - ��¥ ���� */
		combo_Date = new JComboBox<String>();
		combo_Date.setFont(font_MB);
		combo_Date.setBounds(70, 8, 150, 35);
		combo_Date.enable();

		for (int i = 0; i < thismonth + 1; i++) {
			Date[i] = thisyear + "-" + (i + 1) + "-01";
		}

		for (int i = 0; i < thismonth + 1; i++) {
			combo_Date.addItem(Date[i]);
		}
		combo_Date.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();

			}
		});

		panel.add(combo_Date);

		/* Button - �˻� ��ư */
		btn_Search = new JButton(ReadingGlass);
		btn_Search.setSelectedIcon(ReadingGlass_s);
		btn_Search.setRolloverIcon(ReadingGlass_s);
		btn_Search.setBorderPainted(false);
		btn_Search.setFocusPainted(false);
		btn_Search.setContentAreaFilled(false);
		btn_Search.setFont(font_S);
		btn_Search.setBounds(230, 5, 40, 40);
		btn_Search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ����
				row_in = dao.getInComes(combo_Date.getSelectedItem().toString(), "����");
				model = new DefaultTableModel(row_in, col_in) {
					// �� ���� ���ϰ� �ϴ� �κ�
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				table_Income.setModel(model);

				// ����
				row_out = dao.getInComes(combo_Date.getSelectedItem().toString(), "����");
				model = new DefaultTableModel(row_out, col_out) {
					// �� ���� ���ϰ� �ϴ� �κ�
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				table_Outcome.setModel(model);

				// ����
				int inCome = dao.getResultComes(combo_Date.getSelectedItem().toString(), "����");
				int outCome = dao.getResultComes(combo_Date.getSelectedItem().toString(), "����");
				row_result = new Vector<String>();
				Vector row = new Vector();

				row.add(inCome);
				row.add(outCome);
				row.add((inCome - outCome));
				row_result.add(row);
				model = new DefaultTableModel(row_result, col_result) {
					// �� ���� ���ϰ� �ϴ� �κ�
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				table_Result.setModel(model);
			}

		});

		panel.add(btn_Search);

		/* Button - ���� �Է� ��ư */
		btn_Insert = new JButton(SelInsert);
		btn_Insert.setSelectedIcon(SelInsert_s);
		btn_Insert.setRolloverIcon(SelInsert_s);
		btn_Insert.setBorderPainted(false);
		btn_Insert.setFocusPainted(false);
		btn_Insert.setContentAreaFilled(false);
		btn_Insert.setFont(font_S);
		btn_Insert.setBounds(670, 5, 140, 40);
		btn_Insert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Manage_InOut_Insert mii = new Manage_InOut_Insert();
				mii.setVisible(true);
			}

		});

		panel.add(btn_Insert);

		/* Button - ���� ���� ��ư */
		btn_Delete = new JButton(SelDelete);
		btn_Delete.setSelectedIcon(SelDelete_s);
		btn_Delete.setRolloverIcon(SelDelete_s);
		btn_Delete.setBorderPainted(false);
		btn_Delete.setFocusPainted(false);
		btn_Delete.setContentAreaFilled(false);
		btn_Delete.setFont(font_S);
		btn_Delete.setBounds(820, 5, 140, 40);
		btn_Delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dto.getC_S_KIND() == null) {
					JOptionPane.showMessageDialog(null, "���� �Ǵ� ������ ���� �������ּ���", "����", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String Answer = JOptionPane.showInputDialog(null, " ������ ���Ͻø� \'����Ȯ��\'�� �Է��ϼ���", "����/���� ����",
							JOptionPane.INFORMATION_MESSAGE);
					if (Answer == null) {
						JOptionPane.showMessageDialog(null, "������ �ߴ��Ͽ����ϴ�", "���� ����",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (Answer.equals("����Ȯ��")) {
						boolean result = dao.DeleteInCome(dto);
						if (result) {
							JOptionPane.showMessageDialog(null, "���� �Ǿ����ϴ�", "���� ����",
									JOptionPane.INFORMATION_MESSAGE);
							Refresh();
						} else {
							JOptionPane.showMessageDialog(null, "������ �����Ͽ����ϴ�", "���� ����",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}

		});

		panel.add(btn_Delete);

		/* Label - ���� */
		label = new JLabel("����");
		label.setFont(font_L);
		label.setBounds(5, 45, 500, 40);
		label.setForeground(Color.WHITE);

		panel.add(label);

		setInOutCell();
		/* Table - ȸ�� ���� ����ִ� */
		model = new DefaultTableModel(row_in, col_in) {
			// �� ���� ���ϰ� �ϴ� �κ�
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		/* Table - ȸ�� ���� ����ִ� */
		table_Income = new JTable(model); // ���̺� ������
		// table_Income.setBounds(6, 85, 955, 130); // ��ġ�� ������
		table_Income.setFont(font_S); // �۾�ü
		table_Income.setRowHeight(50);
		table_Income.setBackground(new java.awt.Color(227, 225, 230));
		table_Income.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dto = new Input_Come_DTO();
				int row = table_Income.getSelectedRow();
				TableModel md = table_Income.getModel();

				dto.setC_DATE(combo_Date.getSelectedItem().toString());
				dto.setC_S_KIND(md.getValueAt(row, 0).toString());
				dto.setC_PRICE(Integer.parseInt(md.getValueAt(row, 1).toString()));
				if (md.getValueAt(row, 2) == null) {
					dto.setC_MEMO("");
				} else {
					dto.setC_MEMO(md.getValueAt(row, 2).toString());
				}
			}
		});

		scroll = new JScrollPane(table_Income);
		scroll.setViewportView(table_Income);
		scroll.setBounds(6, 85, 955, 130);

		panel.add(scroll); // ��ũ�� �߰�

		/* Label - ���� */
		label = new JLabel("����");
		label.setFont(font_L);
		label.setBounds(5, 215, 500, 40);
		label.setForeground(Color.WHITE);

		panel.add(label);

		/* Table - ȸ�� ���� ����ִ� */
		model = new DefaultTableModel(row_out, col_out) {
			// �� ���� ���ϰ� �ϴ� �κ�
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		/* Table - ȸ�� ���� ����ִ� */
		table_Outcome = new JTable(model); // ���̺� ������
		// table_Outcome.setBounds(6, 255, 955, 130); // ��ġ�� ������
		table_Outcome.setFont(font_S); // �۾�ü
		table_Outcome.setRowHeight(50);
		table_Outcome.setBackground(new java.awt.Color(227, 225, 230));
		table_Outcome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dto = new Input_Come_DTO();
				int row = table_Outcome.getSelectedRow();
				TableModel md = table_Outcome.getModel();

				dto.setC_DATE(combo_Date.getSelectedItem().toString());
				dto.setC_S_KIND(md.getValueAt(row, 0).toString());
				dto.setC_PRICE(Integer.parseInt(md.getValueAt(row, 1).toString()));
				if (md.getValueAt(row, 2) == null) {
					dto.setC_MEMO("");
				} else {
					dto.setC_MEMO(md.getValueAt(row, 2).toString());
				}
			}
		});

		scroll = new JScrollPane(table_Outcome);
		scroll.setViewportView(table_Outcome);
		scroll.setBounds(6, 255, 955, 130);

		panel.add(scroll); // ��ũ�� �߰�

		/* Label - ���հ� */
		label = new JLabel("���հ�");
		label.setFont(font_L);
		label.setBounds(5, 385, 500, 40);
		label.setForeground(Color.WHITE);

		panel.add(label);

		/* Table - ȸ�� ���� ����ִ� */
		setResultCell();
		model = new DefaultTableModel(row_result, col_result) {
			// �� ���� ���ϰ� �ϴ� �κ�
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		/* Table - ȸ�� ���� ����ִ� */
		table_Result = new JTable(model); // ���̺� ������
		// table_Result.setBounds(6, 425, 955, 80); // ��ġ�� ������
		table_Result.setRowHeight(50);
		table_Result.setFont(font_S); // �۾�ü
		table_Result.setBackground(new java.awt.Color(227, 225, 230));

		scroll = new JScrollPane(table_Result);
		scroll.setViewportView(table_Result);
		scroll.setBounds(6, 425, 955, 80);

		panel.add(scroll); // ��ũ�� �߰�
	}

	// ���̺� ��
	public void setInOutCell() {
		col_in.addElement("����");
		col_in.addElement("�ѱݾ�");
		col_in.addElement("���");

		col_out.addElement("����");
		col_out.addElement("�ѱݾ�");
		col_out.addElement("���");
	}

	// ���̺� ��
	public void setResultCell() {
		col_result.addElement("�Ѹ���");
		col_result.addElement("������");
		col_result.addElement("���հ�");
	}

	// �ʱ�ȭ
	public void Refresh() {
		row_in = new Vector();
		model = new DefaultTableModel(row_in, col_in) {
			// �� ���� ���ϰ� �ϴ� �κ�
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_Income.setModel(model);
		
		row_out = new Vector();
		model = new DefaultTableModel(row_out, col_out) {
			// �� ���� ���ϰ� �ϴ� �κ�
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_Outcome.setModel(model);
		
		row_result = new Vector();
		model = new DefaultTableModel(row_result, col_result) {
			// �� ���� ���ϰ� �ϴ� �κ�
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_Result.setModel(model);

		combo_Date.setSelectedIndex(0);
		dto = new Input_Come_DTO();
	}
}
