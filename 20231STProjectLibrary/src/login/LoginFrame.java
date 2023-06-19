package login;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


public class LoginFrame extends WindowAdapter implements ActionListener {
	private JFrame login;
	private JTextField insId;
	private JPasswordField insPw;
	private Checkbox manager;
	private Button bLogin, infoButton;
	private JButton join, grid1, idFind, grid2, pwFind;
	private MemberDAO dao;
	private JLabel mainTitle, idText, pwText, imgArea, infoDia;
	private Font font1, font2, font3;
	private Dialog dia;
	private String img = "..\\20231STProjectLibrary\\img\\loginimg.png";
	static String icon = "..\\20231STProjectLibrary\\img\\mainicon.png";

	public void setLoginFrame() {
		dao = new MemberDAO();
		login = new JFrame("Library Integration System Login");
		insId = new JTextField();
		insPw = new JPasswordField();
		manager = new Checkbox("관리자");
		bLogin = new Button("Login");
		join = new JButton("회원가입");
		grid1 = new JButton();
		grid2 = new JButton();
		idFind = new JButton("아이디찾기");
		pwFind = new JButton("비밀번호찾기");
		mainTitle = new JLabel("도서관 통합 관리 시스템");
		idText = new JLabel("ID");
		pwText = new JLabel("PW");
		imgArea = new JLabel();
		font1 = new Font("고딕", Font.PLAIN, 20);
		font2 = new Font("고딕", Font.PLAIN, 15);
		font3 = new Font("고딕", Font.PLAIN, 13);

//		font = new Font("고딕", Font.PLAIN, 30);
		login.setIconImage(new ImageIcon(icon).getImage());
		login.setSize(600, 600);
		login.setResizable(false);
		login.setLocationRelativeTo(null);
		login.setLayout(null);

		login.add(mainTitle);
		mainTitle.setBounds(135, 320, 330, 27);
		mainTitle.setFont(new Font("고딕", Font.PLAIN, 30));

		login.add(imgArea);
		imgArea.setBounds(0, 0, 600, 300);
		imgArea.setIcon(new ImageIcon(img));

		login.add(idText);
		idText.setBounds(190, 398, 30, 30);
		idText.setFont(font1);

		login.add(pwText);
		pwText.setBounds(182, 433, 35, 30);
		pwText.setFont(font1);

		login.add(insId);
		insId.setBounds(225, 400, 150, 30);
		insId.setFont(font2);

		login.add(insPw);
		insPw.setBounds(225, 435, 150, 30);
		insPw.setFont(font2);

		login.add(bLogin);
		bLogin.addActionListener(this);
		bLogin.setBounds(380, 403, 55, 55);

		login.add(manager);
		manager.setBounds(230, 365, 60, 30);
		manager.setFont(font2);

		login.add(join);
		join.setBounds(170, 485, 85, 30);
		join.setFont(font3);
		join.setBorderPainted(false);
		join.setContentAreaFilled(false);
		join.addActionListener(this);

		login.add(grid1);
		grid1.setBounds(250, 490, 1, 20);

		login.add(idFind);
		idFind.setBounds(250, 485, 100, 30);
		idFind.setFont(font3);
		idFind.setBorderPainted(false);
		idFind.setContentAreaFilled(false);
		idFind.addActionListener(this);

		login.add(grid2);
		grid2.setBounds(345, 490, 1, 20);

		login.add(pwFind);
		pwFind.setBounds(335, 485, 130, 30);
		pwFind.setFont(font3);
		pwFind.setBorderPainted(false);
		pwFind.setContentAreaFilled(false);
		pwFind.addActionListener(this);

		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void viewLoginFrame() {
		setLoginFrame();
		login.setVisible(true);
	}
	public void LoginDialog(String s) {
		dia = new Dialog(login, "Info", true);
		dia.addWindowListener(this);
		infoDia = new JLabel();
		infoDia.setBounds(55, 50, 185, 30);
		infoDia.setText(s);
		infoDia.setFont(font3);
		infoButton = new Button("확인");
		dia.setLayout(null);
		infoButton.setBounds(125, 102, 50, 25);
		dia.setBounds(600, 300, 300, 150);
		dia.add(infoDia);
		dia.add(infoButton);
		infoButton.addActionListener(this);
		dia.setVisible(true);

	}
	
	public void setJoin() {
		
	}

	public void windowClosing(WindowEvent e) {
		if (e.getComponent() == dia || e.getComponent() == infoButton) {
			dia.dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Login")) {

			String tfid = insId.getText();
			String tfpw = "";
			char[] inPw = insPw.getPassword();
			System.out.println(inPw);
			for (char cha : inPw) {
				tfpw += cha;
			}
			System.out.println(tfpw);
			ArrayList<MemberVo> list;

			if (manager.getState()) {
				list = dao.list(tfid, "manager");
			} else {
				list = dao.list(tfid, "g_user");
			}
			System.out.println(manager.getState());
			System.out.println(list);

			String id = null;
			String pw = null;

			for (int i = 0; i < list.size(); i++) {
				MemberVo data = (MemberVo) list.get(i);
				id = data.getId();
				pw = data.getPw();

				System.out.println(id);
				System.out.println(pw);
			}
			if (tfid.equals(id)) {
				if (tfpw.equals(pw)) {
					LoginDialog("성공");
				} else {
					LoginDialog("비밀번호를 다시 입력해주세요.");
				}
			} else {
				LoginDialog("아이디를 다시 입력해주세요.");
			}

		}

		if (e.getActionCommand().equals("확인")) {
			dia.dispose();
		}
	}

	public static void main(String[] args) {
		LoginFrame login = new LoginFrame();
		login.viewLoginFrame();
	}

}
