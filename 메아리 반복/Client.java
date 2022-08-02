
import java.io.*; 
import java.net.*;

public class Client {

   public static void main(String[] args) throws Exception{
      Socket socket = new Socket("192.168.0.153", 4444);
      try {
         //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))); 
         BufferedReader in2 = new BufferedReader(new InputStreamReader(System.in)); 
         InputStream se = socket.getInputStream();
         byte[] data1 = new byte[100];
         int size;
         String str;
         
         while(true){
         str=in2.readLine();   
         System.out.print("To Server : ");
         String data = in2.readLine(); //keyboard
         out.println(data);
         out.flush();
         size = se.read(data1); 
         str=new String(data1,0,size);
         System.out.println("From Server : " + str);
         }
        
      
      }
      catch(IOException e) {
         e.printStackTrace();
      }
   }
}
