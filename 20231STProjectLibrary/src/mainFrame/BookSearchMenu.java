package mainFrame;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
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

@SuppressWarnings("serial")
public class BookSearchMenu extends JPanel implements ActionListener, MouseListener {
	private JTextField inpBookSearch, inpBookCode,inpBookName,inpWriter,inpPublisher;
	private JLabel bookSearch,borderLine1,borderLine2,bookInfo,infoBookCode,infoBookName,infoWriter,infoPublisher,infoPosition,infoLabel;
	private Button searchBt, modifyBt,addBt,deleteBt;
	private Choice positionA,positionB;
	private Checkbox bookcode, bookname, bookwriter, publisher;
	private CheckboxGroup searchGroup;
	private ArrayList<BookVo> list;
	private BookDAO dao = new BookDAO();
	private JTable tb1 = new JTable();
	private JScrollPane listScroll = new JScrollPane(tb1);
	private DefaultTableModel col;
	private Font font1,font2,font3;
	private String code = "all";
	private String table = "all";

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
		borderLine2 = new JLabel();
		LineBorder bb = new LineBorder(Color.black, 1, true);

		setLayout(null);

		add(bookSearch);
		add(inpBookSearch);
		add(searchBt);
		add(bookcode);
		add(bookname);
		add(bookwriter);
		add(publisher);
		add(borderLine1);
		add(borderLine2);
		
		
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
		
		borderLine2.setBounds(30, 275, 522, 235);
		borderLine2.setBorder(bb);
		
		bookSearchTable();
		bookInfomation();

	}

	public void bookSearchTable() {

		list = dao.list1(code, table);
		String[] field = { "책코드", "책이름", "저자", "출판사", "위치", "반납예상일" };
		tb1.setSize(200,200);
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
//		tb1.setVisible(true);

	}

	public void bookInfomation() {
		bookInfo = new JLabel("도서정보관리");
		infoBookCode = new JLabel("책코드");
		infoBookName=new JLabel("책이름");
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
		
//		add(positionB);
		
		bookInfo.setBounds(230, 280, 160, 25);
		bookInfo.setFont(font1);
		
		infoBookCode.setBounds(45,329,50,15);
		infoBookCode.setFont(font2);
		inpBookCode.setBounds(90,325,100,25);
		inpBookCode.setFont(font3);
		
		infoBookName.setBounds(205,329,50,15);
		infoBookName.setFont(font2);
		inpBookName.setBounds(250, 325, 100, 25);
		inpBookName.setFont(font3);
		
		infoWriter.setBounds(55,379,30,15);
		infoWriter.setFont(font2);
		inpWriter.setBounds(90, 375, 100, 25);
		inpWriter.setFont(font3);
		
		infoPublisher.setBounds(205,379,50,15);
		infoPublisher.setFont(font2);
		inpPublisher.setBounds(250, 375, 100, 25);
		inpPublisher.setFont(font3);
		
		infoPosition.setBounds(55,429,30,15);
		infoPosition.setFont(font2);
		positionA.setBounds(90, 425, 100, 25);
		positionA.setFont(font3);
		add(positionA);
		positionA.add("A");
		positionA.add("B");
		positionA.add("C");
		positionA.add("D");
		
		
		
		
		
		
		modifyBt.setBounds(420, 35, 60, 30);
		modifyBt.addActionListener(this);
		
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
