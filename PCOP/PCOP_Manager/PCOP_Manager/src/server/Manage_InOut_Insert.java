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
	//글씨체
	Font font_L = new Font("맑은 고딕", Font.BOLD, 23);
	Font font_MB = new Font("맑은 고딕", Font.BOLD, 18);
	Font font_S = new Font("맑은 고딕", Font.BOLD, 20);
	JPanel panel_MII = new JPanel();
	JLabel label = new JLabel();
	JTextField text_Insert, text_Price;
	JTextArea text_Memo;
	JButton btn_Insert;
	JComboBox com_Division, com_Year, com_Month, com_Kind;
	String[] Kind, S_Year, S_Month;
	Calendar c = Calendar.getInstance();  //날짜
	Integer[] I_Year = new Integer[6];  //올해포함 전 6년
	Integer[] I_Month = new Integer[12];  //12개월
	int thisyear = c.get(Calendar.YEAR);  //올해 년도 가져오는거
	//이미지
	ImageIcon Input = new ImageIcon("image/noselected/Input.png");
	ImageIcon Input_s = new ImageIcon("image/selected/Input_s.png");
	ImageIcon Mark = new ImageIcon("image/PCOP.png");
	
	private Food_Stock_DAO fsDao = new Food_Stock_DAO();
	private Input_Come_DAO dao = new Input_Come_DAO();
	private Input_Come_DTO dto = new Input_Come_DTO();

	//생성자
	public Manage_InOut_Insert() {
		setFont(new Font("돋움", Font.PLAIN, 10)); // 글씨체
		setTitle("매출/지출 직접 입력");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 150, 400, 460); // 앞에 두 개는 프레임 시작 위치, 뒤에 두 개는 프레임 사이즈!
		panel_MII = new JPanel(); // 위에서 선언한 객체 초기화
		panel_MII.setBorder(new EmptyBorder(5, 5, 5, 5));  //상하좌우 살짝씩 여유 주는거야
		panel_MII.setLayout(new BorderLayout(0, 0));  //멀라 Layout 설정해준대
		setContentPane(panel_MII);  //panel_MII을 지금까지 설정해준걸로 세팅해
		setIconImage(Mark.getImage());  //좌측 상단이랑 작업 표시줄 아이콘 설정

		panel_MII.setLayout(null); // 판을 다시 짠다
		panel_MII.setBackground(new java.awt.Color(50, 50, 52));  //뒤에 배경 색상

		/* Label - 구분 */
		label = new JLabel("구분");  //초기화인데 텍스트가 구분
		label.setFont(font_L);  //폰트
		label.setBounds(55, 10, 500, 40); // 위치랑 사이즈
		label.setForeground(Color.WHITE);  //글씨가 하얀색

		panel_MII.add(label);  //panel_MII에 label 추가

		/* ComboBox - 구분 선택 */
		com_Division = new JComboBox<String>();  //콤보박스 초기화인데 String 형식이야
		com_Division.setFont(font_MB);  //폰트
		com_Division.setBounds(123, 10, 90, 40); // 위치랑 사이즈

		com_Division.addItem("매출");  //콤보박스 매출 항목
		com_Division.addItem("지출");  //콤보박스 지출 항목

		com_Division.addItemListener(new ItemListener() {  //나중에 콤보박스 선택에 따라 일어날 이벤트
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();

			}
		});

		panel_MII.add(com_Division);  //panel_MID에 com_Division 추가

		/* Label - 날짜 */
		label = new JLabel("날짜");  //초기화인데 텍스트가 날짜
		label.setFont(font_L);  //폰트
		label.setBounds(55, 60, 500, 40); // 위치랑 사이즈
		label.setForeground(Color.WHITE);  //글씨가 하얀색

		panel_MII.add(label);  //panel_MII에 label 추가

		/* ComboBox - 년 선택 */
		com_Year = new JComboBox<String>();  //콤보박스 재정의인데 String 형식이야
		com_Year.setFont(font_MB);  //폰트
		com_Year.setBounds(123, 60, 90, 40); // 위치랑 사이즈

		//올해포함 이전 5년을 I_Year에 담기
		for (int i = 0, y = thisyear - 5; i < I_Year.length; i++, y++) {
			I_Year[i] = y;
		}

		com_Year.addItem(thisyear);
		panel_MII.add(com_Year);

		/* Label - 년 */
		label = new JLabel("년");
		label.setFont(font_L);
		label.setBounds(218, 60, 100, 40); // 위치랑 사이즈
		label.setForeground(Color.WHITE);

		panel_MII.add(label);

		/* ComboBox - 월 선택 */
		com_Month = new JComboBox<String>();
		com_Month.setFont(font_MB);
		com_Month.setBounds(245, 60, 50, 40); // 위치랑 사이즈
		com_Month.enable();

		for (int i = 0; i < I_Month.length; i++) {  //12월을 추가
			I_Month[i] = i + 1;
		}
		
		for (int i = 0; i < I_Month.length; i++) {  //각 월을 com_Month의 아이템으로 추가
			com_Month.addItem(I_Month[i]);
		}
		com_Month.addItemListener(new ItemListener() {  //나중에 콤보박스 선택에 따라 일어날 이벤트
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();

			}
		});

		panel_MII.add(com_Month);  //panel_MII에 com_Month 추가

		/* Label - 월 */
		label = new JLabel("월");  //초기화인데 텍스트가 월
		label.setFont(font_L);  //글씨체
		label.setBounds(300, 60, 50, 40); // 위치랑 사이즈
		label.setForeground(Color.WHITE);  //글씨 하얀색

		panel_MII.add(label);  //panel_MII에 label 추가
		
		/* Label - 종류 */
		label = new JLabel("종류");  //초기화인데 텍스트가 종류
		label.setFont(font_L);  //글씨체
		label.setBounds(55, 110, 50, 40); // 위치랑 사이즈
		label.setForeground(Color.WHITE);  //글씨 하얀색

		panel_MII.add(label);  //panel_MII에 label 추가
		
		/* TextField - 상품종류 직접입력 */
		text_Insert = new JTextField(20);  //텍스트필드 재정의
		text_Insert.setBounds(123, 160, 200, 40);  //위치랑 사이즈
		text_Insert.setFont(font_MB);  //글씨체
		
		/*ComboBox - 종류 선택*/
		com_Kind = new JComboBox<String>();  //콤보박스 재정의인데 String 형식이야
		com_Kind.setFont(font_MB);  //폰트
		com_Kind.setBounds(123, 110, 200, 40);  //위치랑 사이즈
		
		Kind = fsDao.GetFoodKind();
		
		com_Kind.addItem("직접입력");
		for(int i = 0; i < Kind.length; i++) {  //나중에 String 말고 DB에서 가져와서 여기서 그 DB항목들 추가해줄거야
			com_Kind.addItem(Kind[i]);  //콤보박스에 아이템 추가
		}
		com_Kind.addItemListener(new ItemListener() {  //콤보박스 선택 시 이벤트 여기서
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();
				if(com_Kind.getSelectedItem().toString().equals("직접입력")) {  //지금 선택되어있는게 직접입력이면
					text_Insert.setText("");
					text_Insert.setEditable(true);  //직접입력하는 텍스트필드가 활성화
				} else {
					text_Insert.setEditable(false);  //아니면 비활성화
					text_Insert.setText(com_Kind.getSelectedItem().toString());
				}
			}
		});
		
		panel_MII.add(text_Insert);  //panel_MII에 text_Insert 추가
		panel_MII.add(com_Kind);  //panel_MII에 com_Kind 추가
		
		/* Label - 금액 */
		label = new JLabel("금액");  //초기화인데 텍스트가 금액
		label.setFont(font_L);  //폰트
		label.setBounds(55, 210, 100, 40);  //위치랑 사이즈
		label.setForeground(Color.WHITE);  //글씨 하얀색
		
		panel_MII.add(label);  //panel_MII에 label 추가
		
		/* TextField - 판매금액 입력 */
		text_Price = new JTextField();  //텍스트필드 재정의
		text_Price.setBounds(123, 210, 170, 40);  //위치랑 사이즈
		text_Price.setFont(font_MB);  //글씨체
		
		panel_MII.add(text_Price);  //panel_MII에 text_Price 추가
		
		/* Label - 원 */
		label = new JLabel("원");  //초기화인데 텍스트가 원
		label.setFont(font_L);  //글씨체
		label.setBounds(300, 210, 50, 40);  //위치랑 사이즈
		label.setForeground(Color.WHITE);  //글씨 하얀색
		
		panel_MII.add(label);  //panel_MII에 label 추가
		
		/* Label - 비고 */
		label = new JLabel("비고");  //초기화인데 텍스트가 비고
		label.setFont(font_L);  //글씨체
		label.setBounds(55, 260, 500, 40);  //위치랑 사이즈
		label.setForeground(Color.WHITE);  //글씨 하얀색
		
		panel_MII.add(label);  //panel_MII에 label 추가
		
		/* TextField - 개수 입력 */
		text_Memo = new JTextArea();  //텍스트필드 재정의
		text_Memo.setBounds(123, 260, 200, 100);  //위치랑 사이즈
		text_Memo.setFont(font_MB);  //글씨체
		
		panel_MII.add(text_Memo);  //panel_MII에 text_Memo 추가
		
		/* Button - 입력 버튼 */
		btn_Insert = new JButton(Input);  //버튼 재정의, 아무것도 안했을 때 이미지 Input
		btn_Insert.setSelectedIcon(Input_s);  //눌렀을 떄 이미지 Input_s....왜 Input로 나오지
		btn_Insert.setRolloverIcon(Input_s);  //마우스 올렸을 때 이미지 Input_s
		btn_Insert.setBorderPainted(false);  //테두리 비워
		btn_Insert.setFocusPainted(false);  //뭐 꾸며져 있으면 지워
		btn_Insert.setContentAreaFilled(false);  //이건 모르겠어 그냥 해야된대
		btn_Insert.setBounds(123, 370, 140, 40);  //위치랑 사이즈
		btn_Insert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(text_Insert.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "종류를 입력해주세요", "오류", JOptionPane.INFORMATION_MESSAGE);
				} else if(text_Price.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "가격을 입력해주세요", "오류", JOptionPane.INFORMATION_MESSAGE);
				} else {
					dto.setC_L_KIND(com_Division.getSelectedItem().toString());
					dto.setC_DATE(com_Year.getSelectedItem().toString() + "-" + com_Month.getSelectedItem().toString() + "-01");
					dto.setC_S_KIND(text_Insert.getText().toString());
					dto.setC_PRICE(Integer.parseInt(text_Price.getText().toString()));
					dto.setC_MEMO(text_Memo.getText().toString());
					boolean result = dao.InsertCome(dto);
					if (result) {
						JOptionPane.showMessageDialog(null, "입력 되었습니다", "입력 성공",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "입력에 실패하였습니다", "입력 실패",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		
		panel_MII.add(btn_Insert);  //panel_MII에 btn_Insert 추가
	}
}
