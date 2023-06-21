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

public class IdFind extends WindowAdapter implements ActionListener {
	private JFrame idFind;
	private JLabel idFindText, nameText, birthText, numberText, infoDia1, infoDia2, infoId;
	private JTextField inpName, inpBirth, inpNumber;
	private Button findButton, diaButton;
	private Dialog dia;
	private Font font, font1, font2;
	private MemberDAO dao;

	public void setIdFine() {
		dao = new MemberDAO();
		idFind = new JFrame("ID Find");
		idFindText = new JLabel("아이디 찾기");
		nameText = new JLabel("이름");
		birthText = new JLabel("생년월일");
		numberText = new JLabel("전화번호");
		inpName = new JTextField();
		inpBirth = new JTextField();
		inpNumber = new JTextField();
		findButton = new Button("아이디찾기");
		font = new Font("고딕", Font.PLAIN, 30);
		font1 = new Font("고딕", Font.PLAIN, 17);
		font2 = new Font("고딕", Font.PLAIN, 15);

		idFind.setSize(300, 350);
		idFind.setIconImage(new ImageIcon(LoginFrame.icon).getImage());
		idFind.setResizable(false);
		idFind.setLocationRelativeTo(null);
		idFind.setLayout(null);

		idFindText.setBounds(65, 10, 165, 50);
		idFindText.setFont(font);

		nameText.setBounds(55, 90, 30, 20);
		nameText.setFont(font2);

		inpName.setBounds(95, 85, 125, 30);
		inpName.setFont(font2);

		birthText.setBounds(30, 140, 60, 20);
		birthText.setFont(font2);

		inpBirth.setBounds(95, 135, 125, 30);
		inpBirth.setFont(font2);

		numberText.setBounds(30, 190, 60, 20);
		numberText.setFont(font2);
		inpNumber.setBounds(95, 185, 125, 30);

		findButton.setBounds(100, 240, 90, 30);
		findButton.setFont(font1);
		findButton.addActionListener(this);

		idFind.add(idFindText);
		idFind.add(nameText);
		idFind.add(birthText);
		idFind.add(numberText);
		idFind.add(inpName);
		idFind.add(inpBirth);
		idFind.add(inpNumber);
		idFind.add(findButton);

		idFind.setVisible(true);
	}

	public void findDialog(String s) {
		dia = new Dialog(idFind, "Info", true);
		infoId = new JLabel();
		infoDia1 = new JLabel();
		infoDia2 = new JLabel();
		diaButton = new Button("확인");
		dia.add(infoId);
		dia.add(infoDia1);
		dia.add(infoDia2);
		dia.add(diaButton);
		dia.addWindowListener(this);
		dia.setLayout(null);
		dia.setBounds(600, 300, 300, 150);
		infoDia1.setText("당신의 아이디는 ");
		infoDia2.setText(" 입니다.");
		infoId.setText(s);
		infoId.setFont(font2);
		infoDia1.setBounds(50, 40, 110, 30);
		infoDia2.setBounds(190, 65, 50, 30);
		infoId.setBounds(80, 65, 50, 30);
		diaButton.setBounds(125, 102, 50, 25);
		diaButton.addActionListener(this);

		dia.setVisible(true);
	}

	public void findFailDialog() {
		dia = new Dialog(idFind, "Info", true);
		infoId = new JLabel();
		infoDia1 = new JLabel();
		infoDia2 = new JLabel();
		diaButton = new Button("확인");
		dia.add(infoId);
		dia.add(infoDia1);
		dia.add(infoDia2);
		dia.add(diaButton);
		dia.addWindowListener(this);
		dia.setLayout(null);
		dia.setBounds(600, 300, 300, 150);
		infoDia2.setText("회원정보를 찾을 수 없습니다.");
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
		if (e.getComponent() == idFind) {
			idFind.dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("아이디찾기")) {
			String name = inpName.getText();
			String birth = inpBirth.getText();
			String num = inpNumber.getText();

			ArrayList<MemberVo> list = dao.list(name, birth, num, "g_user");
			list = dao.list(name, birth, num, "g_user");
			if (list.size() != 0) {
				MemberVo data = (MemberVo) list.get(0);
				findDialog(data.getId());
			} else {
				findFailDialog();//예외처리필요
			}

		}
		if (e.getActionCommand().equals("확인")) {
			dia.dispose();
		}
	}

}
