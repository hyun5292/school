package server;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import pcop.dao.Food_Order_DAO;
import pcop.dao.Member_DAO;
import pcop.dao.Seat_DAO;
import pcop.dto.Member_DTO;

public class Manage_Member extends MouseAdapter {
	Font font_L = new Font("맑은 고딕", Font.BOLD, 30);
	Font font = new Font("맑은 고딕", Font.BOLD, 20);
	Font font_B = new Font("맑은 고딕", Font.BOLD, 25);
	JTextField text_ID;
	JTable table_Member;
	private Vector<String> col = new Vector<String>(); // 테이블 컬럼
	private Vector row; // 테이블 내용
	private DefaultTableModel model;
	private JScrollPane scroll;
	JLabel label, lbl_ID, lbl_Name, lbl_Birth, lbl_Tel;
	JTextArea tArea_Info;
	JButton btn_Search, btn_getout, btn_Pw;
	ImageIcon MWithdraw = new ImageIcon("image/noselected/MWithdraw.png");
	ImageIcon MWithdraw_s = new ImageIcon("image/selected/MWithdraw_s.png");
	ImageIcon PwReset = new ImageIcon("image/noselected/PwReset.png");
	ImageIcon PwReset_s = new ImageIcon("image/selected/PwReset_s.png");
	ImageIcon ReadingGlass = new ImageIcon("image/noselected/ReadingGlass.png");
	ImageIcon ReadingGlass_s = new ImageIcon("image/selected/ReadingGlass_s.png");

	private Member_DAO dao = new Member_DAO();
	private Member_DTO dto = new Member_DTO();
	private Seat_DAO sDao = new Seat_DAO();

