import java.io.*;
import java.net.*;
public class Fileout extends Thread {
    Socket takesocket;
    private MyListener client;
    public Fileout(Socket takesocket,MyListener client){
        this.takesocket=takesocket;
        this.client=client;
    }
    public void run(){
        try{
            InputStream in= takesocket.getInputStream();
            PrintStream out = new PrintStream("test.jpg");
            byte[] data= new byte[1024*8];
            int size;
            long filesize=0;
            while((size=in.read(data))!=-1){
                filesize+=size;
                out.write(data,0,size);
                out.flush();
            }
            takesocket.close();
            client.add(filesize);

        }
        catch(Exception e){
            e.printStackTrace();

        }
    }

    
}
