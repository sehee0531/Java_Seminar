import java.net.*;
import java.util.*;
import java.io.*;

public class ServerThread extends Thread {
	
	private Socket socket;
	private BufferedReader in;
	//private PrintStream out;
	private String address;
    private ArrayList<Socket> list;

	public ServerThread(Socket socket, ArrayList<Socket> list)throws Exception {
		
			this.socket=socket;
			this.in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//this.out=new PrintStream(socket.getOutputStream());
			this.address=socket.getInetAddress().toString();
            this.list=list;
			System.out.println(address+"is connect");
            list.add(socket);
            System.out.println("size : "+list.size());
	}
	public void run() {
		try {
			
			String str="";
			while(!(str=in.readLine()).equals("bye")){
				System.out.println(this.address+" : "+str);
                //out.println(str);
                broadCast(this.address+" : "+str);
		    }
        System.out.println(address +"is disconnected");
        this.list.remove(this.socket);
        System.out.println("size : "+list.size());
		socket.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}			
	}
    private void broadCast(String str)throws Exception { //Client Splash
        for(Socket socket : this.list){
            if(socket!=this.socket){ //private socket and Arraylist Socket 
                PrintStream out = new PrintStream(socket.getOutputStream());
                out.println(str);
            }
        
        }
    }

}

