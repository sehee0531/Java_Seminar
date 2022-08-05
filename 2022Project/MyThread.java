import java.io.*;
import java.net.*;

public class MyThread extends Thread {
    private Socket socket;

    public MyThread(Socket socket){
        this.socket=socket;
    }
    public void run(){

        try{

            FileInputStream in= new FileInputStream("dog.jpg");
            OutputStream out = socket.getOutputStream();
            byte[] data = new byte[1024*8];
            int size;

            while((size=in.read(data))!=-1){
            out.write(data,0,size);
            out.flush();
            }
            socket.close();
        }  
        catch(Exception e){
            e.printStackTrace();
        }
            
    }
       
}

