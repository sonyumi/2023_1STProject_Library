package mainFrame;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import login.JoinDAO;
import login.MemberDAO;
import login.MemberVo;

@SuppressWarnings("serial")
public class UserManagementMenu extends JPanel implements ActionListener, MouseListener {
//여기 정리해야함 7-4
	private JTextField[] inpText; // 0=Search, 1=inpId, 2=inpName, 3=inpBrith, 4=inpNumber, 5=inpEmail
	private Checkbox[] searchCheck; // 0=id, 1=name, 2=birth, 3=number, 4=email, 5=gender
	private Checkbox man, woman;
	private Dialog dia;
	private ArrayList<MemberVo> list;
	private ArrayList<BookVo> list1;
	private MemberDAO dao = new MemberDAO();
	private UserBookDAO userBookDao = new UserBookDAO();
	private JoinDAO joinDao = new JoinDAO();
	private JTable tb1 = new JTable();
	private JTable tb2 = new JTable();
	private JScrollPane listScroll = new JScrollPane(tb1);
	private JScrollPane listScroll1 = new JScrollPane(tb2);
	private DefaultTableModel col;
	private JFrame booklist = new JFrame("대여정보");
	private Dimension screenSize;

	public UserManagementMenu() {
		LineBorder bb = new LineBorder(Color.black, 1, true);
		Toolkit tk = Toolkit.getDefaultToolkit();
		screenSize = tk.getScreenSize();
		Font font1 = new Font("맑은고딕", Font.BOLD, 20);
		Font font2 = new Font("맑은고딕", Font.BOLD, 15);
		Font font3 = new Font("맑은고딕", Font.PLAIN, 15);
		JLabel userSearch = new JLabel("사용자검색");
		Button searchBt = new Button("검색");
		CheckboxGroup searchGroup = new CheckboxGroup();
		CheckboxGroup genderGroup = new CheckboxGroup();
		man = new Checkbox("남", genderGroup, true);
		woman = new Checkbox("여", genderGroup, false);
		JLabel borderLine1 = new JLabel();
		JLabel userInfo = new JLabel("사용자정보");
		JLabel infoId = new JLabel("ID");
		JLabel infoName = new JLabel("이름");
		JLabel infoBirth = new JLabel("생년월일");
		JLabel infoGender = new JLabel("성별");
		JLabel infoNumber = new JLabel("연락처");
		JLabel infoEmail = new JLabel("이메일");
		Button modifyBt = new Button("수정하기");
		Button deleteBt = new Button("회원삭제");
		Button nullBt = new Button("초기화");
		Button booklistBt = new Button("대여목록");
		JLabel borderLine2 = new JLabel();
		JLabel infoLabel = new JLabel("※생년월일은 6자리로 입력해주세요.");

		setLayout(null);

		String[] checkText = { "ID", "이름", "생년월일", "성별", "연락처", "이메일" };
		searchCheck = new Checkbox[6];
		for (int i = 0; i < searchCheck.length; i++) {
			searchCheck[i] = new Checkbox(checkText[i], searchGroup, true);
			add(searchCheck[i]);
		}

		inpText = new JTextField[6];
		for (int i = 0; i < inpText.length; i++) {
			inpText[i] = new JTextField();
			add(inpText[i]);
		}

		add(userSearch);
		add(searchBt);
		add(man);
		add(woman);
		add(borderLine1);
		add(userInfo);
		add(infoId);
		add(infoName);
		add(infoBirth);
		add(infoGender);
		add(infoNumber);
		add(infoEmail);
		add(modifyBt);
		add(deleteBt);
		add(nullBt);
		add(booklistBt);
		add(infoLabel);
		add(borderLine2);

		borderLine2.setBounds(30, 275, 522, 235);
		borderLine2.setBorder(bb);

		userInfo.setBounds(230, 280, 160, 25);
		userInfo.setFont(font1);

		infoLabel.setBounds(345, 410, 200, 10);
		infoLabel.setFont(new Font("맑은고딕", Font.PLAIN, 9));

		infoId.setBounds(110, 335, 40, 15);
		infoId.setFont(font2);

		inpText[1].setBounds(140, 329, 120, 30);
		inpText[1].setFont(font3);
		inpText[1].setEnabled(false);

		infoName.setBounds(310, 335, 50, 15);
		infoName.setFont(font2);

		inpText[2].setBounds(350, 329, 120, 30);
		inpText[2].setFont(font2);

		infoGender.setBounds(95, 385, 40, 15);
		infoGender.setFont(font2);

		man.setBounds(155, 386, 30, 15);
		man.setFont(font3);

		woman.setBounds(205, 386, 30, 15);
		woman.setFont(font3);

		infoBirth.setBounds(280, 385, 65, 15);
		infoBirth.setFont(font2);

		inpText[3].setBounds(350, 379, 120, 30);
		inpText[3].setFont(font3);

		infoNumber.setBounds(80, 433, 50, 15);
		infoNumber.setFont(font2);

		inpText[4].setBounds(140, 430, 120, 30);
		inpText[4].setFont(font3);

		infoEmail.setBounds(290, 433, 50, 15);
		infoEmail.setFont(font2);

		inpText[5].setBounds(350, 430, 120, 30);
		inpText[5].setFont(font3);

		modifyBt.setBounds(165, 470, 60, 30);
		modifyBt.addActionListener(this);

		deleteBt.setBounds(255, 470, 60, 30);
		deleteBt.addActionListener(this);

		nullBt.setBounds(35, 280, 50, 25);
		nullBt.addActionListener(this);

		booklistBt.setBounds(345, 470, 60, 30);
		booklistBt.addActionListener(this);

		userSearch.setBounds(40, 37, 200, 30);
		userSearch.setFont(font1);
		searchCheck[0].setBounds(115, 15, 25, 12);
		searchCheck[1].setBounds(155, 15, 35, 12);
		searchCheck[2].setBounds(207, 15, 65, 12);
		searchCheck[3].setBounds(279, 15, 35, 12);
		searchCheck[4].setBounds(333, 15, 55, 12);
		searchCheck[5].setBounds(393, 15, 55, 12);

		searchBt.setBounds(360, 35, 50, 30);
		searchBt.addActionListener(this);

		inpText[0].setBounds(155, 37, 200, 30);

		borderLine1.setBounds(30, 75, 522, 195);
		borderLine1.setBorder(bb);
		userSearchTable("all", 0);
		userInfomation();
	}

