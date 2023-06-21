package mainFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import login.MemberVo;

public class RentalReturnMenu extends JPanel implements ActionListener {

	private JTable tb1;
	private DefaultTableModel col;
	private UserBookDAO dao;
	private MemberVo userInfo;

	RentalReturnMenu(MemberVo userInfo) {
		rentalTable();
	}

	public void rentalView() {

	}

	public void rentalTable() {
		tb1 = new JTable();
		tb1.setSize(200, 200);
		String[] field = { "책번호", "책이름", "저자", "출판사", "대여일", "대여일수" };
		col = new DefaultTableModel(field, 0);
		tb1.setModel(col);
		JScrollPane sc = new JScrollPane(tb1);
		add(sc, BorderLayout.CENTER);
		dao = new UserBookDAO();
		ArrayList<BookVo> list = dao.list(userInfo);
		for(BookVo vo:list) {
			Object[] row= {vo.getCode(),vo.getName(),vo.getWriter(),vo.getPublisher(),vo.getRental(),vo.getDays()};
			col.addRow(row);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
