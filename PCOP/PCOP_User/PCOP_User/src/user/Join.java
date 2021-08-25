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
	// 글씨체
	private Font font_L = new Font("맑은 고딕", Font.BOLD, 25);
	private Font font_MB = new Font("맑은 고딕", Font.BOLD, 18);
	private Font font_S = new Font("맑은 고딕", Font.BOLD, 20);
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
	private Calendar c = Calendar.getInstance(); // 날짜
	String[] year = new String[112];
	String[] month = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
	String[] date = new String[] { "31", "29", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31" };
	int thisyear = c.get(Calendar.YEAR) - 8;
	int thismonth = c.get(Calendar.MONTH);
	int thisdate = c.get(Calendar.DATE);

	/** dao,dto 가져오기 **/
	Member_DAO dao = new Member_DAO();
	Member_DTO dto = new Member_DTO();

	public Join() {
		setTitle("회원가입"); // 타이틀바 텍스트 설정
		setBounds(x / 2 + 50, y / 7, 500, 505); // 앞에 두 개는 프레임 시작 위치, 뒤에 두 개는 프레임 사이즈!
		panel = new JPanel(); // 위에서 선언한 객체 초기화
		panel.setBorder(new EmptyBorder(5, 5, 5, 5)); // 상하좌우 살짝씩 여유 주는거야
		panel.setLayout(new BorderLayout(0, 0)); // 멀라 Layout 설정해준대
		setContentPane(panel); // panel_MID을 지금까지 설정해준걸로 세팅해
		setIconImage(Mark.getImage()); // 좌측 상단이랑 작업 표시줄 아이콘 설정
		setResizable(false);
		panel.setBackground(new java.awt.Color(50, 50, 52)); // 뒤에 배경 블랙

		panel.setLayout(null); // 판을 다시 짠다

		/* Label - 아이디 */
		label = new JLabel("아이디"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(10, 20, 160, 40); // 위치랑 사이즈
		label.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/* TextField - ID 입력 */
		txt_ID = new JTextField(20); // 텍스트필드 재정의
		txt_ID.setBounds(190, 20, 160, 40); // 위치랑 사이즈
		txt_ID.setFont(font_MB); // 글씨체

		add(txt_ID); // panel에 textfield 추가

		/* Label - 이름 */
		label = new JLabel("이름"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(10, 75, 160, 40); // 위치랑 사이즈
		label.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/* TextField - 이름 입력 */
		txt_Name = new JTextField(20); // 텍스트필드 재정의
		txt_Name.setBounds(190, 75, 160, 40); // 위치랑 사이즈
		txt_Name.setFont(font_MB); // 글씨체

		add(txt_Name); // panel에 textfield 추가

		/* Label - 비밀번호 */
		label = new JLabel("비밀번호"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(10, 130, 160, 40); // 위치랑 사이즈
		label.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/* TextField - 비밀번호 입력 */
		txt_PW = new JPasswordField(20); // 텍스트필드 재정의
		txt_PW.setBounds(190, 130, 160, 40); // 위치랑 사이즈
		txt_PW.setFont(font_MB); // 글씨체

		add(txt_PW); // panel에 textfield 추가

		/* Label - 비밀번호 확인 */
		label = new JLabel("비밀번호 확인"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(10, 185, 160, 40); // 위치랑 사이즈
		label.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/* TextField - 비밀번호 확인 입력 */
		txt_PW_Check = new JPasswordField(20); // 텍스트필드 재정의
		txt_PW_Check.setBounds(190, 185, 160, 40); // 위치랑 사이즈
		txt_PW_Check.setFont(font_MB); // 글씨체

		add(txt_PW_Check); // panel에 textfield 추가

		/* Label - 전화번호 */
		label = new JLabel("전화번호"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(10, 240, 160, 40); // 위치랑 사이즈
		label.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/* ComboBox - 전화번호1 선택 */
		com_Phone1 = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_Phone1.setFont(font_MB); // 폰트
		com_Phone1.setBounds(190, 240, 70, 40); // 위치랑 사이즈

		com_Phone1.addItem("010"); // 콤보박스 매출 항목
		com_Phone1.addItem("011"); // 콤보박스 지출 항목
		com_Phone1.addItem("017"); // 콤보박스 지출 항목

		com_Phone1.setSelectedItem(0);
		add(com_Phone1); // panel_MID에 com_Division 추가

		/* Label - */
		label = new JLabel("-"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(225, 235, 50, 40); // 위치랑 사이즈
		label.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/* TextField - 전화번호2 입력 */
		txt_Phone2 = new JTextField(10); // 텍스트필드 재정의
		txt_Phone2.setBounds(280, 240, 70, 40); // 위치랑 사이즈
		txt_Phone2.setFont(font_MB); // 글씨체

		txt_Phone2.setText(dto.getM_TEL2());
		add(txt_Phone2); // panel에 textfield 추가

		/* Label - */
		label = new JLabel("-"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(315, 235, 50, 40); // 위치랑 사이즈
		label.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/* TextField - 전화번호3 입력 */
		txt_Phone3 = new JTextField(10); // 텍스트필드 재정의
		txt_Phone3.setBounds(370, 240, 70, 40); // 위치랑 사이즈
		txt_Phone3.setFont(font_MB); // 글씨체

		add(txt_Phone3); // panel에 textfield 추가

		/* Label - 생년월일 */
		label = new JLabel("생년월일"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(10, 295, 160, 40); // 위치랑 사이즈
		label.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		// 올해포함 이전 5년을 I_Year에 담기
		for (int i = 0, y = thisyear - i; i < year.length; i++, y--) {
			year[i] = String.valueOf(y);
		}

		/* ComboBox - 년 선택 */
		com_Year = new JComboBox<String>(year); // 콤보박스 초기화인데 String 형식이야
		com_Year.setFont(font_MB); // 폰트
		com_Year.setBounds(190, 295, 70, 40); // 위치랑 사이즈

		add(com_Year); // panel_MID에 com_Division 추가

		/* Label - 년 */
		label = new JLabel("년"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(265, 295, 30, 40); // 위치랑 사이즈
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/* ComboBox - 월 선택 */
		com_Month = new JComboBox<String>(month); // 콤보박스 초기화인데 String 형식이야
		com_Month.setFont(font_MB); // 폰트
		com_Month.setBounds(300, 295, 50, 40); // 위치랑 사이즈
		com_Month.setSelectedIndex(thismonth);

		add(com_Month); // panel_MID에 com_Division 추가

		/* Label - 월 */
		label = new JLabel("월"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(355, 295, 30, 40); // 위치랑 사이즈
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/* ComboBox - 일 선택 */
		com_Date = new JComboBox<String>(); // 콤보박스 초기화인데 String 형식이야
		com_Date.setFont(font_MB); // 폰트
		com_Date.setBounds(390, 295, 50, 40); // 위치랑 사이즈
		/* 일 수 계산해서 넣기 */
		for (int i = 0; i < Integer.parseInt(date[com_Month.getSelectedIndex()]); i++) {
			com_Date.addItem(String.valueOf(i + 1));
		}

		com_Month.addItemListener(new ItemListener() { // 나중에 콤보박스 선택에 따라 일어날 이벤트
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();
				com_Date.removeAllItems();

				/* 일 수 계산해서 넣기 */
				for (int i = 0; i < Integer.parseInt(date[com_Month.getSelectedIndex()]); i++) {
					com_Date.addItem(String.valueOf(i + 1));
				}
			}
		});

		add(com_Date); // panel_MID에 com_Division 추가

		/* Label - 일 */
		label = new JLabel("일"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(445, 295, 30, 40); // 위치랑 사이즈
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/* Label - 성별 */
		label = new JLabel("성별"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(10, 350, 160, 40); // 위치랑 사이즈
		label.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/* RadioButton - M */
		radio_M = new JRadioButton("남성");
		radio_M.setFont(font_L); // 폰트
		radio_M.setBounds(190, 350, 80, 40); // 위치랑 사이즈
		radio_M.setForeground(Color.WHITE); // 글씨가 하얀색
		radio_M.setBackground(new java.awt.Color(50, 50, 52));
		radio_M.setSelected(true);

		add(radio_M); // panel에 label 추가

		/* RadioButton - F */
		radio_F = new JRadioButton("여성");
		radio_F.setFont(font_L); // 폰트
		radio_F.setBounds(280, 350, 80, 40); // 위치랑 사이즈
		radio_F.setForeground(Color.WHITE); // 글씨가 하얀색
		radio_F.setBackground(new java.awt.Color(50, 50, 52));

		add(radio_F); // panel에 label 추가

		group_Gender = new ButtonGroup();
		group_Gender.add(radio_M);
		group_Gender.add(radio_F);

		/* Button - 가입 버튼 */
		btn_Join = new JButton(MJoin); // 버튼 재정의, 아무것도 안했을 때 이미지 Delete
		btn_Join.setSelectedIcon(MJoin_s); // 눌렀을 떄 이미지 Delete_s....왜 Delete로 나오지
		btn_Join.setRolloverIcon(MJoin_s); // 마우스 올렸을 때 이미지 Delete_s
		btn_Join.setBorderPainted(false); // 테두리 비워
		btn_Join.setFocusPainted(false); // 뭐 꾸며져 있으면 지워
		btn_Join.setContentAreaFilled(false); // 이건 모르겠어 그냥 해야된대
		btn_Join.setBounds(172, 405, 140, 40); // 위치랑 사이즈

		btn_Join.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/** 메세지박스 UI **/
				UIManager UI = new UIManager();
				UI.put("OptionPane.background", new java.awt.Color(50, 50, 52));
				UI.put("Panel.background", new java.awt.Color(50, 50, 52));
				UI.put("OptionPane.messageFont", font_L);
				UI.put("OptionPane.messageForeground", Color.WHITE);
				UI.put("OptionPane.buttonFont", font_L);

				boolean tel2 = isNumeric(txt_Phone2.getText());
				boolean tel3 = isNumeric(txt_Phone3.getText());
				/** 공백 검사 **/
				if (CheckNull()) {
					/** 아이디가 이미 존재하는 지 확인 **/
					if (dao.SearchID(txt_ID.getText())) {
						/* 전화번호 숫자인지 판단 */
						if (!tel2 || !tel3) {
							JOptionPane.showMessageDialog(null, "전화번호는 숫자만 입력가능합니다", "회원가입 실패",
									JOptionPane.WARNING_MESSAGE);
							txt_Phone2.setText("");
							txt_Phone3.setText("");
						} else { 

							/** 비밀번호 일치 여부 **/
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
								// 성별이 남자면
								if (radio_M.isSelected()) {
									dto.setM_GENDER("남성");
								}
								// 성별이 여자면
								else if (radio_F.isSelected()) {
									dto.setM_GENDER("여성");
								}
								dto.setM_MODE("N");
								dto.setM_BEGIN_TIME("");
								dto.setM_REMAIN_TIME("");
								dto.setUSED_MONEY(0);
								dao.MemberInsert(dto);
								JOptionPane.showMessageDialog(null, "회원가입 되었습니다!", "회원가입",
										JOptionPane.INFORMATION_MESSAGE);
								dispose();

							} else {
								JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다!", "회원가입 실패",
										JOptionPane.WARNING_MESSAGE);
								txt_PW_Check.setText("");
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "아이디가 이미 존재합니다!", "회원가입 실패", JOptionPane.WARNING_MESSAGE);
						txt_ID.setText("");
					}
				}
			}
		});

		add(btn_Join); // panel_MID에 btn_Delete 추가
	}

	/* 공백 검사 */
	public boolean CheckNull() {
		boolean result = true;

		if (txt_ID.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "아이디를 입력하세요", "회원가입 실패", JOptionPane.WARNING_MESSAGE);
			result = false;
		} else if (txt_Name.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "이름을 입력하세요", "회원가입 실패", JOptionPane.WARNING_MESSAGE);
			result = false;
		} else if (txt_PW.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요", "회원가입 실패", JOptionPane.WARNING_MESSAGE);
			result = false;
		} else if (txt_Phone2.getText().equals("") || (txt_Phone3.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "전화번호를 입력하세요", "회원가입 실패", JOptionPane.WARNING_MESSAGE);
			result = false;
		}

		return result;
	}

	// 숫자인가
	public static boolean isNumeric(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
