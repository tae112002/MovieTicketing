import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;

public class Ticket {
	final static String ticketTxt = "Ticket.txt";// AuthForm.memberTxt
	
	public static boolean titleCheck(ImageIcon posterImg_Label) {
		boolean result = false;
		// 파일 처리 작업
		File f = new File(ticketTxt);
		FileReader fr;
		try {			
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line=br.readLine()) != null) {// 한줄씩 읽어온다. null이 아닐때까지 
				String[] lineData = line.split("/");// /로 잘라서 배열에 담는다
				if(lineData[0].equals(posterImg_Label)) {
					result=true;
					break;
				}
			}
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
		System.out.println("");
		}
		
		return result;
	}
	
	
	public static boolean titleCheck(ImageIcon posterImg_Label, String movietitle_Combo) {
		boolean result = false;
		// 파일 처리 작업
		File f = new File(ticketTxt);
		FileReader fr;
		try {			
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line=br.readLine()) != null) {// 한줄씩 읽어온다. null이 아닐때까지 
				String[] lineData = line.split("/");// /로 잘라서 배열에 담는다
				if(lineData[0].equals(posterImg_Label)&& lineData[1].equals(movietitle_Combo)) {
					result=true;
					break;
				}
			}
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
		System.out.println("");
		}
		
		return result;
	}

	public static boolean areaCheck(ImageIcon posterImg_Label, String movietitle_Combo, String area_Combo) {
		boolean result = false;
		
		File f = new File(ticketTxt);
		FileReader fr;
		
		try {			
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line=br.readLine()) != null) {// 한줄씩 읽어온다. null이 아닐때까지 
				String[] lineData = line.split("/");// /로 잘라서 배열에 담는다
				if(lineData[0].equals(posterImg_Label)&&lineData[1].equals(movietitle_Combo)&&lineData[2].equals(area_Combo)) {
					result=true;
					break;
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		System.out.println("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("");
		}
		
		return result;
	}
	
	
	
	public static boolean monthCheck(ImageIcon posterImg_Label, String movietitle_Combo, String area_Combo, String month_Combo) {
		boolean result = false;
		
		File f = new File(ticketTxt);
		FileReader fr;
		
		try {			
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line=br.readLine()) != null) {// 한줄씩 읽어온다. null이 아닐때까지 
				String[] lineData = line.split("/");// /로 잘라서 배열에 담는다
				if(lineData[0].equals(posterImg_Label)&&lineData[1].equals(movietitle_Combo)&&lineData[2].equals(area_Combo)&&lineData[3].equals(month_Combo)) {
					result=true;
					break;
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		System.out.println("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("");
		}
		
		return result;
	}

	public static boolean dayCheck(ImageIcon posterImg_Label, String movietitle_Combo, String area_Combo, String month_Combo, String day_Combo) {
		boolean result = false;
		
		File f = new File(ticketTxt);
		FileReader fr;
		
		try {			
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line=br.readLine()) != null) {// 한줄씩 읽어온다. null이 아닐때까지 
				String[] lineData = line.split("/");// /로 잘라서 배열에 담는다
				if(lineData[0].equals(posterImg_Label)&&lineData[1].equals(movietitle_Combo)&&lineData[2].equals(area_Combo)&&lineData[3].equals(month_Combo)
						&&lineData[4].equals(day_Combo)) {
					result=true;
					break;
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		System.out.println("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("");
		}
		
		return result;
	}
	
	
	
	public static boolean timeCheck(ImageIcon posterImg_Label, String movietitle_Combo, String area_Combo, String month_Combo, String day_Combo, String showtime_Combo) {
		boolean result = false;
		
		File f = new File(ticketTxt);
		FileReader fr;
		
		try {			
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line=br.readLine()) != null) {// 한줄씩 읽어온다. null이 아닐때까지 
				String[] lineData = line.split("/");// /로 잘라서 배열에 담는다
				if(lineData[0].equals(posterImg_Label)&&lineData[1].equals(movietitle_Combo)&&lineData[2].equals(area_Combo)&&lineData[3].equals(month_Combo)
						&&lineData[4].equals(day_Combo)&&lineData[5].equals(showtime_Combo)) {
					result=true;
					break;
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		System.out.println("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("");
		}
		
		return result;
	}
	
	
	public static boolean timeCheck(ImageIcon posterImg_Label, String movietitle_Combo, String area_Combo, String month_Combo, String day_Combo, String showtime_Combo, String seat_TextField) {
		boolean result = false;
		
		File f = new File(ticketTxt);
		FileReader fr;
		
		try {			
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line=br.readLine()) != null) {// 한줄씩 읽어온다. null이 아닐때까지 
				String[] lineData = line.split("/");// /로 잘라서 배열에 담는다
				if(lineData[0].equals(posterImg_Label)&&lineData[1].equals(movietitle_Combo)&&lineData[2].equals(area_Combo)&&lineData[3].equals(month_Combo)
						&&lineData[4].equals(day_Combo)&&lineData[5].equals(showtime_Combo)&&lineData[6].equals(seat_TextField)) {
					result=true;
					break;
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		System.out.println("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("");
		}
		
		return result;
	}
	
	
	
	public static void Ticket2(String data) {
		
		File f = new File(ticketTxt);
		FileWriter fw;
		try {
			fw = new FileWriter(f,true);
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println(data);
			pw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("");
		}		
		
	}
}