import java.util.*;
import java.net.*;
import java.io.*;
import com.google.gson.Gson;

public class Client {
    public static void main(String[] args) throws Exception {
         Socket socket = new Socket("localhost",1000);

         Person person = new Person();
         person.name="kim";
         person.age=20;
         person.address="gimhae";

         ArrayList<Student> students = new ArrayList<>();
         Student  student = new Student();
         student.name="inje";
         student.graduated=2022;
         students.add(student);

         Student student2 = new Student();
         student2.name="computer";
         student2.graduated=2023;
         students.add(student2);

         person.schools=students;

         Gson gson = new Gson();
         String jsonString = gson.toJson(person);

        System.out.println(jsonString);

        OutputStream out = socket.getOutputStream();

        out.write(jsonString.getBytes("utf-8"));
        out.flush();
        socket.close();
    }
}