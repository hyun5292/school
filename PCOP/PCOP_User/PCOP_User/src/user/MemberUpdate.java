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

import pcop.dao.MemberUpdateDAO;
import pcop.dto.MemberUpdateDTO;

public class MemberUpdate extends JFrame {
	/** �۾�ü **/
	private Font font_L = new Font("���� ���", Font.BOLD, 25);
	private Font font_MB = new Font("���� ���", Font.BOLD, 18);
	private Font font_S = new Font("���� ���", Font.BOLD, 20);
	private JPanel panel = new JPanel();
	private JLabel label, lbl_ID;
	private JComboBox com_Phone1;
	private String[] phone = {"010", "011", "017"};
	private JTextField txt_ID, txt_Name, txt_PW, txt_PW_Check, txt_Phone2, txt_Phone3, com_Year, com_Month, com_Date;
	private JButton btn_Update;
	private JRadioButton radio_M, radio_F;
	private ButtonGroup group_Gender;

	private ImageIcon Mark = new ImageIcon("image/PCOP.png");
	private ImageIcon Reset = new ImageIcon("image/noselected/Reset.png");
	private ImageIcon Reset_s = new ImageIcon("image/selected/Reset_s.png");

	int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	/** dao,dto �������� **/
	MemberUpdateDAO dao = new MemberUpdateDAO();
	MemberUpdateDTO dto = new MemberUpdateDTO();

