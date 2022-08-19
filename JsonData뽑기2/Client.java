import java.io.*;
import java.net.URL;

import com.google.gson.*;

import java.util.*;

public class Client{
    public static void main(String[]args)throws Exception{
        URL url = new URL("http://192.168.0.14/car/GetCarSale?start=300&end=310");
        InputStream in = url.openStream();

        byte[] data= new byte[1024];
        int size;
        String jsonString ="";
        while((size=in.read(data))!=-1){
            jsonString=jsonString+(new String(data,0,size,"utf-8"));
        }
        Gson gson = new Gson();
        Cars allcar= gson.fromJson(jsonString, Cars.class);
        HashSet<String> hashSet = new HashSet<>();
        for(Part part : allcar.cars){
            hashSet.add(part.makeYear);
        }
        System.out.println(hashSet);



    }
}