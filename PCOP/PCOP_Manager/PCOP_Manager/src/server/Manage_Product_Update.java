package server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import pcop.dao.Food_Stock_DAO;
import pcop.dto.Food_Stock_DTO;

public class Manage_Product_Update extends JFrame {
	Font font_L = new Font("���� ���", Font.BOLD, 23);
	Font font_MB = new Font("���� ���", Font.BOLD, 18);
	Font font_S = new Font("���� ���", Font.BOLD, 20);
	JPanel panel_MP = new JPanel();
	String[] Kind, Product, Price, Count;
	JLabel label, labelImg;
	JComboBox combo_Kind, combo_Product, combo_Price, combo_Count;
	JButton btn_Reset, btn_Search;
	JTextField text_Kind, text_Count, text_Image;
	ImageIcon Search = new ImageIcon("image/noselected/Search.png");
	ImageIcon Search_s = new ImageIcon("image/selected/Search_s.png");
	ImageIcon Reset = new ImageIcon("image/noselected/Reset.png");
	ImageIcon Reset_s = new ImageIcon("image/selected/Reset_s.png");
	ImageIcon Mark = new ImageIcon("image/PCOP.png");
	JFileChooser chooser = null;

	private Food_Stock_DAO dao = new Food_Stock_DAO();
	private Food_Stock_DTO fsDto = new Food_Stock_DTO();

	public Manage_Product_Update(Food_Stock_DTO dto, JPanel panel) {
		setFont(new Font("����", Font.PLAIN, 10)); // �۾�ü
		setTitle("��ǰ ����");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 50, 480, 625); // �տ� �� ���� ������ ���� ��ġ, �ڿ� �� ���� ������ ������!
		panel_MP = new JPanel(); // ������ ������ ��ü �ʱ�ȭ
		panel_MP.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_MP.setLayout(new BorderLayout(0, 0));
		setContentPane(panel_MP);
		setIconImage(Mark.getImage()); // ���� ����̶� �۾� ǥ���� ������ ����

		panel_MP.setLayout(null); // ���� �ٽ� §��
		panel_MP.setBackground(new java.awt.Color(50, 50, 52));
		setResizable(false);

