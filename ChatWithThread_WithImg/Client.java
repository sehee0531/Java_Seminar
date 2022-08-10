import java.net.*;
import java.io.*;

public class Client{
   public static void main(String[] args) throws Exception{
      // Socket socket = new Socket("192.168.51.100", 3000);
      Socket socket = new Socket("localhost", 3001);      

      BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));
      PrintStream out = new PrintStream(socket.getOutputStream());      
      // BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 이 부분은 thread로 넘긴다

      ClientThread clientThread = new ClientThread(socket);
      clientThread.start();

      String msg = "";
      while(!(msg = keyIn.readLine()).equals("bye")){
         out.println(msg);
		 if((msg = keyIn.readLine()).equals("down")){
			TakeThread take= new TakeThread(socket);
			take.start();
		 }
         // System.out.println("message : " + in.readLine()); // 서버에서 읽어들이는거 필요x Thread에서 한다.         
      }
      out.println(msg);
      socket.close();
   }
}