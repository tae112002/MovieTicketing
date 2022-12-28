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
		
		title_Font = new Font("���� ���", Font.PLAIN, 20);
		text_Font = new Font("���� ���", Font.PLAIN, 12);
      
		lbl = new JLabel("�����ϱ�");
		lbl.setBounds(340, 100, 100, 50);
      
		posterImgs[0]=new ImageIcon("./img/movieIcon.jpg");
		movietitle[0]="��ȭ";
		 movietitle_Combo = new JComboBox(movietitle);
	      movietitle_Combo.setBounds(300, 180, 250, 30);
	      movietitle_Combo.setBackground(Color.WHITE);
	      movietitle_Label = new JLabel("��ȭ");
	      movietitle_Label.setBounds(260, 180, 100, 30);
	      
	      movietitle_Combo.addActionListener(this);
	      
	      posterImg_Label = new JLabel(posterImgs[0]);     // ������ ��ġ
	      posterImg_Label.setBounds(50, 180, 160, 300);
	      pnl.add(posterImg_Label);
	      
	      String[] area = {"���� ����", "���� | �Ե��ó׸�", "��� | �ް��ڽ�", "���� | CGV", "�λ� | CGV"};
	      area_Combo = new JComboBox(area);
	      area_Combo.setBounds(300, 230, 250, 30);
	      area_Combo.setBackground(Color.WHITE);
	      area_Label = new JLabel("����");
	      area_Label.setBounds(260, 230, 100, 30);
	      
	      String[] month = {"��", "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��"};
	      month_Combo = new JComboBox(month);
	      month_Combo.setBounds(300, 280, 120, 30);
	      month_Combo.setBackground(Color.WHITE);
	      String[] day = {"��", "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��", "13��", "14��", "15��", "16��", "17��", "18��", "19��", "20��", "21��", "22��", "23��", "24��", "25��", "26��", "27��", "28��", "29��", "30��"};
	      day_Combo = new JComboBox(day);
	      day_Combo.setBounds(430,280, 120, 30);
	      day_Combo.setBackground(Color.WHITE);
	      date_Label = new JLabel("��¥");
	      date_Label.setBounds(260, 280, 100, 30);
     
//      date_Label = new JLabel("��¥ ����");
//      date_Label.setBounds(230, 280, 100, 30);
      
      String[] showtime = {"�ð� ����", "1ȸ�� | 08:00 ~ 10:20", "2ȸ�� | 14:30 ~ 16:50", "3ȸ�� | 18:00 ~ 20:20"};
      showtime_Combo = new JComboBox(showtime);
      showtime_Combo.setBounds(300, 330, 250, 30);
      showtime_Combo.setBackground(Color.WHITE);
      showtime_Label = new JLabel("�ð�");
      showtime_Label.setBounds(260, 330, 100, 30);
      
      seat_Dialog = new JDialog();
      seat_Button = new JButton("�¼� ����");
      seat_Button.setBounds(300, 420, 250, 30);
      seat_Button.setBackground(Color.WHITE);
      seat_Button.setBackground(new Color(245,208,96));
      seat_Button.addActionListener(this);
      seat_Label = new JLabel("�¼�");
      seat_Label.setBounds(260, 380, 250, 30);
      seat_TextField = new JTextField();
      seat_TextField.setBounds(300, 380, 250, 30);
      
      pay_Button = new JButton("�����ϱ�");
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
  	
//-----------------�¼����ô��̾�α�---------------------------//
	   Object seatObj = e.getSource();
    	if(seatObj == seat_Button) {

    		    	dialog = new Seat(this);  
    	}
	
//-----------------�¼����ô��̾�α׳�---------------------------//   
	
	
//----------------------���ſϷ��ư--------------------------//	
      Object obj = e.getSource();
         if(obj == pay_Button) {       	 
      	 String data = ("����  :  " + movietitle_Combo.getSelectedItem() + 
      			     "\r\n\r\n����  :  " + area_Combo.getSelectedItem() + 
                     "\r\n\r\n��¥  :  " + month_Combo.getSelectedItem() + day_Combo.getSelectedItem() +
                     "\r\n\r\n�ð�  :  " + showtime_Combo.getSelectedItem() +
                     "\r\n\r\n�¼�  :  " + seat_TextField.getText()       
                     );       	 
        	 
      	if (movietitle_Combo.getSelectedIndex() == 0)  {
      		JOptionPane.showMessageDialog(this, "��ȭ�� �������ּ���.");
      	}else if (area_Combo.getSelectedIndex() == 0)  {
      		JOptionPane.showMessageDialog(this, "������ �������ּ���.");
      	}else if (month_Combo.getSelectedIndex() == 0)  {
      		JOptionPane.showMessageDialog(this, "��¥�� �������ּ���.");
      	}else if (day_Combo.getSelectedIndex() == 0)  {
      		JOptionPane.showMessageDialog(this, "��¥�� �������ּ���.");
      	}else if (showtime_Combo.getSelectedIndex() == 0)  {
      		JOptionPane.showMessageDialog(this, "�ð��� �������ּ���.");
      	}else if (seat_TextField.getText().equals(""))  {
      		JOptionPane.showMessageDialog(this, "�¼��� �������ּ���.");
      		
      	}else 
      	{ new Reservation_Check(data,posterImgs[movietitle_Combo.getSelectedIndex()]);
      	 setVisible(false);
		String[] current = FileUtil.getCurrentMember();// �α����� ����� ������ �������� �޼���
      	Ticket.Ticket2(current[1]+"-"+posterImg_Label.getIcon() + "-" + movietitle_Combo.getSelectedItem() + "-" 
      	      + area_Combo.getSelectedItem() + "-" + month_Combo.getSelectedItem() + day_Combo.getSelectedItem() + "-" 
      	      + showtime_Combo.getSelectedItem() + "-" + seat_TextField.getText());
      	 }	
         }      	 
//      if(e.getSource() == pay_Button){//  �ؽ�Ʈ���� ����
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
         
//--------------------���ſϷ��ư��-----------------------------//
      
//------------------ �������̹������-----------------------------//
      Object poster_obj = e.getSource();
      if(poster_obj == movietitle_Combo) {
         int poster_index = movietitle_Combo.getSelectedIndex();

         posterImg_Label.setIcon(posterImgs[poster_index]);
         }
   }
}