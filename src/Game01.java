import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Game01 extends JFrame implements ActionListener{
	JTextField moneytf, horsetf;
	JLabel moneylbl, horselbl;
	JButton startBtn;
	JPanel toppnl, mainpnl, bottompnl, horse1pnl, horse2pnl, horse3pnl, line;
	Thread t;
	int a=50, b=50, c=50;
	double cnt=0;
	public Game01() {
		this.setSize(500,450);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		
		toppnl=new JPanel();
		horselbl=new JLabel("1~3번말 중 골라주세요");
		horsetf=new JTextField(1);
		moneylbl=new JLabel("돈을 입력하세요");
		moneytf=new JTextField(10);
		toppnl.add(horselbl);
		toppnl.add(horsetf);
		toppnl.add(moneylbl);
		toppnl.add(moneytf);
		
		mainpnl=new JPanel(null);
		horse1pnl=new JPanel();
		horse1pnl.setBackground(Color.yellow);
		horse1pnl.setBounds(a, 50, 50, 50);
		horse2pnl=new JPanel();
		horse2pnl.setBackground(Color.pink);
		horse2pnl.setBounds(b, 150, 50, 50);
		horse3pnl=new JPanel();
		horse3pnl.setBackground(Color.orange);
		horse3pnl.setBounds(c, 250, 50, 50);
		line=new JPanel();
		line.setBackground(Color.white);
		line.setBounds(445, 25, 10, 300);
		mainpnl.add(horse1pnl);
		mainpnl.add(horse2pnl);
		mainpnl.add(horse3pnl);
		mainpnl.add(line);
		
		startBtn=new JButton("시작");
		startBtn.addActionListener(this);
		
		this.add(toppnl,"North");
		this.add(mainpnl);
		this.add(startBtn,"South");
		
		this.setVisible(true);
	}
	
	void threadrun() {
		t=new Thread() {
			@Override
			public void run() {
				String money=moneytf.getText();
				int to = Integer.parseInt(money);
				while(true) {
					if(a==400 || b==400 || c==400) {
						if(a==400) {
							if(horsetf.getText().equals("1")) {
								JOptionPane.showMessageDialog(null, "선택하신 말이 1등 하였습니다.\n"+(to*2)+"원을 벌었습니다.\n1등까지 "+cnt+"초 거림");
							}
							else {
								JOptionPane.showMessageDialog(null, "선택하신 말이 1등 못하였습니다.\n 돈을 벌지 못하였습니다");
							}
						}if(b==400) {
							if(horsetf.getText().equals("2")) {
								JOptionPane.showMessageDialog(null, "선택하신 말이 1등 하였습니다.\n"+(to*2)+"원을 벌었습니다.\n1등까지 "+cnt+"초 거림");
							}
							else {
								JOptionPane.showMessageDialog(null, "선택하신 말이 1등 못하였습니다.\n 돈을 벌지 못하였습니다");
							}
						}if(c==400) {
							if(horsetf.getText().equals("3")) {
								JOptionPane.showMessageDialog(null, "선택하신 말이 1등 하였습니다.\n"+(to*2)+"원을 벌었습니다.\n1등까지 "+cnt+"초 거림");
							}
							else {
								JOptionPane.showMessageDialog(null, "선택하신 말이 1등 못하였습니다.\n 돈을 벌지 못하였습니다");
							}
						}
						a=50;
						horse1pnl.setLocation(a, 50);
						b=50;
						horse2pnl.setLocation(b, 150);
						c=50;
						horse3pnl.setLocation(c, 250);
						cnt=0;
						break;
					}else {
						int num=(int)(Math.random()*3);
						if(num==0) {
							a=a+50;
							horse1pnl.setLocation(a, 50);
						}else if(num==1) {
							b=b+50;
							horse2pnl.setLocation(b, 150);
						}else if(num==2) {
							c=c+50;
							horse3pnl.setLocation(c, 250);
						}
						try {
							Thread.sleep(500);
							cnt=cnt+0.5;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				
			}
		};
		t.start();
	}
	
	public static void main(String[] args) {
		new Game01();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==startBtn) {
			threadrun();
		}
	}

}
