import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import com.google.gson.*;

public class Client implements MultipartListener{
    public static void main(String[] args)throws Exception{
        Person person = new Person();
        ArrayList<Person> list = new ArrayList<Person>();
        person.name="정세희";
        person.age=23;
        person.school="석동중";
        list.add(person);

        person=new Person();
        person.name="정진희";
        person.age=29;
        person.school="진여고";
        list.add(person);

        AllPerson allperson = new AllPerson();
        allperson.persons=list;

        Gson gson = new Gson();
        String jsonString = gson.toJson(allperson);

        Client client= new Client();
        MultipartMaker maker = new MultipartMaker("http://localhost/third/Test4",client);
        maker.addFormData("data",jsonString);
        maker.addBinaryData("file1",new FileInputStream("1.jpg"));
        maker.start();
    }

    public void onResult(InputStream in ) throws Exception{
        byte[] data = new byte[1024];
        int size;

        while((size=in.read(data))!=-1){
            System.out.println(new String(data,0,size,"utf-8"));
        }
    }
}