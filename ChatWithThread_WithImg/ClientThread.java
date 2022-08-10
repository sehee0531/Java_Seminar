import java.net.*;
import java.io.*;

public class ClientThread extends Thread{
   private BufferedReader in;
   // private Socket socket;

   public ClientThread(Socket socket)throws Exception{
      // this.socket = socket;
      this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
   }

   public void run(){
      try{
         while(true){
            System.out.println(in.readLine());
         }
      }catch(Exception e){}
   }
}