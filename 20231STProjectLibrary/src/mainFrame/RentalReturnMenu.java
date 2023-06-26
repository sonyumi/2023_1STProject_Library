package mainFrame;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
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
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import login.MemberVo;

@SuppressWarnings("serial")
public class RentalReturnMenu extends JPanel implements ActionListener, MouseListener {

	private JTable tb1 = new JTable();
	private JTable tb2 = new JTable();
	private DefaultTableModel col;
	private JScrollPane listScroll1 = new JScrollPane(tb1);
	private JScrollPane listScroll2 = new JScrollPane(tb2);
	private BookDAO dao = new BookDAO();
	private UserBookDAO userDao = new UserBookDAO();
	private MemberVo userInfo;
	private JTextField inpSearch, inpbookCode;
	private Button searchBt, rentalBt, infoCloseBt, diaBt, mapBt, mapInfoCloseBt;
	private JLabel search, info, voidLabel, borderLine1, borderLine2, infoImg, diaInfo, infoMapImg;
	private CheckboxGroup searchGroup, bookGroup;
	private Checkbox bookcode, bookname, bookwriter, publisher, rentalBook, returnBook;
	private Font font1, font2;
	private LineBorder bb = new LineBorder(Color.black, 1, true);
	private ArrayList<BookVo> list1;
	private JFrame infoFrame = new JFrame("Map Info");
	private JFrame infoMapFrame;
	private Dialog dia;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Dimension screenSize = tk.getScreenSize();

	RentalReturnMenu(MemberVo userInfo) {
		tk = Toolkit.getDefaultToolkit();
		screenSize = tk.getScreenSize();
		this.userInfo = userInfo;
		setLayout(null);
		setsearchPanel();
		setSearchTable1();
		rentalPanel();
		rentalTable();
	}

	public void setsearchPanel() {
		searchGroup = new CheckboxGroup();
		search = new JLabel("도서검색");
		mapBt = new Button("위치확인");
		bookcode = new Checkbox("북코드", searchGroup, true);
		bookname = new Checkbox("책이름", searchGroup, true);
		bookwriter = new Checkbox("저자", searchGroup, true);
		publisher = new Checkbox("출판사", searchGroup, true);
		inpSearch = new JTextField();
		searchBt = new Button("검색");
		font1 = new Font("고딕", Font.BOLD, 15);
		
		add(inpSearch);
		add(searchBt);
		inpSearch.setBounds(105, 35, 150, 30);
		borderLine1 = new JLabel();

		add(search);
		search.setBounds(30, 42, 90, 16);
		search.setFont(font1);

		add(bookcode);
		add(bookname);
		add(bookwriter);
		add(publisher);
		
		bookcode.setBounds(60, 15, 50, 12);
		bookname.setBounds(120, 15, 50, 12);
		bookwriter.setBounds(180, 15, 40, 12);
		publisher.setBounds(230, 15, 50, 12);

		add(searchBt);
		searchBt.setBounds(260, 35, 50, 30);
		searchBt.addActionListener(this);

		add(mapBt);
		mapBt.setBounds(450, 35, 80, 30);
		mapBt.addActionListener(this);

		add(borderLine1);
		borderLine1.setBorder(bb);
		borderLine1.setBounds(30, 75, 510, 205);

	}

	public void setSearchTable1() {
		String[] field1 = { "책코드", "책이름", "저자", "출판사", "위치", "대여가능여부" };
		String inp = "all";
		int code = 0;
		tb1.setBounds(35, 80, 500, 195);
		tb1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tb1.addMouseListener(this);
		list1 = dao.list(inp, code);
		col = new DefaultTableModel(field1, 0);
//		JScrollPane listScroll1 = new JScrollPane(tb1);
		for (BookVo vo : list1) {
			Object[] row = { vo.getCode(), vo.getName(), vo.getWriter(), vo.getPublisher(), vo.getPosition(),
					vo.getbReturn() };
			col.addRow(row);
		}
		tb1.setModel(col);
		add(listScroll1);
		listScroll1.setBounds(35, 80, 500, 195);
	}

