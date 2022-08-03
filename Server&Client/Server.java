import java.net.*;
import java.io.*;
import java.io.*;
import com.google.gson.Gson;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1000);
        Socket socket = serverSocket.accept();

        //InputStream in = socket.getInputStream();

        //byte[] data = new byte[1024];
        //int size;

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
        Gson gson = new Gson();
        Person person = gson.fromJson(in,Person.class);

/*/
        StringBuilder builder = new StringBuilder();
        while((size=in.read(data))!=-1) {
            builder.append(new String(data,0,size,"utf-8"));
        }
        socket.close();
        System.out.println(builder.toString());

        Gson gson = new Gson();
        Person person = gson.fromJson(builder.toString(),Person.class);
        */

        System.out.println(person.name);
        System.out.println(person.schools.get(0).name);
    }
}