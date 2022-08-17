import java.io.*;
import java.net.*;

public class Client {

    public static void main(String [] args)throws Exception{
        URL url = new URL("http://localhost/Second/Test"); 
        HttpURLConnection connection =(HttpURLConnection)url.openConnection(); 

        connection.setDoOutput(true); 
        connection.setDoInput(true);
        connection.setRequestMethod("POST"); 
        connection.setRequestProperty("Content-Type", "text/plain; charset=utf-8"); 

        OutputStream out = connection.getOutputStream();
        String jsonString = "{\"name\":\"정세희\"}";
        out.write(jsonString.getBytes("utf-8"));
        out.flush();

        InputStream in = connection.getInputStream();
        byte[] data = new byte[1024];
        int size;

        while((size=in.read(data))!=-1){
            System.out.println(new String(data,0,size,"utf-8"));
        }

    }
}