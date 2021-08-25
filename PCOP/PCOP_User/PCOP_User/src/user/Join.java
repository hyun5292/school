package user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import pcop.dao.Member_DAO;
import pcop.dto.Member_DTO;

public class Join extends JFrame {
	// �۾�ü
	private Font font_L = new Font("���� ���", Font.BOLD, 25);
	private Font font_MB = new Font("���� ���", Font.BOLD, 18);
	private Font font_S = new Font("���� ���", Font.BOLD, 20);
	private JPanel panel = new JPanel();
	private JLabel label;
	private JComboBox com_Phone1, com_Year, com_Month, com_Date;
	private JTextField txt_ID, txt_Name, txt_Phone2, txt_Phone3;
	private JPasswordField txt_PW, txt_PW_Check;
	private JButton btn_Join;
	private JRadioButton radio_M, radio_F;
	private ButtonGroup group_Gender;

	private ImageIcon Mark = new ImageIcon("image/PCOP.png");
	private ImageIcon MJoin = new ImageIcon("image/noselected/MJoin.png");
	private ImageIcon MJoin_s = new ImageIcon("image/selected/MJoin_s.png");

	int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private Calendar c = Calendar.getInstance(); // ��¥
	String[] year = new String[112];
	String[] month = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
	String[] date = new String[] { "31", "29", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31" };
	int thisyear = c.get(Calendar.YEAR) - 8;
	int thismonth = c.get(Calendar.MONTH);
	int thisdate = c.get(Calendar.DATE);

	/** dao,dto �������� **/
	Member_DAO dao = new Member_DAO();
	Member_DTO dto = new Member_DTO();

	public Join() {
		setTitle("ȸ������"); // Ÿ��Ʋ�� �ؽ�Ʈ ����
		setBounds(x / 2 + 50, y / 7, 500, 505); // �տ� �� ���� ������ ���� ��ġ, �ڿ� �� ���� ������ ������!
		panel = new JPanel(); // ������ ������ ��ü �ʱ�ȭ
		panel.setBorder(new EmptyBorder(5, 5, 5, 5)); // �����¿� ��¦�� ���� �ִ°ž�
		panel.setLayout(new BorderLayout(0, 0)); // �ֶ� Layout �������ش�
		setContentPane(panel); // panel_MID�� ���ݱ��� �������ذɷ� ������
		setIconImage(Mark.getImage()); // ���� ����̶� �۾� ǥ���� ������ ����
		setResizable(false);
		panel.setBackground(new java.awt.Color(50, 50, 52)); // �ڿ� ��� ��

		panel.setLayout(null); // ���� �ٽ� §��

		/* Label - ���̵� */
		label = new JLabel("���̵�"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(10, 20, 160, 40); // ��ġ�� ������
		label.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/* TextField - ID �Է� */
		txt_ID = new JTextField(20); // �ؽ�Ʈ�ʵ� ������
		txt_ID.setBounds(190, 20, 160, 40); // ��ġ�� ������
		txt_ID.setFont(font_MB); // �۾�ü

		add(txt_ID); // panel�� textfield �߰�

		/* Label - �̸� */
		label = new JLabel("�̸�"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(10, 75, 160, 40); // ��ġ�� ������
		label.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/* TextField - �̸� �Է� */
		txt_Name = new JTextField(20); // �ؽ�Ʈ�ʵ� ������
		txt_Name.setBounds(190, 75, 160, 40); // ��ġ�� ������
		txt_Name.setFont(font_MB); // �۾�ü

		add(txt_Name); // panel�� textfield �߰�

		/* Label - ��й�ȣ */
		label = new JLabel("��й�ȣ"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(10, 130, 160, 40); // ��ġ�� ������
		label.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/* TextField - ��й�ȣ �Է� */
		txt_PW = new JPasswordField(20); // �ؽ�Ʈ�ʵ� ������
		txt_PW.setBounds(190, 130, 160, 40); // ��ġ�� ������
		txt_PW.setFont(font_MB); // �۾�ü

		add(txt_PW); // panel�� textfield �߰�

		/* Label - ��й�ȣ Ȯ�� */
		label = new JLabel("��й�ȣ Ȯ��"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(10, 185, 160, 40); // ��ġ�� ������
		label.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/* TextField - ��й�ȣ Ȯ�� �Է� */
		txt_PW_Check = new JPasswordField(20); // �ؽ�Ʈ�ʵ� ������
		txt_PW_Check.setBounds(190, 185, 160, 40); // ��ġ�� ������
		txt_PW_Check.setFont(font_MB); // �۾�ü

		add(txt_PW_Check); // panel�� textfield �߰�

		/* Label - ��ȭ��ȣ */
		label = new JLabel("��ȭ��ȣ"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(10, 240, 160, 40); // ��ġ�� ������
		label.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/* ComboBox - ��ȭ��ȣ1 ���� */
		com_Phone1 = new JComboBox<String>(); // �޺��ڽ� �ʱ�ȭ�ε� String �����̾�
		com_Phone1.setFont(font_MB); // ��Ʈ
		com_Phone1.setBounds(190, 240, 70, 40); // ��ġ�� ������

		com_Phone1.addItem("010"); // �޺��ڽ� ���� �׸�
		com_Phone1.addItem("011"); // �޺��ڽ� ���� �׸�
		com_Phone1.addItem("017"); // �޺��ڽ� ���� �׸�

		com_Phone1.setSelectedItem(0);
		add(com_Phone1); // panel_MID�� com_Division �߰�

		/* Label - */
		label = new JLabel("-"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(225, 235, 50, 40); // ��ġ�� ������
		label.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/* TextField - ��ȭ��ȣ2 �Է� */
		txt_Phone2 = new JTextField(10); // �ؽ�Ʈ�ʵ� ������
		txt_Phone2.setBounds(280, 240, 70, 40); // ��ġ�� ������
		txt_Phone2.setFont(font_MB); // �۾�ü

		txt_Phone2.setText(dto.getM_TEL2());
		add(txt_Phone2); // panel�� textfield �߰�

		/* Label - */
		label = new JLabel("-"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(315, 235, 50, 40); // ��ġ�� ������
		label.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/* TextField - ��ȭ��ȣ3 �Է� */
		txt_Phone3 = new JTextField(10); // �ؽ�Ʈ�ʵ� ������
		txt_Phone3.setBounds(370, 240, 70, 40); // ��ġ�� ������
		txt_Phone3.setFont(font_MB); // �۾�ü

		add(txt_Phone3); // panel�� textfield �߰�

		/* Label - ������� */
		label = new JLabel("�������"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(10, 295, 160, 40); // ��ġ�� ������
		label.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		// �������� ���� 5���� I_Year�� ���
		for (int i = 0, y = thisyear - i; i < year.length; i++, y--) {
			year[i] = String.valueOf(y);
		}

		/* ComboBox - �� ���� */
		com_Year = new JComboBox<String>(year); // �޺��ڽ� �ʱ�ȭ�ε� String �����̾�
		com_Year.setFont(font_MB); // ��Ʈ
		com_Year.setBounds(190, 295, 70, 40); // ��ġ�� ������

		add(com_Year); // panel_MID�� com_Division �߰�

		/* Label - �� */
		label = new JLabel("��"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(265, 295, 30, 40); // ��ġ�� ������
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/* ComboBox - �� ���� */
		com_Month = new JComboBox<String>(month); // �޺��ڽ� �ʱ�ȭ�ε� String �����̾�
		com_Month.setFont(font_MB); // ��Ʈ
		com_Month.setBounds(300, 295, 50, 40); // ��ġ�� ������
		com_Month.setSelectedIndex(thismonth);

		add(com_Month); // panel_MID�� com_Division �߰�

		/* Label - �� */
		label = new JLabel("��"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(355, 295, 30, 40); // ��ġ�� ������
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/* ComboBox - �� ���� */
		com_Date = new JComboBox<String>(); // �޺��ڽ� �ʱ�ȭ�ε� String �����̾�
		com_Date.setFont(font_MB); // ��Ʈ
		com_Date.setBounds(390, 295, 50, 40); // ��ġ�� ������
		/* �� �� ����ؼ� �ֱ� */
		for (int i = 0; i < Integer.parseInt(date[com_Month.getSelectedIndex()]); i++) {
			com_Date.addItem(String.valueOf(i + 1));
		}

		com_Month.addItemListener(new ItemListener() { // ���߿� �޺��ڽ� ���ÿ� ���� �Ͼ �̺�Ʈ
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();
				com_Date.removeAllItems();

				/* �� �� ����ؼ� �ֱ� */
				for (int i = 0; i < Integer.parseInt(date[com_Month.getSelectedIndex()]); i++) {
					com_Date.addItem(String.valueOf(i + 1));
				}
			}
		});

		add(com_Date); // panel_MID�� com_Division �߰�

		/* Label - �� */
		label = new JLabel("��"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(445, 295, 30, 40); // ��ġ�� ������
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/* Label - ���� */
		label = new JLabel("����"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(10, 350, 160, 40); // ��ġ�� ������
		label.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/* RadioButton - M */
		radio_M = new JRadioButton("����");
		radio_M.setFont(font_L); // ��Ʈ
		radio_M.setBounds(190, 350, 80, 40); // ��ġ�� ������
		radio_M.setForeground(Color.WHITE); // �۾��� �Ͼ��
		radio_M.setBackground(new java.awt.Color(50, 50, 52));
		radio_M.setSelected(true);

		add(radio_M); // panel�� label �߰�

		/* RadioButton - F */
		radio_F = new JRadioButton("����");
		radio_F.setFont(font_L); // ��Ʈ
		radio_F.setBounds(280, 350, 80, 40); // ��ġ�� ������
		radio_F.setForeground(Color.WHITE); // �۾��� �Ͼ��
		radio_F.setBackground(new java.awt.Color(50, 50, 52));

		add(radio_F); // panel�� label �߰�

		group_Gender = new ButtonGroup();
		group_Gender.add(radio_M);
		group_Gender.add(radio_F);

		/* Button - ���� ��ư */
		btn_Join = new JButton(MJoin); // ��ư ������, �ƹ��͵� ������ �� �̹��� Delete
		btn_Join.setSelectedIcon(MJoin_s); // ������ �� �̹��� Delete_s....�� Delete�� ������
		btn_Join.setRolloverIcon(MJoin_s); // ���콺 �÷��� �� �̹��� Delete_s
		btn_Join.setBorderPainted(false); // �׵θ� ���
		btn_Join.setFocusPainted(false); // �� �ٸ��� ������ ����
		btn_Join.setContentAreaFilled(false); // �̰� �𸣰ھ� �׳� �ؾߵȴ�
		btn_Join.setBounds(172, 405, 140, 40); // ��ġ�� ������

		btn_Join.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/** �޼����ڽ� UI **/
				UIManager UI = new UIManager();
				UI.put("OptionPane.background", new java.awt.Color(50, 50, 52));
				UI.put("Panel.background", new java.awt.Color(50, 50, 52));
				UI.put("OptionPane.messageFont", font_L);
				UI.put("OptionPane.messageForeground", Color.WHITE);
				UI.put("OptionPane.buttonFont", font_L);

				boolean tel2 = isNumeric(txt_Phone2.getText());
				boolean tel3 = isNumeric(txt_Phone3.getText());
				/** ���� �˻� **/
				if (CheckNull()) {
					/** ���̵� �̹� �����ϴ� �� Ȯ�� **/
					if (dao.SearchID(txt_ID.getText())) {
						/* ��ȭ��ȣ �������� �Ǵ� */
						if (!tel2 || !tel3) {
							JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� ���ڸ� �Է°����մϴ�", "ȸ������ ����",
									JOptionPane.WARNING_MESSAGE);
							txt_Phone2.setText("");
							txt_Phone3.setText("");
						} else { 

							/** ��й�ȣ ��ġ ���� **/
							if (txt_PW_Check.getText().equals(txt_PW.getText())) {
								dto.setM_ID(txt_ID.getText());
								dto.setM_NAME(txt_Name.getText());
								dto.setM_PW(txt_PW.getText());
								dto.setM_TEL1(com_Phone1.getSelectedItem().toString());
								dto.setM_TEL2(txt_Phone2.getText());
								dto.setM_TEL3(txt_Phone3.getText());
								dto.setM_BIRTH1(com_Year.getSelectedItem().toString());
								dto.setM_BIRTH2(com_Month.getSelectedItem().toString());
								dto.setM_BIRTH3(com_Date.getSelectedItem().toString());
								// ������ ���ڸ�
								if (radio_M.isSelected()) {
									dto.setM_GENDER("����");
								}
								// ������ ���ڸ�
								else if (radio_F.isSelected()) {
									dto.setM_GENDER("����");
								}
								dto.setM_MODE("N");
								dto.setM_BEGIN_TIME("");
								dto.setM_REMAIN_TIME("");
								dto.setUSED_MONEY(0);
								dao.MemberInsert(dto);
								JOptionPane.showMessageDialog(null, "ȸ������ �Ǿ����ϴ�!", "ȸ������",
										JOptionPane.INFORMATION_MESSAGE);
								dispose();

							} else {
								JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�!", "ȸ������ ����",
										JOptionPane.WARNING_MESSAGE);
								txt_PW_Check.setText("");
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "���̵� �̹� �����մϴ�!", "ȸ������ ����", JOptionPane.WARNING_MESSAGE);
						txt_ID.setText("");
					}
				}
			}
		});

		add(btn_Join); // panel_MID�� btn_Delete �߰�
	}

	/* ���� �˻� */
	public boolean CheckNull() {
		boolean result = true;

		if (txt_ID.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "���̵� �Է��ϼ���", "ȸ������ ����", JOptionPane.WARNING_MESSAGE);
			result = false;
		} else if (txt_Name.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "�̸��� �Է��ϼ���", "ȸ������ ����", JOptionPane.WARNING_MESSAGE);
			result = false;
		} else if (txt_PW.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��ϼ���", "ȸ������ ����", JOptionPane.WARNING_MESSAGE);
			result = false;
		} else if (txt_Phone2.getText().equals("") || (txt_Phone3.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� �Է��ϼ���", "ȸ������ ����", JOptionPane.WARNING_MESSAGE);
			result = false;
		}

		return result;
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
}
