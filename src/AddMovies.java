import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class AddMovies extends JPanel implements ActionListener{
	private ManagerMoviesList parents;
	File file;
	JImagePanel pnl;
	JPanel inputpnl, datepnl;
	JLabel posterlbl, titlelbl, genrelbl, classlbl, runningTlbl, blanklbl;
	JTextField postertf, titletf, genretf, classtf, runningTtf;
	JRadioButton recommendrb;
	JButton newmoviebtn, backbtn;
	JList movies;
	DefaultListModel model;
	JScrollPane sp;
	Font font=new Font("맑은 고딕", Font.PLAIN, 15);
	Font lblfont=new Font("맑은 고딕", Font.PLAIN, 12);
	public AddMovies(ManagerMoviesList parents) {
		this.parents=parents;
		
		file=new File("movie.txt");
		
		model = new DefaultListModel<>();
		movies=new JList(model);
		
		this.setLayout(new BorderLayout());
		pnl = new JImagePanel(new ImageIcon("./img/background.png"));
		pnl.setLayout(null);
		
		inputpnl=new JPanel(new GridLayout(0,2));
		inputpnl.setBackground(Color.WHITE);
		inputpnl.setBounds(135, 150, 500, 300);
		
		posterlbl=new JLabel("포스터 이미지 위치");
		posterlbl.setFont(lblfont);
		postertf=new JTextField();
		
		titlelbl=new JLabel("영화 제목");
		titlelbl.setFont(lblfont);
		titletf=new JTextField();
		
		genrelbl=new JLabel("영화 장르");
		genrelbl.setFont(lblfont);
		genretf=new JTextField();
		
		classlbl=new JLabel("관람 등급");
		classlbl.setFont(lblfont);
		classtf=new JTextField();
		
		runningTlbl=new JLabel("러닝타임");
		runningTlbl.setFont(lblfont);
		runningTtf=new JTextField();
		
		blanklbl=new JLabel(" ");
		recommendrb=new JRadioButton("추천");
		recommendrb.setFont(lblfont);
		recommendrb.setBackground(Color.white);
		
		inputpnl.add(blanklbl);
		inputpnl.add(recommendrb);
		inputpnl.add(posterlbl);
		inputpnl.add(postertf);
		inputpnl.add(titlelbl);
		inputpnl.add(titletf);
		inputpnl.add(genrelbl);
		inputpnl.add(genretf);
		inputpnl.add(classlbl);
		inputpnl.add(classtf);
		inputpnl.add(runningTlbl);
		inputpnl.add(runningTtf);
		
		JPanel btnwrappnl=new JPanel(null);
		newmoviebtn=new JButton("신규영화 등록");
		newmoviebtn.setBounds(505, 0, 150, 30);
		btnset(newmoviebtn);
		newmoviebtn.addActionListener(this);
		
		backbtn=new JButton("돌아가기");
		backbtn.setBounds(5, 0, 150, 30);
		btnset(backbtn);
		backbtn.addActionListener(this);
		
		btnwrappnl.add(newmoviebtn);
		btnwrappnl.add(backbtn);
		btnwrappnl.setBounds(50, 520, 660, 35);
		btnwrappnl.setBackground(Color.WHITE);
		
		pnl.add(inputpnl);
		pnl.add(btnwrappnl);
		
		this.add(pnl);
		loadFile();
	}
	void btnset(JButton btn) {
		btn.setBackground(new Color(245,208,96));
		btn.setBorderPainted(false);
		btn.setFont(font);
	}
	void saveFile() {
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			
			for(int i=0;i<model.size();i++) {
				pw.println(model.get(i));
			}
			pw.flush();
			pw.close();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	void loadFile() {
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line=null;
			while((line=br.readLine())!=null) {
				model.addElement(line);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==newmoviebtn) {
			String poster=postertf.getText();
			String title=titletf.getText();
			String genre=genretf.getText();
			String mc=classtf.getText();
			String runningT=runningTtf.getText();
			String recommend;
			if(recommendrb.isSelected()) {
				recommend="추천";
			}else {
				recommend="비추천";
			}
			String movie=poster+"-"+title+"-"+genre+"-"+mc+"-"+runningT+"-"+recommend;
			model.addElement(movie);
			saveFile();
			
			JOptionPane.showMessageDialog(null, "영화가 추가 되었습니다.");
			parents.setVisible(false);
			new ManagerMain().cardlayout.show(new ManagerMain().cards, "movies");
		}
		if(e.getSource()==backbtn) {
			parents.cardlayout.show(parents.cards, "movies");
		}
		
	}
}
