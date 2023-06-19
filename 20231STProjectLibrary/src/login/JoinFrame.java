package login;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class JoinFrame extends WindowAdapter implements ActionListener {
	private JFrame join;
	private JLabel joinText, idText, pwText, nameText, gText, birthText, phoneText, emailText, idInfoText1, idInfoText2,
			idInfoText3, birInfoText, phoneNumInfo;
	private JTextField insId, insName, insPhone, insBirth, insEmail;
	private JPasswordField insPw;
	private ButtonGroup bg;
	private JRadioButton[] gender;
	private Button overlapId, joinBt;
	private Font font, font1, font2, font3;
	private String sGender[] = { "남", "여" };
	private MemberDAO dao;
	private JoinDAO dao1;

	public void setJoinFrame() {
		dao = new MemberDAO();
		dao1 = new JoinDAO();
		join = new JFrame("Welcome, Join! :D");
		joinText = new JLabel("회원가입");
		idText = new JLabel("*ID");
		pwText = new JLabel("*PW");
		nameText = new JLabel("*이름");
		gText = new JLabel("*성별");
		birthText = new JLabel("*생년월일");
		phoneText = new JLabel("*전화번호");
		emailText = new JLabel("E-Mail");
		phoneNumInfo = new JLabel("* - 는 제외하고 숫자만 입력해주세요.");
		birInfoText = new JLabel("생년월일은 6자리로 입력해주세요.");
		insId = new JTextField();
		insPw = new JPasswordField();
		insName = new JTextField();
		insBirth = new JTextField();
		insPhone = new JTextField();
		insEmail = new JTextField();
		overlapId = new Button("중복확인");
		joinBt = new Button("회원가입");

		bg = new ButtonGroup();
		gender = new JRadioButton[2];
		gender[0] = new JRadioButton(sGender[0]);
		gender[1] = new JRadioButton(sGender[1]);
		bg.add(gender[0]);
		bg.add(gender[1]);

		font = new Font("고딕", Font.PLAIN, 30);
		font1 = new Font("고딕", Font.PLAIN, 17);
		font2 = new Font("고딕", Font.PLAIN, 15);
		font3 = new Font("고딕", Font.PLAIN, 13);

		join.setIconImage(new ImageIcon(LoginFrame.icon).getImage());
		join.setSize(400, 500);
		join.setResizable(false);
		join.setLocationRelativeTo(null);
		join.setLayout(null);

		join.add(joinText);
		joinText.setBounds(135, 10, 130, 50);
		joinText.setFont(font);

		join.add(idText);
		idText.setBounds(85, 83, 30, 20);
		idText.setFont(font1);

		join.add(insId);
		insId.setBounds(120, 80, 150, 30);
		insId.setFont(font2);

		join.add(pwText);
		pwText.setBounds(80, 135, 42, 20);
		pwText.setFont(font1);

		join.add(insPw);
		insPw.setBounds(120, 130, 150, 30);
		insPw.setFont(font2);

		join.add(nameText);
		nameText.setBounds(75, 177, 50, 20);
		nameText.setFont(font1);

		join.add(insName);
		insName.setBounds(120, 175, 150, 30);
		insName.setFont(font2);

		join.add(gText);
		gText.setBounds(75, 223, 50, 20);
		gText.setFont(font1);

		join.add(gender[0]);
		join.add(gender[1]);
		gender[0].setBounds(150, 222, 50, 20);
		gender[0].setFont(font1);
		gender[0].setSelected(true);
		gender[1].setBounds(220, 222, 50, 20);
		gender[1].setFont(font1);

		join.add(birthText);
		birthText.setBounds(45, 265, 100, 20);
		birthText.setFont(font1);

		join.add(insBirth);
		insBirth.setBounds(120, 262, 150, 30);
		insBirth.setFont(font1);

		join.add(phoneText);
		phoneText.setBounds(45, 310, 80, 20);
		phoneText.setFont(font1);

		join.add(insPhone);
		insPhone.setBounds(120, 308, 150, 30);
		insPhone.setFont(font1);

		join.add(phoneNumInfo);
		phoneNumInfo.setBounds(80, 340, 220, 15);

		join.add(emailText);
		emailText.setBounds(55, 368, 80, 20);
		emailText.setFont(font1);

		join.add(insEmail);
		insEmail.setBounds(120, 365, 150, 30);
		insEmail.setFont(font1);

		join.add(overlapId);
		overlapId.addActionListener(this);
		overlapId.setBounds(276, 80, 65, 30);
		overlapId.setFont(font3);

		join.add(joinBt);
		joinBt.addActionListener(this);
		joinBt.setBounds(172, 410, 65, 30);
		joinBt.setFont(font3);

		idInfoText1 = new JLabel("아이디 입력후 중복확인을 해주세요.");
		join.add(idInfoText1);
		idInfoText1.setForeground(Color.black);
		idInfoText1.setBounds(95, 110, 220, 15);
		idInfoText1.setVisible(true);

		idInfoText2 = new JLabel("사용 가능한 아이디 입니다.");
		join.add(idInfoText2);
		idInfoText2.setForeground(Color.blue);
		idInfoText2.setBounds(95, 110, 180, 15);
		idInfoText2.setVisible(false);

		idInfoText3 = new JLabel("중복되는 아이디가 있습니다.");
		join.add(idInfoText3);
		idInfoText3.setForeground(Color.red);
		idInfoText3.setBounds(95, 110, 180, 15);
		idInfoText3.setVisible(false);

		join.add(birInfoText);
		birInfoText.setBounds(0, 0, 0, 0);

		join.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		join.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String tfId = insId.getText();
		ArrayList<MemberVo> list = dao.list(tfId, "g_user");
		ArrayList<MemberVo> li = dao.list(tfId, "manager");
		if (e.getActionCommand().equals("중복확인")) {
			if (insId.getText().length() != 0) {
				if (list.isEmpty() && li.isEmpty()) {
					idInfoText1.setVisible(false);
					idInfoText3.setVisible(false);
					idInfoText2.setVisible(true);
				} else {
					idInfoText1.setVisible(false);
					idInfoText2.setVisible(false);
					idInfoText3.setVisible(true);
				}
			}
		}

		if (e.getActionCommand().equals("회원가입")) {
			if (insId.getText().length() != 0) {
				String tfpw = "";
				String rbge = "";
				if (gender[0].isSelected()) {
					rbge = gender[0].getText();
				} else {
					rbge = gender[1].getText();
				}
				for (char cha : insPw.getPassword()) {
					tfpw += cha;
				}
				System.out.println(rbge);
				dao1.list(insId.getText(), tfpw, insName.getText(), rbge, insBirth.getText(), insPhone.getText(),
						insEmail.getText());
			}
			join.dispose();
		}
	}

	public void windowClosing(WindowEvent e) {

	}

	public static void main(String[] args) {
		JoinFrame jf = new JoinFrame();
		jf.setJoinFrame();
	}

}
