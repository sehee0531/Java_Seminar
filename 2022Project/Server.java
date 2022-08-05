import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[]args)throws Exception{
        ServerSocket serverSocket = new ServerSocket(8001);
        System.out.println("wait connect ... ");
        
        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("Connect!!!");
           
            MyThread myThread = new MyThread(socket);
            myThread.start();
            }
            
        }
        
}

