import java.net.*;
import java.io.*;
import com.google.gson.*;;

public class URLClient {
    public static void main(String[]args)throws Exception{
        URL url = new URL("http://localhost");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));

        Gson gson = new Gson();
    
        AllPerson Desperson = gson.fromJson(reader,AllPerson.class);
        for(Persons person: Desperson.persons) {
			System.out.println(person.name);
            System.out.println(person.address);
            System.out.println(person.age);
		}
    }
    
}
