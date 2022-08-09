import java.net.*;
import java.util.*;
import java.io.*;

public class Client {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Socket socket = new Socket("192.168.51.100",3000);

		BufferedReader KeyIn = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(socket.getOutputStream());

        ClientThread thread = new ClientThread(socket);
        thread.start();

		String str="";
		while(!(str=KeyIn.readLine()).equals("bye")){
            /*if("Down"){

            }*/
			out.println(str);	
		}
		out.println(str);
		socket.close();
		
		

	}

}
