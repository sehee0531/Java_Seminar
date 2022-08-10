import java.net.*;
import java.util.*;
import java.io.*;

public class Client {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Socket socket = new Socket("localhost",3000);
        Socket takesocket = new Socket("localhost",3001);

		BufferedReader KeyIn = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(socket.getOutputStream());

        ClientThread thread = new ClientThread(socket);
        thread.start();

		String str="";
		while(!(str=KeyIn.readLine()).equals("bye")){
            out.println(str);
            if((str=KeyIn.readLine()).equals("down")){
                Fileout file = new Fileout(takesocket);
                file.start();
            }
			out.println(str);	
		}
		out.println(str);
		socket.close();
		
		

	}

}