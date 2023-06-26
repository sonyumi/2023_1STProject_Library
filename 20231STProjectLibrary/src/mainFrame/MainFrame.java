package mainFrame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import login.LoginFrame;
import login.MemberVo;

public class MainFrame extends WindowAdapter {
	private JFrame main;
	private JTabbedPane tab;
	private MemberVo userInfo;

	public MainFrame(MemberVo userInfo) {
		this.userInfo = userInfo;
	}

	private void setFrameTab() {
		main = new JFrame("도서 대출 반납 시스템");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		tab = new JTabbedPane(JTabbedPane.TOP);
		MainMenu m1 = new MainMenu(userInfo);

		main.setIconImage(new ImageIcon(LoginFrame.icon).getImage());
		main.setLocation(screenSize.width / 2 - 300, screenSize.height / 2 - 300);

		main.setResizable(false);
		main.add(tab);
		main.addWindowListener(this);
		main.setSize(600, 600);

		if (userInfo.getId().equals("super")) {
			BookSearchMenu m2 = new BookSearchMenu();
			tab.addTab("Main", m1);
			tab.addTab("도서관리", m2);
		} else {
			RentalReturnMenu m2 = new RentalReturnMenu(userInfo);
			UserInfoMenu m3 = new UserInfoMenu(userInfo);
			tab.addTab("Main", m1);
			tab.addTab("도서검색 및 대여/반납", m2);
			tab.addTab("회원정보", m3);
		}

		System.out.println(userInfo.getId());
		System.out.println(userInfo.getName());

	}

	public void windowClosing(WindowEvent e) {
		if (e.getComponent() == main) {
			LoginFrame login = new LoginFrame();
			login.getLoginFrame();
			main.dispose();
		}
	}

	public void getMainFrame() {
		setFrameTab();
		main.setVisible(true);
	}
}
