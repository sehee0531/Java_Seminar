import java.net.*;
import java.io.*;

public class URLClient {
    public static void main(String[]args)throws Exception{
        URL url = new URL("http://localhost");
        InputStream in= url.openStream();
    }
    
}
