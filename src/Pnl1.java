import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Pnl1 extends JFrame implements ActionListener{
	JPanel pnlLog;	
	JImagePanel pnlImg;
	JButton btnLog, btnReg;
	JTextField tfId;
	JPasswordField tfPwd;
	JLabel lblId, lblPwd;
	Image background;
	Font font_title, font_text;
	
	public Pnl1() {
		this.setSize(770,660);
		this.setDefaultCloseOperation(3);			
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		font_title = new Font("맑은 고딕", Font.PLAIN, 20); 
		font_text = new Font("맑은 고딕", Font.PLAIN, 15);		
				
		pnlImg = new JImagePanel(new ImageIcon("./img/background.png"));
		pnlImg.setLayout(null);
		
		pnlLog = new JPanel();
		pnlLog.setBounds(35, 100, 700, 460);
		pnlLog.setLayout(null);			
		
		lblId = new JLabel("아이디");
		lblPwd = new JLabel("비밀번호");
		btnLog = new JButton("로그인");
		btnReg = new JButton("계정 만들기");		
		tfId = new JTextField();
		tfPwd = new JPasswordField();
		
		lblId.setFont(font_text);
		lblPwd.setFont(font_text);
		btnLog.setFont(font_text);	
		btnLog.setBackground(new Color(245,208,96));
		btnLog.addActionListener(this); //회원가입
		
		btnReg.setFont(font_text);
		btnReg.setBackground(new Color(245,208,96));
		btnReg.addActionListener(new ActionListener() { //회원가입 페이지로
			
			@Override
			public void actionPerformed(ActionEvent e) {				 
				 setVisible(false);
				 new Pnl2();
			}
		}); 
		
		tfId.setFont(font_text);		
		
		lblId.setBounds(220, 120, 100, 30);
	      tfId.setBounds(310, 120, 180, 30);
	      lblPwd.setBounds(220, 160, 100, 30);
	      tfPwd.setBounds(310, 160, 180, 30);
	      btnLog.setBounds(220, 220, 130, 30);
	      btnReg.setBounds(360, 220, 130, 30);   	
		
		pnlImg.add(pnlLog);		
		pnlLog.add(lblId);
		pnlLog.add(tfId);
		pnlLog.add(lblPwd);
		pnlLog.add(tfPwd);
		pnlLog.add(btnLog,"South");
		pnlLog.add(btnReg);
				
		this.add(pnlImg);		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Pnl1();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnLog) {
			if(tfId.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
				return;
				
			}else if(tfPwd.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
				return;
			}
			
			boolean result = FileUtil.login(tfId.getText(), tfPwd.getText());// 아이디 비밀번호로 맞는지 확인하는 메서드
			// 로그인이 성공하면 CurMember.txt이라는 파일이 생기며 거기엔 로그인한 사람의 회원정보가 담긴다.
			// 로그인 할때 마다 CurMember.txt의 내용은 당연히 바뀐다.			
		
			if(result==true) {
				if(tfId.getText().trim().equals("busanit")) {				
					 setVisible(false);
					 new ManagerMain();			 
					
				}else if(result==true){
					
					 setVisible(false);
					 new CustomerMain();
					 
				}
			}else{
				JOptionPane.showMessageDialog(this, "아이디와 비밀번호를 확인하세요");
			}
			
			String[] current = FileUtil.getCurrentMember();// 로그인한 사람의 정보를 가져오는 메서드
			
			System.out.println("현재 로그인한 멤버 정보");
			System.out.println(current[0]);//이름
			System.out.println(current[1]);//아이디
			System.out.println(current[2]);//비밀번호				
			System.out.println(current[3]);//이메일
			System.out.println(current[4]);//전화번호
			System.out.println(current[5]);//성별
			System.out.println(current[6]);//선호장르	
				
		}
	}

}