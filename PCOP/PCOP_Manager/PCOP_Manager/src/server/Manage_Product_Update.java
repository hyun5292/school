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
	Font font_L = new Font("맑은 고딕", Font.BOLD, 23);
	Font font_MB = new Font("맑은 고딕", Font.BOLD, 18);
	Font font_S = new Font("맑은 고딕", Font.BOLD, 20);
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
		setFont(new Font("돋움", Font.PLAIN, 10)); // 글씨체
		setTitle("상품 수정");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 50, 480, 625); // 앞에 두 개는 프레임 시작 위치, 뒤에 두 개는 프레임 사이즈!
		panel_MP = new JPanel(); // 위에서 선언한 객체 초기화
		panel_MP.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_MP.setLayout(new BorderLayout(0, 0));
		setContentPane(panel_MP);
		setIconImage(Mark.getImage()); // 좌측 상단이랑 작업 표시줄 아이콘 설정

		panel_MP.setLayout(null); // 판을 다시 짠다
		panel_MP.setBackground(new java.awt.Color(50, 50, 52));
		setResizable(false);

		/* Label - 상품 */
		label = new JLabel("상품명");
		label.setFont(font_L);
		label.setBounds(20, 15, 150, 40); // 위치랑 사이즈
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.RIGHT);

		panel_MP.add(label);

		/* label - 상품명 */
		label = new JLabel(dto.getFS_PRODUCT());
		label.setFont(font_L);
		label.setBounds(190, 15, 200, 40); // 위치랑 사이즈
		label.setForeground(Color.WHITE);

		panel_MP.add(label);

		/* Label - 종류 */
		label = new JLabel("종류");
		label.setFont(font_L);
		label.setBounds(20, 70, 150, 40); // 위치랑 사이즈
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.RIGHT);

		panel_MP.add(label);

		/* ComboBox - 종류 선택 */
		combo_Product = new JComboBox<String>();
		combo_Product.setFont(font_MB);
		combo_Product.setBounds(190, 70, 120, 40); // 위치랑 사이즈

		Product = dao.GetFoodKind();

		combo_Product.addItem("직접입력");
		for (int i = 0; i < Product.length; i++) {
			combo_Product.addItem(Product[i]);
		}
		combo_Product.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();
				if (combo_Product.getSelectedItem().toString().equals("직접입력")) {
					text_Kind.setEditable(true);
				} else {
					text_Kind.setEditable(false);
					text_Kind.setText(combo_Product.getSelectedItem().toString());
				}
			}
		});
		panel_MP.add(combo_Product);

		/* TextField - 종류 */
		text_Kind = new JTextField(dto.getFS_KIND()); // 텍스트필드 재정의
		text_Kind.setBounds(320, 70, 120, 40); // 위치랑 사이즈
		text_Kind.setFont(font_MB); // 글씨체

		panel_MP.add(text_Kind); // 판넬에 textfield 추가

		/* Label - 판매금액 */
		label = new JLabel("판매금액");
		label.setFont(font_L);
		label.setBounds(20, 120, 150, 40); // 위치랑 사이즈
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.RIGHT);

		panel_MP.add(label);

		/* TextField - 판매금액 입력 */
		text_Count = new JTextField(dto.getFS_PRICE() + ""); // 텍스트필드 재정의
		text_Count.setBounds(190, 120, 200, 40); // 위치랑 사이즈
		text_Count.setFont(font_MB); // 글씨체

		panel_MP.add(text_Count); // 판넬에 textfield 추가

		/* Label - 수정 재고량 */
		label = new JLabel("원");
		label.setFont(font_L);
		label.setBounds(410, 120, 50, 40); // 위치랑 사이즈
		label.setForeground(Color.WHITE);

		panel_MP.add(label);

		/* Label - 이미지 */
		label = new JLabel("이미지");
		label.setFont(font_L);
		label.setBounds(20, 170, 150, 40); // 위치랑 사이즈
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
			labelImg.setBounds(110, 250, 250, 250); // 위치랑 사이즈
			labelImg.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
			labelImg.setForeground(Color.WHITE);

			panel_MP.add(labelImg);
		} catch (Exception e) {
			System.out.println(e);
		}

		/* Button - 이미지 찾기 버튼 */
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

		/* TextField - 이미지 입력 */
		text_Image = new JTextField(dto.getFS_IMG().toString()); // 텍스트필드 재정의
		text_Image.setBounds(190, 170, 150, 40); // 위치랑 사이즈
		text_Image.setFont(font_MB); // 글씨체
		text_Image.setEditable(false);

		panel_MP.add(text_Image); // 판넬에 textfield 추가

		/* Button - 재고 수정 버튼 */
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
					JOptionPane.showMessageDialog(null, "종류를 입력하세요", "오류", JOptionPane.INFORMATION_MESSAGE);
				} else if (fsDto.getFS_PRICE() == 0) {
					JOptionPane.showMessageDialog(null, "가격를 입력하세요", "오류", JOptionPane.INFORMATION_MESSAGE);
				} else if (fsDto.getFS_IMG().equals("")) {
					JOptionPane.showMessageDialog(null, "이미지를 입력하세요", "오류", JOptionPane.INFORMATION_MESSAGE);
				} else {
					boolean result = dao.ModifyStock(fsDto);
					if (result) {
						JOptionPane.showMessageDialog(null, "상품이 수정되었습니다", "상품수정 성공", JOptionPane.INFORMATION_MESSAGE);
						Manage_Stock ms = new Manage_Stock(panel);
						ms.Refresh();
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "수정에 실패하였습니다", "상품수정 실패", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

		panel_MP.add(btn_Reset);
	}
}
