package mainFrame;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
public class RentalReturnMenu extends JPanel implements ActionListener ,MouseListener{

	private JTable tb1 = new JTable();
	private JTable tb2 = new JTable();
	private DefaultTableModel col;
	private JScrollPane listScroll1 = new JScrollPane(tb1);
	private JScrollPane listScroll2 = new JScrollPane(tb2);
	private BookDAO dao = new BookDAO();
	private UserBookDAO userDao = new UserBookDAO();
	private MemberVo userInfo;
	private JTextField inpSearch, inpbookCode;
	private Button searchBt, rentalBt, returnBt;
	private JLabel search, info, voidLabel, borderLine1, borderLine2;
	private CheckboxGroup searchGroup;
	private Checkbox bookcode, bookname, bookwriter, publisher;
	private Font font1, font2;
	private LineBorder bb = new LineBorder(Color.black, 1, true);
	private ArrayList<String> bookdata = new ArrayList<String>();

	RentalReturnMenu(MemberVo userInfo) {
		this.userInfo = userInfo;
		setLayout(null);
		setsearchIcon();
		returnPanel();
		setSearchTable1();
		rentalTable();
	}

	public void setsearchIcon() {
		searchGroup = new CheckboxGroup();
		search = new JLabel("도서검색");
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
		ArrayList<BookVo> list1 = dao.list(inp, code);
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
		ArrayList<BookVo> list1 = dao.list(inp, code);
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

	public void returnPanel() {
		inpbookCode = new JTextField();
		rentalBt = new Button("대여");
		returnBt = new Button("반납");
		info = new JLabel();
		voidLabel = new JLabel();
		font2 = new Font("맑은고딕", Font.PLAIN, 12);
		add(info);
		add(inpbookCode);
		add(rentalBt);
		add(returnBt);
		add(voidLabel);
		info.setText("※ 북코드로 대여/반납이 가능합니다. ");
		info.setFont(font2);
		info.setBounds(50, 325, 190, 30);
	}

	public void rentalTable() {
		borderLine2 = new JLabel();
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

		add(borderLine2);
		borderLine2.setBorder(bb);
		borderLine2.setBounds(35, 350, 510, 155);
		add(listScroll2);
		listScroll2.setSize(500, 145);
		listScroll2.setLocation(40, 355);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String seachText = inpSearch.getText();
		if (e.getActionCommand().equals("검색")) {
			tb1.setVisible(false);
			if (inpSearch.getText().length() != 0 && bookcode.getState()) {
				setSearchTable2(seachText, 1);
			} else if (inpSearch.getText().length() != 0 && bookname.getState()) {
				setSearchTable2(seachText, 2);
			} else if (inpSearch.getText().length() != 0 && bookwriter.getState()) {
				setSearchTable2(seachText, 3);
			} else if (inpSearch.getText().length() != 0 && publisher.getState()) {
				setSearchTable2(seachText, 4);
			}
			tb1.setVisible(true);
		}
	}
	public void mouseClicked(MouseEvent e) {
		System.out.println(tb1.getSelectedRow() + ":" + tb1.getSelectedColumn());
		System.out.println(tb1.getValueAt(tb1.getSelectedRow(), tb1.getSelectedColumn()));

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
