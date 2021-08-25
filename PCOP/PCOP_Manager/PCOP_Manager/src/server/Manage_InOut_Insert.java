package server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import pcop.dao.Food_Stock_DAO;
import pcop.dao.Input_Come_DAO;
import pcop.dto.Input_Come_DTO;

public class Manage_InOut_Insert extends JFrame {
	//�۾�ü
	Font font_L = new Font("���� ���", Font.BOLD, 23);
	Font font_MB = new Font("���� ���", Font.BOLD, 18);
	Font font_S = new Font("���� ���", Font.BOLD, 20);
	JPanel panel_MII = new JPanel();
	JLabel label = new JLabel();
	JTextField text_Insert, text_Price;
	JTextArea text_Memo;
	JButton btn_Insert;
	JComboBox com_Division, com_Year, com_Month, com_Kind;
	String[] Kind, S_Year, S_Month;
	Calendar c = Calendar.getInstance();  //��¥
	Integer[] I_Year = new Integer[6];  //�������� �� 6��
	Integer[] I_Month = new Integer[12];  //12����
	int thisyear = c.get(Calendar.YEAR);  //���� �⵵ �������°�
	//�̹���
	ImageIcon Input = new ImageIcon("image/noselected/Input.png");
	ImageIcon Input_s = new ImageIcon("image/selected/Input_s.png");
	ImageIcon Mark = new ImageIcon("image/PCOP.png");
	
	private Food_Stock_DAO fsDao = new Food_Stock_DAO();
	private Input_Come_DAO dao = new Input_Come_DAO();
	private Input_Come_DTO dto = new Input_Come_DTO();

	//������
	public Manage_InOut_Insert() {
		setFont(new Font("����", Font.PLAIN, 10)); // �۾�ü
		setTitle("����/���� ���� �Է�");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 150, 400, 460); // �տ� �� ���� ������ ���� ��ġ, �ڿ� �� ���� ������ ������!
		panel_MII = new JPanel(); // ������ ������ ��ü �ʱ�ȭ
		panel_MII.setBorder(new EmptyBorder(5, 5, 5, 5));  //�����¿� ��¦�� ���� �ִ°ž�
		panel_MII.setLayout(new BorderLayout(0, 0));  //�ֶ� Layout �������ش�
		setContentPane(panel_MII);  //panel_MII�� ���ݱ��� �������ذɷ� ������
		setIconImage(Mark.getImage());  //���� ����̶� �۾� ǥ���� ������ ����

		panel_MII.setLayout(null); // ���� �ٽ� §��
		panel_MII.setBackground(new java.awt.Color(50, 50, 52));  //�ڿ� ��� ����

		/* Label - ���� */
		label = new JLabel("����");  //�ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L);  //��Ʈ
		label.setBounds(55, 10, 500, 40); // ��ġ�� ������
		label.setForeground(Color.WHITE);  //�۾��� �Ͼ��

		panel_MII.add(label);  //panel_MII�� label �߰�

		/* ComboBox - ���� ���� */
		com_Division = new JComboBox<String>();  //�޺��ڽ� �ʱ�ȭ�ε� String �����̾�
		com_Division.setFont(font_MB);  //��Ʈ
		com_Division.setBounds(123, 10, 90, 40); // ��ġ�� ������

		com_Division.addItem("����");  //�޺��ڽ� ���� �׸�
		com_Division.addItem("����");  //�޺��ڽ� ���� �׸�

