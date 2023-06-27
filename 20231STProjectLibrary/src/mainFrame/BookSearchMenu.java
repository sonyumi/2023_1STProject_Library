package mainFrame;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class BookSearchMenu extends JPanel implements ActionListener, MouseListener {
	private JTextField inpBookSearch, inpBookCode, inpBookName, inpWriter, inpPublisher;
	private JLabel bookSearch, borderLine1, borderLine2, bookInfo, infoBookCode, infoBookName, infoWriter,
			infoPublisher, infoPosition, infoLabel, inpImage, infoDia;
	private Button searchBt, modifyBt, addBt, deleteBt, imgAddBt, infoButton;
	private Choice positionA, positionB;
	private Checkbox bookcode, bookname, bookwriter, publisher;
	private CheckboxGroup searchGroup;
	private ArrayList<BookVo> list;
	private BookDAO dao = new BookDAO();
	private JTable tb1 = new JTable();
	private JScrollPane listScroll = new JScrollPane(tb1);
	private DefaultTableModel col;
	private Font font1, font2, font3;
	private String code = null;
	private String table = "all";
	private LineBorder bb = new LineBorder(Color.black, 1, true);
	private String imglink = null;
	private MainFrame mf = new MainFrame();
	private Dialog dia;

	public BookSearchMenu() {
//		ArrayList<BookVo> list = dao.list(code);
		font1 = new Font("맑은고딕", Font.BOLD, 20);
		font2 = new Font("맑은고딕", Font.BOLD, 13);
		font3 = new Font("맑은고딕", Font.PLAIN, 13);
		bookSearch = new JLabel("도서검색");
		inpBookSearch = new JTextField();
		searchBt = new Button("검색");
		searchGroup = new CheckboxGroup();
		bookcode = new Checkbox("책코드", searchGroup, true);
		bookname = new Checkbox("책이름", searchGroup, true);
		bookwriter = new Checkbox("저자", searchGroup, true);
		publisher = new Checkbox("출판사", searchGroup, true);
		borderLine1 = new JLabel();

		setLayout(null);

		add(bookSearch);
		add(inpBookSearch);
		add(searchBt);
		add(bookcode);
		add(bookname);
		add(bookwriter);
		add(publisher);
		add(borderLine1);

		bookSearch.setBounds(65, 37, 200, 30);
		bookSearch.setFont(font1);
		bookcode.setBounds(155, 15, 55, 12);
		bookname.setBounds(220, 15, 55, 12);
		bookwriter.setBounds(285, 15, 40, 12);
		publisher.setBounds(335, 15, 55, 12);

		searchBt.setBounds(360, 35, 50, 30);
		searchBt.addActionListener(this);

		inpBookSearch.setBounds(155, 37, 200, 30);

		borderLine1.setBounds(30, 75, 522, 195);
		borderLine1.setBorder(bb);

		bookSearchTable1();
		bookInfomation();

	}

	public void bookSearchTable1() {
		list = dao.list1(code, table);
		String[] field = { "책코드", "책이름", "저자", "출판사", "위치", "반납예상일" };
		tb1.setSize(200, 200);
		tb1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tb1.addMouseListener(this);
		col = new DefaultTableModel(field, 0);
		for (BookVo vo : list) {
			Object[] row = { vo.getCode(), vo.getName(), vo.getWriter(), vo.getPublisher(), vo.getPosition(),
					vo.getbReturn() };
			col.addRow(row);
		}

		tb1.setModel(col);
		add(listScroll);
		listScroll.setBounds(35, 80, 512, 185);
	}

	public void bookSearchTable2(String code, String table) {
		this.code = code;
		this.table = table;
		list = dao.list1(code, table);
		String[] field = { "책코드", "책이름", "저자", "출판사", "위치", "반납예상일" };
		tb1.setSize(200, 200);
		tb1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tb1.addMouseListener(this);
		col = new DefaultTableModel(field, 0);
		for (BookVo vo : list) {
			Object[] row = { vo.getCode(), vo.getName(), vo.getWriter(), vo.getPublisher(), vo.getPosition(),
					vo.getbReturn() };
			col.addRow(row);
		}

		tb1.setModel(col);
		add(listScroll);
		listScroll.setBounds(35, 80, 512, 185);
	}

	public void bookInfomation() {
		bookInfo = new JLabel("도서정보관리");
		infoBookCode = new JLabel("책코드");
		infoBookName = new JLabel("책이름");
		infoWriter = new JLabel("저자");
		infoPublisher = new JLabel("출판사");
		infoPosition = new JLabel("위치");
		infoLabel = new JLabel("※책코드는 수정이 불가능합니다.");
		modifyBt = new Button("수정하기");
		addBt = new Button("추가하기");
		deleteBt = new Button("삭제하기");
		inpBookCode = new JTextField();
		inpBookName = new JTextField();
		inpWriter = new JTextField();
		inpPublisher = new JTextField();
		positionA = new Choice();
		positionB = new Choice();
		borderLine2 = new JLabel();
		inpImage = new JLabel();
		imgAddBt = new Button("이미지첨부");

		add(bookInfo);
		add(infoBookCode);
		add(infoBookName);
		add(infoWriter);
		add(infoPublisher);
		add(infoPosition);
		add(infoLabel);
		add(modifyBt);
		add(addBt);
		add(deleteBt);
		add(inpBookCode);
		add(inpBookName);
		add(inpWriter);
		add(inpPublisher);
		add(positionA);
		add(positionB);
		add(imgAddBt);
		add(inpImage);
		add(borderLine2);

		borderLine2.setBounds(30, 275, 522, 235);
		borderLine2.setBorder(bb);

		bookInfo.setBounds(230, 280, 160, 25);
		bookInfo.setFont(font1);

		infoBookCode.setBounds(45, 329, 50, 15);
		infoBookCode.setFont(font2);
		inpBookCode.setBounds(90, 325, 100, 25);
		inpBookCode.setFont(font3);

		infoBookName.setBounds(205, 329, 50, 15);
		infoBookName.setFont(font2);
		inpBookName.setBounds(250, 325, 100, 25);
		inpBookName.setFont(font3);

		infoWriter.setBounds(55, 379, 30, 15);
		infoWriter.setFont(font2);
		inpWriter.setBounds(90, 375, 100, 25);
		inpWriter.setFont(font3);

		infoPublisher.setBounds(205, 379, 50, 15);
		infoPublisher.setFont(font2);
		inpPublisher.setBounds(250, 375, 100, 25);
		inpPublisher.setFont(font3);

		infoPosition.setBounds(55, 429, 30, 15);
		infoPosition.setFont(font2);
		positionA.setBounds(95, 425, 35, 25);
		positionA.setFont(font3);
		positionA.add("A");
		positionA.add("B");
		positionA.add("C");
		positionA.add("D");

		positionB.setBounds(145, 425, 35, 25);
		positionB.setFont(font3);
		positionB.add("1");
		positionB.add("2");
		positionB.add("3");
		positionB.add("4");
		positionB.add("5");
		positionB.add("6");
		positionB.add("7");

		inpImage.setBorder(bb);
		inpImage.setBounds(400, 298, 125, 190);

		imgAddBt.setBounds(433, 475, 60, 20);
		imgAddBt.setFont(new Font("맑은고딕", Font.PLAIN, 10));
		imgAddBt.addActionListener(this);

		modifyBt.setBounds(100, 470, 60, 30);
		modifyBt.addActionListener(this);

		addBt.setBounds(190, 470, 60, 30);
		addBt.addActionListener(this);

		deleteBt.setBounds(280, 470, 60, 30);
		deleteBt.addActionListener(this);

	}

	public void infoDialog(String s) {
		dia = new Dialog(dia, "Infomation", true);
		infoDia = new JLabel();
		infoButton = new Button("확인");
		dia.setResizable(false);
		dia.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (e.getComponent() == dia) {
					dia.dispose();
				}
			}
		});
		dia.add(infoDia);
		dia.add(infoButton);
		dia.setLayout(null);
		dia.setBounds(600, 300, 300, 150);
		infoDia.setFont(font3);
		infoButton.setBounds(125, 102, 60, 25);
		infoDia.setText(s);
		infoButton.addActionListener(this);
		infoDia.setBounds(30, 50, 250, 30);
		dia.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		list = dao.list1("all", "all");
		String code1 = inpBookCode.getText();
		String name1 = inpBookName.getText();
		String writer1 = inpWriter.getText();
		String publisher1 = inpPublisher.getText();
		String position1 = positionA.getSelectedItem() + "-" + positionB.getSelectedItem();
		String imglink1 = imglink;
		if (e.getActionCommand().equals("검색")) {
			String seachText = inpBookSearch.getText();
			tb1.setVisible(false);
			if (inpBookSearch.getText().length() != 0 && bookcode.getState()) {
				bookSearchTable2(seachText, "책코드");
			} else if (inpBookSearch.getText().length() != 0 && bookname.getState()) {
				bookSearchTable2(seachText, "책이름");
			} else if (inpBookSearch.getText().length() != 0 && bookwriter.getState()) {
				bookSearchTable2(seachText, "저자");
			} else if (inpBookSearch.getText().length() != 0 && publisher.getState()) {
				bookSearchTable2(seachText, "출판사");
			} else {
				bookSearchTable2("all", "all");
			}
			tb1.setVisible(true);
		}
		if (e.getActionCommand().equals("이미지첨부")) {
			imglink = mf.getFileDialog();
			ImageIcon img = new ImageIcon(imglink);
			Image icon = img.getImage();
			Image changeImg = icon.getScaledInstance(125, 190, Image.SCALE_SMOOTH);
			inpImage.setIcon(new ImageIcon(changeImg));
		}

		if (e.getActionCommand().equals("수정하기")) {
			tb1.setVisible(false);
			if (imglink1 == null) {
				imglink1 = "";
			}
			// try {
			if (code1.length() == 0) {
				infoDialog("책 코드를 입력해주세요.");
			} else if (code1.length() > 12) {
				infoDialog("책 길이는 11자 이내로 입력해주세요.");
			} else if (code1.equals(list.get(tb1.getSelectedRow()).getCode()) == false) {
				infoDialog("책 코드는 수정 할 수 없습니다.");
			} else if (name1.length() == 0) {
				infoDialog("책 이름을 입력해주세요.");
			} else if (writer1.length() == 0) {
				infoDialog("저자를 입력해주세요.");
			} else if (publisher1.length() == 0) {
				infoDialog("출판사를 입력해주세요.");
			} else {
				System.out.println("-----------------");
				System.out.println(code1);
				System.out.println(name1);
				System.out.println(writer1);
				System.out.println(publisher1);
				System.out.println(position1);
				System.out.println(imglink1);
				list = dao.list(code1, name1, writer1, publisher1, position1, imglink1, 0);
				infoDialog("책 수정이 완료되었습니다.");
			}
			// } catch (Exception ee) {
			// infoDialog("책을 수정할 수 없습니다.");
			// }
			bookSearchTable2("all", "all");
			tb1.setVisible(true);
		}
		if (e.getActionCommand().equals("확인")) {
			dia.dispose();
		}
		if (e.getActionCommand().equals("추가하기")) {
			tb1.setVisible(false);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getCode().equals(code1)) {
					infoDialog("같은 책 코드가 이미 존재합니다.");
					return;
				}
			}
			if (imglink1 == null) {
				imglink1 = "";
			}
			if (code1.length() == 0) {
				infoDialog("책 코드를 입력해주세요.");
			} else if (code1.length() > 12) {
				infoDialog("책 길이는 11자 이내로 입력해주세요.");
			} else if (name1.length() == 0) {
				infoDialog("책 이름을 입력해주세요.");
			} else if (writer1.length() == 0) {
				infoDialog("저자를 입력해주세요.");
			} else if (publisher1.length() == 0) {
				infoDialog("출판사를 입력해주세요.");
			} else {
				System.out.println("-----------------");
				System.out.println(code1);
				System.out.println(name1);
				System.out.println(writer1);
				System.out.println(publisher1);
				System.out.println(position1);
				System.out.println(imglink1);
				list = dao.list(code1, name1, writer1, publisher1, position1, imglink1, 1);
				infoDialog("책 추가가 완료되었습니다.");
				inpBookCode.setText("");
				inpBookName.setText("");
				inpWriter.setText("");
				inpPublisher.setText("");
				positionA.select(0);
				positionB.select(0);
				inpImage.setIcon(null);
			}
			bookSearchTable2("all", "all");
			tb1.setVisible(true);
		}

		if (e.getActionCommand().equals("삭제하기")) {
			tb1.setVisible(false);
			list = dao.list1(code1, "책코드");
			if (code1.length() == 0) {
				infoDialog("책 코드를 입력해주세요.");
			} else {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getbReturn()==null) {
						if (code1.equals(list.get(i).getCode())) {
						list = dao.list(code1, name1, writer1, publisher1, position1, imglink1, 2);
							infoDialog("책 삭제가 완료되었습니다.");
							inpBookCode.setText("");
							inpBookName.setText("");
							inpWriter.setText("");
							inpPublisher.setText("");
							positionA.select(0);
							positionB.select(0);
							inpImage.setIcon(null);
							return;
						}
					} else {
						infoDialog("빌린내역이 있으면 삭제할 수 없습니다.");
						return;
					}
				}
			}
			bookSearchTable2("all", "all");
			tb1.setVisible(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(tb1.getSelectedRow() + ":" + tb1.getSelectedColumn());
		System.out.println(tb1.getValueAt(tb1.getSelectedRow(), tb1.getSelectedColumn()));
		System.out.println(list.get(tb1.getSelectedRow()).getImgLink());

		String position = list.get(tb1.getSelectedRow()).getPosition();
		String p1 = String.valueOf(position.charAt(0));
		String p2 = String.valueOf(position.charAt(2));

		inpBookCode.setText(list.get(tb1.getSelectedRow()).getCode());
		inpBookName.setText(list.get(tb1.getSelectedRow()).getName());
		inpWriter.setText(list.get(tb1.getSelectedRow()).getWriter());
		inpPublisher.setText(list.get(tb1.getSelectedRow()).getPublisher());
		positionA.select(p1);
		positionB.select(p2);
		imglink = list.get(tb1.getSelectedRow()).getImgLink();
		ImageIcon img = new ImageIcon(imglink);
		Image icon = img.getImage();
		Image changeImg = icon.getScaledInstance(125, 190, Image.SCALE_SMOOTH);
		inpImage.setIcon(new ImageIcon(changeImg));

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
