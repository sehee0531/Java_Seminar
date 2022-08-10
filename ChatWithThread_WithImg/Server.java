import java.util.*;
import java.net.*;

public class Server {

	public static void main(String[] args)throws Exception{
		
		ServerSocket serversocket = new ServerSocket(4444);
		System.out.println("waiting connect");
        ArrayList<Socket> list = new ArrayList<Socket>(); 

		while(true) {
			Socket socket = serversocket.accept(); 
			System.out.println("socket connect");
			ServerThread thread = new ServerThread(socket,list); 
			thread.start();
		}
		

	}

}