	public MemberUpdate(String mID) {
		setTitle("ȸ����������"); // Ÿ��Ʋ�� �ؽ�Ʈ ����
		setBounds(x / 2 + 50, y / 7, 500, 505); // �տ� �� ���� ������ ���� ��ġ, �ڿ� �� ���� ������ ������!
			
		/** dao���� ȸ���˻� ȣ�� **/
		dto = dao.MemberSelect(dto, mID);
		
		panel = new JPanel(); // ������ ������ ��ü �ʱ�ȭ
		panel.setBorder(new EmptyBorder(5, 5, 5, 5)); // �����¿� ��¦�� ���� �ִ°ž�
		panel.setLayout(new BorderLayout(0, 0)); // �ֶ� Layout �������ش�
		setContentPane(panel); // panel_MID�� ���ݱ��� �������ذɷ� ������
		setIconImage(Mark.getImage()); // ���� ����̶� �۾� ǥ���� ������ ����
		setResizable(false);
		panel.setBackground(new java.awt.Color(50, 50, 52)); // �ڿ� ��� ��

		panel.setLayout(null); // ���� �ٽ� §��

		/** Label - ���̵� **/
		label = new JLabel("���̵�"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(10, 20, 160, 40); // ��ġ�� ������
		label.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/** Label - ���̵� **/
		lbl_ID = new JLabel(); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		lbl_ID.setFont(font_L); // ��Ʈ
		lbl_ID.setBounds(190, 20, 160, 40); // ��ġ�� ������
		lbl_ID.setForeground(new java.awt.Color(255, 166, 0)); // �۾��� �Ͼ��
		lbl_ID.setText(dto.getM_ID()); // db�� ������ �ؽ�Ʈ�ڽ��� �ѷ���

		add(lbl_ID); // panel�� textfield �߰�

		/** Label - �̸� **/
		label = new JLabel("�̸�"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(10, 75, 160, 40); // ��ġ�� ������
		label.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/** TextField - �̸� �Է� **/
		txt_Name = new JTextField(20); // �ؽ�Ʈ�ʵ� ������
		txt_Name.setBounds(190, 75, 160, 40); // ��ġ�� ������
		txt_Name.setFont(font_MB); // �۾�ü
		txt_Name.setText(dto.getM_NAME()); // db�� ������ �ؽ�Ʈ�ڽ��� �ѷ���

		add(txt_Name); // panel�� textfield �߰�

		/** Label - ��й�ȣ **/
		label = new JLabel("��й�ȣ"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(10, 130, 160, 40); // ��ġ�� ������
		label.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/** TextField - ��й�ȣ �Է� **/
		txt_PW = new JPasswordField(); // �ؽ�Ʈ�ʵ� ������
		txt_PW.setBounds(190, 130, 160, 40); // ��ġ�� ������
		txt_PW.setFont(font_MB); // �۾�ü

		txt_PW.setText(dto.getM_PW()); // db�� ������ �ؽ�Ʈ�ڽ��� �ѷ���
		add(txt_PW); // panel�� textfield �߰�

		/** Label - ��й�ȣ Ȯ�� **/
		label = new JLabel("��й�ȣ Ȯ��"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(10, 185, 160, 40); // ��ġ�� ������
		label.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/** TextField - ��й�ȣ Ȯ�� �Է� **/
		txt_PW_Check = new JPasswordField(); // �ؽ�Ʈ�ʵ� ������
		txt_PW_Check.setBounds(190, 185, 160, 40); // ��ġ�� ������
		txt_PW_Check.setFont(font_MB); // �۾�ü

		add(txt_PW_Check); // panel�� textfield �߰�

		/** Label - ��ȭ��ȣ **/
		label = new JLabel("��ȭ��ȣ"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(10, 240, 160, 40); // ��ġ�� ������
		label.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/** ComboBox - ��ȭ��ȣ1 ���� **/
		com_Phone1 = new JComboBox(phone); // �޺��ڽ� �ʱ�ȭ�ε� String �����̾�
		com_Phone1.setFont(font_MB); // ��Ʈ
		com_Phone1.setBounds(190, 240, 70, 40); // ��ġ�� ������
		com_Phone1.setSelectedItem(dto.getM_TEL1()); // db�� ������ �ؽ�Ʈ�ڽ��� �ѷ���
		
		add(com_Phone1); // panel_MID�� com_Division �߰�

		/** Label - **/
		label = new JLabel("-"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(225, 235, 50, 40); // ��ġ�� ������
		label.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/** TextField - ��ȭ��ȣ2 �Է� **/
		txt_Phone2 = new JTextField(10); // �ؽ�Ʈ�ʵ� ������
		txt_Phone2.setBounds(280, 240, 70, 40); // ��ġ�� ������
		txt_Phone2.setFont(font_MB); // �۾�ü

		txt_Phone2.setText(dto.getM_TEL2()); // db�� ������ �ؽ�Ʈ�ڽ��� �ѷ���
		add(txt_Phone2); // panel�� textfield �߰�

		/** Label - **/
		label = new JLabel("-"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(315, 235, 50, 40); // ��ġ�� ������
		label.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/** TextField - ��ȭ��ȣ3 �Է� **/
		txt_Phone3 = new JTextField(10); // �ؽ�Ʈ�ʵ� ������
		txt_Phone3.setBounds(370, 240, 70, 40); // ��ġ�� ������
		txt_Phone3.setFont(font_MB); // �۾�ü

		txt_Phone3.setText(dto.getM_TEL3()); // db�� ������ �ؽ�Ʈ�ڽ��� �ѷ���
		add(txt_Phone3); // panel�� textfield �߰�

		/** Label - ������� **/
		label = new JLabel("�������"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(10, 295, 160, 40); // ��ġ�� ������
		label.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/** TextField - �� ���� **/
		com_Year = new JTextField(10);
		com_Year.setFont(font_MB); // ��Ʈ
		com_Year.setBounds(190, 295, 70, 40); // ��ġ�� ������
		com_Year.setEditable(false); // ��������
		com_Year.setText(dto.getM_BIRTH1()); // db�� ������ �ؽ�Ʈ�ڽ��� �ѷ���

		add(com_Year); // panel_MID�� com_Division �߰�

		/** Label - �� **/
		label = new JLabel("��"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(265, 295, 30, 40); // ��ġ�� ������
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/** TextField - �� ���� **/
		com_Month = new JTextField(5);
		; // �޺��ڽ� �ʱ�ȭ�ε� String �����̾�
		com_Month.setFont(font_MB); // ��Ʈ
		com_Month.setBounds(300, 295, 50, 40); // ��ġ�� ������
		com_Month.setEditable(false); // ��������
		com_Month.setText(dto.getM_BIRTH2()); // db�� ������ �ؽ�Ʈ�ڽ��� �ѷ���

		add(com_Month); // panel_MID�� com_Division �߰�

		/** Label - �� **/
		label = new JLabel("��"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(355, 295, 30, 40); // ��ġ�� ������
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/** TextField - �� ���� **/
		com_Date = new JTextField(5);
		; // �޺��ڽ� �ʱ�ȭ�ε� String �����̾�
		com_Date.setFont(font_MB); // ��Ʈ
		com_Date.setBounds(390, 295, 50, 40); // ��ġ�� ������
		com_Date.setEditable(false); // ��������
		com_Date.setText(dto.getM_BIRTH3()); // db�� ������ �ؽ�Ʈ�ڽ��� �ѷ���

		add(com_Date); // panel_MID�� com_Division �߰�

		/** Label - �� **/
		label = new JLabel("��"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(445, 295, 30, 40); // ��ġ�� ������
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/** Label - ���� **/
		label = new JLabel("����"); // �ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L); // ��Ʈ
		label.setBounds(10, 350, 160, 40); // ��ġ�� ������
		label.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
		label.setForeground(Color.WHITE); // �۾��� �Ͼ��

		add(label); // panel�� label �߰�

		/** RadioButton - M **/
		radio_M = new JRadioButton("����");
		radio_M.setFont(font_L); // ��Ʈ
		radio_M.setBounds(190, 350, 80, 40); // ��ġ�� ������
		radio_M.setForeground(Color.WHITE); // �۾��� �Ͼ��
		radio_M.setBackground(new java.awt.Color(50, 50, 52));
		radio_M.setSelected(true);

		add(radio_M); // panel�� label �߰�

		/** RadioButton - F **/
		radio_F = new JRadioButton("����");
		radio_F.setFont(font_L); // ��Ʈ
		radio_F.setBounds(280, 350, 80, 40); // ��ġ�� ������
		radio_F.setForeground(Color.WHITE); // �۾��� �Ͼ��
		radio_F.setBackground(new java.awt.Color(50, 50, 52));

		add(radio_F); // panel�� label �߰�

		group_Gender = new ButtonGroup();
		group_Gender.add(radio_M);
		group_Gender.add(radio_F);

		
		/** ���� üũ **/
		// ������ ���ڸ�
		if (dto.getM_GENDER().equals("����")) {
			radio_M.setSelected(true); // ������ üũ
		}
		// ������ ���ڸ�
		else if (dto.getM_GENDER().equals("����")) {
			radio_F.setSelected(true); // ������ üũ
		}
		
		/** Button - ���� ��ư **/
		btn_Update = new JButton(Reset); // ��ư ������, �ƹ��͵� ������ �� �̹��� Delete
		btn_Update.setSelectedIcon(Reset_s); // ������ �� �̹��� Delete_s....�� Delete�� ������
		btn_Update.setRolloverIcon(Reset_s); // ���콺 �÷��� �� �̹��� Delete_s
		btn_Update.setBorderPainted(false); // �׵θ� ���
		btn_Update.setFocusPainted(false); // �� �ٸ��� ������ ����
		btn_Update.setContentAreaFilled(false); // �̰� �𸣰ھ� �׳� �ؾߵȴ�
		btn_Update.setBounds(172, 405, 140, 40); // ��ġ�� ������

		/** ���� ��ư Ŭ������ ��� **/
		btn_Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** �޼����ڽ� UI **/
				UIManager UI = new UIManager();
	            UI.put("OptionPane.background", new java.awt.Color(50, 50, 52));
	            UI.put("Panel.background", new java.awt.Color(50, 50, 52));
	            UI.put("OptionPane.messageFont", font_L);
	            UI.put("OptionPane.messageForeground", Color.WHITE);
	            UI.put("OptionPane.buttonFont", font_L);
	            
				/** ��й�ȣ ��ġ ���� **/
				if (txt_PW_Check.getText().equals(txt_PW.getText())) {			
					/** ȸ���������� **/				
					dto.setM_NAME(txt_Name.getText().toString()); // �̸�
					dto.setM_PW(txt_PW.getText().toString());     // ��й�ȣ
					dto.setM_TEL1(com_Phone1.getSelectedItem().toString()); // ��ȭ��ȣ1
					dto.setM_TEL2(txt_Phone2.getText().toString()); // ��ȭ��ȣ2
					dto.setM_TEL3(txt_Phone3.getText().toString()); // ��ȭ��ȣ3
					
					/** ����üũ  **/
					if (radio_M.isSelected() == true) {
						dto.setM_GENDER(radio_M.getText().toString());
					}
					else if (radio_F.isSelected() == true) {
						dto.setM_GENDER(radio_F.getText().toString());
					}
					
					/** dao���� ȸ������ ȣ�� **/
					dao.MemberUpdate(dto, mID); 
					
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�!", "ȸ����������", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} 
							
				else {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�!", "ȸ����������", JOptionPane.WARNING_MESSAGE);
				}				
			}
		});

		add(btn_Update); // panel_MID�� btn_Delete �߰�
	}
	
	/** �ؽ�Ʈ ����� **/
	public void Text_Clean() {
		txt_Name.setText(""); // �̸�
		txt_PW.setText(""); // ��й�ȣ
		txt_PW_Check.setText(""); // ��й�ȣ Ȯ��
		com_Phone1.setSelectedIndex(0); // ��ȭ��ȣ1
		//com_Phone1.removeAllItems(); //�޺��ڽ� �׸� �� ����
		txt_Phone2.setText(""); // ��ȭ��ȣ2
		txt_Phone3.setText(""); // ��ȭ��ȣ3
		com_Year.setText(""); // �������1
		com_Month.setText(""); // �������2
		com_Date.setText(""); // �������3
		group_Gender.clearSelection(); // ����
	}
}
