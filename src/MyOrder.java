import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
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
import javax.swing.JFrame;
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

public class MyOrder extends JPanel implements ActionListener{
   JLabel titleLbl;
   JTable table;
   DefaultTableModel model;
   JScrollPane sp;
   JButton cancelBtn;
   JPanel imgPnl, mainPnl, btnPnl, centerPnl;
    ImageIcon img;
    DefaultTableCellRenderer cell;
    TableColumnModel tcm;
    Font fontTitle = new Font("맑은 고딕", Font.BOLD, 20);
   Font font = new Font("맑은 고딕", Font.BOLD, 14);
   File file;
   FileWriter fw;
   PrintWriter pw; 
   /* 
    * 남은것 -> 뒤로가기 버튼 동작 
    * 예매 현황 페이지
    */
   public MyOrder() {
      file = new File("Ticket.txt");
      
      this.setLayout(new BorderLayout());
      
      JImagePanel pnlImg = new JImagePanel(new ImageIcon("./img/background.png"));
      pnlImg.setSize(770,660);
      pnlImg.setLayout(null);
      
      mainPnl = new JPanel();
      mainPnl.setLayout(new BorderLayout());
      mainPnl.setBackground(Color.WHITE);
      mainPnl.setBounds(35, 100, 700, 460);
      
      titleLbl = new JLabel("예매정보", SwingConstants.CENTER);
      titleLbl.setFont(fontTitle);
      
      String[] header = {"아이디", "포스터", "영화제목", "영화관", "날짜", "시간", "좌석번호"};
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
      cell.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로
       tcm = table.getColumnModel();
      for(int i=0;i<table.getColumnCount();i++) {
         tcm.getColumn(i).setCellRenderer(cell);
      }
      
      sp = new JScrollPane(table);
      sp.getViewport().setBackground(Color.white);
      
      cancelBtn = new JButton("예약 취소");
      cancelBtn.setFont(font);
      cancelBtn.setBackground(new Color(245,208,96));
      cancelBtn.setBorder(new LineBorder(Color.black));
      cancelBtn.addActionListener(this);
      
      mainPnl.add(titleLbl,"North");
      mainPnl.add(sp,"Center");
      mainPnl.add(cancelBtn,"South");
      
      pnlImg.add(mainPnl);
      this.add(pnlImg);
      try {
         FileReader fr = new FileReader(file);
         BufferedReader br = new BufferedReader(fr);// 한줄 단위로 읽어짐
         String line=null;//메서드 내에서 만든 변수는 반드시 초기값이 있어야 함.
         while((line=br.readLine())!=null) {
            String[] lineData = line.split("-");// /로 잘라서 배열에 담는다
            String[] rowData = new String[7];   
            String[] current = FileUtil.getCurrentMember();// 로그인한 사람의 정보를 가져오는 메서드
            
            if(lineData[0].toString().equals(current[1])) {
               table.getColumnModel().getColumn(1).setCellRenderer(new ImageRender());
                  for(int i =0; i<lineData.length ; i++) {
                  rowData[i] = lineData[i];
                  }
               model.addRow(rowData);
            }
               
         }
         fr.close();
         br.close();
      } catch (FileNotFoundException e) {
         System.out.println("파일없음");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   private class ImageRender extends DefaultTableCellRenderer { // 포스터 이미지 불러오게 하는 것
      @Override
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
         String poster = value.toString();
         ImageIcon img = new ImageIcon(new ImageIcon(""+poster).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
         return new JLabel(img);
         
      }
      
   }
   public static void main(String[] args) {
      new MyOrder();
   }
   void saveFile() { // -로 한번 자른 배열을 다시 - 붙혀 저장
      try {
         fw = new FileWriter(file);
         pw = new PrintWriter(fw);
         for(int i=0 ; i<table.getRowCount();i++) {
            String data = table.getValueAt(i, 0) + "-" + table.getValueAt(i, 1) + "-" + table.getValueAt(i, 2) +
                  "-" + table.getValueAt(i, 3) + "-" + table.getValueAt(i, 4) + "-" + table.getValueAt(i, 5)+ "-" + table.getValueAt(i, 6);
            System.out.println(data);
            pw.println(data);
         }
         pw.flush();
         pw.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      int row = table.getSelectedRow();
      int col = table.getSelectedColumn();
      if(e.getSource()==cancelBtn) { // 예약 취소 버튼
         if(table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "취소하실 영화를 선택해주세요.");
         } else {
            int result = JOptionPane.showConfirmDialog(this, table.getValueAt(row, 2)+" 의 예약을 취소하시겠습니까?","confirm", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION) {
               model.removeRow(table.getSelectedRow());
               saveFile();
            }
         }
      }
   }
}