		/* Label - ��ǰ */
		label = new JLabel("��ǰ��");
		label.setFont(font_L);
		label.setBounds(20, 15, 150, 40); // ��ġ�� ������
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.RIGHT);

		panel_MP.add(label);

		/* label - ��ǰ�� */
		label = new JLabel(dto.getFS_PRODUCT());
		label.setFont(font_L);
		label.setBounds(190, 15, 200, 40); // ��ġ�� ������
		label.setForeground(Color.WHITE);

		panel_MP.add(label);

		/* Label - ���� */
		label = new JLabel("����");
		label.setFont(font_L);
		label.setBounds(20, 70, 150, 40); // ��ġ�� ������
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.RIGHT);

		panel_MP.add(label);

		/* ComboBox - ���� ���� */
		combo_Product = new JComboBox<String>();
		combo_Product.setFont(font_MB);
		combo_Product.setBounds(190, 70, 120, 40); // ��ġ�� ������

		Product = dao.GetFoodKind();

		combo_Product.addItem("�����Է�");
		for (int i = 0; i < Product.length; i++) {
			combo_Product.addItem(Product[i]);
		}
		combo_Product.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();
				if (combo_Product.getSelectedItem().toString().equals("�����Է�")) {
					text_Kind.setEditable(true);
				} else {
					text_Kind.setEditable(false);
					text_Kind.setText(combo_Product.getSelectedItem().toString());
				}
			}
		});
		panel_MP.add(combo_Product);

		/* TextField - ���� */
		text_Kind = new JTextField(dto.getFS_KIND()); // �ؽ�Ʈ�ʵ� ������
		text_Kind.setBounds(320, 70, 120, 40); // ��ġ�� ������
		text_Kind.setFont(font_MB); // �۾�ü

		panel_MP.add(text_Kind); // �ǳڿ� textfield �߰�

		/* Label - �Ǹűݾ� */
		label = new JLabel("�Ǹűݾ�");
		label.setFont(font_L);
		label.setBounds(20, 120, 150, 40); // ��ġ�� ������
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.RIGHT);

		panel_MP.add(label);

		/* TextField - �Ǹűݾ� �Է� */
		text_Count = new JTextField(dto.getFS_PRICE() + ""); // �ؽ�Ʈ�ʵ� ������
		text_Count.setBounds(190, 120, 200, 40); // ��ġ�� ������
		text_Count.setFont(font_MB); // �۾�ü

		panel_MP.add(text_Count); // �ǳڿ� textfield �߰�

		/* Label - ���� ��� */
		label = new JLabel("��");
		label.setFont(font_L);
		label.setBounds(410, 120, 50, 40); // ��ġ�� ������
		label.setForeground(Color.WHITE);

		panel_MP.add(label);

		/* Label - �̹��� */
		label = new JLabel("�̹���");
		label.setFont(font_L);
		label.setBounds(20, 170, 150, 40); // ��ġ�� ������
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.RIGHT);

		panel_MP.add(label);

		try {
			File f = new File(dto.getFS_IMG().toString());
			BufferedImage img_before = ImageIO.read(f);
			ImageIcon img_before_icon = new ImageIcon(img_before);
			Image img_before_i = img_before_icon.getImage();
			Image img_before_ch = img_before_i.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
			ImageIcon img = new ImageIcon(img_before_ch);

			labelImg = new JLabel(img);
			labelImg.setFont(font_L);
			labelImg.setBounds(110, 250, 250, 250); // ��ġ�� ������
			labelImg.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ����
			labelImg.setForeground(Color.WHITE);

			panel_MP.add(labelImg);
		} catch (Exception e) {
			System.out.println(e);
		}

		/* Button - �̹��� ã�� ��ư */
		btn_Search = new JButton(Search);
		btn_Search.setSelectedIcon(Search_s);
		btn_Search.setRolloverIcon(Search_s);
		btn_Search.setBorderPainted(false);
		btn_Search.setFocusPainted(false);
		btn_Search.setContentAreaFilled(false);
		btn_Search.setBounds(340, 170, 110, 43);
		btn_Search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
				chooser.setFileFilter(filter);
				chooser.addChoosableFileFilter(filter);
				int ret = chooser.showOpenDialog(null);
				if (ret == JFileChooser.APPROVE_OPTION) {
					String pathName = chooser.getSelectedFile().getPath();
					text_Image.setText(pathName);

					try {
						File f = new File(pathName);
						BufferedImage img_before = ImageIO.read(f);
						ImageIcon img_before_icon = new ImageIcon(img_before);
						Image img_before_i = img_before_icon.getImage();
						Image img_before_ch = img_before_i.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
						ImageIcon img = new ImageIcon(img_before_ch);

						labelImg.setIcon(img);
					} catch (Exception e1) {
						System.out.println(e1);
					}
				}
			}
		});

		panel_MP.add(btn_Search);

		/* TextField - �̹��� �Է� */
		text_Image = new JTextField(dto.getFS_IMG().toString()); // �ؽ�Ʈ�ʵ� ������
		text_Image.setBounds(190, 170, 150, 40); // ��ġ�� ������
		text_Image.setFont(font_MB); // �۾�ü
		text_Image.setEditable(false);

		panel_MP.add(text_Image); // �ǳڿ� textfield �߰�

		/* Button - ��� ���� ��ư */
		btn_Reset = new JButton(Reset);
		btn_Reset.setSelectedIcon(Reset_s);
		btn_Reset.setRolloverIcon(Reset_s);
		btn_Reset.setBorderPainted(false);
		btn_Reset.setFocusPainted(false);
		btn_Reset.setContentAreaFilled(false);
		btn_Reset.setBounds(160, 530, 140, 40);
		btn_Reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fsDto = new Food_Stock_DTO();
				fsDto.setFS_PRODUCT(dto.getFS_PRODUCT());
				fsDto.setFS_KIND(text_Kind.getText());
				fsDto.setFS_PRICE(Integer.parseInt(text_Count.getText()));
				fsDto.setFS_IMG(text_Image.getText().toString());

				if (fsDto.getFS_KIND().equals("")) {
					JOptionPane.showMessageDialog(null, "������ �Է��ϼ���", "����", JOptionPane.INFORMATION_MESSAGE);
				} else if (fsDto.getFS_PRICE() == 0) {
					JOptionPane.showMessageDialog(null, "���ݸ� �Է��ϼ���", "����", JOptionPane.INFORMATION_MESSAGE);
				} else if (fsDto.getFS_IMG().equals("")) {
					JOptionPane.showMessageDialog(null, "�̹����� �Է��ϼ���", "����", JOptionPane.INFORMATION_MESSAGE);
				} else {
					boolean result = dao.ModifyStock(fsDto);
					if (result) {
						JOptionPane.showMessageDialog(null, "��ǰ�� �����Ǿ����ϴ�", "��ǰ���� ����", JOptionPane.INFORMATION_MESSAGE);
						Manage_Stock ms = new Manage_Stock(panel);
						ms.Refresh();
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "������ �����Ͽ����ϴ�", "��ǰ���� ����", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

		panel_MP.add(btn_Reset);
	}
}