	public void userSearchTable(String code, int i) {
		list = dao.list1(code, i);
		String[] field = { "ID", "이름", "성별", "생년월일", "연락처", "이메일" };
		tb1.setSize(200, 200);
		tb1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tb1.addMouseListener(this);
		col = new DefaultTableModel(field, 0);
		for (MemberVo vo : list) {
			Object[] row = { vo.getId(), vo.getName(), vo.getBirth(), vo.getGender(), vo.getNumber(), vo.getEmail() };
			col.addRow(row);
		}
		tb1.setModel(col);
		add(listScroll);
		listScroll.setBounds(35, 80, 512, 185);
	}

	public void userInfomation() {

	}

	public void rentalTable() {
		JLabel rentalInfo = new JLabel("대여중인 도서 목록");
		Button rentalClose = new Button("확인");
		booklist.setBounds(screenSize.width / 2 + 150, screenSize.height / 2 + 75, 500, 250);
		booklist.setLayout(null);
		booklist.add(rentalInfo);
		booklist.add(rentalClose);
		booklist.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (e.getComponent() == booklist) {
					booklist.dispose();
				}
			}
		});
		rentalInfo.setBounds(173, 4, 150, 20);
		rentalInfo.setFont(new Font("맑은고딕", Font.BOLD, 14));
		rentalClose.setBounds(215, 175, 50, 30);
		rentalClose.setFont(new Font("맑은고딕", Font.PLAIN, 14));
		rentalClose.addActionListener(this);

		list1 = userBookDao.list1(inpText[1].getText());
		String[] field2 = { "책번호", "책이름", "저자", "출판사", "대여일", "대여일수" };
		col = new DefaultTableModel(field2, 0);
		tb2.setSize(200, 200);
		for (BookVo vo : list1) {
			Object[] row = { vo.getCode(), vo.getName(), vo.getWriter(), vo.getPublisher(), vo.getRental(),
					vo.getDays() };
			col.addRow(row);
		}
		tb2.setModel(col);
		booklist.add(listScroll1);
		listScroll1.setSize(480, 125);
		listScroll1.setLocation(4, 40);
		booklist.setVisible(true);
	}

	public void infoDialog(String s) {
		dia = new Dialog(dia);
		Button diaBt = new Button("닫기");
		JLabel infoDia = new JLabel(s);
		dia.add(diaBt);
		dia.setTitle("Info");
		dia.add(infoDia);
		dia.setModal(true);
		dia.setResizable(false);
		dia.setLayout(null);
		dia.setBounds(screenSize.width / 2 - 150, screenSize.height / 2 - 75, 300, 150);
		diaBt.addActionListener(this);
		diaBt.setBounds(125, 102, 50, 25);
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

	public void infoWarningDia() {
		dia = new Dialog(dia);
		Button diaBt = new Button("취소하기");
		Button diaDeleteBt = new Button("삭제하기");
		JLabel infoDia = new JLabel("정말로 삭제하시겠습니까?");
		dia.add(diaBt);
		dia.add(diaDeleteBt);
		dia.setTitle("Warning");
		dia.add(infoDia);
		dia.setModal(true);
		dia.setResizable(false);
		dia.setLayout(null);
		dia.setBounds(screenSize.width / 2 - 150, screenSize.height / 2 - 75, 300, 150);
		diaBt.addActionListener(this);
		diaBt.setBounds(160, 102, 60, 25);
		diaDeleteBt.setBounds(80, 102, 60, 25);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		tb1.setVisible(false);
		if (e.getActionCommand().equals("닫기")) {
			dia.dispose();
		}

		if (e.getActionCommand().equals("취소하기")) {
			dia.dispose();
		}

		if (e.getActionCommand().equals("회원삭제")) {
			String nId = inpText[1].getText();
			if (nId.isEmpty()) {
				infoDialog("회원을 선택해주세요.");
			} else {
				infoWarningDia();
			}
		}
		if (e.getActionCommand().equals("확인")) {
			booklist.dispose();
		}
		if (e.getActionCommand().equals("초기화")) {
			inpText[1].setText("");
			inpText[2].setText("");
			inpText[3].setText("");
			inpText[4].setText("");
			man.setState(true);
			woman.setState(false);
			inpText[5].setText("");
		}

		if (e.getActionCommand().equals("수정하기")) {
			String gender = null;
			if (man.getState()) {
				gender = "남";
			} else {
				gender = "여";
			}
			try {
				if (inpText[1].getText().isEmpty()) {
					infoDialog("회원을 선택해주세요.");
					return;
				}
				if (inpText[3].getText().isEmpty()) {
					infoDialog("생년월일을 입력해주세요.");
					return;
				} else if (inpText[3].getText().length() >= 7) {
					infoDialog("생년월일을 6자리로 입력해주세요.");
					return;
				} else {
					for (int i = 0; i < inpText[3].getText().length(); i++) {
						char a = inpText[3].getText().charAt(i);
						if (a != '0' && a != '1' && a != '2' && a != '3' && a != '4' && a != '5' && a != '6' && a != '7'
								&& a != '8' && a != '9') {
							infoDialog("생년월일은 숫자만 입력해주세요.");
							return;
						}
						System.out.println(a);
					}
				}
				if (inpText[4].getText().isEmpty()) {
					infoDialog("연락처를 입력해주세요.");
					return;
				} else if (inpText[4].getText().length() > 11) {
					infoDialog("연락처를 11자리 이하로 입력해주세요.");
					return;
				} else {
					for (int i = 0; i < inpText[4].getText().length(); i++) {
						char a = inpText[4].getText().charAt(i);
						if (a != '0' && a != '1' && a != '2' && a != '3' && a != '4' && a != '5' && a != '6' && a != '7'
								&& a != '8' && a != '9') {
							infoDialog("연락처는 숫자만 입력해주세요.");
							return;
						}
					}
				}
				if (inpText[3].isEnabled() && inpText[4].isEnabled() && inpText[5].isEnabled()) {
					String p = joinDao.userInfoRevise1(inpText[1].getText(), inpText[2].getText(), gender,
							inpText[3].getText(), inpText[4].getText(), inpText[5].getText());
					if (p.equals("완료")) {
						infoDialog("회원정보수정이 완료되었습니다.");
					}
				}
			} catch (Exception ex) {
				infoDialog("회원정보를 바르게 입력해주세요.");
			}
		}

		if (e.getActionCommand().equals("삭제하기")) {
			String nId = inpText[1].getText();
			String p = userBookDao.getDeleted(nId);
			if (p.equals("삭제")) {
				list = dao.list1(nId, 7);
				dia.dispose();
				infoDialog("회원 삭제가 완료되었습니다.");
				inpText[1].setText("");
				inpText[2].setText("");
				inpText[3].setText("");
				inpText[4].setText("");
				man.setState(true);
				woman.setState(false);
				inpText[5].setText("");
				return;
			} else {
				dia.dispose();
				infoDialog("빌린내역이 있으면 삭제할 수 없습니다.");
				return;
			}
		}

		if (e.getActionCommand().equals("대여목록"))

		{
			if (booklist.isShowing() == false) {
				if (inpText[1].getText().length() != 0) {
					list1 = userBookDao.list1(inpText[1].getText());
					if (list1.size() != 0) {
						rentalTable();
					} else {
						infoDialog("대여중인 도서가 없습니다.");
					}
				} else {
					infoDialog("회원정보를 불러올 수 없습니다.");
				}
			} else {
				booklist.dispose();
				rentalTable();
			}

		}

		userSearchTable("all", 0);

		if (e.getActionCommand().equals("검색")) {
			String userSearch = inpText[0].getText();
			if (userSearch.length() != 0 && searchCheck[0].getState()) {
				userSearchTable(userSearch, 1);
			} else if (userSearch.length() != 0 && searchCheck[1].getState()) {
				userSearchTable(userSearch, 2);
			} else if (userSearch.length() != 0 && searchCheck[2].getState()) {
				userSearchTable(userSearch, 3);
			} else if (userSearch.length() != 0 && searchCheck[3].getState()) {
				userSearchTable(userSearch, 4);
			} else if (userSearch.length() != 0 && searchCheck[4].getState()) {
				userSearchTable(userSearch, 5);
			} else if (userSearch.length() != 0 && searchCheck[5].getState()) {
				userSearchTable(userSearch, 6);
			} else {
				userSearchTable("all", 0);
			}
		}
		tb1.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		inpText[1].setText(list.get(tb1.getSelectedRow()).getId());
		inpText[2].setText(list.get(tb1.getSelectedRow()).getName());
		inpText[3].setText(list.get(tb1.getSelectedRow()).getBirth());
		if (list.get(tb1.getSelectedRow()).getGender().equals("남")) {
			man.setState(true);
			woman.setState(false);
		} else {
			man.setState(false);
			woman.setState(true);
		}
		inpText[4].setText(list.get(tb1.getSelectedRow()).getNumber());
		inpText[5].setText(list.get(tb1.getSelectedRow()).getEmail());

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
