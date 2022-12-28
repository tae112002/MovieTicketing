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
	Font font = new Font("���� ���", Font.BOLD, 12);
	Font fontTitle = new Font("���� ���", Font.BOLD, 16);
	int count;
	private Reservation parents;
	
	/* 
	 * �¼� ���� ������ 
	 */

	public static boolean seatCheck(String seat) { // db�� ����� �¼����� ��ġ�ϴ��� Ȯ�θ޼ҵ�.
		boolean result = false;
		// ���� ó�� �۾�
		File f = new File("Ticket.txt");
		FileReader fr;
		try {			
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line=br.readLine()) != null) {// ���پ� �о�´�. null�� �ƴҶ����� 
				String[] lineData = line.split("-");// /�� �߶� �迭�� ��´�
				String[] lineData2 = lineData[6].split(",");// /�� �߶� �迭�� ��´�		
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
//		super(parents, true); // ���̾�α� �⺻ �Ӽ� ������
		// ������ �Ǵ� �θ�â, modal ����
		
		this.parents = parents;
		this.setSize(350,300);
		// this.setDefaultCloseOperation(3);
		this.setTitle("�ο� & �¼� ����");
		this.setBackground(Color.WHITE);
		titleLbl = new JLabel("�ο�&�¼� ����", SwingConstants.CENTER);
		titleLbl.setFont(fontTitle);
		titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		topPnl = new JPanel(new FlowLayout());
		topPnl.setBackground(Color.WHITE);
		humanPnl = new JPanel();
		humanPnl.setBackground(Color.white);
		adultLbl = new JLabel("����");
		adultLbl.setFont(font);
		childLbl = new JLabel("���");
		childLbl.setFont(font);
		Integer child[] = {0,1,2,3,4,5};
		Integer adult[] = {0,1,2,3,4,5};
		
		childCombo = new JComboBox<>(child);
		childCombo.setBackground(Color.white);
		adultCombo = new JComboBox<>(adult);
		adultCombo.setBackground(Color.WHITE);
		
		// �¼� ��ư ����
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
			if(seatCheck(seatBtn[i].getText())) { // db�� ����� �¼��� ��ġ�Ұ�� ��ư off
				seatBtn[i].setBackground(Color.black);
				seatBtn[i].setEnabled(false);
			}
		}
		seatPnl = new JPanel(new GridLayout(0,8,5,5));
		seatPnl.setBackground(Color.white);
		selPnl = new JPanel(new BorderLayout());
		selPnl.setBackground(Color.white);
		selLbl = new JLabel("������ �¼�");
		selLbl.setFont(font);
		model = new DefaultListModel<>();
		seatList = new JList(model);
		
		okBtn = new JButton("���ÿϷ�");
		okBtn.setBackground(Color.WHITE);
		okBtn.setBorder(new LineBorder(Color.BLACK));
		okBtn.setBackground(new Color(245,208,96));
		okBtn.setFont(font);
		okBtn.addActionListener(this);
		noBtn = new JButton("�ٽ� ����");
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
		// �¼� ��ư �гο� ���̱�
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
			JOptionPane.showMessageDialog(this, "�ο��� �������ּ���.");
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
		if(e.getSource()==okBtn) { // �¼���ȣ, �ο�
			if(cnt == count) {
				String s = seatList.getModel().toString();
				String splits = s.replaceAll("(?:\\[|null|\\]| +)", "");
				parents.seat_TextField.setText(splits);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "�ο��� �¼��� Ȯ�����ּ���.");
			}
		}		 
	}
	public static void main(String[] args) {
		// new Seat();
	}
}