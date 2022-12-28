import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ManagerMoviesList extends JPanel implements ActionListener {
	AddMovies addpnl;
	JImagePanel pnl;
	File file;
	JScrollPane sc;
	CardLayout cardlayout;
	JPanel cards;
	JList movies;
	DefaultListModel model;
	JButton newmoviebtn, delectbtn;
	Font font=new Font("맑은 고딕", Font.BOLD, 12);
	public ManagerMoviesList() {
		this.setLayout(new BorderLayout());
		pnl = new JImagePanel(new ImageIcon("./img/background.png"));
		pnl.setLayout(null);
		
		addpnl=new AddMovies(this);
		
		cards=new JPanel(new CardLayout());
		cards.add(pnl,"movies");
		cards.add(addpnl, "add");
		cardlayout=(CardLayout)(cards.getLayout());
		
		file=new File("movie.txt");
		
		model = new DefaultListModel();
		movies=new JList(model);
		movies.setFont(font);
		sc=new JScrollPane(movies);
		
		JPanel firstpanel = new JPanel();
        Dimension size = new Dimension(680,1000);
        firstpanel.setPreferredSize(size);
        firstpanel.setBackground(Color.WHITE);
        firstpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
		
        JScrollPane scrollPane = new JScrollPane(firstpanel);
		
        scrollPane.setViewportView(firstpanel);
        
        JPanel moviepanel1 = new JPanel();
		moviepanel1.setBackground(Color.WHITE);
		firstpanel.add(moviepanel1);
		
		Dimension dim = new Dimension(150, 200);
		Dimension dim1 = new Dimension(-15, 0);
		
		moviepanel1.setPreferredSize(dim1);
		moviepanel1.setLayout(null);
        
		int movieCount=0;
		FileReader fr;
		try {
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
				moviepanels[i] = new JPanel();
				moviepanels[i].setBackground(Color.WHITE);
				firstpanel.add(moviepanels[i]);
				moviepanels[i].setPreferredSize(dim);
				moviepanels[i].setLayout(null);
				
				String value = line+"";
				String vals[] = value.split("-");
				
				String posterURL = vals[0];
				
				imageIcons[i] = new ImageIcon(posterURL);

				JButton lblPoster = new JButton("영화 포스터");
				lblPoster.setBounds(0, 0, 160, 200);
				moviepanels[i].add(lblPoster);
				lblPoster.setHorizontalAlignment(SwingConstants.CENTER);
				lblPoster.setIcon(imageIcons[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sc.setBounds(35, 365, 700, 140);
		
		newmoviebtn=new JButton("신규영화 등록");
		newmoviebtn.setBounds(515, 520, 200, 30);
		btnset(newmoviebtn);
		newmoviebtn.addActionListener(this);
		
		delectbtn=new JButton("삭제");
		delectbtn.setBounds(50, 520, 150, 30);
		btnset(delectbtn);
		delectbtn.addActionListener(this);
		
		scrollPane.setBorder(null);
		scrollPane.setBounds(35, 120, 700, 230);
		pnl.add(scrollPane);
		pnl.add(sc);
		pnl.add(newmoviebtn);
		pnl.add(delectbtn);
		
		this.add(cards);
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
			cardlayout.last(cards);
		}
		if(e.getSource()==delectbtn) {
			model.remove(movies.getSelectedIndex());
			
			saveFile();
			
			new ManagerMain().cardlayout.show(new ManagerMain().cards, "movies");
		}
		
	}

}
