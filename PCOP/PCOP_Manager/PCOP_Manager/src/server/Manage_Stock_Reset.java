package server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import pcop.dao.Food_Stock_DAO;
import pcop.dto.Food_Stock_DTO;

public class Manage_Stock_Reset extends JFrame {
	Font font_L = new Font("���� ���", Font.BOLD, 23);
	Font font_MB = new Font("���� ���", Font.BOLD, 18);
	Font font_S = new Font("���� ���", Font.BOLD, 20);
	JPanel panel_MSR = new JPanel();
	JLabel label, lbl_Product;
	JButton btn_Reset;
	JTextField text_Price, text_Count;
	ImageIcon Reset = new ImageIcon("image/noselected/Reset.png");
	ImageIcon Reset_s = new ImageIcon("image/selected/Reset_s.png");
	ImageIcon Mark = new ImageIcon("image/PCOP.png");
	
	private Food_Stock_DAO fsDao = new Food_Stock_DAO();
	
	public Manage_Stock_Reset(Food_Stock_DTO dto, JPanel panel) {
		setFont(new Font("����", Font.PLAIN, 10));  //�۾�ü
		setTitle("��� ����");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 150, 420, 300);  //�տ� �� ���� ������ ���� ��ġ, �ڿ� �� ���� ������ ������!
		panel_MSR = new JPanel();  //������ ������ ��ü �ʱ�ȭ
		panel_MSR.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_MSR.setLayout(new BorderLayout(0, 0));
		setContentPane(panel_MSR);
		setIconImage(Mark.getImage());  //���� ����̶� �۾� ǥ���� ������ ����
		
		panel_MSR.setLayout(null);  //���� �ٽ� §��
		panel_MSR.setBackground(new java.awt.Color(50, 50, 52));
	
		/* Label - ��ǰ */
		label = new JLabel("��ǰ��");
		label.setFont(font_L);
		label.setBounds(20, 15, 150, 40);  //��ġ�� ������
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.RIGHT);
		
		panel_MSR.add(label);
		
		/* Label - ��ǰ */
		lbl_Product = new JLabel(dto.getFS_PRODUCT().toString());
		lbl_Product.setFont(font_L);
		lbl_Product.setBounds(190, 15, 150, 40);  //��ġ�� ������
		lbl_Product.setForeground(Color.WHITE);
		lbl_Product.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		
		panel_MSR.add(lbl_Product);
		
		/* Label - ���� ��� */
		label = new JLabel("���� ���");
		label.setFont(font_L);
		label.setBounds(20, 70, 150, 40);  //��ġ�� ������
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.RIGHT);
		
		panel_MSR.add(label);
		
		/* TextField - ���� ��� */
		text_Price = new JTextField();  //�ؽ�Ʈ�ʵ� ������
		text_Price.setBounds(190, 70, 150, 40);  //��ġ�� ������
		text_Price.setFont(font_MB);  //�۾�ü
		text_Price.setEditable(false);
		text_Price.setText(dto.getFS_NUM()+"");
		
		panel_MSR.add(text_Price);  //�ǳڿ� textfield �߰�
		
		/* Label - ���� ��� */
		label = new JLabel("��");
		label.setFont(font_L);
		label.setBounds(350, 70, 50, 40);  //��ġ�� ������
		label.setForeground(Color.WHITE);
		
		panel_MSR.add(label);
				
		/* Label - ���� ��� */
		label = new JLabel("���� ���");
		label.setFont(font_L);
		label.setBounds(20, 125, 150, 40);  //��ġ�� ������
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.RIGHT);
		
		panel_MSR.add(label);
		
		/* TextField - ���� �Է� */
		text_Count = new JTextField(20);  //�ؽ�Ʈ�ʵ� ������
		text_Count.setBounds(190, 125, 150, 40);  //��ġ�� ������
		text_Count.setFont(font_MB);  //�۾�ü
		
		panel_MSR.add(text_Count);  //�ǳڿ� textfield �߰�
		
		/* Label - ���� ��� */
		label = new JLabel("��");
		label.setFont(font_L);
		label.setBounds(350, 125, 50, 40);  //��ġ�� ������
		label.setForeground(Color.WHITE);
		
		panel_MSR.add(label);
		
		/* Button - ��� ���� ��ư */
		btn_Reset = new JButton(Reset);
		btn_Reset.setSelectedIcon(Reset_s);
		btn_Reset.setRolloverIcon(Reset_s);
		btn_Reset.setBorderPainted(false);
		btn_Reset.setFocusPainted(false);
		btn_Reset.setContentAreaFilled(false);
		btn_Reset.setBounds(120, 190, 140, 40);
		btn_Reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Answer = JOptionPane.showInputDialog(null, "�������� ���Ͻø� \'������\'�� �Է��ϼ���", "������",
						JOptionPane.INFORMATION_MESSAGE);
				if (Answer == null) {
					JOptionPane.showMessageDialog(null, "�������� �ߴ��Ͽ����ϴ�", "������ ����", JOptionPane.INFORMATION_MESSAGE);
				} else if(Answer.equals("������")) {
					boolean  result = fsDao.ResetNum(lbl_Product.getText(), Integer.parseInt(text_Count.getText()));
					if (result) {
						JOptionPane.showMessageDialog(null, "��� �����Ǿ����ϴ�", "������ ����", JOptionPane.INFORMATION_MESSAGE);
						Manage_Stock parent = new Manage_Stock(panel);
						parent.Refresh();
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "������ �����Ͽ����ϴ�", "������ ����", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "�������� �ߴ��Ͽ����ϴ�", "������ ����", JOptionPane.INFORMATION_MESSAGE);
				}
					
			}
		});
		
		panel_MSR.add(btn_Reset);
	}
}
