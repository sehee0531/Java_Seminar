import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import com.google.gson.*;

public class Client implements MultipartListener {
    public static void main(String[] args)throws Exception{
        
        Parts part = new Parts();
        part=new Parts();
        part.carNumber="2019다2222";
        part.centerID=29;
        part.content="정세희";

        Gson gson = new Gson();
        String jsonString = gson.toJson(part);

        Client client= new Client();
        MultipartMaker maker = new MultipartMaker("http://203.241.251.177/car/PutRepair",client);
        maker.addFormData("data",jsonString);
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