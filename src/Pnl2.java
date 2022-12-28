import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Pnl2 extends JFrame implements ActionListener{
	JPanel pnlReg;
	JImagePanel pnlImg;
	JTextField tfName, tfId, tfEmail, tfPhon2,tfPhon3;
	JPasswordField tfPwd1, tfPwd2;
	JLabel lblName, lblId, lblPwd1, lblPwd2, lblEmail, lblPhon, lblGen, lblLike;
	JCheckBox cbM1,cbM2,cbM3,cbM4; 
	JRadioButton cbMale, cbFeMale;
	JButton btnReg, btnCancle, btnIdck;
	JComboBox comboPhon1;
	Matcher matcher;
	Font font_title, font_text;
	
	public Pnl2() {
		
		this.setSize(770,660);
		this.setDefaultCloseOperation(3);			
		this.setResizable(false);
		this.setLocationRelativeTo(null);	
		
		pnlImg = new JImagePanel(new ImageIcon("./img/background.png"));
		pnlImg.setLayout(null);
	
		pnlReg = new JPanel();
		pnlReg.setBounds(35, 100, 700, 460);
		pnlReg.setLayout(null);			
				
		lblName = new JLabel("�̸�");
		lblId = new JLabel("���̵�");
		lblPwd1 = new JLabel("��й�ȣ");
		lblPwd2 = new JLabel("��й�ȣ Ȯ��");
		lblEmail = new JLabel("�̸���");
		lblPhon = new JLabel("��ȭ��ȣ");	
		lblGen = new JLabel("����");
		lblLike = new JLabel("��ȣ ��ȭ�帣");
		font_title = new Font("���� ���", Font.PLAIN, 20); 
		font_text = new Font("���� ���", Font.PLAIN, 15);
				
		pnlReg.add(lblName);
		pnlReg.add(lblId);
		pnlReg.add(lblPwd1);
		pnlReg.add(lblPwd2);
		pnlReg.add(lblEmail);
		pnlReg.add(lblPhon);
		pnlReg.add(lblGen);
		pnlReg.add(lblLike);
		pnlImg.add(pnlReg);
				
		lblName.setBounds(180, 50, 100, 30);
		lblId.setBounds(180, 90, 100, 30);
		lblPwd1.setBounds(180, 130, 100, 30);
		lblPwd2.setBounds(180, 170, 140, 30);
		lblEmail.setBounds(180, 210, 100, 30);
		lblPhon.setBounds(180, 250,100, 30);		
		lblGen.setBounds(180, 290, 100, 30);
		lblLike.setBounds(180, 330,140, 30);	
		
		lblName.setFont(font_text);
		lblId.setFont(font_text);
		lblPwd1.setFont(font_text);
		lblPwd2.setFont(font_text);
		lblEmail.setFont(font_text);
		lblPhon.setFont(font_text);		
		lblGen.setFont(font_text);
		lblLike.setFont(font_text);
		
		tfName = new JTextField();
		tfId = new JTextField();
		tfPwd1 = new JPasswordField();
		tfPwd2 = new JPasswordField();
		tfEmail= new JTextField();
		
		String[] Phon1 = {"010","017","02","051","031","055"};
		comboPhon1= new JComboBox(Phon1);		
		tfPhon2= new JTextField();
		tfPhon3= new JTextField();
				
		JPanel pnlGen = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnlLike = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		ButtonGroup group = new ButtonGroup();		
		cbMale = new JRadioButton("����",true);
		cbMale.setFont(font_text);
		
		cbFeMale = new JRadioButton("����");
		cbFeMale.setFont(font_text);
		
		group.add(cbMale);
		group.add(cbFeMale);		
		pnlGen.add(cbMale);
		pnlGen.add(cbFeMale);
		
		
		
		cbM1 = new JCheckBox("�׼� ");
		cbM1.setFont(font_text);		
		
		cbM2 = new JCheckBox("�ڹ� ");
		cbM2.setFont(font_text);
		
		cbM3 = new JCheckBox("��� ");
		cbM3.setFont(font_text);
		
		cbM4 = new JCheckBox("������ ");
		cbM4.setFont(font_text);
		
		pnlLike.add(cbM1);
		pnlLike.add(cbM2);
		pnlLike.add(cbM3);
		pnlLike.add(cbM4);
		
		btnIdck = new JButton("�ߺ�");
		btnIdck.addActionListener(this);//�ߺ��˻� �̺�Ʈ
		btnIdck.setFont(font_text);
		btnIdck.setBackground(new Color(245,208,96));
		
		btnReg = new JButton("����");
		btnReg.addActionListener(this); //member.Txt ȸ������ ���� 
		btnReg.setFont(font_text);
		btnReg.setBackground(new Color(245,208,96));
		
		btnCancle = new JButton("���");	
		btnCancle.addActionListener(new ActionListener() { //ȸ������ ��������
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 new Pnl1();
				 setVisible(false);
				
			}
		}); 
		btnCancle.setFont(font_text);
		btnCancle.setBackground(new Color(245,208,96));
				
		pnlReg.add(tfName);
		pnlReg.add(tfId);
		pnlReg.add(btnIdck);
		pnlReg.add(tfPwd1);
		pnlReg.add(tfPwd2);
		pnlReg.add(tfEmail);
		pnlReg.add(comboPhon1);		
		pnlReg.add(tfPhon2);		
		pnlReg.add(tfPhon3);
		pnlReg.add(pnlGen);
		pnlReg.add(pnlLike);
		pnlReg.add(btnReg);
		pnlReg.add(btnCancle);
		
		tfName.setBounds(300, 50, 200, 30);
		tfId.setBounds(300, 90, 200, 30);
		btnIdck.setBounds(500, 92, 65, 25);		
		tfPwd1.setBounds(300, 130, 200, 30);
		tfPwd2.setBounds(300, 170, 200, 30);
		tfEmail.setBounds(300, 210, 200, 30);
		comboPhon1.setBounds(300, 250, 60, 30);		
		tfPhon2.setBounds(370, 250, 60, 30);		
		tfPhon3.setBounds(440, 250, 60, 30);		
		pnlGen.setBounds(300, 290, 200, 35);
		pnlLike.setBounds(290, 325, 300, 35);		
		
		tfName.setFont(font_text);
		tfId.setFont(font_text);
		btnIdck.setFont(font_text);	
		tfPwd1.setFont(font_text);
		tfPwd2.setFont(font_text);
		tfEmail.setFont(font_text);
		
		tfPhon2.setFont(font_text);
		tfPhon3.setFont(font_text);	
		
				
		pnlReg.add(btnReg);
		pnlReg.add(btnCancle);
		
		btnReg.setBounds(300,380,90,30);
		btnCancle.setBounds(405,380,90,30);
		
		
		
		this.add(pnlImg);		
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new Pnl2();
	}		 
	
	void registMember() {
		
		String pwd1 = tfPwd1.getText();
		String pwd2 = tfPwd2.getText();
		
		if(!pwd1.equals(pwd2)) {
		JOptionPane.showMessageDialog(this,"��й�ȣ�� ��ġ���� �ʽ��ϴ�");
		return;
		}
		
		if(tfName.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "�̸��� �Է��ϼ���");
		}else if(tfId.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "���̵� �Է��ϼ���");
		}else if(FileUtil.idCheck(tfId.getText())) {
			JOptionPane.showMessageDialog(this, "�̹� ������� ���̵� �Դϴ�. ���̵� �ٲ��ּ���");
		}else if(tfPwd1.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է��ϼ���");					
		}else if(tfEmail.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "�̸����� �Է��ϼ���");
		}else if(tfPhon2.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "��ȭ��ȣ�� �Է��ϼ���");
		}else if(tfPhon3.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "��ȭ��ȣ�� �Է��ϼ���");
		}else {
			String gen = "����";
			if(cbFeMale.isSelected()) {
				gen = "����";
			}
			
			String like = "{"+
					(cbM1.isSelected()?cbM1.getText():"")+
					(cbM2.isSelected()?cbM2.getText():"")+
					(cbM3.isSelected()?cbM3.getText():"")+
					(cbM4.isSelected()?cbM4.getText():"")
					+"}";
			
			String data = tfName.getText()+"/"+tfId.getText()+"/"+tfPwd1.getText()+
					"/"+tfEmail.getText()+"/"+comboPhon1.getSelectedItem()+"-"+tfPhon2.getText()+"-"+tfPhon3.getText()+
					"/"+gen+"/"+like;
			
			
			FileUtil.regiMember(data);
			JOptionPane.showMessageDialog(this, "ȸ�������� ȯ���մϴ�");
			setVisible(false);
			new Pnl1();
			
		}
	    
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnReg) {	
		
			registMember();
		
			
		}else if(e.getSource()==btnIdck) {
			if(FileUtil.idCheck(tfId.getText())) {
				JOptionPane.showMessageDialog(this, "�̹� ������� ���̵� �Դϴ�");
			}else {
				JOptionPane.showMessageDialog(this, "��� ������ ���̵� �Դϴ�");
			}		
		

		}
	}
}
