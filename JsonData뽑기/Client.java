import java.net.*;
import java.util.*;
import java.io.*;
import com.google.gson.*;;

public class Client  {
    public static void main(String[]args)throws Exception{
        URL url = new URL("http://192.168.0.14/car/GetCarList");
        InputStream in=url.openStream();
        byte[] data= new byte[1024];
        int size;
        String jsonString ="";
        while((size=in.read(data))!=-1){
            jsonString=jsonString+(new String(data,0,size,"utf-8"));
        }
        Gson gson = new Gson();
        Kinds allkinds = gson.fromJson(jsonString, Kinds.class);
        int hyundae=0;
        int genesis=0;
        int america=0;
        int korea=0;

        for(Part part : allkinds.kinds){
            if(part.maker.equals("현대자동차")){
                hyundae++;
            }
            if(part.maker.equals("제네시스")){
                genesis++;
            }
            if(part.area.equals("미국")){
                america++;
            }
            if(part.area.equals("한국")){
                korea++;
            }
        
        }  
        System.out.println("현대자동차 : " + hyundae);
        System.out.println("제네시스 : " + genesis);
        System.out.println("미국 : " + america);
        System.out.println("한국 : " + korea);
    }
}
