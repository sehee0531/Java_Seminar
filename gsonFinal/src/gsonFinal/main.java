package gsonFinal;
import java.io.*;
import java.util.*;
import com.google.gson.*;
public class main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("gson.txt")));
		String name="";
		ArrayList<Persons> persons = new ArrayList<Persons>();
		while((name=br.readLine())!=null) {
			String address=br.readLine();
			int age=Integer.parseInt(br.readLine());
			Persons person =new Persons();
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
		System.out.println(json);
		
		PrintStream ps = new PrintStream("Test.txt");
		ps.print(json);
		ps.flush();
		ps.close();
		
		br = new BufferedReader(new InputStreamReader(new FileInputStream("Test.txt")));
		PrintStream fnal = new PrintStream("Final.txt");
		AllPerson Desperson = gson.fromJson(br.readLine(),AllPerson.class);
		for(Persons person: Desperson.persons) {
			fnal.println(person.name);
			fnal.println(person.address);
			fnal.println(person.age);
			System.out.println(person);
		}
		
		fnal.flush();
		fnal.close();

	}

}
