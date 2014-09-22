package com.ss.logs;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LogFile1 {
 
//	public void addLogs(String a){
    
	public static void main(String a[]) {
		try{
            FileWriter fstream = new FileWriter("TransactionLogs.txt",true);
            BufferedWriter fbw = new BufferedWriter(fstream);
 //           fbw.write(""+a);
            fbw.write("");
            fbw.newLine();
            

            fbw.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
  //  }
	
//	public String printLogs() {
        BufferedReader in;
		try {
			in = new BufferedReader(new FileReader("TransactionLogs.txt"));
	        String line = "";
	        String s = "";
	  		while((line = in.readLine()) != null)
				{
					s = s + line +"\n";
				}
	
	  		System.out.println(s);
	  		//	    return s.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
//			return null;
		} catch (IOException e) {
			e.printStackTrace();
	//		return null;
		}

	}

}
