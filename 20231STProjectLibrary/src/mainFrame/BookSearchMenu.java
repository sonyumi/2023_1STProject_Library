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

import javax.swing.JFrame;
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
	private JFrame book;
	private JTextField inpBookSearch;
	private JLabel borderLine;
	private Button searchBt, modifyBt;
	private Checkbox bookcode, bookname, bookwriter, publisher;
	private CheckboxGroup searchGroup;
	private ArrayList<BookVo> list;
	private BookDAO dao = new BookDAO();
	private String code = "all";
	private JTable tb1 = new JTable();
	private JScrollPane listScroll = new JScrollPane(tb1);
	private DefaultTableModel col;
	private Font font1;


	public BookSearchMenu() {
//		ArrayList<BookVo> list = dao.list(code);
		font1 = new Font("고딕", Font.BOLD, 15);
		inpBookSearch = new JTextField();
		searchBt = new Button("검색");
		modifyBt = new Button("상세관리");
		searchGroup = new CheckboxGroup();
		bookcode = new Checkbox("책코드", searchGroup, true);
		bookname = new Checkbox("책이름", searchGroup, true);
		bookwriter = new Checkbox("저자", searchGroup, true);
		publisher = new Checkbox("출판사", searchGroup, true);
		borderLine = new JLabel();
		LineBorder bb = new LineBorder(Color.black, 1, true);

		setLayout(null);

		add(inpBookSearch);
		add(searchBt);
		add(modifyBt);
		add(bookcode);
		add(bookname);
		add(bookwriter);
		add(publisher);
		add(borderLine);

		bookcode.setBounds(175, 15, 55, 12);
		bookname.setBounds(240, 15, 55, 12);
		bookwriter.setBounds(305, 15, 40, 12);
		publisher.setBounds(355, 15, 55, 12);

		searchBt.setBounds(390, 35, 50, 30);
		searchBt.addActionListener(this);

		modifyBt.setBounds(450, 35, 60, 30);
		modifyBt.addActionListener(this);

		inpBookSearch.setBounds(185, 37, 200, 30);

		borderLine.setBounds(30, 75, 510, 205);
		borderLine.setBorder(bb);

	}

	public void bookSearchTable() {
//		list1 = dao.list(inp, code);
		String[] field = { "책코드", "책이름", "저자", "출판사", "위치", "반납예상일" };
		tb1.setBounds(35, 80, 500, 195);
		tb1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tb1.addMouseListener(this);
		col = new DefaultTableModel(field, 0);
//		for (BookVo vo : list) {
//			Object[] row = { vo.getCode(), vo.getName(), vo.getWriter(), vo.getPublisher(), vo.getPosition(),
//					vo.getbReturn() };
//			col.addRow(row);
//		}
//		tb1.setModel(col);
		add(listScroll);
		listScroll.setBounds(35, 80, 500, 195);
		tb1.setVisible(true);

	}

	public void bookInfomation() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(tb1.getSelectedRow() + ":" + tb1.getSelectedColumn());
		System.out.println(tb1.getValueAt(tb1.getSelectedRow(), tb1.getSelectedColumn()));

		System.out.println(list.get(tb1.getSelectedRow()).getImgLink());
//		int rowValue = tb1.getSelectedRow();

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
