import java.io.*;
import java.net.*;
public class Fileout extends Thread {
    Socket takesocket;
    public Fileout(Socket takesocket){
        this.takesocket=takesocket;
    }
    public void run(){
        try{
            InputStream in= takesocket.getInputStream();
            PrintStream out = new PrintStream("test.jpg");
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
