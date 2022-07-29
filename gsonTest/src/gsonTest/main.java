package gsonTest;
import java.util.*;
import java.io.*;
import com.google.gson.*;

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	
		BufferedReader test1 = new BufferedReader(new InputStreamReader(new FileInputStream("gson.txt")));
		String str = "";
        ArrayList<String> lis = new ArrayList<>(); 
        while((str=test1.readLine())!=null){
            lis.add(str);
        }
        test1.close();
        ArrayList<Persons> array =new ArrayList<Persons>();
        Persons person = new Persons();
        
        for(int i=0;i<lis.size();i=i+3) {
        	person = new Persons();
        	person.setName(lis.get(i));
        	person.setAddress(lis.get(i+1));
        	person.setAge(lis.get(i+2));
        	array.add(person);
        			
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        PrintStream pw = new PrintStream("gson2.txt");
        ArrayList<String> result = new ArrayList<String>();
        for(int i=0;i<array.size();i++) {
        	result.add(gson.toJson(array.get(i)));
        }
        pw.print(result);
        pw.flush();
        pw.close();
            
	}
}



