import java.io.*;
import java.net.*;
public class TakeThread extends Thread{
    private Socket socket;
    public TakeThread(Socket socket)throws Exception{
     this.socket=socket;
    }
    public void run(){
        try{

            InputStream filein = socket.getInputStream(); 
            FileOutputStream fileout =new FileOutputStream("test.jpg"); 
            byte[] data= new byte[1024*8];
            int size;
            while((size=filein.read(data))!=-1){
                fileout.write(data,0,size); 
                fileout.flush(); 
            }
            

        }
        catch(Exception e){

        }
        

    }
    
}