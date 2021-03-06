package user;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import pcop.dao.FoodOrderDAO;
import pcop.dao.Food_Stock_DAO;
import pcop.dto.FoodOrderDTO;
import pcop.dto.Food_Stock_DTO;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

public class FoodOrder extends JFrame {
	private Font font_L = new Font("???? ????", Font.BOLD, 25);
	private Font font_M = new Font("???? ????", Font.BOLD, 20);
	private Font font_S = new Font("???? ????", Font.BOLD, 18);
	private JPanel panel = new JPanel();

	private JTextArea Area, area_Want;
	private JTabbedPane tab_Food;
	private JTextField text_Way, text_Want;
	private JButton btn_Order, btn_Plus, btn_Minus;
	private JLabel label;
	private JTable tbl_Food, tbl_Pay;
	private Vector<String> col = new Vector<String>(); // ???̺? ?÷?
	private Vector row; // ???̺? ????
	private DefaultTableModel model;
	private JScrollPane scroll;
	private JComboBox com_Way;
	private String[] FoodKind = null;

	private ImageIcon FoodOrder = new ImageIcon("image/noselected/FOrder.png");
	private ImageIcon FoodOrder_s = new ImageIcon("image/selected/FOrder_s.png");
	private ImageIcon ReadingGlass = new ImageIcon("image/noselected/ReadingGlass.png");
	private ImageIcon ReadingGlass_s = new ImageIcon("image/selected/ReadingGlass_s.png");
	private ImageIcon Mark = new ImageIcon("image/PCOP.png");
	private ImageIcon Category = new ImageIcon("image/MenuCategory.png");
	private ImageIcon Plus = new ImageIcon("image/Plus.png");
	private ImageIcon Minus = new ImageIcon("image/Minus.png");

	int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	/** dao,dto ???????? **/
	FoodOrderDAO dao = new FoodOrderDAO();
	FoodOrderDTO dto = new FoodOrderDTO();
	Food_Stock_DAO fsDao = new Food_Stock_DAO();

