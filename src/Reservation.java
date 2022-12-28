import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JComboBox;
import javax.swing.JDialog;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Reservation extends JPanel implements ActionListener{
	File file;
   JPanel pnl, backpnl;
   JComboBox movietitle_Combo, area_Combo, month_Combo, day_Combo, showtime_Combo, poster_Combo;
   JLabel lbl, movietitle_Label, area_Label, date_Label, showtime_Label, posterImg_Label, seat_Label, lbl2;
   JDialog seat_Dialog;
   JButton seat_Button, pay_Button, date_Button;
   JTextField seat_TextField;
   ImageIcon icon;
   Font title_Font, text_Font;
   Seat dialog, seat;
   
   ImageIcon [] posterImgs;
   String[] movietitle;
   
	public Reservation() {
		file=new File("movie.txt");
		loadFile();
	   
		this.setLayout(new BorderLayout());
		pnl = new JImagePanel(new ImageIcon("./img/background.png"));
		pnl.setLayout(null);
		
		title_Font = new Font("맑은 고딕", Font.PLAIN, 20);
		text_Font = new Font("맑은 고딕", Font.PLAIN, 12);
      
		lbl = new JLabel("예매하기");
		lbl.setBounds(340, 100, 100, 50);
      
		posterImgs[0]=new ImageIcon("./img/movieIcon.jpg");
		movietitle[0]="영화";
		 movietitle_Combo = new JComboBox(movietitle);
	      movietitle_Combo.setBounds(300, 180, 250, 30);
	      movietitle_Combo.setBackground(Color.WHITE);
	      movietitle_Label = new JLabel("영화");
	      movietitle_Label.setBounds(260, 180, 100, 30);
	      
	      movietitle_Combo.addActionListener(this);
	      
	      posterImg_Label = new JLabel(posterImgs[0]);     // 포스터 위치
	      posterImg_Label.setBounds(50, 180, 160, 300);
	      pnl.add(posterImg_Label);
	      
	      String[] area = {"지역 선택", "서울 | 롯데시네마", "경기 | 메가박스", "대전 | CGV", "부산 | CGV"};
	      area_Combo = new JComboBox(area);
	      area_Combo.setBounds(300, 230, 250, 30);
	      area_Combo.setBackground(Color.WHITE);
	      area_Label = new JLabel("지역");
	      area_Label.setBounds(260, 230, 100, 30);
	      
	      String[] month = {"월", "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"};
	      month_Combo = new JComboBox(month);
	      month_Combo.setBounds(300, 280, 120, 30);
	      month_Combo.setBackground(Color.WHITE);
	      String[] day = {"일", "1일", "2일", "3일", "4일", "5일", "6일", "7일", "8일", "9일", "10일", "11일", "12일", "13일", "14일", "15일", "16일", "17일", "18일", "19일", "20일", "21일", "22일", "23일", "24일", "25일", "26일", "27일", "28일", "29일", "30일"};
	      day_Combo = new JComboBox(day);
	      day_Combo.setBounds(430,280, 120, 30);
	      day_Combo.setBackground(Color.WHITE);
	      date_Label = new JLabel("날짜");
	      date_Label.setBounds(260, 280, 100, 30);
     
//      date_Label = new JLabel("날짜 선택");
//      date_Label.setBounds(230, 280, 100, 30);
      
      String[] showtime = {"시간 선택", "1회차 | 08:00 ~ 10:20", "2회차 | 14:30 ~ 16:50", "3회차 | 18:00 ~ 20:20"};
      showtime_Combo = new JComboBox(showtime);
      showtime_Combo.setBounds(300, 330, 250, 30);
      showtime_Combo.setBackground(Color.WHITE);
      showtime_Label = new JLabel("시간");
      showtime_Label.setBounds(260, 330, 100, 30);
      
      seat_Dialog = new JDialog();
      seat_Button = new JButton("좌석 선택");
      seat_Button.setBounds(300, 420, 250, 30);
      seat_Button.setBackground(Color.WHITE);
      seat_Button.setBackground(new Color(245,208,96));
      seat_Button.addActionListener(this);
      seat_Label = new JLabel("좌석");
      seat_Label.setBounds(260, 380, 250, 30);
      seat_TextField = new JTextField();
      seat_TextField.setBounds(300, 380, 250, 30);
      
      pay_Button = new JButton("예매하기");
      pay_Button.setBounds(300, 480, 250, 60);
      pay_Button.setBackground(new Color(245,208,96));

      pay_Button.addActionListener(this);
      
      lbl.setFont(title_Font);
      
      pnl.add(lbl);
      
      pnl.add(movietitle_Combo);   
      movietitle_Combo.setFont(text_Font);
      pnl.add(movietitle_Label);
      movietitle_Label.setFont(text_Font);
      pnl.add(area_Combo);
      area_Combo.setFont(text_Font);
      pnl.add(area_Label);
      area_Label.setFont(text_Font);
      pnl.add(month_Combo);
      month_Combo.setFont(text_Font);
      pnl.add(day_Combo);
      day_Combo.setFont(text_Font);
      pnl.add(date_Label);
      date_Label.setFont(text_Font);
      pnl.add(showtime_Combo);
      showtime_Combo.setFont(text_Font);
      pnl.add(showtime_Label);
      showtime_Label.setFont(text_Font);
      pnl.add(seat_Button);
      seat_Button.setFont(text_Font);
      pnl.add(seat_TextField);
      seat_TextField.setFont(text_Font);
      pnl.add(seat_Label);
      seat_Label.setFont(text_Font);
      pnl.add(pay_Button);
      pay_Button.setFont(text_Font);
   
      this.add(pnl);
   }
   
   void loadFile() {
	   int movieCount=1;
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
		
		posterImgs = new ImageIcon[movieCount];
		movietitle = new String[movieCount];
		
		int i=1;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line=null;
			while((line=br.readLine())!=null) {
				String value = line;
				String vals[] = value.split("-");
				String poster = vals[0];
				posterImgs[i]=new ImageIcon(poster);
				movietitle[i]=vals[1];
				i++;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

   public void actionPerformed(ActionEvent e) {

//  String img = ("" + movietitle_Combo.getSelectedItem());
  	
//-----------------좌석선택다이얼로그---------------------------//
	   Object seatObj = e.getSource();
    	if(seatObj == seat_Button) {

    		    	dialog = new Seat(this);  
    	}
	
//-----------------좌석선택다이얼로그끝---------------------------//   
	
	
//----------------------예매완료버튼--------------------------//	
      Object obj = e.getSource();
         if(obj == pay_Button) {       	 
      	 String data = ("제목  :  " + movietitle_Combo.getSelectedItem() + 
      			     "\r\n\r\n지역  :  " + area_Combo.getSelectedItem() + 
                     "\r\n\r\n날짜  :  " + month_Combo.getSelectedItem() + day_Combo.getSelectedItem() +
                     "\r\n\r\n시간  :  " + showtime_Combo.getSelectedItem() +
                     "\r\n\r\n좌석  :  " + seat_TextField.getText()       
                     );       	 
        	 
      	if (movietitle_Combo.getSelectedIndex() == 0)  {
      		JOptionPane.showMessageDialog(this, "영화를 선택해주세요.");
      	}else if (area_Combo.getSelectedIndex() == 0)  {
      		JOptionPane.showMessageDialog(this, "지역을 선택해주세요.");
      	}else if (month_Combo.getSelectedIndex() == 0)  {
      		JOptionPane.showMessageDialog(this, "날짜를 선택해주세요.");
      	}else if (day_Combo.getSelectedIndex() == 0)  {
      		JOptionPane.showMessageDialog(this, "날짜를 선택해주세요.");
      	}else if (showtime_Combo.getSelectedIndex() == 0)  {
      		JOptionPane.showMessageDialog(this, "시간을 선택해주세요.");
      	}else if (seat_TextField.getText().equals(""))  {
      		JOptionPane.showMessageDialog(this, "좌석을 선택해주세요.");
      		
      	}else 
      	{ new Reservation_Check(data,posterImgs[movietitle_Combo.getSelectedIndex()]);
      	 setVisible(false);
		String[] current = FileUtil.getCurrentMember();// 로그인한 사람의 정보를 가져오는 메서드
      	Ticket.Ticket2(current[1]+"-"+posterImg_Label.getIcon() + "-" + movietitle_Combo.getSelectedItem() + "-" 
      	      + area_Combo.getSelectedItem() + "-" + month_Combo.getSelectedItem() + day_Combo.getSelectedItem() + "-" 
      	      + showtime_Combo.getSelectedItem() + "-" + seat_TextField.getText());
      	 }	
         }      	 
//      if(e.getSource() == pay_Button){//  텍스트파일 저장
//			Ticket.Ticket2(posterImg_Label.getIcon() + "-" + movietitle_Combo.getSelectedItem() + "/" + area_Combo.getSelectedItem() + "/" + month_Combo.getSelectedItem() + "-" + day_Combo.getSelectedItem() + "/" 
//      + showtime_Combo.getSelectedItem() + "/" + seat_TextField.getText() + "/"  );
//	
//
//			
//		}		

//	
//if (movietitle_Combo.getSelectedIndex() == 0) {
//	 ImageIcon[] img = (posterImgs);
//  	    	
//}
         
//--------------------예매완료버튼끝-----------------------------//
      
//------------------ 포스터이미지출력-----------------------------//
      Object poster_obj = e.getSource();
      if(poster_obj == movietitle_Combo) {
         int poster_index = movietitle_Combo.getSelectedIndex();

         posterImg_Label.setIcon(posterImgs[poster_index]);
         }
   }
}