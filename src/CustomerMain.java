import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CustomerMain extends JFrame implements ActionListener{
	Pnl3 main;
	Reservation reservation;
	Screening screening;
	Notmanager cuspnl;
	MyOrder myorder;
	JPanel wrappnl, leftbtnspnl, cardspnl;
	JButton MovieMainBtn, MovieListBtn, TicketingBtn, CheckingBtn, managerbtn;
	CardLayout cardlayout;
	Color back=new Color(232, 232, 232);
	Font font=new Font("맑은 고딕", Font.BOLD, 20);
	public CustomerMain() {
		this.setSize(925,660);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		
		wrappnl=new JPanel(new BorderLayout());
		leftbtnspnl=new JPanel(new GridLayout(0,1));
		cardspnl=new JPanel(new CardLayout());
		
		MovieMainBtn=new JButton("메인");
		btnset(MovieMainBtn);
		MovieMainBtn.addActionListener(this);
		MovieListBtn=new JButton("현재 상영작");
		btnset(MovieListBtn);
		MovieListBtn.addActionListener(this);
		TicketingBtn=new JButton("예매하기");
		btnset(TicketingBtn);
		TicketingBtn.addActionListener(this);
		CheckingBtn=new JButton("예매확인");
		btnset(CheckingBtn);
		CheckingBtn.addActionListener(this);
		managerbtn=new JButton("관리자");
		btnset(managerbtn);
		managerbtn.addActionListener(this);
		
		managerbtn.setVisible(false);
		
		main=new Pnl3(this);
		cuspnl=new Notmanager();
		reservation=new Reservation();
		screening=new Screening(this);
		myorder=new MyOrder();
		
		cardspnl.add(main,"main");
		cardspnl.add(screening,"screening");
		cardspnl.add(reservation,"reservation");
		cardspnl.add(myorder,"myorder");
		cardspnl.add(cuspnl,"notmpnl");
		
		cardlayout=(CardLayout)(cardspnl.getLayout());
		
		leftbtnspnl.add(MovieMainBtn);
		leftbtnspnl.add(MovieListBtn);
		leftbtnspnl.add(TicketingBtn);
		leftbtnspnl.add(CheckingBtn);
		leftbtnspnl.add(managerbtn);
		
		this.add(leftbtnspnl,"West");
		this.add(cardspnl);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new CustomerMain();

	}
	public void btnset(JButton btn) {
		if(btn==MovieMainBtn) {
			btn.setBackground(Color.black);
			btn.setForeground(Color.WHITE);
		}
		else {
			btn.setBackground(back);
		}
		btn.setFont(font);
		btn.setBorderPainted(false);
	}
	public void color(JButton btn,boolean str) {
		if(str==true) {
			btn.setBackground(Color.black);
			btn.setForeground(Color.WHITE);
		}else {
			btn.setBackground(back);
			btn.setForeground(Color.black);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==MovieMainBtn) {
			cardlayout.show(cardspnl, "main");
			color(MovieMainBtn,true);
			color(MovieListBtn,false);
			color(TicketingBtn,false);
			color(CheckingBtn,false);
		}
		if(e.getSource()==MovieListBtn) {
			cardlayout.show(cardspnl, "screening");
			color(MovieMainBtn,false);
			color(MovieListBtn,true);
			color(TicketingBtn,false);
			color(CheckingBtn,false);
		}
		if(e.getSource()==TicketingBtn) {
			cardlayout.show(cardspnl, "reservation");
			color(MovieMainBtn,false);
			color(MovieListBtn,false);
			color(TicketingBtn,true);
			color(CheckingBtn,false);
		}
		if(e.getSource()==CheckingBtn) {
			cardlayout.show(cardspnl, "myorder");
			color(MovieMainBtn,false);
			color(MovieListBtn,false);
			color(TicketingBtn,false);
			color(CheckingBtn,true);
		}
		if(e.getSource()==managerbtn) {
			JOptionPane.showMessageDialog(null, "관리자 권한이 없습니다.");
			cardlayout.show(cardspnl, "main");
			color(MovieMainBtn,true);
			color(MovieListBtn,false);
			color(TicketingBtn,false);
			color(CheckingBtn,false);
		}
	}

}