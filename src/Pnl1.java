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
		
		font_title = new Font("���� ���", Font.PLAIN, 20); 
		font_text = new Font("���� ���", Font.PLAIN, 15);		
				
		pnlImg = new JImagePanel(new ImageIcon("./img/background.png"));
		pnlImg.setLayout(null);
		
		pnlLog = new JPanel();
		pnlLog.setBounds(35, 100, 700, 460);
		pnlLog.setLayout(null);			
		
		lblId = new JLabel("���̵�");
		lblPwd = new JLabel("��й�ȣ");
		btnLog = new JButton("�α���");
		btnReg = new JButton("���� �����");		
		tfId = new JTextField();
		tfPwd = new JPasswordField();
		
		lblId.setFont(font_text);
		lblPwd.setFont(font_text);
		btnLog.setFont(font_text);	
		btnLog.setBackground(new Color(245,208,96));
		btnLog.addActionListener(this); //ȸ������
		
		btnReg.setFont(font_text);
		btnReg.setBackground(new Color(245,208,96));
		btnReg.addActionListener(new ActionListener() { //ȸ������ ��������
			
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
				JOptionPane.showMessageDialog(this, "���̵� �Է��ϼ���");
				return;
				
			}else if(tfPwd.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է��ϼ���");
				return;
			}
			
			boolean result = FileUtil.login(tfId.getText(), tfPwd.getText());// ���̵� ��й�ȣ�� �´��� Ȯ���ϴ� �޼���
			// �α����� �����ϸ� CurMember.txt�̶�� ������ ����� �ű⿣ �α����� ����� ȸ�������� ����.
			// �α��� �Ҷ� ���� CurMember.txt�� ������ �翬�� �ٲ��.			
		
			if(result==true) {
				if(tfId.getText().trim().equals("busanit")) {				
					 setVisible(false);
					 new ManagerMain();			 
					
				}else if(result==true){
					
					 setVisible(false);
					 new CustomerMain();
					 
				}
			}else{
				JOptionPane.showMessageDialog(this, "���̵�� ��й�ȣ�� Ȯ���ϼ���");
			}
			
			String[] current = FileUtil.getCurrentMember();// �α����� ����� ������ �������� �޼���
			
			System.out.println("���� �α����� ��� ����");
			System.out.println(current[0]);//�̸�
			System.out.println(current[1]);//���̵�
			System.out.println(current[2]);//��й�ȣ				
			System.out.println(current[3]);//�̸���
			System.out.println(current[4]);//��ȭ��ȣ
			System.out.println(current[5]);//����
			System.out.println(current[6]);//��ȣ�帣	
				
		}
	}

}