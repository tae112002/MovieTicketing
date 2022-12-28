import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Seat extends JDialog implements ActionListener {
	JButton[] seatBtn;
	JButton okBtn, noBtn;
	JLabel titleLbl, selLbl, adultLbl, childLbl, seat_TextField;
	JPanel topPnl, titlePnl, humanPnl, seatPnl, selPnl, botPnl;
	JList seatList;
	DefaultListModel model;
	JComboBox childCombo, adultCombo;
	File file;
	FileWriter fw;
	PrintWriter pw; 
	Font font = new Font("맑은 고딕", Font.BOLD, 12);
	Font fontTitle = new Font("맑은 고딕", Font.BOLD, 16);
	int count;
	private Reservation parents;
	
	/* 
	 * 좌석 선택 페이지 
	 */

	public static boolean seatCheck(String seat) { // db에 저장된 좌석값과 일치하는지 확인메소드.
		boolean result = false;
		// 파일 처리 작업
		File f = new File("Ticket.txt");
		FileReader fr;
		try {			
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line=br.readLine()) != null) {// 한줄씩 읽어온다. null이 아닐때까지 
				String[] lineData = line.split("-");// /로 잘라서 배열에 담는다
				String[] lineData2 = lineData[6].split(",");// /로 잘라서 배열에 담는다		
				for(int i =0; i<lineData2.length;i++) {
					if(lineData2[i].equals(seat)) {
						result=true;
						break;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	Seat(Reservation parents) {
//		super(parents, true); // 다이얼로그 기본 속성 생성자
		// 기준이 되는 부모창, modal 여부
		
		this.parents = parents;
		this.setSize(350,300);
		// this.setDefaultCloseOperation(3);
		this.setTitle("인원 & 좌석 선택");
		this.setBackground(Color.WHITE);
		titleLbl = new JLabel("인원&좌석 선택", SwingConstants.CENTER);
		titleLbl.setFont(fontTitle);
		titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		topPnl = new JPanel(new FlowLayout());
		topPnl.setBackground(Color.WHITE);
		humanPnl = new JPanel();
		humanPnl.setBackground(Color.white);
		adultLbl = new JLabel("성인");
		adultLbl.setFont(font);
		childLbl = new JLabel("어린이");
		childLbl.setFont(font);
		Integer child[] = {0,1,2,3,4,5};
		Integer adult[] = {0,1,2,3,4,5};
		
		childCombo = new JComboBox<>(child);
		childCombo.setBackground(Color.white);
		adultCombo = new JComboBox<>(adult);
		adultCombo.setBackground(Color.WHITE);
		
		// 좌석 버튼 생성
		seatBtn = new JButton[40];
		
		for(int i=0;i<seatBtn.length;i++) {
			if(i<8) {
				seatBtn[i] = new JButton("A" + String.format("%02d", (1+i)));
			} else if(i<16) {
				seatBtn[i] = new JButton("B" + String.format("%02d", (i-7)));
			} else if(i<24) {
				seatBtn[i] = new JButton("C" + String.format("%02d", (i-15)));
			} else if(i<32) {
				seatBtn[i] = new JButton("D" + String.format("%02d", (i-23)));
			} else {
				seatBtn[i] = new JButton("E" + String.format("%02d", (i-31)));
			}
			seatBtn[i].addActionListener(this);
			seatBtn[i].setFont(font);
			seatBtn[i].setBackground(Color.WHITE);
			seatBtn[i].setBorder(new LineBorder(Color.BLACK));
			if(seatCheck(seatBtn[i].getText())) { // db에 저장된 좌석과 일치할경우 버튼 off
				seatBtn[i].setBackground(Color.black);
				seatBtn[i].setEnabled(false);
			}
		}
		seatPnl = new JPanel(new GridLayout(0,8,5,5));
		seatPnl.setBackground(Color.white);
		selPnl = new JPanel(new BorderLayout());
		selPnl.setBackground(Color.white);
		selLbl = new JLabel("선택한 좌석");
		selLbl.setFont(font);
		model = new DefaultListModel<>();
		seatList = new JList(model);
		
		okBtn = new JButton("선택완료");
		okBtn.setBackground(Color.WHITE);
		okBtn.setBorder(new LineBorder(Color.BLACK));
		okBtn.setBackground(new Color(245,208,96));
		okBtn.setFont(font);
		okBtn.addActionListener(this);
		noBtn = new JButton("다시 선택");
		noBtn.setBackground(Color.WHITE);
		noBtn.setBorder(new LineBorder(Color.BLACK));
		noBtn.setBackground(new Color(245,208,96));
		noBtn.setFont(font);
		noBtn.addActionListener(this);
		botPnl = new JPanel(new FlowLayout());
		botPnl.setBackground(Color.white);

		humanPnl.add(adultLbl);
		humanPnl.add(adultCombo);
		humanPnl.add(childLbl);
		humanPnl.add(childCombo);
		// 좌석 버튼 패널에 붙이기
		for(int i=0;i<seatBtn.length;i++) {
			seatPnl.add(seatBtn[i]);
		}
		selPnl.add(selLbl, "North");
		selPnl.add(seatList, "Center");
		topPnl.add(titleLbl);
		topPnl.add(humanPnl);
		botPnl.add(noBtn);
		botPnl.add(okBtn);
		
		this.add(topPnl, "North");
		this.add(seatPnl, "Center");
		this.add(selPnl, "East");
		this.add(botPnl, "South");
		this.setLocationRelativeTo(parents);
		//this.add(titlePnl);
		this.setVisible(true);
	}

	int cnt = 0;
	public void actionPerformed(ActionEvent e) {
		count = (int)childCombo.getSelectedItem() + (int)adultCombo.getSelectedItem();

		if(count == 0) {
			JOptionPane.showMessageDialog(this, "인원을 선택해주세요.");
		} else {
			for(int i=0; i<seatBtn.length; i++) {
				if(cnt == count) {
					if(seatBtn[i].getBackground()==Color.WHITE) {
						seatBtn[i].setEnabled(false);
					}
				} else if(e.getSource()==seatBtn[i]) {
					if(seatBtn[i].getBackground()==Color.LIGHT_GRAY) {
						cnt--;
						seatBtn[i].setBackground(Color.WHITE);
						model.removeElement(seatBtn[i].getText());
					} else {
						cnt++;
						seatBtn[i].setBackground(Color.LIGHT_GRAY);
						model.addElement(seatBtn[i].getText());
					}
				}		
			}
		}
		
		if(e.getSource()==noBtn) {
			for(int i=0; i<seatBtn.length; i++) {
			if(seatBtn[i].getBackground()==Color.LIGHT_GRAY) {
				seatBtn[i].setBackground(Color.WHITE);
			} else if(seatBtn[i].getBackground()==Color.WHITE) {
				seatBtn[i].setEnabled(true);
			}
		}
			cnt = 0;
			model.removeAllElements();
		}
		if(e.getSource()==okBtn) { // 좌석번호, 인원
			if(cnt == count) {
				String s = seatList.getModel().toString();
				String splits = s.replaceAll("(?:\\[|null|\\]| +)", "");
				parents.seat_TextField.setText(splits);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "인원과 좌석을 확인해주세요.");
			}
		}		 
	}
	public static void main(String[] args) {
		// new Seat();
	}
}