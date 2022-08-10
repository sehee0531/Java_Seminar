import java.io.*;
import java.net.*;
import java.util.*;

public class ServerThread extends Thread{
   private Socket socket;
   private Socket filsocket; 
   private BufferedReader in;
   // private PrintStream out;
   private String address;
   private ArrayList<Socket> clientList; 
   
   public ServerThread(Socket socket, ArrayList<Socket> clientList) throws Exception{ 
	this.filsocket=filsocket;
      this.socket = socket;
      this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      // this.out = new PrintStream(socket.getOutputStream());
      this.address = socket.getInetAddress().toString();
      this.clientList = clientList;
      System.out.println(address + " is connected");
      this.clientList.add(socket);
      System.out.println("size : " + clientList.size()); 
   }

   public void run(){ 
      try{
         String msg = "";
         broadCast(this.address + " : Client come");
         while(!(msg = in.readLine()).equals("bye")){
			if(msg.equals("down")){  //if down
				SendThread send = new SendThread(this.filsocket);
				send.start();
				broadCast(this.address + " : " + msg);
			}
			broadCast(this.address + " : " + msg);	
         }
         System.out.println(this.address + " : disconnected");
         this.clientList.remove(this.socket);
         System.out.println("size : " + clientList.size());
         broadCast(this.address + " : Client gone");
         this.socket.close();
      }catch(Exception e){}
   }

   private void broadCast(String msg) throws Exception{ 
      for(Socket socket : this.clientList){
         if(this.socket != socket){ 
            PrintStream out = new PrintStream(socket.getOutputStream());
            out.println(msg);
         }
      }
   }
}
