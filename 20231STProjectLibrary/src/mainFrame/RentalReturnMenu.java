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
	private JScrollPane listScroll1 = new JScrollPane(tb1);
	private JScrollPane listScroll2 = new JScrollPane(tb2);
	private BookDAO dao = new BookDAO();
	private UserBookDAO userDao = new UserBookDAO();
	private MemberVo userInfo;
	private JTextField inpSearch, inpbookCode;
	private Button infoCloseBt, diaBt, mapInfoCloseBt;
	private Checkbox[] searchBox; // 0=bookCode, 1=bookName, 2=bookWriter, 3=publisher
	private Checkbox rentalBook, returnBook;
	private LineBorder bb = new LineBorder(Color.black, 1, true);
	private ArrayList<BookVo> list1;
	private JFrame infoFrame = new JFrame("BookInfo");
	private JFrame infoMapFrame = new JFrame("Map Info");
	private Dialog dia;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Dimension screenSize = tk.getScreenSize();

	RentalReturnMenu(MemberVo userInfo) {
		this.userInfo = userInfo;
		setLayout(null);
		setsearchPanel();
		SearchTable("all", 0);
		rentalTable();
	}

	public void setsearchPanel() {
		CheckboxGroup searchGroup = new CheckboxGroup();
		JLabel search = new JLabel("도서검색");
		Button mapBt = new Button("위치확인");
		inpSearch = new JTextField();
		Button searchBt = new Button("검색");
		Font font1 = new Font("고딕", Font.BOLD, 15);
		String[] box = { "책코드", "책이름", "저자", "출판사" };
		searchBox = new Checkbox[4];
		for (int i = 0; i < searchBox.length; i++) {
			searchBox[i] = new Checkbox(box[i], searchGroup, true);
			add(searchBox[i]);
		}

		add(inpSearch);
		add(searchBt);
		inpSearch.setBounds(105, 35, 150, 30);
		JLabel borderLine1 = new JLabel();

		add(search);
		search.setBounds(30, 42, 90, 16);
		search.setFont(font1);

		searchBox[0].setBounds(60, 15, 50, 12);
		searchBox[1].setBounds(120, 15, 50, 12);
		searchBox[2].setBounds(180, 15, 40, 12);
		searchBox[3].setBounds(230, 15, 50, 12);

		add(searchBt);
		searchBt.setBounds(260, 35, 50, 30);
		searchBt.addActionListener(this);

		add(mapBt);
		mapBt.setBounds(450, 35, 80, 30);
		mapBt.addActionListener(this);

		add(borderLine1);
		borderLine1.setBorder(bb);
		borderLine1.setBounds(30, 75, 510, 205);

		inpbookCode = new JTextField();
		Button rentalBt = new Button("확인");
		JLabel borderLine2 = new JLabel();
		JLabel info = new JLabel();
		JLabel voidLabel = new JLabel("대여반납");
		CheckboxGroup bookGroup = new CheckboxGroup();
		rentalBook = new Checkbox("대여", bookGroup, true);
		returnBook = new Checkbox("반납", bookGroup, true);
		Font font2 = new Font("맑은고딕", Font.PLAIN, 12);
		add(rentalBook);
		add(returnBook);
		add(info);
		add(inpbookCode);
		add(rentalBt);
		add(voidLabel);
		add(borderLine2);
		borderLine2.setBorder(bb);
		borderLine2.setBounds(35, 370, 490, 135);
		info.setText("※ 책코드로 대여/반납이 가능합니다. ");
		info.setFont(font2);
		rentalBook.setBounds(60, 299, 50, 12);
		returnBook.setBounds(120, 299, 50, 12);
		info.setBounds(50, 345, 190, 30);
		inpbookCode.setBounds(105, 319, 150, 30);
		rentalBt.setBounds(260, 319, 50, 30);
		rentalBt.addActionListener(this);
		voidLabel.setBounds(32, 325, 90, 16);
		voidLabel.setFont(font1);

	}

	public void SearchTable(String inp, int code) {
		String[] field1 = { "책코드", "책이름", "저자", "출판사", "위치", "대여가능여부" };
		tb1.setSize(200, 200);
		tb1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tb1.addMouseListener(this);
		list1 = dao.list(inp, code);
		DefaultTableModel col = new DefaultTableModel(field1, 0);
		add(listScroll1);
		listScroll1.setBounds(35, 80, 500, 195);
		for (BookVo bo : list1) {
			Object[] row = { bo.getCode(), bo.getName(), bo.getWriter(), bo.getPublisher(), bo.getPosition(),
					bo.getbReturn() };
			col.addRow(row);
		}
		tb1.setModel(col);

	}

	public void rentalTable() {
		ArrayList<BookVo> list = userDao.list(userInfo);
		String[] field2 = { "책번호", "책이름", "저자", "출판사", "대여일", "대여일수" };
		DefaultTableModel col = new DefaultTableModel(field2, 0);
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
	public void bookInfo(String imglink) {
		infoFrame = new JFrame("BookInfo");
		infoCloseBt = new Button("닫기");
		JLabel infoImg = new JLabel("");
		ImageIcon img = new ImageIcon(imglink);
		Image icon = img.getImage();
		Image changeImg = icon.getScaledInstance(125, 190, Image.SCALE_SMOOTH);
		infoImg.setIcon(new ImageIcon(changeImg));
		infoFrame.setLocation(screenSize.width / 2 + 260, screenSize.height / 2 - 300);
		infoFrame.setSize(200, 300);
		infoFrame.add(infoImg);
		infoFrame.add(infoCloseBt);
		infoCloseBt.setBounds(70, 225, 50, 20);
		infoCloseBt.addActionListener(this);
		infoCloseBt.setActionCommand("BookImgClose");
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
		mapInfoCloseBt = new Button("닫기");
		JLabel infoMapImg = new JLabel("");
		String imglink = "..\\20231STProjectLibrary\\img\\map.png";
		infoMapImg.setIcon(new ImageIcon(imglink));
		infoMapFrame.setLocation(screenSize.width / 2 + 200, screenSize.height / 2 - 300);
		infoMapFrame.setSize(315, 370);
		infoMapFrame.add(infoMapImg);
		infoMapFrame.add(mapInfoCloseBt);
		mapInfoCloseBt.setBounds(132, 305, 50, 20);
		mapInfoCloseBt.addActionListener(this);
		mapInfoCloseBt.setActionCommand("MapImgClose");
		infoMapFrame.setLayout(null);
		infoMapFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (e.getComponent() == infoMapFrame) {
					infoMapFrame.dispose();
				}
			}
		});
		infoMapImg.setBounds(0, 0, 300, 300);
		infoMapFrame.setVisible(true);
	}

	// 대여/반납 성공/실패 dialog 창
	public void bookRentalDialog(String s) {
		dia = new Dialog(dia);
		diaBt = new Button("닫기");
		JLabel diaInfo = new JLabel(s);
		dia.add(diaBt);
		dia.setTitle("Info");
		dia.add(diaInfo);
		dia.setModal(true);
		dia.setResizable(false);
		dia.setLayout(null);
		dia.setBounds(screenSize.width / 2 - 150, screenSize.height / 2 - 75, 300, 150);
		diaBt.addActionListener(this);
		diaBt.setActionCommand("DialogClose");
		diaBt.setBounds(125, 102, 50, 25);
		diaInfo.setBounds(35, 50, 225, 30);
		dia.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (e.getComponent() == dia) {
					dia.dispose();
				}
			}
		});
		dia.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("위치확인")) {
			if (infoMapFrame.isShowing()) {
				infoMapFrame.dispose();
				mapInfo();
			} else {
				mapInfo();
			}

		}

		if (e.getActionCommand().equals("검색")) {
			String seachText = inpSearch.getText();
			tb1.setVisible(false);
			if (inpSearch.getText().length() != 0 && searchBox[0].getState()) {
				SearchTable(seachText, 1);
			} else if (inpSearch.getText().length() != 0 && searchBox[1].getState()) {
				SearchTable(seachText, 2);
			} else if (inpSearch.getText().length() != 0 && searchBox[2].getState()) {
				SearchTable(seachText, 3);
			} else if (inpSearch.getText().length() != 0 && searchBox[3].getState()) {
				SearchTable(seachText, 4);
			} else {
				SearchTable("all", 0);
			}

			tb1.setVisible(true);
		}

		if (e.getActionCommand().equals("BookImgClose")) {
			infoFrame.dispose();
		}
		if (e.getActionCommand().equals("MapImgClose")) {
			infoMapFrame.dispose();
		}
		if (e.getActionCommand().equals("DialogClose")) {
			dia.dispose();
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
						bookRentalDialog("책 대여가 완료되었습니다.");
					} else if (value.equals("없음")) {
						bookRentalDialog("이미 대여된 책 입니다.");
					} else {
						bookRentalDialog("대여에 실패하였습니다.");

					}
				} else if (inpbookCode.getText().length() != 0 && returnBook.getState()) {
					value = userDao.getReturnValue(bookCode, 1, userInfo.getId());
					if (value.equals("성공")) {

						// 반납 성공 창 띄우기
						bookRentalDialog("책 반납이 완료되었습니다.");

					} else if (value.equals("없음")) {
						// 대여처리가 안됐을때
						bookRentalDialog("대여처리가 안 된 책입니다.");
					} else {
						bookRentalDialog("반납에 실패하였습니다.");
						// 반납 실패 창 띄우기
					}
				}
			} catch (Exception ee) {
				bookRentalDialog("올바른 책코드값을 입력해 주세요.");
			}
			rentalTable();
			tb2.setVisible(true);
		}

	}

	public void mouseClicked(MouseEvent e) {
		int rowValue = tb1.getSelectedRow();
		String imglink = list1.get(rowValue).getImgLink();
		if (infoFrame.isVisible()) {
			infoFrame.dispose();
			bookInfo(imglink);
		} else {
			bookInfo(imglink);
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
