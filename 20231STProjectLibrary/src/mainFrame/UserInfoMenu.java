package mainFrame;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import login.JoinDAO;
import login.MemberVo;

@SuppressWarnings("serial")
public class UserInfoMenu extends JPanel implements ActionListener {
	private JLabel userIn, userId, userPw, userName, userGender, userBirth, userNumber, userEmail, diaInfo, infomsg;
	private JTextField inpId, inpName, inpBirth, inpNumber, inpEmail;
	private JPasswordField inpPw;
	private Checkbox man, woman;
	private CheckboxGroup genderGroup;
	private Button pwBt, endBt, diaBt;
	private Dialog dia;
	private Font font1, font2, font3;
	private MemberVo userInfo;
	private JoinDAO joinDao = new JoinDAO();

	public UserInfoMenu(MemberVo userinfo) {
		this.userInfo = userinfo;

		font1 = new Font("맑은고딕", Font.BOLD, 25);
		font2 = new Font("맑은고딕", Font.PLAIN, 20);
		font3 = new Font("맑은고딕", Font.PLAIN, 18);
		userIn = new JLabel("회원정보관리");
		userId = new JLabel("ID");
		userPw = new JLabel("PW");
		userName = new JLabel("이름");
		userGender = new JLabel("성별");
		userBirth = new JLabel("생년월일");
		userNumber = new JLabel("연락처");
		userEmail = new JLabel("Email");
		infomsg = new JLabel("※바뀐 비밀번호는 다음 로그인부터 적용됩니다.");

		inpId = new JTextField();
		inpPw = new JPasswordField();
		inpName = new JTextField();
		inpBirth = new JTextField();
		inpNumber = new JTextField();
		inpEmail = new JTextField();

		genderGroup = new CheckboxGroup();

		man = new Checkbox("남", genderGroup, true);
		woman = new Checkbox("여", genderGroup, false);
		if (userinfo.getGender().equals("남")) {
			man.setState(true);
			woman.setState(false);
		} else {
			man.setState(false);
			woman.setState(true);
		}

		pwBt = new Button("비밀번호확인");
		endBt = new Button("회원정보수정");

		add(userIn);
		add(userId);
		add(userPw);
		add(userName);
		add(userGender);
		add(userBirth);
		add(userNumber);
		add(userEmail);
		add(inpId);
		add(inpPw);
		add(inpName);
		add(inpBirth);
		add(inpNumber);
		add(inpEmail);
		add(man);
		add(woman);
		add(pwBt);
		add(endBt);
		add(infomsg);

		setLayout(null);
		userIn.setBounds(210, 30, 160, 25);
		userIn.setFont(font1);

		userId.setBounds(90, 120, 20, 20);
		userId.setFont(font2);

		inpId.setBounds(120, 115, 130, 30); //
		inpId.setText(userInfo.getId());
		inpId.setFont(font3);
		inpId.setEnabled(false);

		userPw.setBounds(300, 120, 30, 20);
		userPw.setFont(font3);

		inpPw.setBounds(340, 115, 130, 30);
		inpPw.setFont(font3);

		pwBt.setBounds(480, 114, 80, 30);
		pwBt.addActionListener(this);

		userName.setBounds(70, 190, 40, 22);
		userName.setFont(font2);

		inpName.setBounds(120, 185, 130, 30);
		inpName.setText(userInfo.getName());
		inpName.setFont(font3);
		inpName.setEnabled(false);

		userGender.setBounds(295, 185, 40, 22);
		userGender.setFont(font2);

		man.setBounds(360, 185, 35, 22);
		man.setFont(font3);

		woman.setBounds(420, 185, 35, 22);
		woman.setFont(font3);

		userBirth.setBounds(35, 260, 80, 22);
		userBirth.setFont(font2);

		inpBirth.setBounds(120, 255, 130, 30);
		inpBirth.setText(userinfo.getBirth());
		inpBirth.setFont(font3);
		inpBirth.setEnabled(false);

		userEmail.setBounds(60, 330, 70, 22);
		userEmail.setFont(font2);

		inpEmail.setBounds(120, 325, 130, 30);
		inpEmail.setText(userInfo.getEmail());
		inpEmail.setFont(new Font("맑은고딕", Font.PLAIN, 11));
		inpEmail.setEnabled(false);

		userNumber.setBounds(275, 260, 60, 22);
		userNumber.setFont(font2);

		inpNumber.setBounds(340, 255, 130, 30);
		inpNumber.setFont(font3);
		inpNumber.setText(userInfo.getNumber());
		inpNumber.setEnabled(false);

		endBt.setBounds(240, 420, 120, 45);
		endBt.setFont(font3);
		endBt.addActionListener(this);

		infomsg.setFont(new Font("맑은고딕", Font.PLAIN, 10));
		infomsg.setBounds(280, 150, 250, 10);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String pw = userInfo.getPw();
		String inppws = "";
		char[] cpw = inpPw.getPassword();
		for (char a : cpw) {
			inppws += a;
		}
		if (e.getActionCommand().equals("비밀번호확인")) {

			if (pw.equals(inppws)) {
				inpBirth.setEnabled(true);
				inpNumber.setEnabled(true);
				inpEmail.setEnabled(true);
			} else {
				infoDia("비밀번호를 다시 입력해주세요.");
			}
		}
		if (e.getActionCommand().equals("닫기")) {
			dia.dispose();
		}

		if (e.getActionCommand().equals("회원정보수정")) {
			String gender = null;
			if (man.getState()) {
				gender = "남";
			} else {
				gender = "여";
			}
			try {
				if (inpBirth.getText().isEmpty()) {
					infoDia("생년월일을 입력해주세요.");
					return;
				} else if (inpBirth.getText().length() >= 7) {
					infoDia("생년월일을 6자리로 입력해주세요.");
					return;
				} else {
					for (int i = 0; i < inpBirth.getText().length(); i++) {
						char a = inpBirth.getText().charAt(i);
						if (a != '0' && a != '1' && a != '2' && a != '3' && a != '4' && a != '5' && a != '6' && a != '7'
								&& a != '8' && a != '9') {
							infoDia("생년월일은 숫자만 입력해주세요.");
							return;
						}
						System.out.println(a);
					}
				}

				if (inpNumber.getText().isEmpty()) {
					infoDia("연락처를 입력해주세요.");
					return;
				} else if (inpNumber.getText().length() > 12) {
					infoDia("연락처를 11자리 이하로 입력해주세요.");
					return;
				} else {
					for (int i = 0; i < inpNumber.getText().length(); i++) {
						char a = inpNumber.getText().charAt(i);
						if (a != '0' && a != '1' && a != '2' && a != '3' && a != '4' && a != '5' && a != '6' && a != '7'
								&& a != '8' && a != '9') {
							infoDia("연락처는 숫자만 입력해주세요.");
							return;
						}
					}
				}

				if (inpBirth.isEnabled() && inpNumber.isEnabled() && inpEmail.isEnabled()) {
					String p = joinDao.userInfoRevise(inpId.getText(), inppws, gender, inpBirth.getText(),
							inpNumber.getText(), inpEmail.getText());
					if (p.equals("완료")) {
						inpBirth.setEnabled(false);
						inpNumber.setEnabled(false);
						inpEmail.setEnabled(false);
						infoDia("회원정보수정이 완료되었습니다.");
						return;
					}
					infoDia("회원정보수정에 실패하었습니다.");
				} else {
					infoDia("비밀번호를 확인해주세요.");
				}

			} catch (Exception ex) {
				infoDia("회원정보를 바르게 입력해주세요.");
			}
		}
	}

	public void infoDia(String s) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
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
		diaInfo.setBounds(45, 50, 220, 30);
		dia.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (e.getComponent() == dia) {
					dia.dispose();
				}
			}
		});
		dia.setVisible(true);

	}

}
