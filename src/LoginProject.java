import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class LoginProject {

	JPanel cardPanel;
	LoginProject lp;
	CardLayout card;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LoginProject lp = new LoginProject();
		lp.setFrame(lp);
	}

	public void setFrame(LoginProject lpro) {

		JFrame jf = new JFrame();
		LoginPanel lp = new LoginPanel(lpro);
		signupPanel sp = new signupPanel(lpro);

		card = new CardLayout();

		cardPanel = new JPanel(card);
		cardPanel.add(lp.mainPanel, "Login");
		cardPanel.add(sp.mainPanel, "Register");
		
		jf.add(cardPanel);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(500, 700);
		jf.setVisible(true);
	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;

		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_DB?serverTimezone=UTC", "root",
				"1234");

		return conn;
	}

}

class LoginPanel extends JPanel implements ActionListener {

	JPanel mainPanel;
	JTextField idTextField;
	JPasswordField passTextField;

	String userMode = "�Ϲ�";
	LoginProject lp;
	Font font = new Font("ȸ������", Font.BOLD, 40);
	String admin = "admin";

	public LoginPanel(LoginProject lp) {
		this.lp = lp;
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(5, 1));

		JPanel centerPanel = new JPanel();
		JLabel loginLabel = new JLabel("�α��� ȭ��");
		loginLabel.setFont(font);
		centerPanel.add(loginLabel);

		JPanel userPanel = new JPanel();

		JPanel gridBagidInfo = new JPanel(new GridBagLayout());
		gridBagidInfo.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		GridBagConstraints c = new GridBagConstraints();

		JLabel idLabel = new JLabel(" ���̵� : ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		gridBagidInfo.add(idLabel, c);

		idTextField = new JTextField(15);
		c.insets = new Insets(0, 5, 0, 0);
		c.gridx = 1;
		c.gridy = 0;
		gridBagidInfo.add(idTextField, c);

		JLabel passLabel = new JLabel(" ��й�ȣ : ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(20, 0, 0, 0);
		gridBagidInfo.add(passLabel, c);

		passTextField = new JPasswordField(15);
		c.insets = new Insets(20, 5, 0, 0);
		c.gridx = 1;
		c.gridy = 1;
		gridBagidInfo.add(passTextField, c);

		JPanel loginPanel = new JPanel();
		JButton loginButton = new JButton("�α���");
		loginPanel.add(loginButton);

		JPanel signupPanel = new JPanel();
		JButton signupButton = new JButton("ȸ������");
		loginPanel.add(signupButton);

		mainPanel.add(centerPanel);
		mainPanel.add(userPanel);
		mainPanel.add(gridBagidInfo);
		mainPanel.add(loginPanel);
		mainPanel.add(signupPanel);


		loginButton.addActionListener(this);

		signupButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lp.card.next(lp.cardPanel);
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton jb = (JButton) e.getSource();

		switch (e.getActionCommand()) {

		case "�Ϲ�":
			userMode = "�Ϲ�";
			break;

		case "������":
			userMode = "������";
			break;

		case "�α���":

			String id = idTextField.getText();
			String pass = passTextField.getText();

			try {

				String sql_query = String.format("SELECT password FROM user_info WHERE id = '%s' AND password ='%s'",
						id, pass);

				Connection conn = lp.getConnection();
				Statement stmt = conn.createStatement();

				ResultSet rset = stmt.executeQuery(sql_query);
				rset.next();

				if (pass.equals(rset.getString(1))) {
					JOptionPane.showMessageDialog(this, "Login Success", "�α��� ����", 1);
				
				} else
					JOptionPane.showMessageDialog(this, "Login Failed", "�α��� ����", 1);

			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(this, "Login Failed", "�α��� ����", 1);
				System.out.println("SQLException" + ex);
			}

			break;

		}
	}

} // class LoginPanel

class signupPanel extends JPanel {

	JTextField idTf;
	JPasswordField passTf;
	JPasswordField passReTf;
	JTextField nameTf;
	JTextField yearTf;
	JTextField phoneTf;
	JPanel mainPanel;
	JPanel subPanel;
	JComboBox<String> monthComboBox;
	JComboBox<String> dayComboBox;
	JRadioButton menButton;
	JRadioButton girlButton;
	JButton registerButton;
	Font font = new Font("ȸ������", Font.BOLD, 40);

	String year = "", month = "", day = "";
	String id = "", pass = "", passRe = "", name = "", sex = "", phone = "";
	LoginProject lp;

	public signupPanel(LoginProject lp) {

		this.lp = lp;
		subPanel = new JPanel();
		subPanel.setLayout(new GridBagLayout());
		subPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

		JLabel idLabel = new JLabel("���̵� : ");
		JLabel passLabel = new JLabel("��й�ȣ : ");
		JLabel passReLabel = new JLabel("��й�ȣ ��Ȯ�� : ");
		JLabel nameLabel = new JLabel("�̸� : ");
		JLabel birthLabel = new JLabel("������� : ");
		JLabel sexLabel = new JLabel("���� : ");
		JLabel phoneLabel = new JLabel("�ڵ�����ȣ : ");

		idTf = new JTextField(15);
		passTf = new JPasswordField(15);
		passReTf = new JPasswordField(15);
		nameTf = new JTextField(15);
		yearTf = new JTextField(4);
		phoneTf = new JTextField(11);

		monthComboBox = new JComboBox<String>(
				new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" });
		dayComboBox = new JComboBox<String>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
				"28", "29", "30", "31" });

