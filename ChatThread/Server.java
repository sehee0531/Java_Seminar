import java.io.*;
import java.util.*;
import java.net.*;

public class Server {

	public static void main(String[] args)throws Exception{
		// TODO Auto-generated method stub
		
		ServerSocket serversocket = new ServerSocket(3000);
        ArrayList<Socket> list = new ArrayList<Socket>();

		while(true) {
			Socket socket = serversocket.accept();
			System.out.println("socket connect");
			ServerThread thread = new ServerThread(socket,list);
			thread.start();
		}
		

	}

}


