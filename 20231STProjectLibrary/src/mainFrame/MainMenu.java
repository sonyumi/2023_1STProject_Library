package mainFrame;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import login.MemberVo;

@SuppressWarnings("serial")
public class MainMenu extends JPanel implements ActionListener {
	private JLabel[] book, bookText;
	private BookDAO dao = new BookDAO();
	private ArrayList<BookVo> list;

	public MainMenu(MemberVo userInfo) {
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		String sDay = dateFormat.format(date);
		Font font1 = new Font("고딕", Font.BOLD, 23);
		Font font2 = new Font("고딕", Font.PLAIN, 15);
		JLabel bestBookText = new JLabel("");
		JLabel userText = new JLabel();
		Button privious = new Button("< 이전");
		Button next = new Button("다음 >");
		LineBorder bb = new LineBorder(Color.black, 1, true);

		setLayout(null);

		add(bestBookText);
		add(userText);
		add(privious);
		add(next);
		int day = Integer.parseInt(sDay);
		list = dao.list(day);
		// 15일 이전일경우 랜덤 추천도서
		if (day < 15 || list.get(6).getId() == null) {
			int i = 0;
			bestBookText.setText("추천도서");
			while (true) {
				for (BookVo vo : list) {
					if (list.get(0).getName().equals(vo.getName())) {
						i++;
					}
				}
				if (i > 1) {
					list = dao.list(day);
				} else {

					break;
				}
			}

		} else {
			bestBookText.setText("이달의 베스트셀러");
		}

		userText.setText(userInfo.getName() + " 님 어서오세요 :D");
		userText.setBounds(375, 20, 210, 15);
		userText.setFont(font2);

		bestBookText.setBounds(50, 100, 210, 30);
		bestBookText.setFont(font1);

		privious.addActionListener(this);
		privious.setBounds(190, 420, 50, 30);
		next.addActionListener(this);
		next.setBounds(340, 420, 50, 30);

		JLabel[] grid = new JLabel[4];
		for (int i = 0; i < grid.length; i++) {
			grid[i] = new JLabel();
			add(grid[i]);
			grid[i].setBorder(bb);

		}

		grid[3].setBounds(30, 80, 520, 400); // 전체영역
		grid[0].setBounds(65, 145, 135, 200); // 책영역1
		grid[1].setBounds(227, 145, 135, 200); // 책영역2
		grid[2].setBounds(385, 145, 135, 200); // 책영역3

		book();
		for (int i = 0, j = 3; i < 3 && j < 6; i++, j++) {
			book[j].setVisible(false);
			bookText[j].setVisible(false); // 4,5,6번 책 이미지,책 이름 숨기기
			book[i].setVisible(true);
			bookText[i].setVisible(true); // 1,2,3번 책 이미지,책 이름 보이기
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("< 이전")) {
			if (book[0].isVisible()) { // 첫번째 책이 보여지고있는 경우
				for (int i = 0, j = 3; i < 3 && j < 6; i++, j++) {
					book[i].setVisible(false);
					bookText[i].setVisible(false); // 1,2,3번 책 이미지,책 이름 숨기기
					book[j].setVisible(true);
					bookText[j].setVisible(true); // 4,5,6번 책 이미지,책 이름 보이기
				}
			} else {
				for (int i = 0, j = 3; i < 3 && j < 6; i++, j++) {
					book[j].setVisible(false);
					bookText[j].setVisible(false); // 4,5,6번 책 이미지,책 이름 숨기기
					book[i].setVisible(true);
					bookText[i].setVisible(true); // 1,2,3번 책 이미지,책 이름 보이기
				}
			}
		}
		if (e.getActionCommand().equals("다음 >")) {
			if (book[0].isVisible()) { // 첫번째 책이 보여지고있는 경우
				for (int i = 0, j = 3; i < 3 && j < 6; i++, j++) {
					book[i].setVisible(false);
					bookText[i].setVisible(false); // 1,2,3번 책 이미지,책 이름 숨기기
					book[j].setVisible(true);
					bookText[j].setVisible(true); // 4,5,6번 책 이미지,책 이름 보이기
				}
			} else {
				for (int i = 0, j = 3; i < 3 && j < 6; i++, j++) {
					book[j].setVisible(false);
					bookText[j].setVisible(false); // 4,5,6번 책 이미지,책 이름 숨기기
					book[i].setVisible(true);
					bookText[i].setVisible(true); // 1,2,3번 책 이미지,책 이름 보이기
				}
			}
		}
	}

	public void book() {

		book = new JLabel[6];
		bookText = new JLabel[6];

		for (int i = 0; i < book.length; i++) {
			book[i] = new JLabel();
			bookText[i] = new JLabel();
			add(book[i]);
			add(bookText[i]);
			bookText[i].setText("<html><body style='text-align:center;'>" + (i + 1) + ". " + list.get(i).getName());
			ImageIcon img = new ImageIcon(list.get(i).getImgLink());
			Image icon = img.getImage();
			Image changeImg = icon.getScaledInstance(125, 190, Image.SCALE_SMOOTH);
			book[i].setIcon(new ImageIcon(changeImg));
		}

		bookText[0].setBounds(80, 350, 120, 50);
		book[0].setBounds(70, 150, 125, 190);

		bookText[1].setBounds(240, 350, 120, 50);
		book[1].setBounds(232, 150, 125, 190);

		bookText[2].setBounds(400, 350, 120, 50);
		book[2].setBounds(390, 150, 125, 190);

		bookText[3].setBounds(80, 350, 120, 50);
		book[3].setBounds(70, 150, 125, 190);

		bookText[4].setBounds(240, 350, 120, 50);
		book[4].setBounds(232, 150, 125, 190);

		bookText[5].setBounds(400, 350, 120, 50);
		book[5].setBounds(390, 150, 125, 190);
	}

}
