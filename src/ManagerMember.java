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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ManagerMember extends JPanel implements ActionListener{
	private ManagerMain parents;
	JLabel titleLbl;
	JTable table;
	DefaultTableModel model;
	JScrollPane sp;
	JButton cancelBtn, backBtn;
	JPanel imgPnl, mainPnl, btnPnl, centerPnl;
    ImageIcon img;
    DefaultTableCellRenderer cell;
    TableColumnModel tcm;
    Font fontTitle = new Font("���� ���", Font.BOLD, 20);
	Font font = new Font("���� ���", Font.BOLD, 14);
	File file;
	FileWriter fw;
	PrintWriter pw; 
	public ManagerMember(ManagerMain parents) {
		this.parents=parents;
		
		file = new File("Member.txt");
		
		this.setLayout(new BorderLayout());
		
		JImagePanel pnlImg = new JImagePanel(new ImageIcon("./img/background.png"));
		pnlImg.setSize(770,660);
		pnlImg.setLayout(null);
		
		mainPnl = new JPanel();
		mainPnl.setLayout(new BorderLayout());
		mainPnl.setBackground(Color.WHITE);
		mainPnl.setBounds(35, 100, 700, 460);
		
		titleLbl = new JLabel("ȸ������", SwingConstants.CENTER);
		titleLbl.setFont(fontTitle);
		
		String[] header = {"�̸�", "���̵�", "�̸���", "��ȭ��ȣ", "����", "��ȣ �帣"};
		String[][] contents = {};

		
		model = new DefaultTableModel(contents ,header) {
			public boolean isCellEditable(int row, int column) {
		        return false;
		     }
		};
		table = new JTable(model);
		table.setModel(model);
		table.setRowHeight(50);
		table.setFont(font);
		table.getTableHeader().setBackground(new Color(245,208,96));
		table.getTableHeader().setFont(font);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cell = new DefaultTableCellRenderer();
		cell.setHorizontalAlignment(SwingConstants.CENTER); // �������� ���������� CENTER��
	    tcm = table.getColumnModel();
		for(int i=0;i<table.getColumnCount();i++) {
			tcm.getColumn(i).setCellRenderer(cell);
		}
		
		sp = new JScrollPane(table);
		sp.getViewport().setBackground(Color.white);
		btnPnl = new JPanel();
		
		backBtn = new JButton("�ڷ� ����");
		backBtn.setFont(font);
		backBtn.setBackground(new Color(245,208,96));
		backBtn.setBorder(new LineBorder(Color.black));
		backBtn.addActionListener(this);
		
		
		btnPnl.add(backBtn);
		
		mainPnl.add(titleLbl,"North");
		mainPnl.add(sp,"Center");
		mainPnl.add(btnPnl,"South");
		
		pnlImg.add(mainPnl);
		this.add(pnlImg);
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);// ���� ������ �о���
			String line=null;//�޼��� ������ ���� ������ �ݵ�� �ʱⰪ�� �־�� ��.
			int m=1;
			while((line=br.readLine())!=null) {
				if(m>=2) {
					String[] lineData = line.split("/");// /�� �߶� �迭�� ��´�
					String[] rowData = new String[6];	
					int j=3;
		            for(int i =0; i<rowData.length ; i++) {
		            	if(i>=2) {
		            		rowData[i] = lineData[j];
		            		j++;
						}
		            	else {
		            		rowData[i] = lineData[i];
		            	}
		            	
		            }
					model.addRow(rowData);
				}
				m++;
			}
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("���Ͼ���");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==backBtn) { // �ڷΰ��� ��ư
			System.out.println("�ڷΰ���");
			parents.cardlayout.show(parents.cards, "main");
		}
		
	}

}
