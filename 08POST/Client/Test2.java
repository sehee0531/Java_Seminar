import java.io.*;
import java.net.*;

public class Test2 {

    public static void main(String [] args)throws Exception{
        URL url = new URL("http://localhost/Second/Test2"); 
        HttpURLConnection connection =(HttpURLConnection)url.openConnection(); 

        connection.setDoOutput(true); 
        connection.setDoInput(true);
        connection.setRequestMethod("POST"); 
        connection.setRequestProperty("Content-Type", "image/jpeg; charset=utf-8"); 

        OutputStream out = connection.getOutputStream();
        FileInputStream in = new FileInputStream("cat.jpg");
 
        byte[] data = new byte[1024];
        int size;

        while((size=in.read(data))!=-1){
            out.write(data,0,size);
            out.flush();
        }
        in.close();

        InputStream stringin = connection.getInputStream();
        byte[] data2 = new byte[1024];
        int size2;

        while((size2=stringin.read(data2) )!=-1){
            System.out.println(new String(data2,0,size2,"utf-8"));
        }



    }
}