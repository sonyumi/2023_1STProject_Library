package mainFrame;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

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
	private JTextField[] search;
	private JLabel inpImage, infoDia;
	private Button infoButton, diaBt, diaDeleteBt;
	private Choice positionA, positionB;
	private Checkbox bookcode, bookname, bookwriter, publisher;
	private ArrayList<BookVo> list;
	private BookDAO dao = new BookDAO();
	private JTable tb1 = new JTable();
	private JScrollPane listScroll = new JScrollPane(tb1);
	private DefaultTableModel col;
	private String imglink = null;
	private MainFrame mf = new MainFrame();
	private Dialog dia;

	public BookSearchMenu() {
		LineBorder bb = new LineBorder(Color.black, 1, true);
		Font font1 = new Font("맑은고딕", Font.BOLD, 20);
		Font font2 = new Font("맑은고딕", Font.BOLD, 13);
		Font font3 = new Font("맑은고딕", Font.PLAIN, 13);
		JLabel bookSearch = new JLabel("도서검색");
		Button searchBt = new Button("검색");
		CheckboxGroup searchGroup = new CheckboxGroup();
		bookcode = new Checkbox("책코드", searchGroup, true);
		bookname = new Checkbox("책이름", searchGroup, true);
		bookwriter = new Checkbox("저자", searchGroup, true);
		publisher = new Checkbox("출판사", searchGroup, true);
		JLabel borderLine1 = new JLabel();

		setLayout(null);

		add(bookSearch);
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

		borderLine1.setBounds(30, 75, 522, 195);
		borderLine1.setBorder(bb);

		bookSearchTable("all", "all");
		bookInfomation();

		JLabel bookInfo = new JLabel("도서정보관리");
		JLabel infoBookCode = new JLabel("책코드");
		JLabel infoBookName = new JLabel("책이름");
		JLabel infoWriter = new JLabel("저자");
		JLabel infoPublisher = new JLabel("출판사");
		JLabel infoPosition = new JLabel("위치");
		JLabel infoLabel = new JLabel("※책코드는 수정이 불가능합니다.");
		Button modifyBt = new Button("책정보수정");
		Button addBt = new Button("책추가");
		Button deleteBt = new Button("책폐기");
		Button nullBt = new Button("초기화");

		search = new JTextField[5];
		for (int i = 0; i < search.length; i++) {
			search[i] = new JTextField();
			add(search[i]);
			search[i].setFont(font3);
		}

		positionA = new Choice();
		positionB = new Choice();
		JLabel borderLine2 = new JLabel();
		inpImage = new JLabel();
		Button imgAddBt = new Button("이미지첨부");

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
		add(positionA);
		add(positionB);
		add(imgAddBt);
		add(nullBt);
		add(inpImage);
		add(borderLine2);

		borderLine2.setBounds(30, 275, 522, 235);
		borderLine2.setBorder(bb);

		bookInfo.setBounds(230, 280, 160, 25);
		bookInfo.setFont(font1);

		infoBookCode.setBounds(45, 329, 50, 15);
		infoBookCode.setFont(font2);
		search[0].setBounds(90, 325, 100, 25);
		search[0].setEnabled(false);
		search[0].setText(getNewCode());

		infoBookName.setBounds(205, 329, 50, 15);
		infoBookName.setFont(font2);
		search[1].setBounds(250, 325, 100, 25);

		infoWriter.setBounds(55, 379, 30, 15);
		infoWriter.setFont(font2);
		search[2].setBounds(90, 375, 100, 25);

		infoPublisher.setBounds(205, 379, 50, 15);
		infoPublisher.setFont(font2);
		search[3].setBounds(250, 375, 100, 25);

		search[4].setBounds(155, 37, 200, 30);
		infoLabel.setBounds(55, 350, 150, 10);
		infoLabel.setFont(new Font("맑은고딕", Font.PLAIN, 9));
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

		modifyBt.setBounds(100, 470, 80, 30);
		modifyBt.addActionListener(this);

		addBt.setBounds(205, 470, 60, 30);
		addBt.addActionListener(this);

		deleteBt.setBounds(290, 470, 60, 30);
		deleteBt.addActionListener(this);

		nullBt.setBounds(35, 280, 50, 25);
		nullBt.addActionListener(this);

	}

	public void bookSearchTable(String code, String table) {
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

	}

	public void infoDialog(String s) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
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
		dia.setBounds(screenSize.width / 2 - 150, screenSize.height / 2 - 75, 300, 150);
		infoButton.setBounds(125, 102, 60, 25);
		infoDia.setText(s);
		infoButton.addActionListener(this);
		infoDia.setBounds(30, 50, 250, 30);
		dia.setVisible(true);
	}

	public void infoWainingDia() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		dia = new Dialog(dia);
		diaBt = new Button("취소하기");
		diaDeleteBt = new Button("삭제하기");
		infoDia = new JLabel("정말로 삭제하시겠습니까?");
		dia.add(diaBt);
		dia.add(diaDeleteBt);
		dia.setTitle("Info");
		dia.add(infoDia);
		dia.setModal(true);
		dia.setResizable(false);
		dia.setLayout(null);
		dia.setBounds(screenSize.width / 2 - 150, screenSize.height / 2 - 75, 300, 150);
		diaBt.addActionListener(this);
		diaBt.setBounds(170, 102, 65, 25);
		diaDeleteBt.setBounds(70, 102, 65, 25);
		diaDeleteBt.addActionListener(this);
		infoDia.setBounds(45, 50, 220, 30);
		dia.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (e.getComponent() == dia) {
					dia.dispose();
				}
			}
		});
		dia.setVisible(true);
	}

	public String getNewCode() {
		Instant instant = new Date().toInstant();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		String formatted = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime);
		formatted = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String getCode = formatted.toString();
		getCode = dao.getNewCode(getCode);
		return getCode;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		list = dao.list1("all", "all");
		String code1 = search[0].getText();
		String name1 = search[1].getText();
		String writer1 = search[2].getText();
		String publisher1 = search[3].getText();
		String position1 = positionA.getSelectedItem() + "-" + positionB.getSelectedItem();
		String imglink1 = imglink;

		if (e.getActionCommand().equals("초기화")) {
			search[0].setText(getNewCode());
			search[1].setText("");
			search[2].setText("");
			search[3].setText("");
			positionA.select(0);
			positionB.select(0);
			inpImage.setIcon(null);
		}

		if (e.getActionCommand().equals("이미지첨부")) {
			imglink = mf.getFileDialog();
			ImageIcon img = new ImageIcon(imglink);
			Image icon = img.getImage();
			Image changeImg = icon.getScaledInstance(125, 190, Image.SCALE_SMOOTH);
			inpImage.setIcon(new ImageIcon(changeImg));
		}

		if (e.getActionCommand().equals("책정보수정")) {
			if (imglink1 == null) {
				imglink1 = "";
			}
			try {
				if (name1.length() == 0) {
					infoDialog("책이름을 입력해주세요.");
				} else if (writer1.length() == 0) {
					infoDialog("저자를 입력해주세요.");
				} else if (publisher1.length() == 0) {
					infoDialog("출판사를 입력해주세요.");
				} else {
					list = dao.list(code1, name1, writer1, publisher1, position1, imglink1, 0);
					infoDialog("책 수정이 완료되었습니다.");
				}
			} catch (Exception ee) {
				ee.printStackTrace();
				infoDialog("책을 수정할 수 없습니다.");
			}
//			bookSearchTable("all", "all");
//			tb1.setVisible(true);
		}
		if (e.getActionCommand().equals("확인")) {
			dia.dispose();
		}
		tb1.setVisible(false);
		if (e.getActionCommand().equals("책추가")) {
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
				list = dao.list(code1, name1, writer1, publisher1, position1, imglink1, 1);
				infoDialog("책 추가가 완료되었습니다.");
				search[0].setText("");
				search[1].setText("");
				search[2].setText("");
				search[3].setText("");
				positionA.select(0);
				positionB.select(0);
				inpImage.setIcon(null);
			}
			bookSearchTable("all", "all");
		}
		if (e.getActionCommand().equals("책폐기")) {
			list = dao.list1(code1, "책코드");
			if (list.size() == 0) {
				infoDialog("현재 북 코드는 없는 책입니다.");
			} else {
				infoWainingDia();
			}
		}

		if (e.getActionCommand().equals("삭제하기")) {
			tb1.setVisible(false);
			list = dao.list1(code1, "책코드");
			if (code1.length() == 0) {
				dia.dispose();
				infoDialog("책 코드를 입력해주세요.");
			} else {
				if (list.get(0).getbReturn() == null && code1.equals(list.get(0).getCode())) {
					list = dao.list(code1, name1, writer1, publisher1, position1, imglink1, 2);
					dia.dispose();
					infoDialog("책 삭제가 완료되었습니다.");
					search[0].setText("");
					search[1].setText("");
					search[2].setText("");
					search[3].setText("");
					positionA.select(0);
					positionB.select(0);
					inpImage.setIcon(null);
					return;

				} else {
					dia.dispose();
					infoDialog("빌린내역이 있으면 삭제할 수 없습니다.");
					return;

				}
			}
		}
		if (e.getActionCommand().equals("취소하기")) {
			dia.dispose();
		}
		bookSearchTable("all", "all");

		if (e.getActionCommand().equals("검색")) {
			String seachText = search[4].getText();
			tb1.setVisible(false);
			if (seachText.length() != 0 && bookcode.getState()) {
				bookSearchTable(seachText, "책코드");
			} else if (seachText.length() != 0 && bookname.getState()) {
				bookSearchTable(seachText, "책이름");
			} else if (seachText.length() != 0 && bookwriter.getState()) {
				bookSearchTable(seachText, "저자");
			} else if (seachText.length() != 0 && publisher.getState()) {
				bookSearchTable(seachText, "출판사");
			} else {
				bookSearchTable("all", "all");
			}
		}

		tb1.setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String position = list.get(tb1.getSelectedRow()).getPosition();
		String p1 = String.valueOf(position.charAt(0));
		String p2 = String.valueOf(position.charAt(2));

		search[0].setText(list.get(tb1.getSelectedRow()).getCode());
		search[1].setText(list.get(tb1.getSelectedRow()).getName());
		search[2].setText(list.get(tb1.getSelectedRow()).getWriter());
		search[3].setText(list.get(tb1.getSelectedRow()).getPublisher());
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
