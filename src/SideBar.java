import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SideBar extends JPanel implements ActionListener{
	private ManagerMain parents;
	JButton mainBtn, movieslistBtn, reservationBtn, checkingBtn, managerBtn;
	Color back=new Color(232, 232, 232);
	Font font=new Font("맑은 고딕", Font.BOLD, 20);
	public SideBar(ManagerMain parents) {
		this.parents=parents;
		
		this.setLayout(new GridLayout(0,1));
		mainBtn=new JButton("메인");
		btnset(mainBtn);
		mainBtn.addActionListener(this);
		
		movieslistBtn=new JButton("현재 상영작");
		btnset(movieslistBtn);
		movieslistBtn.addActionListener(this);
		
		reservationBtn=new JButton("예매하기");
		btnset(reservationBtn);
		reservationBtn.addActionListener(this);
		
		checkingBtn=new JButton("예매확인");
		btnset(checkingBtn);
		checkingBtn.addActionListener(this);
		
		managerBtn=new JButton("관리자");
		btnset(managerBtn);
		managerBtn.addActionListener(this);
		
		
		if(FileUtil.getCurrentMember()[1].equals("busanit")) {
			mainBtn.setVisible(false);
			movieslistBtn.setVisible(false);
			reservationBtn.setVisible(false);
			checkingBtn.setVisible(false);
		}
		
		
		
		this.add(mainBtn);
		this.add(movieslistBtn);
		this.add(reservationBtn);
		this.add(checkingBtn);
		this.add(managerBtn);
		
	}
	public void btnset(JButton btn) {
		if(btn==managerBtn) {
			btn.setBackground(Color.black);
			btn.setForeground(Color.WHITE);
		}
		else {
			btn.setBackground(back);
		}
		btn.setFont(font);
		btn.setBorderPainted(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==managerBtn) {
			parents.cardlayout.first(parents.cards);
		}
		else {
			JOptionPane.showMessageDialog(null, "현재 관리자 모드입니다.");
		}
	}

}
