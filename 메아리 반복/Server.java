import java.io.*; 
import java.net.*; 
import java.nio.*; 


public class Server {
   public static void main(String[] args) {
      try {
         ServerSocket server_socket = new ServerSocket(4444);
      
      
         System.out.println("Server Open.");
         Socket socket = server_socket.accept(); 
         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
         PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))); 
         String str = null;
         
         
         while(true){
         str=in.readLine();
         System.out.println("Client : " + str);
         out.write(str); 
         out.flush();
         }
         
         
      }catch(IOException e) {
         e.printStackTrace();
      }
   }
}