		com_Division.addItemListener(new ItemListener() {  //���߿� �޺��ڽ� ���ÿ� ���� �Ͼ �̺�Ʈ
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();

			}
		});

		panel_MII.add(com_Division);  //panel_MID�� com_Division �߰�

		/* Label - ��¥ */
		label = new JLabel("��¥");  //�ʱ�ȭ�ε� �ؽ�Ʈ�� ��¥
		label.setFont(font_L);  //��Ʈ
		label.setBounds(55, 60, 500, 40); // ��ġ�� ������
		label.setForeground(Color.WHITE);  //�۾��� �Ͼ��

		panel_MII.add(label);  //panel_MII�� label �߰�

		/* ComboBox - �� ���� */
		com_Year = new JComboBox<String>();  //�޺��ڽ� �������ε� String �����̾�
		com_Year.setFont(font_MB);  //��Ʈ
		com_Year.setBounds(123, 60, 90, 40); // ��ġ�� ������

		//�������� ���� 5���� I_Year�� ���
		for (int i = 0, y = thisyear - 5; i < I_Year.length; i++, y++) {
			I_Year[i] = y;
		}

		com_Year.addItem(thisyear);
		panel_MII.add(com_Year);

		/* Label - �� */
		label = new JLabel("��");
		label.setFont(font_L);
		label.setBounds(218, 60, 100, 40); // ��ġ�� ������
		label.setForeground(Color.WHITE);

		panel_MII.add(label);

		/* ComboBox - �� ���� */
		com_Month = new JComboBox<String>();
		com_Month.setFont(font_MB);
		com_Month.setBounds(245, 60, 50, 40); // ��ġ�� ������
		com_Month.enable();

		for (int i = 0; i < I_Month.length; i++) {  //12���� �߰�
			I_Month[i] = i + 1;
		}
		
		for (int i = 0; i < I_Month.length; i++) {  //�� ���� com_Month�� ���������� �߰�
			com_Month.addItem(I_Month[i]);
		}
		com_Month.addItemListener(new ItemListener() {  //���߿� �޺��ڽ� ���ÿ� ���� �Ͼ �̺�Ʈ
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();

			}
		});

		panel_MII.add(com_Month);  //panel_MII�� com_Month �߰�

		/* Label - �� */
		label = new JLabel("��");  //�ʱ�ȭ�ε� �ؽ�Ʈ�� ��
		label.setFont(font_L);  //�۾�ü
		label.setBounds(300, 60, 50, 40); // ��ġ�� ������
		label.setForeground(Color.WHITE);  //�۾� �Ͼ��

		panel_MII.add(label);  //panel_MII�� label �߰�
		
		/* Label - ���� */
		label = new JLabel("����");  //�ʱ�ȭ�ε� �ؽ�Ʈ�� ����
		label.setFont(font_L);  //�۾�ü
		label.setBounds(55, 110, 50, 40); // ��ġ�� ������
		label.setForeground(Color.WHITE);  //�۾� �Ͼ��

		panel_MII.add(label);  //panel_MII�� label �߰�
		
		/* TextField - ��ǰ���� �����Է� */
		text_Insert = new JTextField(20);  //�ؽ�Ʈ�ʵ� ������
		text_Insert.setBounds(123, 160, 200, 40);  //��ġ�� ������
		text_Insert.setFont(font_MB);  //�۾�ü
		
		/*ComboBox - ���� ����*/
		com_Kind = new JComboBox<String>();  //�޺��ڽ� �������ε� String �����̾�
		com_Kind.setFont(font_MB);  //��Ʈ
		com_Kind.setBounds(123, 110, 200, 40);  //��ġ�� ������
		
		Kind = fsDao.GetFoodKind();
		
		com_Kind.addItem("�����Է�");
		for(int i = 0; i < Kind.length; i++) {  //���߿� String ���� DB���� �����ͼ� ���⼭ �� DB�׸�� �߰����ٰž�
			com_Kind.addItem(Kind[i]);  //�޺��ڽ��� ������ �߰�
		}
		com_Kind.addItemListener(new ItemListener() {  //�޺��ڽ� ���� �� �̺�Ʈ ���⼭
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();
				if(com_Kind.getSelectedItem().toString().equals("�����Է�")) {  //���� ���õǾ��ִ°� �����Է��̸�
					text_Insert.setText("");
					text_Insert.setEditable(true);  //�����Է��ϴ� �ؽ�Ʈ�ʵ尡 Ȱ��ȭ
				} else {
					text_Insert.setEditable(false);  //�ƴϸ� ��Ȱ��ȭ
					text_Insert.setText(com_Kind.getSelectedItem().toString());
				}
			}
		});
		
		panel_MII.add(text_Insert);  //panel_MII�� text_Insert �߰�
		panel_MII.add(com_Kind);  //panel_MII�� com_Kind �߰�
		
		/* Label - �ݾ� */
		label = new JLabel("�ݾ�");  //�ʱ�ȭ�ε� �ؽ�Ʈ�� �ݾ�
		label.setFont(font_L);  //��Ʈ
		label.setBounds(55, 210, 100, 40);  //��ġ�� ������
		label.setForeground(Color.WHITE);  //�۾� �Ͼ��
		
		panel_MII.add(label);  //panel_MII�� label �߰�
		
		/* TextField - �Ǹűݾ� �Է� */
		text_Price = new JTextField();  //�ؽ�Ʈ�ʵ� ������
		text_Price.setBounds(123, 210, 170, 40);  //��ġ�� ������
		text_Price.setFont(font_MB);  //�۾�ü
		
		panel_MII.add(text_Price);  //panel_MII�� text_Price �߰�
		
		/* Label - �� */
		label = new JLabel("��");  //�ʱ�ȭ�ε� �ؽ�Ʈ�� ��
		label.setFont(font_L);  //�۾�ü
		label.setBounds(300, 210, 50, 40);  //��ġ�� ������
		label.setForeground(Color.WHITE);  //�۾� �Ͼ��
		
		panel_MII.add(label);  //panel_MII�� label �߰�
		
		/* Label - ��� */
		label = new JLabel("���");  //�ʱ�ȭ�ε� �ؽ�Ʈ�� ���
		label.setFont(font_L);  //�۾�ü
		label.setBounds(55, 260, 500, 40);  //��ġ�� ������
		label.setForeground(Color.WHITE);  //�۾� �Ͼ��
		
		panel_MII.add(label);  //panel_MII�� label �߰�
		
		/* TextField - ���� �Է� */
		text_Memo = new JTextArea();  //�ؽ�Ʈ�ʵ� ������
		text_Memo.setBounds(123, 260, 200, 100);  //��ġ�� ������
		text_Memo.setFont(font_MB);  //�۾�ü
		
		panel_MII.add(text_Memo);  //panel_MII�� text_Memo �߰�
		
		/* Button - �Է� ��ư */
		btn_Insert = new JButton(Input);  //��ư ������, �ƹ��͵� ������ �� �̹��� Input
		btn_Insert.setSelectedIcon(Input_s);  //������ �� �̹��� Input_s....�� Input�� ������
		btn_Insert.setRolloverIcon(Input_s);  //���콺 �÷��� �� �̹��� Input_s
		btn_Insert.setBorderPainted(false);  //�׵θ� ���
		btn_Insert.setFocusPainted(false);  //�� �ٸ��� ������ ����
		btn_Insert.setContentAreaFilled(false);  //�̰� �𸣰ھ� �׳� �ؾߵȴ�
		btn_Insert.setBounds(123, 370, 140, 40);  //��ġ�� ������
		btn_Insert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(text_Insert.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "������ �Է����ּ���", "����", JOptionPane.INFORMATION_MESSAGE);
				} else if(text_Price.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "������ �Է����ּ���", "����", JOptionPane.INFORMATION_MESSAGE);
				} else {
					dto.setC_L_KIND(com_Division.getSelectedItem().toString());
					dto.setC_DATE(com_Year.getSelectedItem().toString() + "-" + com_Month.getSelectedItem().toString() + "-01");
					dto.setC_S_KIND(text_Insert.getText().toString());
					dto.setC_PRICE(Integer.parseInt(text_Price.getText().toString()));
					dto.setC_MEMO(text_Memo.getText().toString());
					boolean result = dao.InsertCome(dto);
					if (result) {
						JOptionPane.showMessageDialog(null, "�Է� �Ǿ����ϴ�", "�Է� ����",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "�Է¿� �����Ͽ����ϴ�", "�Է� ����",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		
		panel_MII.add(btn_Insert);  //panel_MII�� btn_Insert �߰�
	}
}
