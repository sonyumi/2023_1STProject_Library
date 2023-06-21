package mainFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import login.LoginFrame;
import login.MemberVo;

public class MainFrame extends WindowAdapter {
	private JFrame main;
	private JTabbedPane tab;
	private MemberVo userInfo;
	
	public MainFrame(){
		
	}
	public MainFrame(MemberVo userInfo){
		this.userInfo = userInfo;
	}
	private void setFrameTab() {
		main = new JFrame("도서 대출 반납 시스템");
		tab=new JTabbedPane(JTabbedPane.TOP);
		MainMenu m1 = new MainMenu(userInfo);
		RentalReturnMenu m2 = new RentalReturnMenu(userInfo);
		main.setLocationRelativeTo(null);
		main.setResizable(false);
		main.add(tab);
		main.addWindowListener(this);
		tab.addTab("Main",m1);
		tab.addTab("대여/반납", m2);
		main.setSize(600,600);

		System.out.println(userInfo.getId());
		System.out.println(userInfo.getName());
		
	}
	public void windowClosing(WindowEvent e) {
		if(e.getComponent()==main) {
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