	public void setSearchTable2(String inp, int code) {
		String[] field1 = { "책코드", "책이름", "저자", "출판사", "위치", "대여가능여부" };
		tb1.setSize(200, 200);

		tb1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tb1.addMouseListener(this);
		list1 = dao.list(inp, code);
		col = new DefaultTableModel(field1, 0);
		add(listScroll1);
		listScroll1.setBounds(35, 80, 500, 195);
		for (BookVo bo : list1) {
			Object[] row = { bo.getCode(), bo.getName(), bo.getWriter(), bo.getPublisher(), bo.getPosition(),
					bo.getbReturn() };
			col.addRow(row);
		}
		tb1.setModel(col);

	}

	public void rentalPanel() {
		inpbookCode = new JTextField();
		rentalBt = new Button("확인");
		borderLine2 = new JLabel();
//		returnBt = new Button("반납");
		info = new JLabel();
		voidLabel = new JLabel("대출반납");
		bookGroup = new CheckboxGroup();
		rentalBook = new Checkbox("대출", bookGroup, true);
		returnBook = new Checkbox("반납", bookGroup, true);
		font2 = new Font("맑은고딕", Font.PLAIN, 12);
		add(rentalBook);
		add(returnBook);
		add(info);
		add(inpbookCode);
		add(rentalBt);
//		add(returnBt);
		add(voidLabel);
		add(borderLine2);
		borderLine2.setBorder(bb);
		borderLine2.setBounds(35, 370, 490, 135);
		info.setText("※ 북코드로 대여/반납이 가능합니다. ");
		info.setFont(font2);
		rentalBook.setBounds(60, 299, 50, 12);
		returnBook.setBounds(120, 299, 50, 12);
		info.setBounds(50, 345, 190, 30);
		inpbookCode.setBounds(105, 319, 150, 30);
		rentalBt.setBounds(260, 319, 50, 30);
		rentalBt.addActionListener(this);
		voidLabel.setBounds(32, 325, 90, 16);
		voidLabel.setFont(font1);
//		returnBt.setBounds(315, 295, 50, 30);

	}

	public void rentalTable() {
		ArrayList<BookVo> list = userDao.list(userInfo);
		String[] field2 = { "책번호", "책이름", "저자", "출판사", "대여일", "대여일수" };
		col = new DefaultTableModel(field2, 0);
		tb2.setSize(200, 200);
		for (BookVo vo : list) {
			Object[] row = { vo.getCode(), vo.getName(), vo.getWriter(), vo.getPublisher(), vo.getRental(),
					vo.getDays() };
			col.addRow(row);
		}
		tb2.setModel(col);
		add(listScroll2);
		listScroll2.setSize(480, 125);
		listScroll2.setLocation(40, 375);

	}

