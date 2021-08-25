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

	private JPanel MainFrame; // Panel ����ؼ� ��ü�� ����
	
	private Manage_Seat ms = null;
	private Manage_Member mm = null;
	private Manage_InOut mi = null;
	private Manage_Stock mst = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) { // Main �Լ���
		EventQueue.invokeLater(new Runnable() { // �̰� ����
			public void run() { // ���� �޼���
				try { // �̰� �����ؼ�
					MainFrame frame = new MainFrame(); // �ؿ� Member �޼��� ����, ȸ�� �� MEMBER �ƴ� ����!
					frame.setVisible(true); // ���� �� ������ ���̵��� ����
				} catch (Exception e) { // �������� ���!
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {  //�����ھ� �����ϸ� ���⼭ �ʱ⼳�� ����
		setFont(new Font("���� ���", Font.BOLD, 10)); // �۾�ü
		setTitle("������ ���");  //Ÿ��Ʋ�� �۾� ����
		// setUndecorated(true);  //�̰Ÿ� ���ָ� ����  _��x �̰� ������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �̰� �س��� x������ �ƿ� ���α׷��� ���� ��
		setBounds(100, 50, 1000, 600); // �տ� �� ���� ������ ���� ��ġ, �ڿ� �� ���� ������ ������!
		MainFrame = new JPanel(); // ������ ������ ��ü �ʱ�ȭ
		MainFrame.setBorder(new EmptyBorder(5, 5, 5, 5));  //�����¿� ��¦�� ���� �ִ°ž�
		MainFrame.setLayout(new BorderLayout(0, 0));  //�ֶ� Layout �������ش�
		setResizable(false);
		setContentPane(MainFrame);  //MainFrame�� ���ݱ��� �������ذɷ� ������
		getContentPane().setBackground(new java.awt.Color(126, 125, 128));  //�� �ڿ� ����� £�� ȸ���̾�
		setIconImage(Mark.getImage());  //���� ����̶� �۾� ǥ���� ������ ����

		/* �� �߰� */
		JTabbedPane Manage_Seat = new JTabbedPane(JTabbedPane.TOP);  //���� ��ܿ� �߰�����
		Manage_Seat.setFont(new Font("���� ���", Font.BOLD, 21));  //�� ��Ʈ ��������
		Manage_Seat.addTab("�¼�����", new Seat());  //�¼����� ��
		Manage_Seat.addTab("ȸ������", new Member());  //ȸ������ ��
		Manage_Seat.addTab("������", new Stock());  //������ ��
		Manage_Seat.addTab("�������", new InOut());  //������� ��
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
		
		Manage_Seat.setBackground(new java.awt.Color(50, 50, 52));  //�� ���� ���� �����̾�
		Manage_Seat.setForeground(Color.WHITE);  //�� ���� �۾� �Ͼ���̾�

		MainFrame.add(Manage_Seat, BorderLayout.CENTER);  //MainFrame�� Tab �߰�����

	}

	class Seat extends JPanel { // �¼� ���� Ŭ������ �̵�
		public Seat() {
			ms = new Manage_Seat(this);
		}
	}

	class Member extends JPanel { // ȸ�� ���� Ŭ������ �̵�
		public Member() {
			mm = new Manage_Member(this);
		}
	}

	class Stock extends JPanel { // ��� ���� Ŭ������ �̵�
		public Stock() {
			mst = new Manage_Stock(this);
		}
	}

	class InOut extends JPanel { // ���� ���� Ŭ������ �̵�
		public InOut() {
			mi = new Manage_InOut(this);
		}
	}
}
