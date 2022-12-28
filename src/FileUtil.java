import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtil {
	final static String memberTxt = "Member.txt";// AuthForm.memberTxt
	final static String currentTxt = "CurMember.txt";
	
	
	// idChek("asdf") => true/false
	public static boolean idCheck(String id) {
		boolean result = false;
		// ���� ó�� �۾�
		File f = new File(memberTxt);
		FileReader fr;
		
		try {			
			
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line=br.readLine()) != null) {// ���پ� �о�´�. null�� �ƴҶ����� 
				String[] lineData = line.split("/");// /�� �߶� �迭�� ��´�
				if(lineData[1].equals(id)) {
					result=true;
					break;
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("�ɹ� ������ �����ϴ�.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void regiMember(String data) {//
		
		File f = new File(memberTxt);
		FileWriter fw;
		try {
			fw = new FileWriter(f,true);
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println(data);
			pw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public static boolean login(String id, String pw) {
		boolean result = false;
		// ���� ó�� �۾�
		File f = new File(memberTxt);
		FileReader fr;
		
		try {			
			
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line=br.readLine()) != null) {// ���پ� �о�´�. null�� �ƴҶ����� 
				String[] lineData = line.split("/");// /�� �߶� �迭�� ��´�
				if(lineData[1].equals(id) && lineData[2].equals(pw)) {
					result=true;
					currentMember(line);					
					break;
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("�ɹ� ������ �����ϴ�.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void currentMember(String data) {// ���� �α����� �ɹ� ������ ����
		File f = new File(currentTxt);		
		try {			
			PrintWriter pw = new PrintWriter(new FileWriter(f));			
			pw.println(data);
			pw.close();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public static String[] getCurrentMember(){
		File f = new File(currentTxt);
		FileReader fr;
		
		String[] memberInfo=null;
		
		try {			
			
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			if((line=br.readLine()) != null) {// ���پ� �о�´�. null�� �ƴҶ����� 
				memberInfo = line.split("/");// /�� �߶� �迭�� ��´�
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("�ɹ� ������ �����ϴ�.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return memberInfo;
		
	}

}

