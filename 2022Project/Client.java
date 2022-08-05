import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("192.168.0.10",8001);

        InputStream in= socket.getInputStream();
        PrintStream out = new PrintStream("test.jpg");
        byte[] data = new byte[1024*8];
        int size;

        while((size=in.read(data))!=-1){
            out.write(data,0,size);
            out.flush();
        }
        socket.close();
    }
    
}