	// 검색 테이블 클릭시 책 이미지 프레임
	public void bookInfo(int i) {
		infoFrame = new JFrame("BookInfo");
		infoCloseBt = new Button("닫기");
		infoImg = new JLabel("");
		ImageIcon img = new ImageIcon(list1.get(i).getImgLink());
		Image icon = img.getImage();
		Image changeImg = icon.getScaledInstance(125, 190, Image.SCALE_SMOOTH);
		infoImg.setIcon(new ImageIcon(changeImg));
		infoFrame.setLocation(screenSize.width / 2 + 260, screenSize.height / 2 - 300);
		infoFrame.setSize(200, 300);
		infoFrame.add(infoImg);
		infoFrame.add(infoCloseBt);
		infoCloseBt.setBounds(70, 225, 50, 20);
		infoCloseBt.addActionListener(this);
		infoFrame.setLayout(null);
		infoFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (e.getComponent() == infoFrame) {
					infoFrame.dispose();
				}
			}
		});
		infoFrame.setResizable(false);
		infoImg.setBounds(32, 20, 125, 190);
		infoFrame.setVisible(true);
	}

	public void mapInfo() {
		infoMapFrame = new JFrame("Map Info");
		mapInfoCloseBt = new Button("닫기");
		infoMapImg = new JLabel("");
		String imglink = "..\\20231STProjectLibrary\\img\\map.png";
		infoMapImg.setIcon(new ImageIcon(imglink));
		infoMapFrame.setLocation(screenSize.width / 2 + 200, screenSize.height / 2 - 300);
		infoMapFrame.setSize(315, 370);
		infoMapFrame.add(infoMapImg);
		infoMapFrame.add(mapInfoCloseBt);
		mapInfoCloseBt.setBounds(132, 305, 50, 20);
		mapInfoCloseBt.addActionListener(this);
		infoMapFrame.setLayout(null);
		infoMapFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (e.getComponent() == infoMapFrame) {
					infoMapFrame.dispose();
				}
			}
		});
		// infoMapFrame.setResizable(false);
		infoMapImg.setBounds(0, 0, 300, 300);
		infoMapFrame.setVisible(true);
	}

	// 대출/반납 성공/실패 dialog 창
	public void bookRentalDialog(String s) {
		dia = new Dialog(dia);
		diaBt = new Button("닫기");
		diaInfo = new JLabel(s);
		dia.add(diaBt);
		dia.setTitle("Info");
		dia.add(diaInfo);
		dia.setModal(true);
		dia.setResizable(false);
		dia.setLayout(null);
		dia.setBounds(screenSize.width / 2 - 150, screenSize.height / 2 - 75, 300, 150);
		diaBt.addActionListener(this);
		diaBt.setBounds(125, 102, 50, 25);
		diaInfo.setBounds(55, 50, 185, 30);
		dia.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (e.getComponent() == infoFrame) {
					dia.dispose();
				}
			}
		});
		dia.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("위치확인")) {
			mapInfo();
		}

		if (e.getActionCommand().equals("검색")) {
			String seachText = inpSearch.getText();
			tb1.setVisible(false);
			if (inpSearch.getText().length() != 0 && bookcode.getState()) {
				setSearchTable2(seachText, 1);
			} else if (inpSearch.getText().length() != 0 && bookname.getState()) {
				setSearchTable2(seachText, 2);
			} else if (inpSearch.getText().length() != 0 && bookwriter.getState()) {
				setSearchTable2(seachText, 3);
			} else if (inpSearch.getText().length() != 0 && publisher.getState()) {
				setSearchTable2(seachText, 4);
			} else {
				setSearchTable2("all", 0);
			}

			tb1.setVisible(true);
		}

		if (e.getActionCommand().equals("닫기")) {
			if (e.getSource() == infoCloseBt) {
				infoFrame.dispose();
			}
			if (e.getSource() == mapInfoCloseBt) {
				infoMapFrame.dispose();
			}
			if (e.getSource() == diaBt) {
				dia.dispose();
			}
		}

		if (e.getActionCommand().equals("확인")) {
			System.out.println("확인");
			try {
				String bookCode = inpbookCode.getText();
				String value = null;
				if (inpbookCode.getText().length() != 0 && rentalBook.getState()) {
					tb2.setVisible(false);
					value = userDao.getReturnValue(bookCode, 0, userInfo.getId());
					if (value.equals("성공")) {
						// 대출 성공 창 띄우기
						bookRentalDialog("책 대출이 완료되었습니다.");

					} else if (value.equals("없음")) {
						bookRentalDialog("이미 대출된 책 입니다.");
					} else {
						System.out.println("대출에 실패하였습니다.");
						bookRentalDialog("대출에 실패하였습니다.");

					}
				} else if (inpbookCode.getText().length() != 0 && returnBook.getState()) {
					value = userDao.getReturnValue(bookCode, 1, userInfo.getId());
					System.out.println(value);
					if (value.equals("성공")) {

						// 반납 성공 창 띄우기
						System.out.println("반납성공");
						bookRentalDialog("책 반납이 완료되었습니다.");

					} else if (value.equals("없음")) {
						// 북 코드값이 잘못되엇습니다 창 띄우기
						System.out.println("북 코드값 에러");
						bookRentalDialog("대출처리가 안 된 책입니다.");
					} else {
						System.out.println("반납실패");
						bookRentalDialog("반납에 실패하였습니다.");
						// 반납 실패 창 띄우기
					}
				}

			} catch (Exception ee) {
				System.out.println("올바른 북 코드값을 입력해 주세요");
				bookRentalDialog("올바른 북 코드값을 입력해 주세요.");
			}
			rentalTable();
			tb2.setVisible(true);
		}

	}

	public void mouseClicked(MouseEvent e) {
		System.out.println(tb1.getSelectedRow() + ":" + tb1.getSelectedColumn());
		System.out.println(tb1.getValueAt(tb1.getSelectedRow(), tb1.getSelectedColumn()));

		System.out.println(list1.get(tb1.getSelectedRow()).getImgLink());
		int rowValue = tb1.getSelectedRow();
		if (infoFrame.isShowing()) {
			infoFrame.dispose();
			bookInfo(rowValue);
		} else {
			bookInfo(rowValue);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