	public Manage_Member(JPanel panel) {
		panel.setLayout(null); // 판을 다시 짠다
		panel.setBackground(new java.awt.Color(50, 50, 52));

		// 메세지박스 UI
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", new java.awt.Color(50, 50, 52));
		UI.put("Panel.background", new java.awt.Color(50, 50, 52));
		UI.put("OptionPane.messageFont", font_B);
		UI.put("OptionPane.messageForeground", Color.WHITE);
		UI.put("OptionPane.buttonFont", font_B);

		/* TextField - 회원 ID 검색하는 */
		text_ID = new JTextField("이름을 입력하세요"); // 텍스트필드 재정의
		text_ID.setBounds(10, 8, 445, 40); // 위치랑 사이즈
		text_ID.setFont(font); // 글씨체
		text_ID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				text_ID.setText("");
			}
		});

		panel.add(text_ID); // 판넬에 textfield 추가

		/* Button - 회원 검색 버튼 */
		btn_Search = new JButton(ReadingGlass);
		btn_Search.setSelectedIcon(ReadingGlass_s);
		btn_Search.setRolloverIcon(ReadingGlass_s);
		btn_Search.setBorderPainted(false);
		btn_Search.setFocusPainted(false);
		btn_Search.setContentAreaFilled(false);
		btn_Search.setBounds(460, 5, 45, 45);
		btn_Search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dto = new Member_DTO();
				dto = dao.getMember(text_ID.getText().toString());

				row = dao.getNameMembers(text_ID.getText().toString());
				model = new DefaultTableModel(row, col) {
					// 셀 수정 못하게 하는 부분
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				table_Member.setModel(model);
			}
		});

		panel.add(btn_Search);

		/* TextArea - 회원 정보 띄워주는 */
		tArea_Info = new JTextArea();
		tArea_Info.setForeground(Color.DARK_GRAY);
		tArea_Info.setFont(font_L);
		tArea_Info.setBounds(520, 55, 435, 300);
		tArea_Info.setEditable(false);
		tArea_Info.setBackground(new java.awt.Color(227, 225, 230));
		tArea_Info.setBorder(new LineBorder(Color.DARK_GRAY, 5));

		panel.add(tArea_Info);

		/* Table - 회원 정보 띄워주는 */
		row = dao.getAllMembers();
		setCell();
		model = new DefaultTableModel(row, col) {
			// 셀 수정 못하게 하는 부분
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_Member = new JTable(model);
		// table_Member.setBounds(5, 55, 500, 450); // 위치랑 사이즈
		table_Member.setFont(font); // 글씨체
		table_Member.setRowHeight(50);
		table_Member.setBackground(new java.awt.Color(227, 225, 230));
		table_Member.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tArea_Info.setText("");
				dto = new Member_DTO();
				String str = "";
				int row = table_Member.getSelectedRow();
				TableModel md = table_Member.getModel();
				dto.setM_ID(md.getValueAt(row, 1).toString());

				dto = dao.getMember(dto.getM_ID());
				str += "ID: " + dto.getM_ID();
				str += "\r\n\r\n성명: " + dto.getM_NAME();
				str += "\r\n\r\n전화번호: " + dto.getM_TEL1() + "-" + dto.getM_TEL2() + "-" + dto.getM_TEL3();
				str += "\r\n\r\n생년월일: " + dto.getM_BIRTH();

				tArea_Info.setText(str);
			}
		});

		scroll = new JScrollPane(table_Member);
		scroll.setViewportView(table_Member);
		scroll.setBounds(5, 55, 500, 450);

		panel.add(scroll); // 스크롤 추가

		/* Label - 회원정보 글씨 */
		label = new JLabel("회원정보");
		label.setFont(font_L);
		label.setForeground(Color.WHITE);
		label.setBounds(520, 5, 500, 40);

		panel.add(label);

		/* Button - 회원 탈퇴 버튼 */
		btn_getout = new JButton(MWithdraw);
		btn_getout.setSelectedIcon(MWithdraw_s);
		btn_getout.setRolloverIcon(MWithdraw_s);
		btn_getout.setBorderPainted(false);
		btn_getout.setFocusPainted(false);
		btn_getout.setContentAreaFilled(false);
		btn_getout.setFont(font);
		btn_getout.setBounds(520, 360, 200, 140);
		btn_getout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dto.getM_ID() == null) {
					JOptionPane.showMessageDialog(null, "회원을 먼저 선택해주세요", "오류", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String Answer = JOptionPane.showInputDialog(null, "회원 탈퇴를 원하시면 \'회원탈퇴\'를 입력하세요", "회원탈퇴",
							JOptionPane.INFORMATION_MESSAGE);
					if (Answer == null) {
						JOptionPane.showMessageDialog(null, "회원탈퇴를 중단하였습니다", "회원탈퇴 실패",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (Answer.equals("회원탈퇴")) {
						boolean result_m = dao.getOutMemger(dto.getM_ID());
						sDao.getOut(dto.getM_ID());
						if (result_m) {
							JOptionPane.showMessageDialog(null, "회원이 탈퇴되었습니다", "회원탈퇴 성공",
									JOptionPane.INFORMATION_MESSAGE);
							Refresh();
						} else {
							JOptionPane.showMessageDialog(null, "탈퇴에 실패하였습니다", "회원탈퇴 실패",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "탈퇴에 실패하였습니다", "회원탈퇴 실패",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

		panel.add(btn_getout);

		/* Button - 비밀번호 초기화 버튼 */
		btn_Pw = new JButton(PwReset);
		btn_Pw.setSelectedIcon(PwReset_s);
		btn_Pw.setRolloverIcon(PwReset_s);
		btn_Pw.setBorderPainted(false);
		btn_Pw.setFocusPainted(false);
		btn_Pw.setContentAreaFilled(false);
		btn_Pw.setFont(font);
		btn_Pw.setBounds(750, 360, 200, 140);
		btn_Pw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dto.getM_ID() == null) {
					JOptionPane.showMessageDialog(null, "회원을 먼저 선택해주세요", "오류", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String Answer = JOptionPane.showInputDialog(null, "비밀번호 초기화를 원하시면 \'초기화\'를 입력하세요", "비밀번호 초기화",
							JOptionPane.INFORMATION_MESSAGE);
					if (Answer == null) {
						JOptionPane.showMessageDialog(null, "비밀번호 초기화를 중단하였습니다", "비밀번호 초기화 실패",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (Answer.equals("초기화")) {
						boolean result = dao.PWReset(dto.getM_ID());
						sDao.getOut(dto.getM_ID());
						if (result) {
							JOptionPane.showMessageDialog(null, "비밀번호가 \'a123456789\'로 초기화 되었습니다", "비밀번호 초기화 성공",
									JOptionPane.INFORMATION_MESSAGE);
							Refresh();
						} else {
							JOptionPane.showMessageDialog(null, "초기화에 실패하였습니다", "비밀번호 초기화 실패",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "초기화에 실패하였습니다", "비밀번호 초기화 실패",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

		panel.add(btn_Pw);
	}

	// 테이블 셀
	public void setCell() {
		col.addElement("번호");
		col.addElement("ID");
		col.addElement("성명");
		col.addElement("성별");
		col.addElement("전화번호");
	}

	// 갱신
	public void Refresh() {
		/* Table - 회원 정보 띄워주는 */
		row = dao.getAllMembers();
		model = new DefaultTableModel(row, col) {
			// 셀 수정 못하게 하는 부분
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_Member.setModel(model);

		tArea_Info.setText("");
		text_ID.setText("이름을 입력하세요");
	}
}