		menButton = new JRadioButton("����");
		girlButton = new JRadioButton("����");
		ButtonGroup sexGroup = new ButtonGroup();
		sexGroup.add(menButton);
		sexGroup.add(girlButton);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(15, 5, 0, 0);

		c.gridx = 0;
		c.gridy = 0;
		subPanel.add(idLabel, c);

		c.gridx = 1;
		c.gridy = 0;
		subPanel.add(idTf, c); // ���̵�

		c.gridx = 0;
		c.gridy = 1;
		subPanel.add(passLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		subPanel.add(passTf, c); // pass
		
		c.gridx = 2;
		c.gridy = 1; 
		subPanel.add(new JLabel("Ư������ + 8��"),c); //���ȼ���

		c.gridx = 0;
		c.gridy = 2;
		subPanel.add(passReLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		subPanel.add(passReTf, c); // password ��Ȯ��

		c.gridx = 0;
		c.gridy = 3;
		subPanel.add(nameLabel, c);

		c.gridx = 1;
		c.gridy = 3;
		subPanel.add(nameTf, c); // �̸�

		c.gridx = 0;
		c.gridy = 4;
		subPanel.add(birthLabel, c);

		c.gridx = 1;
		c.gridy = 4;
		c.weightx = 0.6;
		subPanel.add(yearTf, c);

		c.gridx = 2;
		c.gridy = 4;
		c.weightx = 0.2;
		subPanel.add(monthComboBox, c);

		c.gridx = 3;
		c.gridy = 4;
		c.weightx = 0.2;
		subPanel.add(dayComboBox, c);

		c.gridx = 0;
		c.gridy = 5;
		subPanel.add(sexLabel, c);

		c.gridx = 1;
		c.gridy = 5;
		subPanel.add(menButton, c);

		c.gridx = 2;
		c.gridy = 5;
		subPanel.add(girlButton, c);

		c.gridx = 0;
		c.gridy = 6;
		subPanel.add(phoneLabel, c);

		c.gridx = 1;
		c.gridy = 6;
		subPanel.add(phoneTf, c);

		mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JLabel signupLabel = new JLabel("ȸ������ ȭ�� ");
		signupLabel.setFont(font);
		signupLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		registerButton = new JButton("ȸ������");
		registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		mainPanel.add(signupLabel);
		mainPanel.add(subPanel);
		mainPanel.add(registerButton);

		monthComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == monthComboBox) {
					JComboBox monthBox = (JComboBox) e.getSource();
					month = (String) monthBox.getSelectedItem();
					System.out.println(month);
				}

			}
		});
		dayComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == dayComboBox) {
					JComboBox dayBox = (JComboBox) e.getSource();
					day = (String) dayBox.getSelectedItem();
					System.out.println(month);
				}
			}
		});

		menButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sex = e.getActionCommand();
			}
		});

		girlButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sex = e.getActionCommand();
			}
		});
		registerButton.addActionListener(new ActionListener() {      //ȸ�����Թ�ư

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				id = idTf.getText();
				pass = new String(passTf.getPassword());
				passRe = new String(passReTf.getPassword());
				name = nameTf.getText();
				year = yearTf.getText();
				phone = phoneTf.getText();

				String sql = "insert into user_info(id, password, name, birthday, sex, phoneNumber) values (?,?,?,?,?,?)";

				Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$"); //8�� ����+Ư��+����
				Matcher passMatcher = passPattern1.matcher(pass);

				if (!passMatcher.find()) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ����+Ư������+���� 8�ڷ� �����Ǿ�� �մϴ�", "��й�ȣ ����", 1);
				} else if (!pass.equals(passRe)) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ���� ���� �ʽ��ϴ�", "��й�ȣ ����", 1);

				} else {
					try {
						Connection conn = lp.getConnection();

						PreparedStatement pstmt = conn.prepareStatement(sql);

						String date = yearTf.getText() + "-" + month + "-" + day;

						pstmt.setString(1, idTf.getText());
						pstmt.setString(2, pass);
						pstmt.setString(3, nameTf.getText());
						pstmt.setString(4, date);
						pstmt.setString(5, sex);
						pstmt.setString(6, phoneTf.getText());

						int r = pstmt.executeUpdate();
						System.out.println("����� row " + r);
						JOptionPane.showMessageDialog(null, "ȸ�� ���� �Ϸ�!", "ȸ������", 1);
						lp.card.previous(lp.cardPanel); // �� �Ϸ�Ǹ� �α��� ȭ������
					} catch (SQLException e1) {
						System.out.println("SQL error" + e1.getMessage());
						if (e1.getMessage().contains("PRIMARY")) {
							JOptionPane.showMessageDialog(null, "���̵� �ߺ�!", "���̵� �ߺ� ����", 1);
						} else
							JOptionPane.showMessageDialog(null, "������ ����� �Է����ּ���!", "����", 1);
					} // try ,catch
				}
			}
		});

	}
}