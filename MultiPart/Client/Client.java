import java.net.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

public class Client implements MultipartListener {
    public static void main(String[]args)throws Exception{

        Client client = new Client();
        MultipartMaker maker = new MultipartMaker("http://localhost/third/Test4",client);
        maker.addFormData("data","{\"name\":\"정세희\"}");
        maker.addBinaryData("file1",new FileInputStream("1.jpg"));
        maker.start();
    }
   
    public void onResult(InputStream in)throws Exception{
        byte[] data= new byte[1024];
        int size;
        while((size=in.read(data))!=-1){
            System.out.println(new String(data,0,size,"utf-8"));
        }
    }
        
}
    

