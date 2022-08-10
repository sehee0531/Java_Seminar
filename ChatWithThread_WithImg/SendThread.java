import java.io.*;
import java.net.Socket;

public class SendThread extends Thread{
    Socket clientsocket;
    public SendThread(Socket socket)throws Exception{
        this.clientsocket=clientsocket;
    }
    public void run(){
        try{
            FileInputStream filein =new FileInputStream("dog.jpg");
            OutputStream fileout = clientsocket.getOutputStream();
            byte[] data = new byte[1024*8];
            int size;

            while((size=filein.read(data))!=-1){
                fileout.write(data,0,size);
                fileout.flush();
            
                
            }
             
           
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
