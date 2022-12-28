import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ManagerMain extends JFrame implements ActionListener{
	ManagerMember mmpnl;
	ManagerMoviesList mmlpnl;
	SideBar sidebar;
	JImagePanel pnl;
	JPanel mainpnl, cards;
	JButton moviesbtn, memberbtn, logoutbtn;
	CardLayout cardlayout;
	Font btnfont=new Font("¸¼Àº °íµñ", Font.PLAIN, 20); 
	Font font=new Font("¸¼Àº °íµñ", Font.PLAIN, 15); 
	public ManagerMain() {
		this.setSize(925,700);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		
		mmpnl=new ManagerMember(this);
		mmlpnl=new ManagerMoviesList();
		
		mainpnl=new JPanel(new BorderLayout());
		
		sidebar=new SideBar(this);
		pnl = new JImagePanel(new ImageIcon("./img/background.png"));
		pnl.setLayout(null);
		
		moviesbtn=new JButton("¿µÈ­°ü¸®");
		moviesbtn.setFont(btnfont);
		moviesbtn.setBounds(55, 180, 300, 300);
		btnset(moviesbtn);
		moviesbtn.addActionListener(this);
		
		memberbtn=new JButton("È¸¿ø°ü¸®");
		memberbtn.setFont(btnfont);
		btnset(memberbtn);
		memberbtn.setBounds(415, 180, 300, 300);
		memberbtn.addActionListener(this);
		
		logoutbtn=new JButton("·Î±×¾Æ¿ô");
		logoutbtn.setFont(font);
		btnset(logoutbtn);
		logoutbtn.setBounds(615, 490, 100, 30);
		logoutbtn.addActionListener(this);
		
		pnl.add(moviesbtn);
		pnl.add(memberbtn);
		pnl.add(logoutbtn);
		
		cards=new JPanel(new CardLayout());
		cards.add(pnl,"main");
		cards.add(mmlpnl,"movies");
		cards.add(mmpnl,"mmpnl");
		cardlayout=(CardLayout)(cards.getLayout());
		
		mainpnl.add(sidebar,"West");
		mainpnl.add(cards);
		
		this.add(mainpnl);
		
		this.setVisible(true);
	}
	void btnset(JButton btn) {
		btn.setBackground(new Color(245,208,96));
		btn.setBorderPainted(false);
	}
	
	public static void main(String[] args) {
		new ManagerMain();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==moviesbtn) {
			cardlayout.next(cards);
		}
		if(e.getSource()==memberbtn) {
			cardlayout.show(cards, "mmpnl");
		}
		if(e.getSource()==logoutbtn) {
			new Pnl1();
			setVisible(false);
		}
		
	}

}