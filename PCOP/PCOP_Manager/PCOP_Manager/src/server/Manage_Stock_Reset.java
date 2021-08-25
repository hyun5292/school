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
	Font font_L = new Font("맑은 고딕", Font.BOLD, 23);
	Font font_MB = new Font("맑은 고딕", Font.BOLD, 18);
	Font font_S = new Font("맑은 고딕", Font.BOLD, 20);
	JPanel panel_MSR = new JPanel();
	JLabel label, lbl_Product;
	JButton btn_Reset;
	JTextField text_Price, text_Count;
	ImageIcon Reset = new ImageIcon("image/noselected/Reset.png");
	ImageIcon Reset_s = new ImageIcon("image/selected/Reset_s.png");
	ImageIcon Mark = new ImageIcon("image/PCOP.png");
	
	private Food_Stock_DAO fsDao = new Food_Stock_DAO();
	
	public Manage_Stock_Reset(Food_Stock_DTO dto, JPanel panel) {
		setFont(new Font("돋움", Font.PLAIN, 10));  //글씨체
		setTitle("재고 수정");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 150, 420, 300);  //앞에 두 개는 프레임 시작 위치, 뒤에 두 개는 프레임 사이즈!
		panel_MSR = new JPanel();  //위에서 선언한 객체 초기화
		panel_MSR.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_MSR.setLayout(new BorderLayout(0, 0));
		setContentPane(panel_MSR);
		setIconImage(Mark.getImage());  //좌측 상단이랑 작업 표시줄 아이콘 설정
		
		panel_MSR.setLayout(null);  //판을 다시 짠다
		panel_MSR.setBackground(new java.awt.Color(50, 50, 52));
	
		/* Label - 상품 */
		label = new JLabel("상품명");
		label.setFont(font_L);
		label.setBounds(20, 15, 150, 40);  //위치랑 사이즈
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.RIGHT);
		
		panel_MSR.add(label);
		
		/* Label - 상품 */
		lbl_Product = new JLabel(dto.getFS_PRODUCT().toString());
		lbl_Product.setFont(font_L);
		lbl_Product.setBounds(190, 15, 150, 40);  //위치랑 사이즈
		lbl_Product.setForeground(Color.WHITE);
		lbl_Product.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		
		panel_MSR.add(lbl_Product);
		
		/* Label - 원래 재고량 */
		label = new JLabel("원래 재고량");
		label.setFont(font_L);
		label.setBounds(20, 70, 150, 40);  //위치랑 사이즈
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.RIGHT);
		
		panel_MSR.add(label);
		
		/* TextField - 원래 재고량 */
		text_Price = new JTextField();  //텍스트필드 재정의
		text_Price.setBounds(190, 70, 150, 40);  //위치랑 사이즈
		text_Price.setFont(font_MB);  //글씨체
		text_Price.setEditable(false);
		text_Price.setText(dto.getFS_NUM()+"");
		
		panel_MSR.add(text_Price);  //판넬에 textfield 추가
		
		/* Label - 수정 재고량 */
		label = new JLabel("개");
		label.setFont(font_L);
		label.setBounds(350, 70, 50, 40);  //위치랑 사이즈
		label.setForeground(Color.WHITE);
		
		panel_MSR.add(label);
				
		/* Label - 수정 재고량 */
		label = new JLabel("수정 재고량");
		label.setFont(font_L);
		label.setBounds(20, 125, 150, 40);  //위치랑 사이즈
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.RIGHT);
		
		panel_MSR.add(label);
		
		/* TextField - 개수 입력 */
		text_Count = new JTextField(20);  //텍스트필드 재정의
		text_Count.setBounds(190, 125, 150, 40);  //위치랑 사이즈
		text_Count.setFont(font_MB);  //글씨체
		
		panel_MSR.add(text_Count);  //판넬에 textfield 추가
		
		/* Label - 수정 재고량 */
		label = new JLabel("개");
		label.setFont(font_L);
		label.setBounds(350, 125, 50, 40);  //위치랑 사이즈
		label.setForeground(Color.WHITE);
		
		panel_MSR.add(label);
		
		/* Button - 재고 수정 버튼 */
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
				String Answer = JOptionPane.showInputDialog(null, "재고수정을 원하시면 \'재고수정\'을 입력하세요", "재고수정",
						JOptionPane.INFORMATION_MESSAGE);
				if (Answer == null) {
					JOptionPane.showMessageDialog(null, "재고수정을 중단하였습니다", "재고수정 실패", JOptionPane.INFORMATION_MESSAGE);
				} else if(Answer.equals("재고수정")) {
					boolean  result = fsDao.ResetNum(lbl_Product.getText(), Integer.parseInt(text_Count.getText()));
					if (result) {
						JOptionPane.showMessageDialog(null, "재고가 수정되었습니다", "재고수정 성공", JOptionPane.INFORMATION_MESSAGE);
						Manage_Stock parent = new Manage_Stock(panel);
						parent.Refresh();
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "수정에 실패하였습니다", "재고수정 실패", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "재고수정을 중단하였습니다", "재고수정 실패", JOptionPane.INFORMATION_MESSAGE);
				}
					
			}
		});
		
		panel_MSR.add(btn_Reset);
	}
}
