import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Pnl3 extends JPanel implements ActionListener{
	private CustomerMain parents;
	File file;
	JList movies;
	DefaultListModel model;
	JImagePanel pnlImg;
	JButton btnLogout ;
	Font font=new Font("맑은 고딕", Font.PLAIN, 15); 
	public Pnl3(CustomerMain parents) {
		file=new File("movie.txt");
		
		model = new DefaultListModel<>();
		movies=new JList(model);
		
		this.parents=parents;
		this.setLayout(new BorderLayout());
		
		pnlImg = new JImagePanel(new ImageIcon("./img/background.png"));
		pnlImg.setLayout(null);				
		
		btnLogout = new JButton("로그아웃");
		
		JPanel firstpanel = new JPanel();
        firstpanel.setBackground(Color.white);
        firstpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 15));
		       
        JPanel moviepanel1 = new JPanel();
		moviepanel1.setBackground(Color.WHITE);
		firstpanel.add(moviepanel1);
		
		Dimension dim = new Dimension(150, 240);
		Dimension dim1 = new Dimension(-30, 0);
		
		moviepanel1.setPreferredSize(dim1);
		moviepanel1.setLayout(null);
        
		int movieCount=0;
		FileReader fr;
		try {
			int i=0;
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line=null;
			while((line=br.readLine())!=null) {
				model.addElement(line);
				movieCount++;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JPanel[] moviepanels = new JPanel[movieCount];
		Image[] images = new Image[movieCount];
		ImageIcon[] imageIcons = new ImageIcon[movieCount];
		
		try {
			int i=0;
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line=null;
			while((line=br.readLine())!=null) {
				String value = line+"";
				String vals[] = value.split("-");
				
				String posterURL = vals[0];
				String recommend=vals[5];
				if(recommend.equals("추천")) {
					moviepanels[i] = new JPanel();
					moviepanels[i].setBackground(Color.WHITE);
					firstpanel.add(moviepanels[i]);
					moviepanels[i].setPreferredSize(dim);
					moviepanels[i].setLayout(null);
					
					imageIcons[i] = new ImageIcon(posterURL);

					JButton lblPoster = new JButton("영화 포스터");
					lblPoster.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

						}
					});
					lblPoster.setBounds(0, 0, 160, 200);
					moviepanels[i].add(lblPoster);
					lblPoster.setHorizontalAlignment(SwingConstants.CENTER);
					lblPoster.setIcon(imageIcons[i]);
					
					JButton reservationBtn=new JButton("예매하기");
					reservationBtn.setBounds(22, 210, 106, 30);
					btnset(reservationBtn);
					moviepanels[i].add(reservationBtn);
					reservationBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							parents.cardlayout.show(parents.cardspnl, "reservation");
							parents.color(parents.MovieMainBtn,false);
							parents.color(parents.MovieListBtn,false);
							parents.color(parents.TicketingBtn,true);
							parents.color(parents.CheckingBtn,false);
						}
					});
				}
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		btnLogout.setFont(font);
		btnLogout.setBackground(new Color(245,208,96));
		btnLogout.addActionListener(new ActionListener() {//로그아웃 이벤트
			
			@Override
			public void actionPerformed(ActionEvent e) {
				parents.dispose();
				new Pnl1();
			}
		});
		
		firstpanel.setBounds(35,195,700,270);
		
		pnlImg.add(btnLogout);
		pnlImg.add(firstpanel);
		
		btnLogout.setBounds(40, 105, 100, 30);
		
		this.add(pnlImg);
	}
	void btnset(JButton btn) {
		btn.setBackground(new Color(245,208,96));
		btn.setBorderPainted(false);
		btn.setFont(font);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