	public FoodOrder(String m_id, int s_num) { // ?????ھ? ?????ϸ? ???⼭ ?ʱ⼳?? ????
		setTitle("?԰Ÿ? ?ֹ?"); // Ÿ??Ʋ?? ?ؽ?Ʈ ????
		setBounds(x / 2 - 600, y / 2 - 350, 1200, 700); // ?տ? ?? ???? ?????? ???? ??ġ, ?ڿ? ?? ???? ?????? ??????!

		panel = new JPanel(); // ?????? ?????? ??ü ?ʱ?ȭ
		panel.setBorder(new EmptyBorder(5, 5, 5, 5)); // ?????¿? ??¦?? ???? ?ִ°ž?
		panel.setLayout(new BorderLayout(0, 0)); // ?ֶ? Layout ???????ش?
		setContentPane(panel); // panel_MID?? ???ݱ??? ???????ذɷ? ??????
		setIconImage(Mark.getImage()); // ???? ?????̶? ?۾? ǥ???? ?????? ????
		setResizable(false);
		panel.setBackground(new java.awt.Color(50, 50, 52)); // ?ڿ? ???? ????

		panel.setLayout(null); // ???? ?ٽ? §??

		/** ?޼????ڽ? UI **/
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", new java.awt.Color(50, 50, 52));
		UI.put("Panel.background", new java.awt.Color(50, 50, 52));
		UI.put("OptionPane.messageFont", font_L);
		UI.put("OptionPane.messageForeground", Color.WHITE);
		UI.put("OptionPane.buttonFont", font_L);

		/** JLabel - ??ǰ?ֹ????? **/
		label = new JLabel(Category);
		label.setBounds(5, 5, 180, 50);

		add(label);

		FoodKind = fsDao.GetFoodKind();
		MakeBtn[] mb = new MakeBtn[FoodKind.length];
		/** ?? ?߰? **/
		tab_Food = new JTabbedPane(JTabbedPane.LEFT); // ???? ???ܿ? ?߰?????
		tab_Food.setFont(new Font("???? ????", Font.BOLD, 50)); // ?? ??Ʈ ????????
		for (int i = 0; i < FoodKind.length; i++) {
			tab_Food.addTab(FoodKind[i], mb[i] = new MakeBtn(FoodKind[i]));
		}

		tab_Food.setBackground(new java.awt.Color(50, 50, 52)); // ?? ???? ???? ?????̾?
		tab_Food.setForeground(Color.WHITE); // ?? ???? ?۾? ?Ͼ????̾?
		// tab_Food.setBounds(5, 60, 1173, 400);
		tab_Food.setBorder(new LineBorder(Color.GRAY, 5));
		tab_Food.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// mb[tab_Food.getSelectedIndex()].removeAll();
			}
		});

		/** ??ũ?? ???? **/
		scroll = new JScrollPane(tab_Food);
		scroll.setViewportView(tab_Food);
		scroll.setBounds(5, 60, 1173, 400);

		add(scroll); // ??ũ?? ?߰?

		/** JLabel - ??ǰ?ֹ????? **/
		label = new JLabel("??ǰ?ֹ?????");
		label.setFont(font_S);
		label.setBounds(10, 467, 250, 25);
		label.setForeground(Color.BLACK);

		add(label);

		/** Table - ȸ?? ???? ?????ִ? **/
		tbl_Food = new JTable(); // ???̺? ?缳??
		scroll = new JScrollPane(); // ??ũ?? ?缳??

		/** Table ?÷? **/
		col.addElement("??ǰ??");
		col.addElement("?Ǹűݾ?");
		col.addElement("????");
		col.addElement("?????ݾ?");
		// col.addElement("ó??????");

		model = new DefaultTableModel(row, col) {
			// ?? ???? ???ϰ? ?ϴ? ?κ?
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tbl_Food = new JTable(model);
		tbl_Food.setRowHeight(50);
		tbl_Food.setFont(font_S); // ?۾?ü
		tbl_Food.setBackground(new java.awt.Color(227, 225, 230));

		panel.add(tbl_Food);

		/** ??ũ?? ???? **/
		scroll = new JScrollPane(tbl_Food);
		scroll.setViewportView(tbl_Food);
		scroll.setBounds(10, 495, 700, 155);

		add(scroll); // ??ũ?? ?߰?

		/** JLabel - ??ǰ???????? **/
		label = new JLabel("??ǰ????????");
		label.setFont(font_S);
		label.setBounds(722, 467, 250, 25);
		label.setForeground(Color.BLACK);

		add(label);

		/** Button - Plus **/
		btn_Plus = new JButton(Plus);
		btn_Plus.setSelectedIcon(Plus);
		btn_Plus.setRolloverIcon(Plus);
		btn_Plus.setBorderPainted(false);
		btn_Plus.setFocusPainted(false);
		btn_Plus.setContentAreaFilled(false);
		btn_Plus.setBounds(635, 455, 50, 50);
		btn_Plus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Ŭ???? ???? ?????? +1?? ?ø?
				int count = 0; // ????
				int rowClick = tbl_Food.getSelectedRow();

				if (rowClick != -1) {
					count = Integer.parseInt(tbl_Food.getValueAt(rowClick, 2).toString()) + 1; // Ŭ???? ??
					tbl_Food.setValueAt(count, rowClick, 2);

					// ?????? ???? ?????? ????
					int price = 0;
					price = Integer.parseInt(tbl_Food.getValueAt(rowClick, 2).toString())
							* Integer.parseInt(tbl_Food.getValueAt(rowClick, 1).toString());
					tbl_Food.setValueAt(price, rowClick, 3);
				}
			};
		});

		add(btn_Plus);

		/** Button - Minus **/
		btn_Minus = new JButton(Minus);
		btn_Minus.setSelectedIcon(Minus);
		btn_Minus.setRolloverIcon(Minus);
		btn_Minus.setBorderPainted(false);
		btn_Minus.setFocusPainted(false);
		btn_Minus.setContentAreaFilled(false);
		btn_Minus.setBounds(670, 455, 50, 50);
		btn_Minus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Ŭ???? ???? ?????? -1?? ?ø?
				int count = 0; // ????
				int rowClick = tbl_Food.getSelectedRow(); // Ŭ???? ??

				if (rowClick != -1) {
					count = Integer.parseInt(tbl_Food.getValueAt(rowClick, 2).toString()) - 1;
					tbl_Food.setValueAt(count, rowClick, 2);

					// ?????? ???? ?????? ????
					int price = 0;
					price = Integer.parseInt(tbl_Food.getValueAt(rowClick, 2).toString())
							* Integer.parseInt(tbl_Food.getValueAt(rowClick, 1).toString());
					tbl_Food.setValueAt(price, rowClick, 3);

					// ?????? 0?̵Ǹ? ????Ʈ???? ????
					if (count == 0) {
						model.removeRow(tbl_Food.getSelectedRow());
					}
				}
			}
		});

		add(btn_Minus);

		/** TextField - ???õȰ??????? **/
		text_Way = new JTextField("?????????? ?????ϼ???"); // ?ؽ?Ʈ?ʵ? ??????
		text_Way.setBounds(720, 495, 255, 40); // ??ġ?? ??????
		text_Way.setEditable(false); // ???? ????
		text_Way.setFont(font_M); // ?۾?ü

		panel.add(text_Way); // ?ǳڿ? textfield ?߰?

		/** ComboBox - ???????? ???? **/
		com_Way = new JComboBox<String>(); // ?޺??ڽ? ?ʱ?ȭ?ε? String ?????̾?
		com_Way.createToolTip();
		com_Way.setFont(font_M); // ??Ʈ
		com_Way.setBounds(720, 540, 255, 40); // ??ġ?? ??????

		com_Way.addItem("????");
		com_Way.addItem("ī??????");
		com_Way.setSelectedIndex(-1);
		com_Way.addItemListener(new ItemListener() { // ?޺??ڽ? ???? ??
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object a = e.getItem();
				text_Way.setText(e.getItem().toString());
			}
		});

		add(com_Way); // panel_MID?? com_Division ?߰?

		/** TextArea - ?ֹ??? ??û???? ?????ִ? **/
		area_Want = new JTextArea("??û???? ?Է?");
		area_Want.setFont(font_S);
		// area_Want.setBounds(720, 585, 255, 63); => ?????ϸ? ??ũ???? ?ȸ???
		area_Want.setLineWrap(true); // ?ڵ? ?ٹٲ?

		add(area_Want);

		/** ??ũ?? ???? **/
		scroll = new JScrollPane(area_Want);
		scroll.setBounds(720, 585, 255, 63);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		add(scroll); // ??ũ?? ?߰?

		/** Button - ??ǰ?ֹ? **/
		btn_Order = new JButton(FoodOrder);
		btn_Order.setSelectedIcon(FoodOrder_s);
		btn_Order.setRolloverIcon(FoodOrder_s);
		btn_Order.setBorderPainted(false);
		btn_Order.setFocusPainted(false);
		btn_Order.setContentAreaFilled(false);
		btn_Order.setBounds(1200 - 215, 460, 200, 200);
		btn_Order.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (text_Way.getText().equals("?????????? ?????ϼ???")) {
					JOptionPane.showMessageDialog(null, "?????????? ?????ϼ???", "????", JOptionPane.INFORMATION_MESSAGE);
				} else {
					int message = JOptionPane.showConfirmDialog(null, "??ǰ ?ֹ? ?Ͻðڽ??ϱ??", "??ǰ?ֹ?", 2);

					if (message == JOptionPane.YES_OPTION) {

						TableModel md = tbl_Food.getModel();
						for (int i = 0; i < tbl_Food.getRowCount(); i++) {
							FoodOrderDTO foDto = new FoodOrderDTO();

							foDto.setM_ID(m_id);
							foDto.setFO_NUM(dao.OrderNum(m_id) + 1);
							foDto.setS_NUM(s_num);
							foDto.setFO_PRICE(Integer.parseInt(md.getValueAt(i, 1).toString()));
							foDto.setFO_COUNT(Integer.parseInt(md.getValueAt(i, 2).toString()));
							foDto.setFO_PRODUCT(md.getValueAt(i, 0).toString());
							foDto.setFO_WAY(text_Way.getText().toString());
							foDto.setFO_RESULT("?????Ϸ?");
							if ((area_Want.getText().toString()).equals("??û???? ?Է?")) {
								foDto.setFO_MEMO("");
							} else {
								foDto.setFO_MEMO(area_Want.getText().toString());
							}

							/** db?? ?? insert ?ϱ? **/
							dao.OrderInsert(foDto);
						}
						JOptionPane.showMessageDialog(null, "?ֹ??? ?Ϸ??Ǿ????ϴ?", "?????Ϸ?", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

		add(btn_Order);

		/** TextArea - ???? ???? **/
		Area = new JTextArea();
		Area.setForeground(Color.WHITE);
		Area.setFont(font_M);
		Area.setBounds(5, 465, 980, 190);
		Area.disable();
		Area.setBackground(Color.GRAY);

		add(Area);
	}

	class MakeBtn extends JPanel {
		private JPanel panel = new JPanel();
		private String[] FoodsKind = null;
		private String[] images = null;
		private JButton[] btnImg = null;
		int height = 160;
		int[] x = { 190, 450, 710 };
		int y = 75;

		public MakeBtn(String str) {
			FoodsKind = fsDao.GetFoodsKind(str);
			images = fsDao.FoodImage(str);
			btnImg = new JButton[images.length];

			try {
				for (int i = 0; i < FoodsKind.length; i++) {
					File f = new File(images[i]);
					BufferedImage img_before = ImageIO.read(f);
					ImageIcon img_before_icon = new ImageIcon(img_before);
					Image img_before_i = img_before_icon.getImage();
					Image img_before_ch = img_before_i.getScaledInstance(300, 180, Image.SCALE_SMOOTH);
					ImageIcon img = new ImageIcon(img_before_ch);

					String sKind = FoodsKind[i];
					btnImg[i] = new JButton(img);
					btnImg[i].setBorder(new LineBorder(Color.GRAY, 5));
					btnImg[i].addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							Food_Stock_DTO fsDto = fsDao.GetFoodInfo(sKind);
							Object[] row = new Object[] { fsDto.getFS_PRODUCT(), fsDto.getFS_PRICE(), 1,
									Integer.parseInt(fsDto.getFS_PRICE()) };
							model.addRow(row);
						}
					});
					add(btnImg[i]);

					label = new JLabel(FoodsKind[i]);
					label.setFont(font_S);
					if (i % 3 == 0) {
						y += height;
					}
					label.setForeground(Color.WHITE);
					label.setBorder(new LineBorder(Color.GRAY, 5));

					add(label);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
