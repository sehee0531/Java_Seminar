import java.io.File;
import java.net.*;

public class FileManager extends Thread {
    Socket takesocket;
    ServerSocket sendSocket;

    public FileManager (ServerSocket sendSocket){
        this.takesocket=takesocket;
        this.sendSocket=sendSocket;
    }
    public void run(){
        try{
        while(true) {
			Socket takesocket = sendSocket.accept();
			System.out.println("socket connect");
			FileIn file = new FileIn(takesocket);
			file.start();
		}
    }catch(Exception e){
        e.printStackTrace();
    }
    
    }
    
}
