import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.io.*;

public class Client {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("192.168.0.11",1000);
        
        InputStream in = socket.getInputStream();
        FileOutputStream fileout = new FileOutputStream("test2.jpg");

        byte[] data = new byte[1024];
        int size;

        while((size=in.read(data))!=-1){
            fileout.write(data,0,size);
            fileout.flush();
        }

        socket.close();
    }
    
}
