package server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.EventListener;

import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class MainFrame extends JFrame {
	ImageIcon Mark = new ImageIcon("image/PCOP.png");

	private JPanel MainFrame; // Panel 등록해서 객체로 쓰고
	
	private Manage_Seat ms = null;
	private Manage_Member mm = null;
	private Manage_InOut mi = null;
	private Manage_Stock mst = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) { // Main 함수고
		EventQueue.invokeLater(new Runnable() { // 이건 뭘까
			public void run() { // 실행 메서드
				try { // 이거 실행해서
					MainFrame frame = new MainFrame(); // 밑에 Member 메서드 실행, 회원 그 MEMBER 아님 주의!
					frame.setVisible(true); // 제일 겉 프레임 보이도록 설정
				} catch (Exception e) { // 오류나면 경고!
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {  //생성자야 실행하면 여기서 초기설정 해줘
		setFont(new Font("맑은 고딕", Font.BOLD, 10)); // 글씨체
		setTitle("관리자 모드");  //타이틀바 글씨 설정
		// setUndecorated(true);  //이거를 해주면 위에  _ㅁx 이게 없어져
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 이거 해놓고 x누르면 아예 프로그램이 종료 돼
		setBounds(100, 50, 1000, 600); // 앞에 두 개는 프레임 시작 위치, 뒤에 두 개는 프레임 사이즈!
		MainFrame = new JPanel(); // 위에서 선언한 객체 초기화
		MainFrame.setBorder(new EmptyBorder(5, 5, 5, 5));  //상하좌우 살짝씩 여유 주는거야
		MainFrame.setLayout(new BorderLayout(0, 0));  //멀라 Layout 설정해준대
		setResizable(false);
		setContentPane(MainFrame);  //MainFrame을 지금까지 설정해준걸로 세팅해
		getContentPane().setBackground(new java.awt.Color(126, 125, 128));  //탭 뒤에 배경이 짙은 회색이야
		setIconImage(Mark.getImage());  //좌측 상단이랑 작업 표시줄 아이콘 설정

		/* 탭 추가 */
		JTabbedPane Manage_Seat = new JTabbedPane(JTabbedPane.TOP);  //탭을 상단에 추가해줘
		Manage_Seat.setFont(new Font("맑은 고딕", Font.BOLD, 21));  //탭 폰트 설정해줘
		Manage_Seat.addTab("좌석관리", new Seat());  //좌석관리 탭
		Manage_Seat.addTab("회원관리", new Member());  //회원관리 탭
		Manage_Seat.addTab("재고관리", new Stock());  //재고관리 탭
		Manage_Seat.addTab("매출관리", new InOut());  //매출관리 탭
		Manage_Seat.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				switch(Manage_Seat.getSelectedIndex()) {
				case 0:
					ms.Refresh();
					break;
				case 1:
					mm.Refresh();
					break;
				case 2:
					mst.Refresh();
					break;
				case 3:
					mi.Refresh();
					break;
				}
			}
		});
		
		Manage_Seat.setBackground(new java.awt.Color(50, 50, 52));  //탭 위에 색깔 검정이야
		Manage_Seat.setForeground(Color.WHITE);  //탭 위에 글씨 하얀색이야

		MainFrame.add(Manage_Seat, BorderLayout.CENTER);  //MainFrame에 Tab 추가해줘

	}

	class Seat extends JPanel { // 좌석 관리 클래스로 이동
		public Seat() {
			ms = new Manage_Seat(this);
		}
	}

	class Member extends JPanel { // 회원 관리 클래스로 이동
		public Member() {
			mm = new Manage_Member(this);
		}
	}

	class Stock extends JPanel { // 재고 관리 클래스로 이동
		public Stock() {
			mst = new Manage_Stock(this);
		}
	}

	class InOut extends JPanel { // 매출 관리 클래스로 이동
		public InOut() {
			mi = new Manage_InOut(this);
		}
	}
}
