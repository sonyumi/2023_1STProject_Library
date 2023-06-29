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

	private JTextField inpUserSearch, inpId, inpName, inpBirth, inpNumber, inpEmail;
	private JLabel userSearch, borderLine1, borderLine2, borderLine3, userInfo, infoId, infoName, infoBirth, infoGender,
			infoNumber, infoEmail, infoLabel, infoDia, rentalInfo;
	private Button searchBt, modifyBt, deleteBt, booklistBt, nullBt, diaBt, diaDeleteBt, rentalClose;
	private Checkbox id, name, birth, number, email, gender, man, woman;
	private CheckboxGroup searchGroup, genderGroup;
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
	private Font font1, font2, font3;
	private LineBorder bb;
	private String code = "all";
	private int i = 0;
	private JFrame booklist = new JFrame("대여정보");
	private Toolkit tk;
	private Dimension screenSize;

	public UserManagementMenu() {
		bb = new LineBorder(Color.black, 1, true);
		tk = Toolkit.getDefaultToolkit();
		screenSize = tk.getScreenSize();
		font1 = new Font("맑은고딕", Font.BOLD, 20);
		font2 = new Font("맑은고딕", Font.BOLD, 15);
		font3 = new Font("맑은고딕", Font.PLAIN, 15);
		userSearch = new JLabel("사용자검색");
		inpUserSearch = new JTextField();
		searchBt = new Button("검색");
		searchGroup = new CheckboxGroup();
		id = new Checkbox("ID", searchGroup, true);
		name = new Checkbox("이름", searchGroup, false);
		birth = new Checkbox("생년월일", searchGroup, false);
		gender = new Checkbox("성별", searchGroup, false);
		number = new Checkbox("연락처", searchGroup, false);
		email = new Checkbox("이메일", searchGroup, false);
		genderGroup = new CheckboxGroup();
		man = new Checkbox("남", genderGroup, true);
		woman = new Checkbox("여", genderGroup, false);
		borderLine1 = new JLabel();

		setLayout(null);

		add(userSearch);
		add(inpUserSearch);
		add(searchBt);
		add(id);
		add(name);
		add(birth);
		add(gender);
		add(number);
		add(email);
		add(man);
		add(woman);
		add(borderLine1);

		userSearch.setBounds(40, 37, 200, 30);
		userSearch.setFont(font1);
		id.setBounds(115, 15, 25, 12);
		name.setBounds(155, 15, 35, 12);
		birth.setBounds(207, 15, 65, 12);
		gender.setBounds(279, 15, 35, 12);
		number.setBounds(333, 15, 55, 12);
		email.setBounds(393, 15, 55, 12);

		searchBt.setBounds(360, 35, 50, 30);
		searchBt.addActionListener(this);

		inpUserSearch.setBounds(155, 37, 200, 30);

		borderLine1.setBounds(30, 75, 522, 195);
		borderLine1.setBorder(bb);
		userSearchTable1();
		userInfomation();
	}

	public void userSearchTable1() {
		list = dao.list1(code, i);
		String[] field = { "ID", "이름", "생년월일", "성별", "연락처", "이메일" };
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

	public void userSearchTable1(String code, int i) {
		this.code = code;
		this.i = i;
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
		userInfo = new JLabel("사용자정보");
		infoId = new JLabel("ID");
		infoName = new JLabel("이름");
		infoBirth = new JLabel("생년월일");
		infoGender = new JLabel("성별");
		infoNumber = new JLabel("연락처");
		infoEmail = new JLabel("이메일");
		modifyBt = new Button("수정하기");
		deleteBt = new Button("회원삭제");
		nullBt = new Button("초기화");
		booklistBt = new Button("대여목록");
		inpId = new JTextField();
		inpName = new JTextField();
		inpBirth = new JTextField();
		inpNumber = new JTextField();
		inpEmail = new JTextField();
		borderLine2 = new JLabel();
		infoLabel = new JLabel("※생년월일은 6자리로 입력해주세요.");

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
		add(inpId);
		add(inpName);
		add(inpBirth);
		add(inpNumber);
		add(inpEmail);
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

		inpId.setBounds(140, 329, 120, 30);
		inpId.setFont(font3);
		inpId.setEnabled(false);

		infoName.setBounds(310, 335, 50, 15);
		infoName.setFont(font2);

		inpName.setBounds(350, 329, 120, 30);
		inpName.setFont(font2);

		infoGender.setBounds(95, 385, 40, 15);
		infoGender.setFont(font2);

		man.setBounds(155, 386, 30, 15);
		man.setFont(font3);

		woman.setBounds(205, 386, 30, 15);
		woman.setFont(font3);

		infoBirth.setBounds(280, 385, 65, 15);
		infoBirth.setFont(font2);

		inpBirth.setBounds(350, 379, 120, 30);
		inpBirth.setFont(font3);

		infoNumber.setBounds(80, 433, 50, 15);
		infoNumber.setFont(font2);

		inpNumber.setBounds(140, 430, 120, 30);
		inpNumber.setFont(font3);

		infoEmail.setBounds(290, 433, 50, 15);
		infoEmail.setFont(font2);

		inpEmail.setBounds(350, 430, 120, 30);
		inpEmail.setFont(font3);

		modifyBt.setBounds(165, 470, 60, 30);
		modifyBt.addActionListener(this);

		deleteBt.setBounds(255, 470, 60, 30);
		deleteBt.addActionListener(this);

		nullBt.setBounds(35, 280, 50, 25);
		nullBt.addActionListener(this);

		booklistBt.setBounds(345, 470, 60, 30);
		booklistBt.addActionListener(this);
	}

	public void rentalTable() {
		borderLine3 = new JLabel();
		rentalInfo = new JLabel("대여중인 도서 목록");
		rentalClose = new Button("확인");
		booklist.setBounds(screenSize.width / 2 + 150, screenSize.height / 2 + 75, 500, 250);
		booklist.setLayout(null);
		booklist.add(borderLine3);
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
		rentalInfo.setFont(font2);
		rentalClose.setBounds(215, 175, 50, 30);
		rentalClose.setFont(font3);
		rentalClose.addActionListener(this);
		borderLine3.setBorder(bb);

		list1 = userBookDao.list1(inpId.getText());
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
		borderLine3.setBounds(0, 37, 490, 130);
		booklist.setVisible(true);
	}

	public void infoDialog(String s) {
		dia = new Dialog(dia);
		diaBt = new Button("닫기");
		infoDia = new JLabel(s);
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
		diaBt = new Button("취소하기");
		diaDeleteBt = new Button("삭제하기");
		infoDia = new JLabel("정말로 삭제하시겠습니까?");
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
			String nId = inpId.getText();
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
			inpId.setText("");
			inpName.setText("");
			inpBirth.setText("");
			inpNumber.setText("");
			man.setState(true);
			woman.setState(false);
			inpEmail.setText("");
		}

		if (e.getActionCommand().equals("수정하기")) {
			String gender = null;
			if (man.getState()) {
				gender = "남";
			} else {
				gender = "여";
			}
			try {
				if (inpBirth.getText().isEmpty()) {
					infoDialog("생년월일을 입력해주세요.");
					return;
				} else if (inpBirth.getText().length() >= 7) {
					infoDialog("생년월일을 6자리로 입력해주세요.");
					return;
				} else {
					for (int i = 0; i < inpBirth.getText().length(); i++) {
						char a = inpBirth.getText().charAt(i);
						if (a != '0' && a != '1' && a != '2' && a != '3' && a != '4' && a != '5' && a != '6' && a != '7'
								&& a != '8' && a != '9') {
							infoDialog("생년월일은 숫자만 입력해주세요.");
							return;
						}
						System.out.println(a);
					}
				}
				if (inpNumber.getText().isEmpty()) {
					infoDialog("연락처를 입력해주세요.");
					return;
				} else if (inpNumber.getText().length() > 12) {
					infoDialog("연락처를 11자리 이하로 입력해주세요.");
					return;
				} else {
					for (int i = 0; i < inpNumber.getText().length(); i++) {
						char a = inpNumber.getText().charAt(i);
						if (a != '0' && a != '1' && a != '2' && a != '3' && a != '4' && a != '5' && a != '6' && a != '7'
								&& a != '8' && a != '9') {
							infoDialog("연락처는 숫자만 입력해주세요.");
							return;
						}
					}
				}
				if (inpBirth.isEnabled() && inpNumber.isEnabled() && inpEmail.isEnabled()) {
					String p = joinDao.userInfoRevise1(inpId.getText(), inpName.getText(), gender, inpBirth.getText(),
							inpNumber.getText(), inpEmail.getText());
					if (p.equals("완료")) {
						inpBirth.setEnabled(false);
						inpNumber.setEnabled(false);
						inpEmail.setEnabled(false);
						infoDialog("회원정보수정이 완료되었습니다.");
					}
				}
			} catch (Exception ex) {
				infoDialog("회원정보를 바르게 입력해주세요.");
			}
		}

		if (e.getActionCommand().equals("삭제하기")) {
			String nId = inpId.getText();
			String p = userBookDao.getDeleted(nId);
			if (p.equals("삭제")) {
				list = dao.list1(nId, 7);
				dia.dispose();
				infoDialog("회원 삭제가 완료되었습니다.");
				inpId.setText("");
				inpName.setText("");
				inpBirth.setText("");
				inpNumber.setText("");
				man.setState(true);
				woman.setState(false);
				inpEmail.setText("");
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
				if (inpId.getText().length() != 0) {
					list1 = userBookDao.list1(inpId.getText());
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

		userSearchTable1("all", 0);

		if (e.getActionCommand().equals("검색")) {
			String userSearch = inpUserSearch.getText();
			if (inpUserSearch.getText().length() != 0 && id.getState()) {
				userSearchTable1(userSearch, 1);
			} else if (inpUserSearch.getText().length() != 0 && name.getState()) {
				userSearchTable1(userSearch, 2);
			} else if (inpUserSearch.getText().length() != 0 && birth.getState()) {
				userSearchTable1(userSearch, 3);
			} else if (inpUserSearch.getText().length() != 0 && gender.getState()) {
				userSearchTable1(userSearch, 4);
			} else if (inpUserSearch.getText().length() != 0 && number.getState()) {
				userSearchTable1(userSearch, 5);
			} else if (inpUserSearch.getText().length() != 0 && email.getState()) {
				userSearchTable1(userSearch, 6);
			} else {
				userSearchTable1("all", 0);
			}
		}
		tb1.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		inpId.setText(list.get(tb1.getSelectedRow()).getId());
		inpName.setText(list.get(tb1.getSelectedRow()).getName());
		inpBirth.setText(list.get(tb1.getSelectedRow()).getBirth());
		if (list.get(tb1.getSelectedRow()).getGender().equals("남")) {
			man.setState(true);
			woman.setState(false);
		} else {
			man.setState(false);
			woman.setState(true);
		}
		inpNumber.setText(list.get(tb1.getSelectedRow()).getNumber());
		inpEmail.setText(list.get(tb1.getSelectedRow()).getEmail());

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
