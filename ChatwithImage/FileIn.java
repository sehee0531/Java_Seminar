import java.io.*;
import java.net.*;
public class FileIn extends Thread {
    Socket takesocket;
    public FileIn(Socket takesocket){
        this.takesocket=takesocket;
    }
    public void run(){
        try{
            FileInputStream in= new FileInputStream("dog.jpg");
            OutputStream out = takesocket.getOutputStream();
            byte[] data= new byte[1024*8];
            int size;
            while((size=in.read(data))!=-1){
                out.write(data,0,size);
                out.flush();
            }

        }
        catch(Exception e){
            e.printStackTrace();

        }
    }

    
}