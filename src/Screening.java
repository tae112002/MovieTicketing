import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class Screening extends JPanel implements ActionListener{
	private CustomerMain parents;
	File file;
   JPanel pnl;
   JLabel screen_Label;
   Font title_Font, text_Font;
   Reservation reser;
   Font font=new Font("¸¼Àº °íµñ", Font.PLAIN, 15);
   public Screening(CustomerMain parents) {
	   file=new File("movie.txt");
	   
	   this.parents=parents;
	   this.setLayout(new BorderLayout());
	   
		pnl = new JImagePanel(new ImageIcon("./img/background.png"));
		pnl.setLayout(null);
   
   title_Font = new Font("¸¼Àº °íµñ", Font.PLAIN, 20);
   text_Font = new Font("¸¼Àº °íµñ", Font.PLAIN, 12);
   
   screen_Label = new JLabel("ÇöÀç »ó¿µÀÛ");
   screen_Label.setBounds(305, 90, 150, 80);
   
   JPanel firstpanel = new JPanel();
   Dimension size = new Dimension(680,1000);
   firstpanel.setPreferredSize(size);
   firstpanel.setBackground(Color.WHITE);
   firstpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
	
   JScrollPane scrollPane = new JScrollPane(firstpanel);
   scrollPane.setBorder(null);
   scrollPane.setViewportView(firstpanel);
   
   JPanel moviepanel1 = new JPanel();
	moviepanel1.setBackground(Color.WHITE);
	firstpanel.add(moviepanel1);
	
	Dimension dim = new Dimension(150, 240);
	Dimension dim1 = new Dimension(-15, 0);
	
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
			
			moviepanels[i] = new JPanel();
			moviepanels[i].setBackground(Color.WHITE);
			firstpanel.add(moviepanels[i]);
			moviepanels[i].setPreferredSize(dim);
			moviepanels[i].setLayout(null);
			
			imageIcons[i] = new ImageIcon(posterURL);

			JButton lblPoster = new JButton("¿µÈ­ Æ÷½ºÅÍ");
			lblPoster.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
			lblPoster.setBounds(0, 0, 160, 200);
			moviepanels[i].add(lblPoster);
			lblPoster.setHorizontalAlignment(SwingConstants.CENTER);
			lblPoster.setIcon(imageIcons[i]);
			
			JButton reservationBtn=new JButton("¿¹¸ÅÇÏ±â");
			btnset(reservationBtn);
			reservationBtn.setBounds(22, 205, 100, 30);
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
	} catch (Exception e) {
		e.printStackTrace();
	}
   
   screen_Label.setFont(title_Font);
   
   pnl.add(screen_Label);
   
   scrollPane.setBounds(35, 150, 700, 400);
	pnl.add(scrollPane);

   
   this.add(pnl);
   
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