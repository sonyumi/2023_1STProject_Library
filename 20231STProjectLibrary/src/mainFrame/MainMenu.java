package mainFrame;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import login.MemberVo;

@SuppressWarnings("serial")
public class MainMenu extends JPanel implements ActionListener {
	private JLabel userText, bestBookText, bookFrame, book1, book1Text1, book2, book2Text1, book3, book3Text1, book4,
			book4Text1, book5, book5Text1, book6, book6Text1, grid1, grid2, grid3;
	private Button privious, next;
	private BookDAO dao;
	private ArrayList<String> codeList;
	private ArrayList<String> imgList;
	private ArrayList<String> nameList;
	private ArrayList<String> wirterList;
	private ArrayList<String> publisherList;
	private LineBorder bb;
	private Font font1, font2;

	public MainMenu(MemberVo userInfo) {
		font1 = new Font("고딕", Font.BOLD, 23);
		font2 = new Font("고딕", Font.PLAIN, 15);
		bestBookText = new JLabel("이 달의 베스트셀러");
		userText = new JLabel();
		privious = new Button("< 이전");
		next = new Button("다음 >");

		setLayout(null);

		add(bestBookText);
		add(userText);
		add(privious);
		add(next);

		userText.setText(userInfo.getName() + " 님 어서오세요 :D");
		userText.setBounds(400, 20, 165, 15);
		userText.setFont(font2);

		bestBookText.setBounds(50, 100, 210, 30);
		bestBookText.setFont(font1);

		privious.addActionListener(this);
		privious.setBounds(190, 420, 50, 30);
		next.addActionListener(this);
		next.setBounds(340, 420, 50, 30);
		grid();
		book1();
		book2();
		book3();
//		book4();
//		book5();
//		book6();
		System.out.println(userInfo.getId());
		System.out.println(userInfo.getName());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("< 이전")) {
			if (book1.isVisible()) {
				book1.setVisible(false);
				book2.setVisible(false);
				book3.setVisible(false);
				book1Text1.setVisible(false);
				book2Text1.setVisible(false);
				book3Text1.setVisible(false);
				book4();
				book5();
				book6();
			} else {
				book4.setVisible(false);
				book5.setVisible(false);
				book6.setVisible(false);
				book4Text1.setVisible(false);
				book5Text1.setVisible(false);
				book6Text1.setVisible(false);
				book1.setVisible(true);
				book2.setVisible(true);
				book3.setVisible(true);
				book1Text1.setVisible(true);
				book2Text1.setVisible(true);
				book3Text1.setVisible(true);
			}
		}
		if (e.getActionCommand().equals("다음 >")) {
			if (book1.isVisible()) {
				book1.setVisible(false);
				book2.setVisible(false);
				book3.setVisible(false);
				book1Text1.setVisible(false);
				book2Text1.setVisible(false);
				book3Text1.setVisible(false);
				book4();
				book5();
				book6();
			} else {
				book4.setVisible(false);
				book5.setVisible(false);
				book6.setVisible(false);
				book4Text1.setVisible(false);
				book5Text1.setVisible(false);
				book6Text1.setVisible(false);
				book1.setVisible(true);
				book2.setVisible(true);
				book3.setVisible(true);
				book1Text1.setVisible(true);
				book2Text1.setVisible(true);
				book3Text1.setVisible(true);
			}
		}
	}

	public void setBookcode() {
		dao = new BookDAO();
		ArrayList<BookVo> list = dao.list(6);
		codeList = new ArrayList<String>();
		nameList = new ArrayList<String>();
		imgList = new ArrayList<String>();
		wirterList = new ArrayList<String>();
		publisherList = new ArrayList<String>();

		BookVo data;

		// 베스트셀러 상위 6위 책 코드값, 이미지값
		for (int i = 0; i < 6; i++) {
			data = (BookVo) list.get(i);
			codeList.add(data.getCode());
			nameList.add(data.getName());
			wirterList.add(data.getWriter());
			publisherList.add(data.getPublisher());
			imgList.add(data.getImgLink());
		}
	}

	public void grid() {
		bb = new LineBorder(Color.black, 1, true);
		grid1 = new JLabel();
		grid2 = new JLabel();
		grid3 = new JLabel();
		bookFrame = new JLabel();
		bookFrame.setBorder(bb);
		add(bookFrame);
		bookFrame.setBounds(30, 80, 520, 400);
		add(grid1);
		grid1.setBounds(65, 145, 135, 200);
		grid1.setBorder(bb);
		add(grid2);
		grid2.setBounds(227, 145, 135, 200);
		grid2.setBorder(bb);
		add(grid3);
		grid3.setBounds(385, 145, 135, 200);
		grid3.setBorder(bb);

	}

	public void book1() {
		setBookcode();
		book1 = new JLabel();
		book1Text1 = new JLabel(
				"<html><body style='text-align:center;'>1. " + nameList.get(0));
		add(book1);
		add(book1Text1);
		book1Text1.setBounds(80, 350, 120, 50);
		ImageIcon img = new ImageIcon(imgList.get(0));
		Image icon = img.getImage();
		Image changeImg = icon.getScaledInstance(125, 190, Image.SCALE_SMOOTH);
		book1.setIcon(new ImageIcon(changeImg));
		book1.setBounds(70, 150, 125, 190);

	}

	public void book2() {
		setBookcode();
		book2 = new JLabel();
		book2Text1 = new JLabel();
		add(book2);
		add(book2Text1);
		book2Text1.setText("<html><body style='text-align:center;'>2. " + nameList.get(1));
		book2Text1.setBounds(240, 350, 120, 50);
		ImageIcon img = new ImageIcon(imgList.get(1));
		Image icon = img.getImage();
		Image changeImg = icon.getScaledInstance(125, 190, Image.SCALE_SMOOTH);
		book2.setIcon(new ImageIcon(changeImg));
		book2.setBounds(232, 150, 125, 190);
	}

	public void book3() {
		setBookcode();
		book3 = new JLabel();
		book3Text1 = new JLabel();
		add(book3);
		add(book3Text1);
		book3Text1.setBounds(400, 350, 120, 50);
		book3Text1.setText("<html><body style='text-align:center;'>3. " + nameList.get(2));
		ImageIcon img = new ImageIcon(imgList.get(2));
		Image icon = img.getImage();
		Image changeImg = icon.getScaledInstance(125, 190, Image.SCALE_SMOOTH);
		book3.setIcon(new ImageIcon(changeImg));
		book3.setBounds(390, 150, 125, 190);
	}

	public void book4() {
		setBookcode();
		book4 = new JLabel();
		book4Text1 = new JLabel();
		add(book4);
		add(book4Text1);
		book4Text1.setBounds(80, 350, 120, 50);
		book4Text1.setText("<html><body style='text-align:center;'>4. " + nameList.get(3));
		ImageIcon img = new ImageIcon(imgList.get(3));
		Image icon = img.getImage();
		Image changeImg = icon.getScaledInstance(125, 190, Image.SCALE_SMOOTH);
		book4.setIcon(new ImageIcon(changeImg));
		book4.setBounds(70, 150, 125, 190);
	}

	public void book5() {
		setBookcode();
		book5 = new JLabel();
		book5Text1 = new JLabel();
		add(book5);
		add(book5Text1);
		book5Text1.setBounds(240, 350, 120, 50);
		book5Text1.setText("<html><body style='text-align:center;'>5. " + nameList.get(4));
		ImageIcon img = new ImageIcon(imgList.get(4));
		Image icon = img.getImage();
		Image changeImg = icon.getScaledInstance(125, 190, Image.SCALE_SMOOTH);
		book5.setIcon(new ImageIcon(changeImg));
		book5.setBounds(232, 150, 125, 190);
	}

	public void book6() {
		setBookcode();
		book6 = new JLabel();
		book6Text1 = new JLabel();
		add(book6);
		add(book6Text1);
		book6Text1.setBounds(400, 350, 120, 50);
		book6Text1.setText("<html><body style='text-align:center;'>6. " + nameList.get(5));
		ImageIcon img = new ImageIcon(imgList.get(5));
		Image icon = img.getImage();
		Image changeImg = icon.getScaledInstance(125, 190, Image.SCALE_SMOOTH);
		book6.setIcon(new ImageIcon(changeImg));
		book6.setBounds(390, 150, 125, 190);
	}

}
