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
	/** 글씨체 **/
	private Font font_L = new Font("맑은 고딕", Font.BOLD, 25);
	private Font font_MB = new Font("맑은 고딕", Font.BOLD, 18);
	private Font font_S = new Font("맑은 고딕", Font.BOLD, 20);
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
	
	/** dao,dto 가져오기 **/
	MemberUpdateDAO dao = new MemberUpdateDAO();
	MemberUpdateDTO dto = new MemberUpdateDTO();

	public MemberUpdate(String mID) {
		setTitle("회원정보수정"); // 타이틀바 텍스트 설정
		setBounds(x / 2 + 50, y / 7, 500, 505); // 앞에 두 개는 프레임 시작 위치, 뒤에 두 개는 프레임 사이즈!
			
		/** dao에서 회원검색 호출 **/
		dto = dao.MemberSelect(dto, mID);
		
		panel = new JPanel(); // 위에서 선언한 객체 초기화
		panel.setBorder(new EmptyBorder(5, 5, 5, 5)); // 상하좌우 살짝씩 여유 주는거야
		panel.setLayout(new BorderLayout(0, 0)); // 멀라 Layout 설정해준대
		setContentPane(panel); // panel_MID을 지금까지 설정해준걸로 세팅해
		setIconImage(Mark.getImage()); // 좌측 상단이랑 작업 표시줄 아이콘 설정
		setResizable(false);
		panel.setBackground(new java.awt.Color(50, 50, 52)); // 뒤에 배경 블랙

		panel.setLayout(null); // 판을 다시 짠다

		/** Label - 아이디 **/
		label = new JLabel("아이디"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(10, 20, 160, 40); // 위치랑 사이즈
		label.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/** Label - 아이디 **/
		lbl_ID = new JLabel(); // 초기화인데 텍스트가 구분
		lbl_ID.setFont(font_L); // 폰트
		lbl_ID.setBounds(190, 20, 160, 40); // 위치랑 사이즈
		lbl_ID.setForeground(new java.awt.Color(255, 166, 0)); // 글씨가 하얀색
		lbl_ID.setText(dto.getM_ID()); // db를 가져와 텍스트박스에 뿌려줌

		add(lbl_ID); // panel에 textfield 추가

		/** Label - 이름 **/
		label = new JLabel("이름"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(10, 75, 160, 40); // 위치랑 사이즈
		label.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/** TextField - 이름 입력 **/
		txt_Name = new JTextField(20); // 텍스트필드 재정의
		txt_Name.setBounds(190, 75, 160, 40); // 위치랑 사이즈
		txt_Name.setFont(font_MB); // 글씨체
		txt_Name.setText(dto.getM_NAME()); // db를 가져와 텍스트박스에 뿌려줌

		add(txt_Name); // panel에 textfield 추가

		/** Label - 비밀번호 **/
		label = new JLabel("비밀번호"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(10, 130, 160, 40); // 위치랑 사이즈
		label.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/** TextField - 비밀번호 입력 **/
		txt_PW = new JPasswordField(); // 텍스트필드 재정의
		txt_PW.setBounds(190, 130, 160, 40); // 위치랑 사이즈
		txt_PW.setFont(font_MB); // 글씨체

		txt_PW.setText(dto.getM_PW()); // db를 가져와 텍스트박스에 뿌려줌
		add(txt_PW); // panel에 textfield 추가

		/** Label - 비밀번호 확인 **/
		label = new JLabel("비밀번호 확인"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(10, 185, 160, 40); // 위치랑 사이즈
		label.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/** TextField - 비밀번호 확인 입력 **/
		txt_PW_Check = new JPasswordField(); // 텍스트필드 재정의
		txt_PW_Check.setBounds(190, 185, 160, 40); // 위치랑 사이즈
		txt_PW_Check.setFont(font_MB); // 글씨체

		add(txt_PW_Check); // panel에 textfield 추가

		/** Label - 전화번호 **/
		label = new JLabel("전화번호"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(10, 240, 160, 40); // 위치랑 사이즈
		label.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/** ComboBox - 전화번호1 선택 **/
		com_Phone1 = new JComboBox(phone); // 콤보박스 초기화인데 String 형식이야
		com_Phone1.setFont(font_MB); // 폰트
		com_Phone1.setBounds(190, 240, 70, 40); // 위치랑 사이즈
		com_Phone1.setSelectedItem(dto.getM_TEL1()); // db를 가져와 텍스트박스에 뿌려줌
		
		add(com_Phone1); // panel_MID에 com_Division 추가

		/** Label - **/
		label = new JLabel("-"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(225, 235, 50, 40); // 위치랑 사이즈
		label.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/** TextField - 전화번호2 입력 **/
		txt_Phone2 = new JTextField(10); // 텍스트필드 재정의
		txt_Phone2.setBounds(280, 240, 70, 40); // 위치랑 사이즈
		txt_Phone2.setFont(font_MB); // 글씨체

		txt_Phone2.setText(dto.getM_TEL2()); // db를 가져와 텍스트박스에 뿌려줌
		add(txt_Phone2); // panel에 textfield 추가

		/** Label - **/
		label = new JLabel("-"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(315, 235, 50, 40); // 위치랑 사이즈
		label.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/** TextField - 전화번호3 입력 **/
		txt_Phone3 = new JTextField(10); // 텍스트필드 재정의
		txt_Phone3.setBounds(370, 240, 70, 40); // 위치랑 사이즈
		txt_Phone3.setFont(font_MB); // 글씨체

		txt_Phone3.setText(dto.getM_TEL3()); // db를 가져와 텍스트박스에 뿌려줌
		add(txt_Phone3); // panel에 textfield 추가

		/** Label - 생년월일 **/
		label = new JLabel("생년월일"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(10, 295, 160, 40); // 위치랑 사이즈
		label.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/** TextField - 년 선택 **/
		com_Year = new JTextField(10);
		com_Year.setFont(font_MB); // 폰트
		com_Year.setBounds(190, 295, 70, 40); // 위치랑 사이즈
		com_Year.setEditable(false); // 수정막음
		com_Year.setText(dto.getM_BIRTH1()); // db를 가져와 텍스트박스에 뿌려줌

		add(com_Year); // panel_MID에 com_Division 추가

		/** Label - 년 **/
		label = new JLabel("년"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(265, 295, 30, 40); // 위치랑 사이즈
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/** TextField - 월 선택 **/
		com_Month = new JTextField(5);
		; // 콤보박스 초기화인데 String 형식이야
		com_Month.setFont(font_MB); // 폰트
		com_Month.setBounds(300, 295, 50, 40); // 위치랑 사이즈
		com_Month.setEditable(false); // 수정막음
		com_Month.setText(dto.getM_BIRTH2()); // db를 가져와 텍스트박스에 뿌려줌

		add(com_Month); // panel_MID에 com_Division 추가

		/** Label - 월 **/
		label = new JLabel("월"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(355, 295, 30, 40); // 위치랑 사이즈
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/** TextField - 일 선택 **/
		com_Date = new JTextField(5);
		; // 콤보박스 초기화인데 String 형식이야
		com_Date.setFont(font_MB); // 폰트
		com_Date.setBounds(390, 295, 50, 40); // 위치랑 사이즈
		com_Date.setEditable(false); // 수정막음
		com_Date.setText(dto.getM_BIRTH3()); // db를 가져와 텍스트박스에 뿌려줌

		add(com_Date); // panel_MID에 com_Division 추가

		/** Label - 일 **/
		label = new JLabel("일"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(445, 295, 30, 40); // 위치랑 사이즈
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/** Label - 성별 **/
		label = new JLabel("성별"); // 초기화인데 텍스트가 구분
		label.setFont(font_L); // 폰트
		label.setBounds(10, 350, 160, 40); // 위치랑 사이즈
		label.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬
		label.setForeground(Color.WHITE); // 글씨가 하얀색

		add(label); // panel에 label 추가

		/** RadioButton - M **/
		radio_M = new JRadioButton("남성");
		radio_M.setFont(font_L); // 폰트
		radio_M.setBounds(190, 350, 80, 40); // 위치랑 사이즈
		radio_M.setForeground(Color.WHITE); // 글씨가 하얀색
		radio_M.setBackground(new java.awt.Color(50, 50, 52));
		radio_M.setSelected(true);

		add(radio_M); // panel에 label 추가

		/** RadioButton - F **/
		radio_F = new JRadioButton("여성");
		radio_F.setFont(font_L); // 폰트
		radio_F.setBounds(280, 350, 80, 40); // 위치랑 사이즈
		radio_F.setForeground(Color.WHITE); // 글씨가 하얀색
		radio_F.setBackground(new java.awt.Color(50, 50, 52));

		add(radio_F); // panel에 label 추가

		group_Gender = new ButtonGroup();
		group_Gender.add(radio_M);
		group_Gender.add(radio_F);

		
		/** 성별 체크 **/
		// 성별이 남자면
		if (dto.getM_GENDER().equals("남성")) {
			radio_M.setSelected(true); // 남성에 체크
		}
		// 성별이 여자면
		else if (dto.getM_GENDER().equals("여성")) {
			radio_F.setSelected(true); // 여성에 체크
		}
		
		/** Button - 수정 버튼 **/
		btn_Update = new JButton(Reset); // 버튼 재정의, 아무것도 안했을 때 이미지 Delete
		btn_Update.setSelectedIcon(Reset_s); // 눌렀을 떄 이미지 Delete_s....왜 Delete로 나오지
		btn_Update.setRolloverIcon(Reset_s); // 마우스 올렸을 때 이미지 Delete_s
		btn_Update.setBorderPainted(false); // 테두리 비워
		btn_Update.setFocusPainted(false); // 뭐 꾸며져 있으면 지워
		btn_Update.setContentAreaFilled(false); // 이건 모르겠어 그냥 해야된대
		btn_Update.setBounds(172, 405, 140, 40); // 위치랑 사이즈

		/** 수정 버튼 클릭했을 경우 **/
		btn_Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** 메세지박스 UI **/
				UIManager UI = new UIManager();
	            UI.put("OptionPane.background", new java.awt.Color(50, 50, 52));
	            UI.put("Panel.background", new java.awt.Color(50, 50, 52));
	            UI.put("OptionPane.messageFont", font_L);
	            UI.put("OptionPane.messageForeground", Color.WHITE);
	            UI.put("OptionPane.buttonFont", font_L);
	            
				/** 비밀번호 일치 여부 **/
				if (txt_PW_Check.getText().equals(txt_PW.getText())) {			
					/** 회원정보수정 **/				
					dto.setM_NAME(txt_Name.getText().toString()); // 이름
					dto.setM_PW(txt_PW.getText().toString());     // 비밀번호
					dto.setM_TEL1(com_Phone1.getSelectedItem().toString()); // 전화번호1
					dto.setM_TEL2(txt_Phone2.getText().toString()); // 전화번호2
					dto.setM_TEL3(txt_Phone3.getText().toString()); // 전화번호3
					
					/** 성별체크  **/
					if (radio_M.isSelected() == true) {
						dto.setM_GENDER(radio_M.getText().toString());
					}
					else if (radio_F.isSelected() == true) {
						dto.setM_GENDER(radio_F.getText().toString());
					}
					
					/** dao에서 회원수정 호출 **/
					dao.MemberUpdate(dto, mID); 
					
					JOptionPane.showMessageDialog(null, "수정되었습니다!", "회원정보수정", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} 
							
				else {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다!", "회원정보수정", JOptionPane.WARNING_MESSAGE);
				}				
			}
		});

		add(btn_Update); // panel_MID에 btn_Delete 추가
	}
	
	/** 텍스트 지우기 **/
	public void Text_Clean() {
		txt_Name.setText(""); // 이름
		txt_PW.setText(""); // 비밀번호
		txt_PW_Check.setText(""); // 비밀번호 확인
		com_Phone1.setSelectedIndex(0); // 전화번호1
		//com_Phone1.removeAllItems(); //콤보박스 항목 다 지움
		txt_Phone2.setText(""); // 전화번호2
		txt_Phone3.setText(""); // 전화번호3
		com_Year.setText(""); // 생년월일1
		com_Month.setText(""); // 생년월일2
		com_Date.setText(""); // 생년월일3
		group_Gender.clearSelection(); // 성별
	}
}
