import java.net.*;
import com.google.gson.*;
import java.util.*;

import java.io.*;

public class Server {
    public static void main(String [] args)throws Exception{
        ServerSocket serversocket = new ServerSocket(80);
        Socket socket = serversocket.accept();

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("gson.txt")));
        OutputStream out = socket.getOutputStream();
		String name="";
		ArrayList<Persons> persons = new ArrayList<Persons>();
		while((name=br.readLine())!=null) {
			String address=br.readLine();
			int age=Integer.parseInt(br.readLine());
			Persons person =new Persons(name,address,age);
			person.name=name;
			person.address=address;
			person.age=age;
		    persons.add(person);
		}
		br.close();
		
		Gson gson = new Gson();
		AllPerson all = new AllPerson();
		all.persons=persons;
		String json = gson.toJson(all);

        out.write(json.getBytes());
        

        /*FileInputStream file = new FileInputStream("cat.png");
        byte[] data = new byte[1024];
        int size;
        while((size=file.read(data))!=-1){
            out.write(data,0,size);
            out.flush();
        }*/

        /*String message;
        while(!(message=in.readLine()).equals((""))){
            System.out.println(message);
        }*/

        //String body="안녕하세요";
        out.write("HTTP/1.1 200 ok\r\n".getBytes());
        out.write("Content-Type: text/html; charset=utf-8\r\n".getBytes());
        
     
        //out.write("Content-Type: image/jpeg\r\n".getBytes());
        //String contentLength = "Content-Length: "+body.length()+"\r\n\r\n";
        //out.write(contentLength.getBytes());
        //out.write(body.getBytes("utf-8"));
        socket.close();
    }
    
}
