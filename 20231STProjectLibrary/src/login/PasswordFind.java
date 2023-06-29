package login;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PasswordFind extends WindowAdapter implements ActionListener {
	private JFrame pwFind;
	private JLabel pwFindText, idText, nameText, numberText, infoDia1, infoDia2, infoPw;
	private JTextField inpId, inpName, inpNumber;
	private Button findButton, diaButton;
	private Dialog dia;
	private Font font, font1, font2;
	private MemberDAO dao;

	public void setPasswordFine() {
		dao = new MemberDAO();
		pwFind = new JFrame("ID Find");
		pwFindText = new JLabel("비밀번호 찾기");
		idText = new JLabel("ID");
		nameText = new JLabel("이름");
		numberText = new JLabel("전화번호");
		inpId = new JTextField();
		inpName = new JTextField();
		inpNumber = new JTextField();
		findButton = new Button("비밀번호찾기");
		font = new Font("고딕", Font.PLAIN, 30);
		font1 = new Font("고딕", Font.PLAIN, 17);
		font2 = new Font("고딕", Font.PLAIN, 15);

		pwFind.setSize(300, 350);
		pwFind.addWindowListener(this);
		pwFind.setIconImage(new ImageIcon(LoginFrame.icon).getImage());
		pwFind.setResizable(false);
		pwFind.setLocationRelativeTo(null);
		pwFind.setLayout(null);

		pwFindText.setBounds(50, 10, 195, 50);
		pwFindText.setFont(font);

		idText.setBounds(70, 90, 30, 20);
		idText.setFont(font2);

		inpId.setBounds(95, 85, 125, 30);
		inpId.setFont(font2);

		nameText.setBounds(55, 140, 60, 20);
		nameText.setFont(font2);

		inpName.setBounds(95, 135, 125, 30);
		inpName.setFont(font2);

		numberText.setBounds(30, 190, 60, 20);
		numberText.setFont(font2);
		inpNumber.setBounds(95, 185, 125, 30);

		findButton.setBounds(80, 240, 120, 30);
		findButton.setFont(font1);
		findButton.addActionListener(this);

		pwFind.add(pwFindText);
		pwFind.add(idText);
		pwFind.add(nameText);
		pwFind.add(numberText);
		pwFind.add(inpId);
		pwFind.add(inpName);
		pwFind.add(inpNumber);
		pwFind.add(findButton);

		pwFind.setVisible(true);
	}

	public void findDialog(String s) {
		dia = new Dialog(pwFind, "Info", true);
		infoPw = new JLabel();
		infoDia1 = new JLabel();
		infoDia2 = new JLabel();
		diaButton = new Button("확인");
		dia.add(infoPw);
		dia.add(infoDia1);
		dia.add(infoDia2);
		dia.add(diaButton);
		dia.addWindowListener(this);
		dia.setLayout(null);
		dia.setBounds(600, 300, 300, 150);
		infoDia1.setText("당신의 비밀번호는 ");
		infoDia2.setText(" 입니다.");
		infoPw.setText(s);
		infoPw.setFont(font2);
		infoDia1.setBounds(50, 40, 110, 30);
		infoDia2.setBounds(190, 65, 50, 30);
		infoPw.setBounds(80, 65, 50, 30);
		diaButton.setBounds(125, 102, 50, 25);
		diaButton.addActionListener(this);

		dia.setVisible(true);
	}

	public void findFailDialog(String s) {
		dia = new Dialog(pwFind, "Info", true);
		infoPw = new JLabel();
		infoDia1 = new JLabel();
		infoDia2 = new JLabel();
		diaButton = new Button("확인");
		dia.add(infoPw);
		dia.add(infoDia1);
		dia.add(infoDia2);
		dia.add(diaButton);
		dia.addWindowListener(this);
		dia.setLayout(null);
		dia.setBounds(600, 300, 300, 150);
		infoDia2.setText(s);
		infoDia2.setBounds(45, 55, 200, 30);
		infoDia2.setFont(font2);
		diaButton.setBounds(125, 102, 50, 25);
		diaButton.addActionListener(this);

		dia.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		if (e.getComponent() == dia) {
			dia.dispose();
		}
		if (e.getComponent() == pwFind) {
			LoginFrame login = new LoginFrame();
			login.getLoginFrame();
			pwFind.dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("비밀번호찾기")) {
			String id = inpId.getText();
			String name = inpName.getText();
			String num = inpNumber.getText();

			ArrayList<MemberVo> list = dao.list(id, name, num);
			list = dao.list(id, name, num);
			if (list.size() != 0) {
				MemberVo data = (MemberVo) list.get(0);
				findDialog(data.getPw());
			} else if (id.isEmpty()) {
				findFailDialog("아이디를 입력해주세요");
			} else if (name.isEmpty()) {
				findFailDialog("이름을 입력해주세요");
			} else if (num.isEmpty()) {
				findFailDialog("전화번호를 입력해주세요");
			} else {
				findFailDialog("회원정보를 찾을 수 없습니다.");
			}

		}
		if (e.getActionCommand().equals("확인")) {
			dia.dispose();
		}
	}

}