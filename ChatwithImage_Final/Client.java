import java.io.*;
import java.net.*;
import java.util.*;

public class Client implements MyListener{	
	public static void main(String[]args) throws Exception{
		
		
		Socket socket=new Socket("localhost",3000);
		Socket takesocket=new Socket("localhost",3001);
		
		BufferedReader keyIn=new BufferedReader(new InputStreamReader(System.in));
		PrintStream out=new PrintStream(socket.getOutputStream());
		
		
		ClientThread thread=new ClientThread(socket); 
		thread.start();
		//Test test = new Test();
		String message="";
		while(!(message=keyIn.readLine()).equals("bye")){
			if(message.equals("down"))
			{
				MyListener client = new Client();
				Fileout thread2=new Fileout(takesocket,client); 
				thread2.start();
			}
			
			out.println(message); 
        }
		out.println(message);
		socket.close();		
	}
	public void add(long size){
		System.out.println("complete...."+ size);
	}	
}

	


