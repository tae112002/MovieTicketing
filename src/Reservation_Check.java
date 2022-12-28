import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Reservation_Check extends JFrame implements ActionListener{
	CustomerMain main;
   JPanel pnl, pnlall, background;
   JLabel reserComplete_Label, poster_Label, img_Label;
   JTextArea ReserDetail_JTextArea;
   JButton check_Btn, main_Btn;    
   Font title_Font, text_Font;
   public Reservation_Check(String data, ImageIcon img) {
	   main=new CustomerMain();
//     String[] tadata = data.split("/");	   
      
	   this.setSize(770, 660);
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	   ImageIcon icon = new ImageIcon("./img/background.png");      
	   
	   
	   pnl = new JPanel() {
	 public void paintComponent(Graphics g) {
	   
	 g.drawImage(icon.getImage(), 0, 0, null);
	 setOpaque(false); 
	 super.paintComponent(g);
	    }
	};

	   pnl.setLayout(null);

	   poster_Label = new JLabel(img);
	   poster_Label.setBounds(50, 180, 160, 300);
	 
	   title_Font = new Font("맑은 고딕", Font.PLAIN, 20);
	   text_Font = new Font("맑은 고딕", Font.PLAIN, 12);
	   
	   reserComplete_Label = new JLabel("예매가 완료되었습니다!");
	   reserComplete_Label.setBounds(280, 130, 300, 30);
	   
	   ReserDetail_JTextArea = new JTextArea(data);
	   ReserDetail_JTextArea.setBounds(300, 200, 400, 200);

	   main_Btn = new JButton("메인화면");
	   main_Btn.setBounds(300, 420, 250, 60);
	   main_Btn.setFont(text_Font);
	   main_Btn.setBackground(new Color(245,208,96));
	   main_Btn.addActionListener(this);
	   
	   reserComplete_Label.setFont(title_Font);

	   pnl.add(poster_Label);
	   pnl.add(ReserDetail_JTextArea);
	   pnl.add(reserComplete_Label);
	   pnl.add(main_Btn);

	   this.add(pnl);
	   this.setVisible(true);
   }
   
  
   @Override
   public void actionPerformed(ActionEvent e) {
   	// TODO Auto-generated method stub
	   if(e.getSource() == main_Btn) {
		   this.dispose();
		   new CustomerMain();
	   }
   	
   }

}